package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class CreateUserController {

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
    private TextField createuserNameField;
    @FXML
    private PasswordField createPasswordField;
    @FXML
    private PasswordField createPasswordField2;
    public void createUser(ActionEvent event){
    	if(createPasswordField.getText().equals(createPasswordField2.getText())) {
    		passwordNoMatchAlert();
    		return;
    	}
    	if(firstNameField.getText().isEmpty() || lastNameField.getText().isEmpty()  ||
    			emailField.getText().isEmpty() || phoneNumberField.getText().isEmpty() || 
    			streetNumberField.getText().isEmpty() || streetField.getText().isEmpty() ||
    			cityField.getText().isEmpty() || zipCodeField.getText().isEmpty() ||
    			createuserNameField.getText().isEmpty() || createPasswordField.getText().isEmpty() ||
    			createPasswordField2.getText().isEmpty() || createPasswordField.getText().isEmpty() ||
    			stateBox.getSelectionModel().isEmpty() || userTypeBox.getSelectionModel().isEmpty()
    			){
    		
    		fieldEmptyAlert();
    		return;
    	}
    	
    	
		
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
}
