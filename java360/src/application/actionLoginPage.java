package application;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class actionLoginPage {

	@FXML
	PasswordField password = new PasswordField();
	@FXML
	TextField userName = new TextField();

	Statement st = null;

	ResultSet rs = null;

	public DBConnection frm = new DBConnection();

	public void patRegister(ActionEvent event) {
		try {
			Node node = (Node) event.getSource();
			Stage stage = (Stage) node.getScene().getWindow();

			Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/abcd/userregis.fxml")));
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

	public void viewMedicalHistory(ActionEvent event) throws SQLException {
		String userDB = "", passDB = "", firstname="", lastname="";
		if (userName.getText().equals("") || password.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Please enter Username & Password");
			return;
		}
		st = frm.con.createStatement();
		String firstSql = "Select email , password, firstname, lastname from patient_info where  email='" + userName.getText() + "'";
		rs = st.executeQuery(firstSql);
		if (rs.next()) {
			userDB = rs.getString("email");
			passDB = rs.getString("password");
			firstname = rs.getString(3);
			lastname = rs.getString(4);
		}
		rs.close();

		if (password.getText().equals(passDB) && userName.getText().equals(userDB)) {

			try {
				Node node = (Node) event.getSource();
				Stage stage = (Stage) node.getScene().getWindow();

				FXMLLoader loader = new FXMLLoader(getClass().getResource("/abcd/view_medicalhistory.fxml"));
				Parent root = loader.load();

				actionViewMedical nurseVitals = loader.getController();
				nurseVitals.setPatName(firstname + " " + lastname);

				stage.setScene(new Scene(root));
				stage.show();

			} catch (IOException ex) {
				System.err.println(ex.getMessage());
			}
		} else {
			JOptionPane.showMessageDialog(null, "UserName or Password is InValid");
			return;
		}
	}

	public void patientRegistration(ActionEvent event) {
		try {
			Node node = (Node) event.getSource();
			Stage stage = (Stage) node.getScene().getWindow();

			Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/abcd/userregis.fxml")));
			stage.setScene(scene);
			stage.show();

		} catch (IOException ex) {
			System.err.println(ex.getMessage());
		}
	}

}
