package model;

public class Employee extends Person {
	private String id;

	public Employee(String firstName, String lastName, String email, String phoneNumber, Address address,
			String username, String password, String id) {
		super(firstName, lastName, email, phoneNumber, address, username, password);
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
