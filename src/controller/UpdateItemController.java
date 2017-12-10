package controller;

import java.io.File;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Item;

public class UpdateItemController implements Initializable {
	@FXML
	private ListView<String> updateItemListView;

	@FXML
	private Button updateSearchBtn;

	@FXML
	private TextField nameField;

	@FXML
	private TextField modelField;

	@FXML
	private TextField brandField;

	@FXML
	private TextField colorField;

	@FXML
	private TextField priceField;

	@FXML
	private TextField sizeField;

	@FXML
	private TextField urlField;

	@FXML
	private TextField qtyField;

	@FXML
	private TextField searchItemField;
	@FXML
	private Button browseUpdateBtn;

	@FXML
	private Button updateItemBtn;

	@FXML
	private ChoiceBox<String> categoryBox;
	@FXML
	private VBox vBox;
	private Item item;
	private ArrayList<Item> itemList = new ArrayList<Item>();

	public void browseBtnClicked(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		Stage stage = new Stage();
		File file = fileChooser.showOpenDialog(stage);
		urlField.setText(file.toURI().getPath());
	}

	public void searchBtnClicked(ActionEvent event) {
		try {

			itemList = DbConnect.getItem(searchItemField.getText());

		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (itemList.isEmpty()) {
			productDoesntExistAlert();
			return;
		}
		updateItemListView.getItems().clear();
		for (int i = 0; i < itemList.size(); i++) {
			updateItemListView.getItems().add(itemList.get(i).getProductName());
		}
	}

	private void productDoesntExistAlert() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setHeaderText("Product Does not Exist");
		alert.setContentText("Please enter another Product Name");
		alert.showAndWait();
	}

	public void updateBtnClicked(ActionEvent event) {
		String productName = nameField.getText();
		String modelNumber = modelField.getText();
		String brand = brandField.getText();
		String color = colorField.getText();
		String price = priceField.getText();
		String demensions = sizeField.getText();
		String category = categoryBox.getSelectionModel().getSelectedItem();
		String qty = qtyField.getText();
		String url = urlField.getText();
		
		Item newItem = new Item(productName, modelNumber, brand, color, price, demensions, url, category, qty);

		if(DbConnect.updateItem(newItem)) {
			updateGoodAlert();
		}else {
			updateBadAlert();
		}
	}
	private void updateBadAlert() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setHeaderText("Could not update Item ");
		alert.setContentText("Try again");
		alert.showAndWait();
	}
	private void updateGoodAlert() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText("Updated Item ");
		alert.setContentText("Thank you");
		alert.showAndWait();
	}
	public void setImage(String string) {
		Image image;
		try {
			image = new Image(string);
			ImageView imageView = new ImageView();
			imageView.setImage(image);
			imageView.setPreserveRatio(true);
			imageView.setSmooth(true);
			imageView.setFitWidth(165);
			imageView.setFitHeight(165);
			vBox.getChildren().clear();
			vBox.getChildren().add(imageView);
		} catch (Exception e) {
			imageUrlInvalidAlert();
		}

	}

	private void imageUrlInvalidAlert() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setHeaderText("Inavlid Image Url");
		alert.setContentText("Please Enter valid URL File \n For Example: file:///C://Users// ");
		alert.showAndWait();
	}

	public void setFields() {
		colorField.setText(item.getColor());
		priceField.setText(item.getPrice());
		sizeField.setText(item.getItemDemensions());
		brandField.setText(item.getBrand());
		nameField.setText(item.getProductName());
		qtyField.setText(item.getQty());
		modelField.setText(item.getModelNum());
		categoryBox.getSelectionModel().select(item.getCategory());
		urlField.setText(item.getImageUrl());
		setImage(item.getImageUrl());
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		categoryBox.getItems().removeAll(categoryBox.getItems());
		categoryBox.getItems().addAll("Window", "Paint", "Furniture");
		updateItemListView.focusedProperty().addListener(new ChangeListener<Boolean>() {
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				String productName = updateItemListView.getSelectionModel().getSelectedItem();
				if (productName != null) {
					for (int i = 0; i < itemList.size(); i++) {
						if (itemList.get(i).getProductName().equals(productName)) {
							item = itemList.get(i);
							setFields();
						}
					}
					setFields();
				}
			}
		});
	}
}
