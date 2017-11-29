package com.infotech.client;

import org.hibernate.Session;

import com.infotech.entities.Department;
import com.infotech.entities.Employee;
import com.infotech.util.HibernateUtil;

public class NoFetchingClientTest {

	public static void main(String[] args) {

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// No fetching example
			String username = "barryj";
			String password = "barry@123";
			Employee employee = session.createQuery(
				    "select e " +
				    "from Employee e " +
				    "where " +
				    "e.username = :username and " +
				    "e.password = :password",
				 Employee.class)
				.setParameter( "username", username)
				.setParameter( "password", password)
				.getSingleResult();
			
			if (employee != null) {
				System.out.println("Employee details::::::");
				System.out.println(employee.getId() + "\t" + employee.getEmployeeName() + "\t" + employee.getUsername()
						+ "\t" + employee.getPassword() + "\t" + employee.getAccessLevel());
				System.out.println("Employee's department details:");
				Department department = employee.getDepartment();
				if (department != null)
					System.out.println(department.getId() + "\t" + department.getDeptName());
				else
					System.out.println("Department details not found");

			} else {
				System.out.println("Employee not found with  provided credential");
			}

			//No fetching (scalar) example
			Integer accessLevel = session.createQuery(
				    "select e.accessLevel " +
				    "from Employee e " +
				    "where " +
				    " e.username = :username and " +
				    "e.password = :password",
				    Integer.class)
				.setParameter( "username", username)
				.setParameter( "password", password)
				.getSingleResult();
			System.out.println("Access Level:"+accessLevel);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
