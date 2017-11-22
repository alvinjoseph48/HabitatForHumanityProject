package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import model.Address;
import model.Customer;
import model.Employee;
import model.Manager;
import model.Person;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

public class CreateUserController implements Initializable {
	@FXML
	private BorderPane borderPane;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField phoneNumberField;
    @FXML
    private ChoiceBox<String> userTypeBox;	
    @FXML
    private TextField streetNumberField;
    @FXML
    private TextField streetField;
    @FXML
    private TextField cityField;
    @FXML
    private ChoiceBox<String> stateBox;
    @FXML
    private TextField zipCodeField;
	@FXML
    private TextField createUserNameField;
    @FXML
    private PasswordField createPasswordField;
    @FXML
    private PasswordField createPasswordField2;
    @FXML
    private Button  createUserBtn;
    
    public void createUserBtnClicked(ActionEvent event){
    	if(!createPasswordField.getText().equals(createPasswordField2.getText())) {
    		passwordNoMatchAlert();
    		return;
    	}
    	if(firstNameField.getText().isEmpty() || lastNameField.getText().isEmpty()  ||
    			emailField.getText().isEmpty() || phoneNumberField.getText().isEmpty() || 
    			streetNumberField.getText().isEmpty() || streetField.getText().isEmpty() ||
    			cityField.getText().isEmpty() || zipCodeField.getText().isEmpty() ||
    			createPasswordField2.getText().isEmpty() ||
    			createUserNameField.getText().isEmpty() ||
    			stateBox.getSelectionModel().isEmpty() || userTypeBox.getSelectionModel().isEmpty()
    			){
    		
    		fieldEmptyAlert();
    		return;
    	}
    	Person p = null;
    	Address a = new Address(streetNumberField.getText(),streetField.getText(),cityField.getText(),stateBox.getSelectionModel().getSelectedItem(),zipCodeField.getText()); 
    	String firstName = firstNameField.getText();
    	String lastName = lastNameField.getText();
    	String email = emailField.getText();
    	String phoneNum = phoneNumberField.getText();
    	String userName = createUserNameField.getText();
    	String password = createPasswordField.getText();
    	String userType = userTypeBox.getSelectionModel().getSelectedItem();
    	if(userType.equals("Manager")) {
    	 p = new Manager(firstName,lastName, email , phoneNum,a,userName,password );
    	}
    	if(userType.equals("Customer")) {
       	 p = new Customer(firstName,lastName, email , phoneNum,a,userName,password );
       	}
    	if(userType.equals("Employee")) {
       	 p = new Employee(firstName,lastName, email , phoneNum,a,userName,password );
       	}
    	try {
			DbConnect.insertPerson(p);
		} catch(MySQLIntegrityConstraintViolationException e) {
			userNameNotUniqueAlert();
			return;
		}
    		catch (SQLException e) {
			e.printStackTrace();
			return;
		}
    	accountCreatedAlert();
    	Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/view/loginPane.fxml"));
			Node node = (Node) event.getSource();
			BorderPane pane = (BorderPane) node.getParent().getParent().getParent();
			pane.setCenter(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
    private void userNameNotUniqueAlert() {
  		Alert alert = new Alert(AlertType.ERROR);
  		alert.setHeaderText("UserName is already Taken");
  		alert.setContentText("Please Enter another userName");
  		alert.showAndWait();
  	}
 
    private void fieldEmptyAlert() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setHeaderText("Empty Fields");
		alert.setContentText("Please Enter all fields to create user");
		alert.showAndWait();
	}
    private void accountCreatedAlert() {
  		Alert alert = new Alert(AlertType.CONFIRMATION);
  		alert.setHeaderText("Welcome");
  		alert.setContentText("Congrats your account was created");
  		alert.showAndWait();
  	}
    private void passwordNoMatchAlert() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setHeaderText("Password's Do Not Match");
		alert.setContentText("Please enter password again");
		alert.showAndWait();
	}
	private ObservableList<String> states = FXCollections.observableArrayList("Alabama", "Alaska", "Arkansas", "Arizona",
			"California", "Colorado", "Connecticut", "Delaware", "Florida", "Georgia", "Hawaii", "Idaho",
			"Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts",
			"Michigan", "Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire",
			"New Jersey", "New Mexico", "New York", "North Carolina,", "North Dakota", "Ohio", "Oklahoma", "Oregon",
			"Pennsylvania", "Rhode Island", "South Carolina", "South Dakota", "Tennessee", "Texas", "Utah",
			"Vermont", "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming");
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		userTypeBox.getItems().removeAll(userTypeBox.getItems());
		userTypeBox.getItems().addAll("Customer", "Employee", "Manager");
		stateBox.getItems().removeAll(stateBox.getItems());
		stateBox.getItems().addAll(states);
		
	}



}
