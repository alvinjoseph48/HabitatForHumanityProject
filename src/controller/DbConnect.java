package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Address;
import model.Customer;
import model.Employee;
import model.Manager;
import model.Person;

public class DbConnect {
	// private Connection con;
	// private Statement st;
	// private ResultSet rs;
	private static Person person;

	private static Connection connect() throws SQLException, ClassNotFoundException {
		// try {
		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/habitatforhumanity", "root", "");
		// st = con.createStatement();
		// } catch (Exception ex) {
		// System.out.println("Error: " + ex);
		// }
	}

	public static void insertPerson(Person p) throws SQLException {
		try {
			Connection con = connect();
			String query = " insert into person (firstName, lastName, email, phoneNumber, userName, password, streetNumber,"
					+ "street, city, state, zipCode, userType)" + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";

			// create the mysql insert preparedstatement
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setString(1, p.getFirstName());
			preparedStmt.setString(2, p.getLastName());
			preparedStmt.setString(3, p.getEmail());
			preparedStmt.setString(4, p.getPhoneNumber());
			preparedStmt.setString(5, p.getUsername());
			preparedStmt.setString(6, p.getPassword());
			preparedStmt.setString(7, p.getAddress().getStreetNum());
			preparedStmt.setString(8, p.getAddress().getStreet());
			preparedStmt.setString(9, p.getAddress().getCity());
			preparedStmt.setString(10, p.getAddress().getState());
			preparedStmt.setString(11, p.getAddress().getZip());
			if (p instanceof Employee) {
				preparedStmt.setString(12, "employee");
			}
			if (p instanceof Manager) {
				preparedStmt.setString(12, "manager");
			}
			if (p instanceof Customer) {
				preparedStmt.setString(12, "customer");
			}

			// execute the preparedstatement
			preparedStmt.execute();

			// close connectione
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Person getPerson(String givenuserName) throws SQLException {
		// set person object to preson retrieved
		Person p = null;
		Connection con = null;
		try {
			con = connect();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String query = "SELECT * FROM person WHERE `userName`= ?" ;
		// Statement st = con.createStatement();
		
		PreparedStatement preparedStmt = con.prepareStatement(query);
		preparedStmt.setString(0, givenuserName);
		ResultSet rs = preparedStmt.executeQuery();
		if (rs.first()) {
			String firstName = rs.getString("firstName");
			String lastName = rs.getString("lastName");
			String email = rs.getString("email");
			String phoneNumber = rs.getString("phoneNumber");
			String userName = rs.getString("userName");
			String password = rs.getString("password");
			String streetNumber = rs.getString("streetNumber");
			String street = rs.getString("street");
			String city = rs.getString("city");
			String state = rs.getString("state");
			String zipCode = rs.getString("zipCode");
			String userType = rs.getString("userType");
			Address a = new Address(streetNumber, street, city, state, zipCode);
			if (userType.equals("customer")) {
				p = (Customer) new Person(firstName, lastName, email, phoneNumber, a, userName, password);
			}
			if (userType.equals("manager")) {
				p = (Manager) new Person(firstName, lastName, email, phoneNumber, a, userName, password);
			}
			if (userType.equals("employee")) {
				p = (Employee) new Person(firstName, lastName, email, phoneNumber, a, userName, password);
			}
			con.close();
			return p;
		}
		con.close();
		return null;
	}
}
