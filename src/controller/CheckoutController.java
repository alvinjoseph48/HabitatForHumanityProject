package controller;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Alert.AlertType;
import model.Address;
import model.Person;

public class CheckoutController implements Initializable {

	@FXML
	private Button placeOrderBtn;

	@FXML
	private TextField streetNumField;

	@FXML
	private TextField streetField;

	@FXML
	private TextField cityField;

	@FXML
	private ChoiceBox<String> stateBox;

	@FXML
	private TextField zipCodeField;

	@FXML
	private TextField nameField;

	@FXML
	private TextField numberField;

	@FXML
	private ChoiceBox<String> monthBox;

	@FXML
	private ChoiceBox<String> yearBox;

	@FXML
	private TextField codeField;

	@FXML
	private Label checkoutSubLbl;

	@FXML
	private Label shippingLbl;

	@FXML
	private Label taxLbl;

	@FXML
	private Label totalLbl;

	Person person = DbConnect.getPerson();
	public static boolean isPurchased = false; 
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setField();
		setBoxes();
		getCosts();
	}

	public void setBoxes() {
		stateBox.getItems().removeAll(stateBox.getItems());
		stateBox.getItems().addAll(states);
		monthBox.getItems().removeAll(monthBox.getItems());
		monthBox.getItems().addAll("01", "02", "03", "03", "05", "06", "07", "08", "09", "10", "11", "12");
		yearBox.getItems().removeAll(yearBox.getItems());
		yearBox.getItems().addAll("2017", "2018", "2019", "2020", "2021");
	}

	public int getSubtotal() {

		int subtotal = 0;
		for (int i = 0; i < ShoppingPaneController.cart.getCart().size(); i++) {
			subtotal += Integer.valueOf(ShoppingPaneController.cart.getCart().get(i).getPrice());
		}
		return subtotal;
	}

	private void noItemsInCartAlert() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setHeaderText("No Items in cart");
		alert.setContentText("Please add Items first");
		alert.showAndWait();
	}

	public void getCosts() {
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2);
		int subtotal = getSubtotal();
		checkoutSubLbl.setText(String.valueOf(subtotal)+" $");
		final double TAX_RATE = .08875;
		double tax = TAX_RATE * subtotal;
		final double SHIPPING_RATE = .1;
		double shipping = SHIPPING_RATE * subtotal;
		taxLbl.setText(df.format(tax)+" $");
		shippingLbl.setText(String.valueOf(shipping)+" $");
		double total = (subtotal + tax + shipping);
		totalLbl.setText(df.format(total)+" $");
	}

	private ObservableList<String> states = FXCollections.observableArrayList("Alabama", "Alaska", "Arkansas",
			"Arizona", "California", "Colorado", "Connecticut", "Delaware", "Florida", "Georgia", "Hawaii", "Idaho",
			"Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts",
			"Michigan", "Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire",
			"New Jersey", "New Mexico", "New York", "North Carolina,", "North Dakota", "Ohio", "Oklahoma", "Oregon",
			"Pennsylvania", "Rhode Island", "South Carolina", "South Dakota", "Tennessee", "Texas", "Utah", "Vermont",
			"Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming");

	public void setField() {
		streetNumField.setText(person.getAddress().getStreetNum());
		streetField.setText(person.getAddress().getStreet());
		cityField.setText(person.getAddress().getCity());
		zipCodeField.setText(person.getAddress().getZip());
		stateBox.getSelectionModel().select(person.getAddress().getState());
	}

	@FXML
	void placeOrderBtnClicked(ActionEvent event) {
		if (getSubtotal() == 0) {
			noItemsInCartAlert();
			return;
		}
		String month = monthBox.getSelectionModel().getSelectedItem();
		String year = yearBox.getSelectionModel().getSelectedItem();
		String cardNumber = numberField.getText();
		String code = codeField.getText();
		String name = nameField.getText();
		if (cardNumber.length() < 10 || cardNumber.length() > 20) {
			creditCardError();
			return;
		}
		if (code.length() != 3) {
			creditCardError();
			return;
		}
		if(!isNumber(cardNumber)) {
			creditCardError();
			return;
		}
		
		if (month == null || year == null || cardNumber == null || code == null || name == null || code.isEmpty()) {
			emptyFields();
			return;
		}
		Address address = new Address(streetNumField.getText(), streetField.getText(), cityField.getText(),
				zipCodeField.getText(), stateBox.getSelectionModel().getSelectedItem());
		if (address.getCity().isEmpty() || address.getState().isEmpty() || address.getStreet().isEmpty()
				|| address.getStreetNum().isEmpty() || address.getZip().isEmpty()) {
			emptyFields();
			return;
		}
		placedOrderAlert();
		isPurchased = true; 
		BorderPane borderPane = (BorderPane) placeOrderBtn.getParent().getParent();
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/view/ShoppingPane.fxml"));
			borderPane.setCenter(root);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		

	}
	public boolean isNumber(String string) {
		  for (int i = 0; i < string.length(); i++) {
		    if (!Character.isDigit(string.charAt(i))) {
		      return false;
		    }
		  }

		  return true;
		}
	private void emptyFields() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setHeaderText("Empty Fields");
		alert.setContentText("Please enter all fields");
		alert.showAndWait();
	}

	private void creditCardError() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setHeaderText("Credit card info is invalid");
		alert.setContentText("Please enter valid information \n"
				+ "Security code: 3 digits \nNumber: 10-20 digits");
		alert.showAndWait();
	}

	private void placedOrderAlert() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText("Ordered placed");
		alert.setContentText("Thank you !");
		alert.showAndWait();
	}

}
