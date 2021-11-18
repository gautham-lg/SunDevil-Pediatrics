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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class actionDoctorNurseLogin {

	@FXML
	ChoiceBox<String> roles = new ChoiceBox<>();

	@FXML
	PasswordField password = new PasswordField();
	@FXML
	TextField userName = new TextField();

	Statement st = null;

	ResultSet rs = null;

	public DBConnection frm = new DBConnection();

	@FXML
	public void initialize() throws SQLException {

		String[] role = { "Doctor", "Nurse" };
		roles.getItems().addAll(role);
	}

	public void login(ActionEvent event) throws SQLException {

		String userDB = "", passDB = "", firstname="", lastname="";
		if (userName.getText().equals("") || password.getText().equals("") || roles.getSelectionModel().getSelectedItem() == null) {
			JOptionPane.showMessageDialog(null, "Please enter Username & Password & Role");
			return;
		}
		st = frm.con.createStatement();
		String firstSql = "";
		if (roles.getSelectionModel().getSelectedItem() != null && roles.getSelectionModel().getSelectedItem().equalsIgnoreCase("Doctor")) {
			firstSql = "Select doctorID , password, first_name, last_name from doctorInfo where  doctorID='" + userName.getText() + "'";
			rs = st.executeQuery(firstSql);
			if (rs.next()) {
				userDB = rs.getString("doctorID");
				passDB = rs.getString("password");
				firstname= rs.getString(3);
				lastname= rs.getString(4);
			}
		} else {
			firstSql = "Select NurseID , password, first_name, last_name from NurseInfo where  NurseID='" + userName.getText() + "'";
			rs = st.executeQuery(firstSql);
			if (rs.next()) {
				userDB = rs.getString("NurseID");
				passDB = rs.getString("password");
				firstname= rs.getString(3);
				lastname= rs.getString(4);
			}
		}

		rs.close();

		if (password.getText().equals(passDB) && userName.getText().equals(userDB)) {

			try {
				Node node = (Node) event.getSource();
				Stage stage = (Stage) node.getScene().getWindow();

				FXMLLoader loader = new FXMLLoader(getClass().getResource("/abcd/fetchrecord_nurse_doctor.fxml"));
				Parent root = loader.load();

				actionFetchPatient nurseVitals = loader.getController();
				nurseVitals.setLabelText(roles.getSelectionModel().getSelectedItem());
				nurseVitals.setInfo(firstname +" " + lastname);

				stage.setScene(new Scene(root));
				stage.show();

			} catch (IOException ex) {
				System.err.println(ex.getMessage());
			}
		} else

		{
			JOptionPane.showMessageDialog(null, "UserName or Password is InValid");
			return;
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
