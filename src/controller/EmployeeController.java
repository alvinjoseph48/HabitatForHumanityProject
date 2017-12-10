package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Person;

public class EmployeeController implements Initializable {

	@FXML
	private MenuItem employeeLogout;
	@FXML
	private MenuBar employeeMenuBar;
	@FXML
	private MenuItem employeeExit;

	@FXML
	private MenuItem employeeProductSearch;

	@FXML
	private MenuItem employeeCustomerSearch;

	@FXML
	private MenuItem employeeInsert;

	@FXML
	private MenuItem employeeUpdate;

	@FXML
	private MenuItem employeeDelete;
	
	@FXML
	private BorderPane employeeBorderPane;
	
	public void productSearchClicked(ActionEvent event) {
		try {
		
			Parent root = FXMLLoader.load(getClass().getResource("/view/UpdateItemPane.fxml"));
			employeeBorderPane.setCenter(root);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public void employeeDeleteUserClicked(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Dialog");
		alert.setHeaderText("Deleting User");
		alert.setContentText("Are you sure you want to delete this account?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			try {
				Person p = DbConnect.deletePerson();
				employeeAlertAccountDeleted();

			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}else {
			return;
		}
		try {
			Stage stage = (Stage) employeeMenuBar.getScene().getWindow();
			stage.close();
			Stage stage2 = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/view/LoginPane.fxml"));
			Scene scene2 = new Scene(root);
			stage2.setScene(scene2);
			stage2.show();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}
	private void employeeAlertAccountDeleted() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText("Acount deleted");
		alert.setContentText("Sorry to see you go, please come another time!");
		alert.showAndWait();
	}

	@FXML
	public void employeeLogoutClicked(ActionEvent event) {
		if (!employeeAlertLogoutSuccesfull()) {
			return;
		}
		try {
			Stage stage = (Stage) employeeMenuBar.getScene().getWindow();
			stage.close();
			Stage stage2 = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/view/LoginPane.fxml"));
			Scene scene2 = new Scene(root);
			stage2.setScene(scene2);
			stage2.show();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

	private boolean employeeAlertLogoutSuccesfull() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText("Logout");
		alert.setContentText("Are you sure you want to logout?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.CANCEL) {
			return false;
		}
		return true;
	}
	public void employeeUpdateUserClicked(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/UpdatePane.fxml"));
			employeeBorderPane.setCenter(root);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}
	public void insertItemClicked(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/InsertItemPane.fxml"));
			employeeBorderPane.setCenter(root);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public void employeeExitClicked(ActionEvent event) {
		System.exit(0);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Image image = new Image("file:///C:/Users/alvin/Desktop/CSE%20248/JosephCse248HabitatForHumanityProject/images/1200px-Habitat_for_humanity.svg.png");
		ImageView iv = new ImageView();
		iv.setImage(image);
		iv.setPreserveRatio(true);
		iv.setSmooth(true);
		iv.setFitWidth(300);
		iv.setFitHeight(300);
		iv.setImage(image);
		employeeBorderPane.setCenter(iv);
	}

}
