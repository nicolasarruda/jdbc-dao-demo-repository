package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program2 {

	public static void main(String[] args) {

		DepartmentDao departmentDao = DaoFactory.createDeparmentDao();

		System.out.println("==== Testing DepartmentDao ====");

		System.out.println("\n==== Test 1: department findById ====");

		int id = 3;
		Department dep = departmentDao.findById(id);
		System.out.println(dep);
		
		System.out.println("\n==== Test 2: department findAll ====");

		List<Department> list = departmentDao.findAll();
		for (Department obj : list) {
			System.out.println(obj);
		}
		
		System.out.println("\n==== Test 3: department insert ====");

		Department newDep = new Department(null, "Human Resources");
		departmentDao.insert(newDep);
		
		System.out.println("\n==== Test 4: department update ====");

		newDep = departmentDao.findById(5);
		newDep.setName("RH");
		departmentDao.update(newDep);
		
		System.out.println("Updated! Department id: " + newDep.getId());
		
		System.out.println("\n==== Test 5: department update ====");

		departmentDao.deleteById(5);
		
		System.out.println("Delete completed!");

	}
}
