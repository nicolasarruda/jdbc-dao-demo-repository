package model.dao;

import model.dao.impl.DepartmentDaoJDBC;
import model.dao.impl.SellerDaoJDBC;

public class DaoFactory  {

	// Both methods statics don't expose the implementation
	
	public static SellerDao createSellerDao() {
		return new SellerDaoJDBC();
	}
	
	public static DepartmentDao createDeparmentDao() { // Interface declare
		return new DepartmentDaoJDBC(); // implementation instance
	}
	
	

}
