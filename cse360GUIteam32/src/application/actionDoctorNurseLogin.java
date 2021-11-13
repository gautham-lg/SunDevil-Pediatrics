package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class actionDoctorNurseLogin {
	
public void fetchRecord(ActionEvent event) {
		
		try {
			Node node = (Node) event.getSource();
			Stage stage = (Stage) node.getScene().getWindow();
					
			Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/abcd/fetchrecord_nurse_doctor.fxml")));
			stage.setScene(scene);
			stage.show();

		} catch (IOException ex) {
			System.err.println(ex.getMessage());
		}
	}
public void home(ActionEvent event) {
	try {
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
				
		Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/abcd/Homepage.fxml")));
		stage.setScene(scene);
		stage.show();

	} catch (IOException ex) {
		System.err.println(ex.getMessage());
	}
}
public void contact(ActionEvent event) {
	try {
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
				
		Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/abcd/contactpage.fxml")));
		stage.setScene(scene);
		stage.show();

	} catch (IOException ex) {
		System.err.println(ex.getMessage());
	}
}
public void aboutus(ActionEvent event) {
	try {
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
				
		Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/abcd/aboutUs.fxml")));
		stage.setScene(scene);
		stage.show();

	} catch (IOException ex) {
		System.err.println(ex.getMessage());
	}
}
public void menu(ActionEvent event) {
	try {
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		System.out.println("hello world");
				
		Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/abcd/aboutUs.fxml")));
		stage.setScene(scene);
		stage.show();

	} catch (IOException ex) {
		System.err.println(ex.getMessage());
	}
}

}
