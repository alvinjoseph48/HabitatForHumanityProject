package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

public class LoginController {
	BorderPane borderPane;
	public LoginController(BorderPane borderPane) {
		this.borderPane = borderPane;
	}
	public void createAccount(ActionEvent event) throws IOException {
	        Parent root = FXMLLoader.load(getClass().getResource("/view/CreateUserPane.fxml"));
	        borderPane.setCenter(root);
	    }


    }


