package controller;

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
	void insertIntoDatabaseClicked(ActionEvent event) {
		
		if( productNameField.getText().isEmpty()  ||modelNumberField.getText().isEmpty() || 
				demensionsField.getText().isEmpty() ||imageUrlField.getText().isEmpty() ||
				priceField.getText().isEmpty() ||colorField.getText().isEmpty() || 
				brandField.getText().isEmpty() ||categoryBox.getSelectionModel().isEmpty()
    			){
    		fieldInsertEmptyAlert();
    		return;
    	}
		String productName = productNameField.getText();
		String modelNumber = modelNumberField.getText();
		String brand = brandField.getText();
		String color = colorField.getText();
		String price = priceField.getText();
		String demensions = demensionsField.getText();
		String imageUrl = imageUrlField.getText();
		String category = categoryBox.getSelectionModel().getSelectedItem();
		Item newItem = new Item(productName, modelNumber, brand, color, price, demensions, imageUrl, category);
		
		try {
			DbConnect.insertItem(newItem);
		} catch(MySQLIntegrityConstraintViolationException e) {
			producNameNotUniqueAlert();
			return;
		} catch (SQLException e) {
			e.printStackTrace();
		}

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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		categoryBox.getItems().removeAll(categoryBox.getItems());
		categoryBox.getItems().addAll("Window", "Paint", "Furniture");
	}

}
