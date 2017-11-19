package view;

import javafx.scene.layout.BorderPane;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class View {
	private BorderPane borderPane;
	private Menu fileMenu;
	private Menu searchMenu;
	private Menu removeMenu;
	private Menu updateMenu;
	private Menu createMenu;
	private Menu shoppingMenu;
	private MenuBar menuBar;
	private MenuItem exit;
	private MenuItem createUser;
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
		createMenu = new Menu("Create");
		updateMenu = new Menu("Update");
		shoppingMenu = new Menu("Shop");
		createUser = new MenuItem("Create User");
		deleteUser = new MenuItem("Delete User");
		updateUser = new MenuItem("Update User");
		searchItem = new MenuItem("Search");
		shoppingCart = new MenuItem("Cart");
		exit = new MenuItem("Exit");
		
		
		fileMenu.getItems().addAll(exit);
		searchMenu.getItems().addAll(searchItem);
		removeMenu.getItems().addAll(deleteUser);
		createMenu.getItems().addAll(createUser);
		updateMenu.getItems().addAll(updateUser);
		shoppingMenu.getItems().addAll(shoppingCart);
		menuBar.getMenus().setAll(fileMenu , searchMenu,createMenu, removeMenu,shoppingMenu);
		borderPane.setTop(menuBar);
	}

	public BorderPane getBorderPane() {
		return borderPane;
	}
	
	
	

}
