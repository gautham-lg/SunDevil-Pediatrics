package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;
//import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception 
	{
		Parent root = FXMLLoader.load(getClass().getResource("/abcd/Homepage.fxml"));
//		fetchrecors_nurse_doctor
//		primaryStage.setScene(scene);
		primaryStage.setTitle("Hello Welcome to Doctor's Office");
		primaryStage.setScene(new Scene(root,700,400));
//		new Scene(root,700,400)
		primaryStage.show();

	}
}

			
		
			
//			BorderPane root = new BorderPane();
//			Scene scene = new Scene(root,400,400);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
		

	
	

