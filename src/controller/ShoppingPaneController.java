package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import model.Cart;
import model.Item;

public class ShoppingPaneController implements Initializable {
	@FXML
	private TextField searchField;

	@FXML
	private Button searchBtn;

	@FXML
	private Button addToCartBtn;
	@FXML
	private Label produtNameLbl;

	@FXML
	private Label priceLbl;

	@FXML
	private Label brandLbl;

	@FXML
	private Label colorLbl;

	@FXML
	private Label demensionsLbl;
	@FXML
	private VBox imageVBox;

	@FXML
	private ListView<String> itemListView;
	public static Cart cart = new Cart();
	private Item item;
	private ArrayList<Item> list = new ArrayList<Item>();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		itemListView.focusedProperty().addListener(new ChangeListener<Boolean>() {
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				String productName = itemListView.getSelectionModel().getSelectedItem();
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).getProductName().equals(productName)) {
						item = list.get(i);
					}
				}
				displayItem(productName);
			}
		});
	

	}

	public void displayItem(String productName) {
		colorLbl.setText(item.getColor());
		priceLbl.setText(item.getPrice());
		demensionsLbl.setText(item.getItemDemensions());
		brandLbl.setText(item.getBrand());
		produtNameLbl.setText(item.getProductName());
		Image image = new Image(item.getImageUrl());
		ImageView imageView = new ImageView();
		imageView.setImage(image);
		imageView.setPreserveRatio(true);
		imageView.setSmooth(true);
		imageView.setFitWidth(165);
		imageView.setFitHeight(165);
		imageVBox.getChildren().clear();
		imageVBox.getChildren().add(imageView);

	}

	private void addedToCartAlert() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText("Succesfully added to cart");
		alert.setContentText("Thank you please checkout when done");
		alert.showAndWait();
	}

	public void addToCartBtnClicked(ActionEvent event) {
		if (item == null) {
			noProuductSelectedAlert();
			return;
		}
		if (cart.addItem(item)) {
			addedToCartAlert();
		}
	}

	private void noProuductSelectedAlert() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setHeaderText("Cannot add item to cart");
		alert.setContentText("Please search for an Item!");
		alert.showAndWait();
	}

	public void searchBtnClicked(ActionEvent event) {
		try {

			list = DbConnect.getItem(searchField.getText());

		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (list.isEmpty()) {
			productDoesntExistAlert();
			return;
		}
		itemListView.getItems().clear();
		for (int i = 0; i < list.size(); i++) {
			itemListView.getItems().add(list.get(i).getProductName());
		}
	}

	private void productDoesntExistAlert() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setHeaderText("Product Does not Exist");
		alert.setContentText("Please enter another Product Name");
		alert.showAndWait();
	}

	public void listViewItemClicked(ActionEvent event) {
		colorLbl.setText(item.getColor());
		priceLbl.setText(item.getPrice());
		demensionsLbl.setText(item.getItemDemensions());
		produtNameLbl.setText(item.getProductName());
		brandLbl.setText(item.getBrand());
		addToCartBtn.setDisable(false);
	}

}
