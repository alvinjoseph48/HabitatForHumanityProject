package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class ShoppingPaneController implements Initializable {
    @FXML
    private TextField searchField;

    @FXML
    private Button searchBtn;

    @FXML
    private ChoiceBox<String> productTypeBox;	
	public void searchBtnClicked(ActionEvent event)  {
		
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		productTypeBox.getItems().removeAll(productTypeBox.getItems());
		productTypeBox.getItems().addAll("Window","Paint","Furniture");
		
	}
}
