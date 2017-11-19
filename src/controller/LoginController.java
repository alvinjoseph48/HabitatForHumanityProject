package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class LoginController {
	BorderPane borderPane;

	public LoginController(BorderPane borderPane)    {
		this.borderPane = borderPane;
	}
	@FXML Button createUserBtn ;
	public void createUserBtnClicked(ActionEvent event) {
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/view/LoginPane.fxml"));
			borderPane.getChildren().remove(root);
			root = FXMLLoader.load(getClass().getResource("/view/CreateUserPane.fxml"));
			borderPane.setCenter(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
