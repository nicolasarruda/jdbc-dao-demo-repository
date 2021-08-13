package application;


import java.util.Date;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		
		// It is a form of dependency injection without set out the implementation
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		System.out.println("=== Test 1: seller findById ===");
		Seller seller = sellerDao.findById(3);
		System.out.println(seller);
		
		System.out.println("\n=== Test 2: seller findByDepartment ===");
		Department department = new Department(2, null);
		List<Seller> list = sellerDao.findByDepartment(department);
		for (Seller obj : list) { // For every Seller obj in my list
			System.out.println(obj);
		}
		System.out.println("\n=== Test 3: seller findAll ===");
		list = sellerDao.findAll();
		for (Seller obj : list) {
			System.out.println(obj);
		}
		
		
		System.out.println("\n=== Test 4: seller insert ===");
		Seller newSeller = new Seller(null, "José Guanabara", "guanabara@mail.com",
				new Date(), 5000.0, new Department(3, null));
		sellerDao.insert(newSeller);
		System.out.println("Inserted! New id = " + newSeller.getId());
		
		System.out.println("\n=== Test 5: seller update ===");
		seller = sellerDao.findById(1);
		seller.setName("Martha Gray");
		sellerDao.update(seller);
		
		System.out.println("Updated! Seller id = " + seller.getId());
		
		System.out.println("\n=== Test 6: seller delete ===");
		int id = 7;
		sellerDao.deleteById(id);
		System.out.println("Delete completed!");
		
		
				
				
			
	}

}
