package view;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

import controller.DbConnect;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import model.Person;

public class View {
	private BorderPane borderPane;
	private Menu fileMenu;
	private Menu searchMenu;
	private Menu removeMenu;
	private Menu updateMenu;
	private Menu insertMenu;
	// private Menu createMenu;
	private Menu shoppingMenu;
	private MenuBar menuBar;
	private MenuItem exit;
	private MenuItem insertItem;
	private MenuItem logout;
	// private MenuItem createUser;
	private MenuItem deleteUser;
	private MenuItem updateUser;
	private MenuItem searchItem;
	private MenuItem shoppingCart;

	public View() {
		borderPane = new BorderPane();
		menuBar = new MenuBar();
		fileMenu = new Menu("File");
		searchMenu = new Menu("Search");
		removeMenu = new Menu("Remove");
		insertMenu = new Menu("Insert");
		// createMenu = new Menu("Create");
		updateMenu = new Menu("Update");
		shoppingMenu = new Menu("Shop");
		// createUser = new MenuItem("Create User");
		deleteUser = new MenuItem("Delete User");
		updateUser = new MenuItem("Update User");
		searchItem = new MenuItem("Search For Item");
		shoppingCart = new MenuItem("Cart");
		insertItem = new MenuItem("Product into database");
		exit = new MenuItem("Exit");
		logout = new MenuItem("Logout");

		fileMenu.getItems().addAll(exit, logout);
		searchMenu.getItems().addAll(searchItem);
		removeMenu.getItems().addAll(deleteUser);
		// createMenu.getItems().addAll(createUser);
		updateMenu.getItems().addAll(updateUser);
		shoppingMenu.getItems().addAll(shoppingCart);
		insertMenu.getItems().addAll(insertItem);
		menuBar.getMenus().setAll(fileMenu, searchMenu, removeMenu, shoppingMenu);

		borderPane.setTop(menuBar);
		setLoginPane();
	}

	private void setLoginPane() {
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/view/LoginPane.fxml"));
			borderPane.setCenter(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
		disableUntilLogin();
	}

	public void searchItemClicked() {
		searchItem.setOnAction(e -> {
			try {
				Parent root = FXMLLoader.load(getClass().getResource("/view/ShoppingPane.fxml"));
				borderPane.setCenter(root);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
	}

	public void setInsert() {
		menuBar.getMenus().add(insertMenu);
	}

	public void disableUntilLogin() {
		logout.setDisable(true);
		deleteUser.setDisable(true);
		updateUser.setDisable(true);
		searchItem.setDisable(true);
		shoppingCart.setDisable(true);
	}

	public void unDisableUntilLogin() {
		if (DbConnect.isLoggedIn()) {
			unDisableBtns();
		}
	}

	public void unDisableBtns() {
		logout.setDisable(false);
		deleteUser.setDisable(false);
		updateUser.setDisable(false);
		searchItem.setDisable(false);
		shoppingCart.setDisable(false);
	}

	public void shoppingCartClicked() {
		shoppingCart.setOnAction(e -> {
			try {
				Parent root = FXMLLoader.load(getClass().getResource("/view/ShoppingPane.fxml"));
				borderPane.setCenter(root);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
	}

	public void deleteUserClicked() {
		deleteUser.setOnAction(e -> {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmation Dialog");
			alert.setHeaderText("Deleting User");
			alert.setContentText("Are you sure you want to delete this account?");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				try {
					Person p = DbConnect.deletePerson();
					alertAccountDeleted();
					setLoginPane();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});

	}

	private void alertAccountDeleted() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText("Acount deleted");
		alert.setContentText("Sorry to see you go, please come another time!");
		alert.showAndWait();
	}

	public void logoutClicked() {
		logout.setOnAction(e -> {
			setLoginPane();
		});
	}

	public void updateUserClicked() {
		updateUser.setOnAction(e -> {
			try {
				Parent root = FXMLLoader.load(getClass().getResource("/view/LoginPane.fxml"));
				borderPane.setCenter(root);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
	}

	public void exit() {
		exit.setOnAction(e -> {
			System.exit(0);
		});
	}

	public BorderPane getBorderPane() {
		return borderPane;
	}

}
