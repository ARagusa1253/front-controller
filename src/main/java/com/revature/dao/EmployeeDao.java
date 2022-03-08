package com.revature.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Employee;
import com.revature.util.HibernateUtil;

public class EmployeeDao {
	
	// insert the employee object by calling the save() method from Hibernate, return the PK generated
	public int insert(Employee e) {
		
		// grab the session object
		Session ses = HibernateUtil.getSession();
		
		// begin a transaction
		Transaction tx = ses.beginTransaction(); // import transaction from Hibernate
		
		// capture the primary key returned
		int pk = (int) ses.save(e);
		
		// commit the transaction
		tx.commit();
		
		// return the primary key
		return pk;
	}
	
	// findAll() method that returns a list of employees
	public List<Employee> findAll() {
		
		//grab the session
		Session ses = HibernateUtil.getSession();
		
		// make an HQL statement
		List<Employee> emps = ses.createQuery("from Employee", Employee.class).list();
		
		// return the list of employees
		return emps;
	}
	
	public boolean delete(int id) {
		return false;
	}
	
	public boolean update(Employee e) {
		return false;
	}
	
}
