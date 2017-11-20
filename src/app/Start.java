package app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.View;

public class Start extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		View view = new View();
//		LoginController lc = new LoginController();
		//lc.setBorderPane(view.getBorderPane());
//		lc.setView(view);
		// Parent root =
		// FXMLLoader.load(getClass().getResource("/view/LoginPane.fxml"));
		primaryStage.setTitle("Habitat for Humanity");
		primaryStage.setScene(new Scene(view.getBorderPane(), 600, 400));
		primaryStage.show();
		view.exit();
		view.shoppingCartClicked();
	}

	// public static void main(String[] args) {
	// launch(args);
	// }

}
