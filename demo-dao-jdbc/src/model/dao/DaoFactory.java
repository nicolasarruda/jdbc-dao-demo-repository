package model.dao;

import db.DB;
import model.dao.impl.DepartmentDaoJDBC;
import model.dao.impl.SellerDaoJDBC;

public class DaoFactory  {

	// Both methods statics don't expose the implementation
	
	public static SellerDao createSellerDao() {
		return new SellerDaoJDBC(DB.getConnection());
	}
	
	public static DepartmentDao createDeparmentDao() { // Interface declare
		return new DepartmentDaoJDBC(); // implementation instance
	}
	
	

}
