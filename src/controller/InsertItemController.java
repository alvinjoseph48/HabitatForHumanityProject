package controller;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import model.Item;

public class InsertItemController implements Initializable {

	@FXML
	private Button insertBtn;

	@FXML
	private ChoiceBox<String> categoryBox;

	@FXML
	private TextField productNameField;

	@FXML
	private TextField modelNumberField;

	@FXML
	private TextField demensionsField;

	@FXML
	private TextField imageUrlField;

	@FXML
	private TextField priceField;

	@FXML
	private TextField colorField;

	@FXML
	private TextField brandField;

	@FXML
	private TextField qtyField;

	@FXML
	private Button browseBtn;

	@FXML
	void insertIntoDatabaseClicked(ActionEvent event) {
		String imageUrl = imageUrlField.getText();
		try {
			Image image = new Image(imageUrl);
		} catch (IllegalArgumentException e1) {
			imageUrlInvalidAlert();
			return;
		}
		if (productNameField.getText().isEmpty() || modelNumberField.getText().isEmpty()
				|| demensionsField.getText().isEmpty() || imageUrlField.getText().isEmpty()
				|| priceField.getText().isEmpty() || colorField.getText().isEmpty() || brandField.getText().isEmpty()
				|| categoryBox.getSelectionModel().isEmpty() || qtyField.getText().isEmpty()) {
			fieldInsertEmptyAlert();
			return;
		}
		String productName = productNameField.getText();
		String modelNumber = modelNumberField.getText();
		String brand = brandField.getText();
		String color = colorField.getText();
		String price = priceField.getText();
		String demensions = demensionsField.getText();
		
		String category = categoryBox.getSelectionModel().getSelectedItem();
		String qty = qtyField.getText();

		

		
		Item newItem = new Item(productName, modelNumber, brand, color, price, demensions, imageUrl, category, qty);

		try {
			DbConnect.insertItem(newItem);
			itemInsertedAlert();
		} catch (MySQLIntegrityConstraintViolationException e) {
			producNameNotUniqueAlert();
			return;
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void browseBtnClicked(ActionEvent event) {
		 FileChooser fileChooser = new FileChooser();
		 Stage stage = new Stage();
		 File file = fileChooser.showOpenDialog(stage);
		 imageUrlField.setText("File://" + file.toURI().getPath());
	}

	private void imageUrlInvalidAlert() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setHeaderText("Inavlid Image Url");
		alert.setContentText("Please Enter valid URL File \n For Example: file:///C://Users// ");
		alert.showAndWait();
	}

	private void producNameNotUniqueAlert() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setHeaderText("Product Name is alreay Taken");
		alert.setContentText("Please Enter another product Name");
		alert.showAndWait();
	}

	private void fieldInsertEmptyAlert() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setHeaderText("Empty Fields");
		alert.setContentText("Please Enter all fields to insert Item!");
		alert.showAndWait();
	}

	private void itemInsertedAlert() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText("Product Inserted Into Database");
		alert.setContentText("Thank you! ");
		alert.showAndWait();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		categoryBox.getItems().removeAll(categoryBox.getItems());
		categoryBox.getItems().addAll("Window", "Paint", "Furniture");
	}

}
