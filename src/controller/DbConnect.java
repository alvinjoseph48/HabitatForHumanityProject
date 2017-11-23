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

	private static Person person = new Person();

	private static Connection connect() throws SQLException, ClassNotFoundException {
		// try {
		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/habitatforhumanity", "root", "");

	}

	public static boolean isLoggedIn() {
		String s = person.getUsername();
		if (s == null) {
			return false;
		}
		return true;
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

	public static Person deletePerson() throws SQLException {
		Connection con = null;
		try {
			con = connect();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String query = "delete from person where userName = ?";

		PreparedStatement preparedStmt = con.prepareStatement(query);
		preparedStmt.setString(1, person.getUsername());
		if (preparedStmt.execute()) {
			return person;
		}

		return null;
	}
	public static Person getPerson() {
		return person;
	}
	public static boolean updatePerson(Person newPerson) {
		Connection con = null;
		PreparedStatement preparedStmt;
		String query;
		try {
			con = connect();
			query = "UPDATE person set firstName=?,lastName=?,email=?,phoneNumber=?,userName=?,password=?,"
					+ "streetNumber=?,street=?,city=?,state=?,zipCode=? where username=?";
			preparedStmt= con.prepareStatement(query);
			preparedStmt.setString(1, newPerson.getFirstName());
			preparedStmt.setString(2, newPerson.getLastName());
			preparedStmt.setString(3, newPerson.getEmail());
			preparedStmt.setString(4, newPerson.getPhoneNumber());
			preparedStmt.setString(5, newPerson.getUsername());
			preparedStmt.setString(6, newPerson.getPassword());
			preparedStmt.setString(7, newPerson.getAddress().getStreetNum());
			preparedStmt.setString(8, newPerson.getAddress().getStreet());
			preparedStmt.setString(9, newPerson.getAddress().getCity());
			preparedStmt.setString(10, newPerson.getAddress().getState());
			preparedStmt.setString(11, newPerson.getAddress().getZip());
			preparedStmt.setString(12, person.getUsername() );
			preparedStmt.executeUpdate();
		    con.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return false;
		} 
		return true;

	}

	public static Person getPerson(String givenuserName) throws SQLException {
		Customer c = null;
		Employee em = null;
		Manager m = null;
		Connection con = null;
		try {
			con = connect();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String query = "SELECT * FROM person WHERE `userName`= ?";
		PreparedStatement preparedStmt = con.prepareStatement(query);
		preparedStmt.setString(1, givenuserName);
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
				c = new Customer(firstName, lastName, email, phoneNumber, a, userName, password);
				person = c;
				con.close();
				return c;
			}
			if (userType.equals("manager")) {
				m = new Manager(firstName, lastName, email, phoneNumber, a, userName, password);
				person = m;
				con.close();
				return m;
			}
			if (userType.equals("employee")) {
				em = new Employee(firstName, lastName, email, phoneNumber, a, userName, password);
				person = em;
				con.close();
				return em;
			}

		}
		con.close();
		return null;
	}
}
