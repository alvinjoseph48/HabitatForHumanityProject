package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import model.Address;
import model.Person;

public class UpdateController implements Initializable {

    @FXML
    private TextField customerFirstNameUpdate;

    @FXML
    private TextField customerLastNameUpdate;

    @FXML
    private TextField customerEmailUpdate;

    @FXML
    private PasswordField customerPasswordUpdate;

    @FXML
    private TextField customerUsernameUpdate;

    @FXML
    private PasswordField customerPassword2Update;

    @FXML
    private TextField customerPhoneUpdate;

    @FXML
    private TextField customerStreetNumberUpdate;

    @FXML
    private TextField customerStreetUpdate;

    @FXML
    private TextField customerCityUpdate;

    @FXML
    private TextField customerZipCodeUpdate;

    @FXML
    private ChoiceBox<String> customerStateUpdate;
    
    @FXML
    private Button customerUpdateBtn;
    
    public void customerUpdateClicked(ActionEvent event) {

    	
    	if(!customerPasswordUpdate.getText().equals(customerPassword2Update.getText())) {
    		passwordNoMatchAlert();
    		return;
    	}
    	if(customerFirstNameUpdate.getText().isEmpty() || customerLastNameUpdate.getText().isEmpty()  ||
    			customerEmailUpdate.getText().isEmpty() || customerPhoneUpdate.getText().isEmpty() || 
    			customerStreetNumberUpdate.getText().isEmpty() || customerStreetUpdate.getText().isEmpty() ||
    			customerCityUpdate.getText().isEmpty() || customerZipCodeUpdate.getText().isEmpty() ||
    			customerPasswordUpdate.getText().isEmpty() ||
    			customerUsernameUpdate.getText().isEmpty() ||customerStateUpdate.getSelectionModel().isEmpty() 
    			){
    		
    		fieldEmptyAlert();
    		return;
    	}
    	Address a = new Address(customerStreetNumberUpdate.getText(),customerStreetUpdate.getText(),customerCityUpdate.getText(),customerStateUpdate.getSelectionModel().getSelectedItem(),customerZipCodeUpdate.getText()); 
    	String firstName = customerFirstNameUpdate.getText();
    	String lastName = customerLastNameUpdate.getText();
    	String email = customerEmailUpdate.getText();
    	String phoneNum = customerPhoneUpdate.getText();
    	String userName = customerUsernameUpdate.getText();
    	String password = customerPasswordUpdate.getText();
    	Person newPerson = new Person(firstName,lastName,email,phoneNum,a,userName,password);
    	if(DbConnect.updatePerson(newPerson)) {
    		updateCustomerAlert();
    	}else {
    		noUpdateCustomerAlert();
    	}
    }
    public void setFields() {
    	Person p = DbConnect.getPerson();
    	customerFirstNameUpdate.setText(p.getFirstName());
    	customerLastNameUpdate.setText(p.getLastName());
    	customerEmailUpdate.setText(p.getEmail());
    	customerPhoneUpdate.setText(p.getPhoneNumber());
    	customerStreetNumberUpdate.setText(p.getAddress().getStreetNum());
    	customerStreetUpdate.setText(p.getAddress().getStreet());
    	customerCityUpdate.setText(p.getAddress().getCity());
    	customerZipCodeUpdate.setText(p.getAddress().getZip());
    	customerStateUpdate.getSelectionModel().select(p.getAddress().getState());
    	customerPasswordUpdate.setText(p.getPassword());
    	customerPassword2Update.setText(p.getPassword());
    	customerUsernameUpdate.setText(p.getUsername());
    }
    private void updateCustomerAlert() {
    	Alert alert = new Alert(AlertType.INFORMATION);
  		alert.setHeaderText("Succesfully Updated");
  		alert.setContentText("Thank you!");
  		alert.showAndWait();
	}
    private void noUpdateCustomerAlert() {
    	Alert alert = new Alert(AlertType.INFORMATION);
  		alert.setHeaderText("Sorry Not succesfull");
  		alert.setContentText("Please try again later");
  		alert.showAndWait();
	}
	private void fieldEmptyAlert() {
  		Alert alert = new Alert(AlertType.ERROR);
  		alert.setHeaderText("Empty Fields");
  		alert.setContentText("Please Enter all fields to create user");
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
		customerStateUpdate.getItems().removeAll(customerStateUpdate.getItems());
		customerStateUpdate.getItems().addAll(states);
		setFields();
	}
}
