package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
//import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Main extends Application {
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception 
	{
		Parent root = FXMLLoader.load(getClass().getResource("/abcd/Homepage.fxml"));
		primaryStage.setTitle("Hello Welcome to Doctor's Office");
		primaryStage.setScene(new Scene(root,700,400));
		primaryStage.show();

	}
}
