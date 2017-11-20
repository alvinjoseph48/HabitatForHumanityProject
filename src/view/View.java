package view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;

public class View {
	private BorderPane borderPane;
	private Menu fileMenu;
	private Menu searchMenu;
	private Menu removeMenu;
	private Menu updateMenu;
//	private Menu createMenu;
	private Menu shoppingMenu;
	private MenuBar menuBar;
	private MenuItem exit;
	private MenuItem logout;
//	private MenuItem createUser;
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
		//createMenu = new Menu("Create");
		updateMenu = new Menu("Update");
		shoppingMenu = new Menu("Shop");
	//	createUser = new MenuItem("Create User");
		deleteUser = new MenuItem("Delete User");
		updateUser = new MenuItem("Update User");
		searchItem = new MenuItem("Search For Item");
		shoppingCart = new MenuItem("Cart");
		exit = new MenuItem("Exit");
		logout = new MenuItem("Logout");
		
		
		
		fileMenu.getItems().addAll(exit,logout);
		searchMenu.getItems().addAll(searchItem);
		removeMenu.getItems().addAll(deleteUser);
	//	createMenu.getItems().addAll(createUser);
		updateMenu.getItems().addAll(updateUser);
		shoppingMenu.getItems().addAll(shoppingCart);
		menuBar.getMenus().setAll(fileMenu , searchMenu, removeMenu,shoppingMenu);
		borderPane.setTop(menuBar);
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/view/LoginPane.fxml"));
			borderPane.setCenter(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public void searchItemClicked() {
		searchItem.setOnAction(e ->{
			try {
				Parent root = FXMLLoader.load(getClass().getResource("/view/LoginPane.fxml"));
				borderPane.setCenter(root);
			} catch (IOException e1) {
				e1.printStackTrace();
			}	
		});
	}
	public void shoppingCartClicked() {
		shoppingCart.setOnAction(e ->{
			try {
				Parent root = FXMLLoader.load(getClass().getResource("/view/ShoppingPane.fxml"));
				borderPane.setCenter(root);
			} catch (IOException e1) {
				e1.printStackTrace();
			}	
		});
	}
	public void deleteUserClicked() {
		deleteUser.setOnAction(e ->{
			try {
				Parent root = FXMLLoader.load(getClass().getResource("/view/CreateUserPane.fxml"));
				borderPane.setCenter(root);
			} catch (IOException e1) {
				e1.printStackTrace();
			}	
		});
	}
	
	public void updateUserClicked() {
		updateUser.setOnAction(e ->{
			try {
				Parent root = FXMLLoader.load(getClass().getResource("/view/LoginPane.fxml"));
				borderPane.setCenter(root);
			} catch (IOException e1) {
				e1.printStackTrace();
			}	
		});
	}
	public void exit () {
		exit.setOnAction(e -> {
			System.exit(0);
		});
	}
	

	public BorderPane getBorderPane() {
		return borderPane;
	}
	public void setCenter(Parent root) {
		borderPane.setCenter(root);
	}
	
	
	

}
