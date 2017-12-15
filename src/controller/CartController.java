package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import model.Cart;
import model.Item;

public class CartController implements Initializable{
	@FXML
    private ListView<Item> cartListView;

    @FXML
    private Button checkoutBtn;

    @FXML
    private Label itemsLbl;

    @FXML
    private Label subtotalLbl;

    @FXML
    private Label cartProductNameLbl;

    @FXML
    private Label cartPriceLbl;

    @FXML
    private Label cartQtyLbl;

    @FXML
    private VBox cartItemsView;
    private Cart cart = ShoppingPaneController.cart;
    private Item item;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		cartListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Item>() {

			@Override
			public void changed(ObservableValue<? extends Item> arg0, Item old, Item newValue) {
				if(newValue == null) {
					return;
				}
				displayItem(newValue);
				item = newValue;
			}});

		if (cart.getCart().isEmpty()) {
			return;
		}
		insertItems();
		
		
	}
	public void insertItems() {
		cartListView.getItems().clear();
		for (int i = 0; i < cart.getCart().size(); i++) {
			cartListView.getItems().add(cart.getCart().get(i));
		}
		subtotalLbl.setText(String.valueOf(getSubtotal() )+ " $");
	}
	public int getSubtotal() {
		int subtotal  = 0;
		for (int i = 0; i < cart.getCart().size(); i++) {
			subtotal +=	Integer.valueOf(cart.getCart().get(i).getPrice());
		}
		return subtotal;
	}
	ImageView imageView;
	public void displayItem(Item item) {
		cartPriceLbl.setText(item.getPrice());
		cartProductNameLbl.setText(item.getProductName());
		
		Image image = new Image(item.getImageUrl());
		imageView = new ImageView();
		imageView.setImage(image);
		imageView.setPreserveRatio(true);
		imageView.setSmooth(true);
		imageView.setFitWidth(165);
		imageView.setFitHeight(165);
		cartItemsView.getChildren().clear();
		cartItemsView.getChildren().add(imageView);
	}
    @FXML
    private Button deleteBtn;

    @FXML
    public void deleteBtnClicked(ActionEvent event) {
    	if(cartListView.getSelectionModel().getSelectedItem() == null) {
    		noProuductSelectedAlert();
    		return;
    	}
    	if(cart.removeItem(item.getProductName())!= null){
    		deletedItemAlert();
    		insertItems();
    		cartPriceLbl.setText("");
    		cartProductNameLbl.setText("");
    		cartItemsView.getChildren().remove(imageView);
    	}
    }
    public void checkoutBtnClicked(ActionEvent event) {
    	Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/view/CheckoutPane.fxml"));
			BorderPane parent = (BorderPane) checkoutBtn.getParent().getParent();
			parent.setCenter(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
    }
    private void noProuductSelectedAlert() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setHeaderText("No Item selected item");
		alert.setContentText("Please select an Item!");
		alert.showAndWait();
	}
    private void deletedItemAlert() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText("Item deleted");
		alert.setContentText("Please checkout when done ");
		alert.showAndWait();
	}
	

}
