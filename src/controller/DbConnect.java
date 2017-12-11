package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Address;
import model.Customer;
import model.Employee;
import model.Item;
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
	public static boolean updateItem(Item i) {
		Connection con = null;
		PreparedStatement preparedStmt;
		String query;
		try {
			con = connect();
			query = "UPDATE items set modelNumber=?,brand=?,color=?,price=?,itemDemensions=?,"
					+ "imageUrl=?,category=?,quanity=?where productName=?";
			preparedStmt= con.prepareStatement(query);
	
			preparedStmt.setString(1, i.getModelNum());
			preparedStmt.setString(2, i.getBrand());
			preparedStmt.setString(3, i.getColor());
			preparedStmt.setString(4, i.getPrice());
			preparedStmt.setString(5, i.getItemDemensions());
			preparedStmt.setString(6, i.getImageUrl());
			preparedStmt.setString(7, i.getCategory());
			preparedStmt.setString(8, i.getQty());
			preparedStmt.setString(9, i.getProductName());
			preparedStmt.executeUpdate();
		    con.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return false;
		} 
		return true;
	}
	public static void insertItem(Item p) throws SQLException {
		try {
			Connection con = connect();
			String query = " insert into items (productName, modelNumber, brand, color, price, itemDemensions, imageUrl, category, quanity)" 
			+ " values (?, ?, ?, ?, ?, ?, ?, ?,?)";

			// create the mysql insert preparedstatement
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setString(1, p.getProductName());
			preparedStmt.setString(2, p.getModelNum());
			preparedStmt.setString(3, p.getBrand());
			preparedStmt.setString(4, p.getColor());
			preparedStmt.setString(5, p.getPrice());
			preparedStmt.setString(6, p.getItemDemensions());
			preparedStmt.setString(7, p.getImageUrl());
			preparedStmt.setString(8, p.getCategory());
			preparedStmt.setString(9, p.getQty());
			// execute the preparedstatement
			preparedStmt.execute();

			// close connectione
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static ArrayList<Item> getItem(String givenProductName) throws SQLException {
		
		Connection con = null;
		try {
			con = connect();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String query = "SELECT * FROM items WHERE productName LIKE ? ";
		//String query = "SELECT productName FROM items WHERE CONTAINS (productName, ?) ";
		PreparedStatement preparedStmt = con.prepareStatement(query);
		System.out.println("name:"+ givenProductName);
		preparedStmt.setString(1, "%"+givenProductName+"%");
		ResultSet rs = preparedStmt.executeQuery();
		ArrayList<Item> resultList = new ArrayList<Item>();
		while(rs.next()) {
			String productName = rs.getString("productName");
			String modelNumber = rs.getString("modelNumber");
			String brand = rs.getString("brand");
			String color = rs.getString("color");
			String price = rs.getString("price");
			String itemDemensions = rs.getString("itemDemensions");
			String imageUrl = rs.getString("imageUrl");
			String category = rs.getString("category");
			String quanity = rs.getString("quanity");
			Item item = new Item(productName,modelNumber,brand,  color,  price, itemDemensions, imageUrl, category,quanity);
			resultList.add(item);
			System.out.println(item.getProductName());
		}
		con.close();
		return resultList;
		
		
	
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
		person = newPerson;
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
