package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
//import view.View;

public class Start extends Application {
//	public static Stage primaryStage;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
//		Start.primaryStage = primaryStage;
	//	View view = new View();
	//	LoginController lc = new LoginController();
	//	lc.setView(view);
		primaryStage.setTitle("Habitat for Humanity");
		Parent root = FXMLLoader.load(getClass().getResource("/view/loginPane.fxml"));
		//primaryStage.setScene(new Scene(view.getBorderPane(), 600, 400));
		primaryStage.setScene(new Scene(root, 600, 400));

		primaryStage.show();
	//	view.exit();
	//	view.shoppingCartClicked();
	//	view.deleteUserClicked();
	//	view.unDisableUntilLogin();
	
	}

	

}
