package app;

	import controller.LoginController;
import javafx.application.Application;
	import javafx.fxml.FXMLLoader;
	import javafx.scene.Parent;
	import javafx.scene.Scene;
	import javafx.stage.Stage;
import view.View;

	public class Start extends Application {
	    @Override
	    public void start(Stage primaryStage) throws Exception{
	    	View view = new View();
	    	 LoginController lc = new LoginController(view.getBorderPane());
	       // Parent root = FXMLLoader.load(getClass().getResource("/view/LoginPane.fxml"));
	        primaryStage.setTitle("Habitat for Humanity");
	        primaryStage.setScene(new Scene(view.getBorderPane(), 600, 400));
	        primaryStage.show();
	        view.exit();
	       
	    }


//	    public static void main(String[] args) {
//	        launch(args);
//	    }

	}


