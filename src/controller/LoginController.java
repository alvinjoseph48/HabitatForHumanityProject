package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import model.Person;
import view.View;

public class LoginController implements Initializable {
	// private View view;
	@FXML
	private PasswordField passwordField;
	@FXML
	private TextField usernameField;
	@FXML
	private Button loginBtn;
	@FXML
	private Button createUserBtn;

	public void createUserBtnClicked(ActionEvent event) {
		Parent root;
		try {

			root = FXMLLoader.load(getClass().getResource("/view/CreateUserPane.fxml"));
			Node node = (Node) event.getSource();
			BorderPane pane = (BorderPane) node.getParent().getParent();
			pane.setCenter(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void loginBtnClicked(ActionEvent event) {
		try {
			Person p = DbConnect.getPerson(usernameField.getText());
			if(p == null) {
				createAccountAlert();
			}
			if(!p.getPassword().equals(passwordField.getText())) {
				passwordErrorAlert();
				return;
			}
			
		} catch (SQLException e1) {
			
			e1.printStackTrace();
		}
		
		//set after pane after comfirmed 
		Parent root;
		try {

			root = FXMLLoader.load(getClass().getResource("/view/CreateUserPane.fxml"));
			Node node = (Node) event.getSource();
			BorderPane pane = (BorderPane) node.getParent().getParent();
			pane.setCenter(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	 private void passwordErrorAlert() {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Password is Wrong ");
			alert.setContentText("Please enter password again");
			alert.showAndWait();
		}
	 private void createAccountAlert() {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Username does not exist!");
			alert.setContentText("Please Create a Account");
			alert.showAndWait();
		}
	 

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

}
