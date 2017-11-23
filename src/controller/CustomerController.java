package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Person;

public class CustomerController {
	@FXML
	private MenuBar customerMenuBar;
	@FXML
	private MenuItem customerLogout;

	@FXML
	private MenuItem customerExit;

	@FXML
	private MenuItem customerSearch;

	@FXML
	private MenuItem customerCart;

	@FXML
	private MenuItem customerUpdate;

	@FXML
	private MenuItem customerDelete;

	public void searchClicked(ActionEvent event) {
		try {
			Node node = (Node) event.getSource();
			BorderPane pane = (BorderPane) node.getParent();
			Parent root = FXMLLoader.load(getClass().getResource("/view/ShoppingPane.fxml"));
			pane.setCenter(root);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public void shoppingCartClicked(ActionEvent event) {
		try {
			Node node = (Node) event.getSource();
			BorderPane pane = (BorderPane) node.getParent();
			Parent root = FXMLLoader.load(getClass().getResource("/view/ShoppingPane.fxml"));
			pane.setCenter(root);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

	public void deleteUserClicked(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Dialog");
		alert.setHeaderText("Deleting User");
		alert.setContentText("Are you sure you want to delete this account?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			try {
				Person p = DbConnect.deletePerson();
				alertAccountDeleted();
				// setLoginPane();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	private void alertAccountDeleted() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText("Acount deleted");
		alert.setContentText("Sorry to see you go, please come another time!");
		alert.showAndWait();
	}

	@FXML
	public void logoutClicked(ActionEvent event) {
		if(!alertLogoutSuccesfull()) {
			return;
		}
		try {
			Stage stage = (Stage) customerMenuBar.getScene().getWindow();
			stage.close();

			Stage stage2 = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/view/LoginPane.fxml"));
			Scene scene = new Scene(root);
			stage2.setScene(scene);
			stage2.show();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

	private boolean alertLogoutSuccesfull() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText("Logout");
		alert.setContentText("Are you sure you want to logout?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.CANCEL) {
			return false;
		}
		return true;
	}

	public void updateUserClicked(ActionEvent event) {
		try {
			Node node = (Node) event.getSource();
			BorderPane pane = (BorderPane) node.getParent();
			Parent root = FXMLLoader.load(getClass().getResource("/view/CreateUserPane.fxml"));
			pane.setCenter(root);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

	public void exitClicked(ActionEvent event) {
		System.exit(0);
	}
}
