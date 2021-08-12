package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao{

	private Connection conn;
	
	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	
	@Override
	public void insert(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seller findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
		st = conn.prepareStatement(
				"SELECT seller.*, department.Name as DepName "
			  + "FROM seller INNER JOIN department "
			  + "ON seller.DepartmentId = Department.Id "
			  + "WHERE seller.Id = ?");
		
		st.setInt(1, id);
		rs = st.executeQuery();
		
		if(rs.next()) {
			Department dep = instantiateDepartment(rs);
			Seller obj = instantiateSeller(rs, dep);
			
			return obj;
		}
		return null;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	// The exception is treated on the findById
	private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException {
		Seller seller = new Seller();
		seller.setId(rs.getInt("Id"));
		seller.setName(rs.getString("Name"));
		seller.setEmail(rs.getString("Email"));
		seller.setBaseSalary(rs.getDouble("baseSalary"));
		seller.setBirthDate(rs.getDate("BirthDate"));
		seller.setDepartment(dep);
		
		return seller;
	}

	// The exception is treated on the findById
	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department();
		dep.setId(rs.getInt("DepartmentId"));
		dep.setName(rs.getString("DepName"));
		return dep;
	}


	@Override
	public List<Seller> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
		st = conn.prepareStatement(
				"SELECT seller.*, department.Name as DepName "
			  + "FROM seller INNER JOIN department "
			  + "ON seller.DepartmentId = department.Id "
			  + "ORDER BY Id");

		rs = st.executeQuery();
		
		List<Seller> list = new ArrayList<>();
		
		// Created map empty. Later, this map will return every instantiate department
		// This solution is more interesting to a future application - using Map
		// This logic with Map works on method findAll() too
		// With Map, I don't add a new department
		Map<Integer, Department> map = new HashMap<>();
		
		while(rs.next()) {
			
			Department dep = map.get(rs.getInt("DepartmentId"));
			
			if (dep == null) { // That means the "dep" don't exist yet;
				dep = instantiateDepartment(rs); // so here in this line the "dep" will be instantiate
				map.put(rs.getInt("DepartmentId"), dep);								 // I need to salve in "map" to verify if dep exists one more time;
			}
			
			Seller obj = instantiateSeller(rs, dep);
			list.add(obj);
		}
		return list;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}


	@Override
	public List<Seller> findByDepartment(Department department) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
		st = conn.prepareStatement(
				"SELECT seller.*, department.Name as DepName "
			  + "FROM seller INNER JOIN department "
			  + "ON seller.DepartmentId = department.Id "
			  + "WHERE DepartmentId = ? "
			  + "ORDER BY Name");

		st.setInt(1, department.getId());
		rs = st.executeQuery();
		
		List<Seller> list = new ArrayList<>();
		
		// Created map empty. Later, this map will return every instantiate department
		// This solution is more interesting to a future application - using Map
		Map<Integer, Department> map = new HashMap<>();
		
		while(rs.next()) {
			
			Department dep = map.get(rs.getInt("DepartmentId"));
			
			if (dep == null) { // That means the "dep" don't exist yet;
				dep = instantiateDepartment(rs); // so here in this line the "dep" will be instantiate
				map.put(rs.getInt("DepartmentId"), dep);								 // I need to salve in "map" to verify if dep exists one more time;
			}
			
			Seller obj = instantiateSeller(rs, dep);
			list.add(obj);
		}
		return list;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	
}
