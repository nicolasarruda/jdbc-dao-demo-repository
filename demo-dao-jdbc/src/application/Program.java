package application;

import java.util.Date;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		Department obj = new Department(1, "Books");
		System.out.println(obj);

		Seller seller = new Seller(21, "Bob", "bob@mail.com", new Date(), 3000.0, obj);
		
		// It is a form of dependency injection without set out the implementation
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		
		System.out.println(seller);
	}

}
