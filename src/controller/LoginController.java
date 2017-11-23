package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.Customer;
import model.Employee;
import model.Manager;
import model.Person;


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
	@FXML
	public void createUserBtnClicked(ActionEvent event) {
		
		try {
			Stage stage = (Stage) createUserBtn.getScene().getWindow();
			stage.close();
			Stage stage2 = new Stage();
			//stage2.initModality(Modality.WINDOW_MODAL);
			//stage2.initOwner(primaryStage);
			Parent root = FXMLLoader.load(getClass().getResource("/view/createUserPane.fxml"));
			Scene scene = new Scene (root);
			stage2.setScene(scene);
			stage2.show();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}
	@FXML
	public void loginBtnClicked(ActionEvent event) {
		Person p = null;
		try {
			p = DbConnect.getPerson(usernameField.getText());
			if (p == null) {
				createAccountAlert();
				return;
			}
			if (passwordField.getText().isEmpty()) {
				passwordErrorAlert();
				return;
			}
			if (!p.getPassword().equals(passwordField.getText())) {
				passwordErrorAlert();
				return;
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		// set after pane after comfirmed
		if (p instanceof Customer) {
			try {
				Stage stage = (Stage) createUserBtn.getScene().getWindow();
				stage.close();
				Stage stage2 = new Stage();
				Parent root = FXMLLoader.load(getClass().getResource("/view/CustomerPane.fxml"));
				Scene scene = new Scene(root);
				stage2.setScene(scene);
				stage2.show();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		if (p instanceof Employee) {
			try {
				Stage stage = (Stage) createUserBtn.getScene().getWindow();
				stage.close();
				Stage stage2 = new Stage();
				Parent root = FXMLLoader.load(getClass().getResource("/view/EmployeePane.fxml"));
				Scene scene = new Scene(root);
				stage2.setScene(scene);
				stage2.show();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		if (p instanceof Manager) {
			try {
				Stage stage = (Stage) createUserBtn.getScene().getWindow();
				stage.close();
				Stage stage2 = new Stage();
				Parent root = FXMLLoader.load(getClass().getResource("/view/CustomerPane.fxml"));
				Scene scene = new Scene(root);
				stage2.setScene(scene);
				stage2.show();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
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
	// public void setView(View view) {
	// this.view = view;
	// }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

}
