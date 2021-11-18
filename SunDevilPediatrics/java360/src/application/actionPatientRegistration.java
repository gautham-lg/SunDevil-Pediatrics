package application;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.ZoneId;
import java.util.Date;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class actionPatientRegistration {

	@FXML
	PasswordField password = new PasswordField();
	@FXML
	TextField firstname = new TextField();
	@FXML
	DatePicker dob = new DatePicker();
	@FXML
	PasswordField cpassword = new PasswordField();
	@FXML
	TextField lastname = new TextField();
	@FXML
	TextField pharmacy = new TextField();
	@FXML
	TextField number = new TextField();
	@FXML
	TextField gender = new TextField();
	@FXML
	TextField email = new TextField();
	@FXML
	TextField insurance = new TextField();

	Statement st = null;

	ResultSet rs = null;

	public DBConnection frm = new DBConnection();

	public void submit(ActionEvent event) throws SQLException {

		if(dob.getValue() == null) {
			JOptionPane.showMessageDialog(null, "Date cannot be empty!!");
			return;
		}
		java.sql.Date dobDB = new java.sql.Date(
				Date.from(dob.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime());
		
		if (!validateNumber() && number.getText().length() > 10) {
			JOptionPane.showMessageDialog(null, "Phone number not correct!!");
			return;
		}
		
		if(password.getText() != null && cpassword.getText() != null && !password.getText().equals(cpassword.getText())){
			JOptionPane.showMessageDialog(null, "Password and Confirm password not same!!");
			return;
		}
		
		st = frm.con.createStatement();
		int count =0;
		String firstSql = "Select count(1) as count from patient_info where  email='" + email.getText() + "'";
		rs = st.executeQuery(firstSql);
		if (rs.next()) {
			count = rs.getInt("count");
		}
		if(count != 0) {
			JOptionPane.showMessageDialog(null, "Email already registered!!");
			return;
		}

		String sql = "Insert into patient_info"
				+ "(firstname,lastname,email,password,cpassword,insurance,dob,number,gender,pharmacy)"
				+ " values(?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = frm.con.prepareStatement(sql);
		ps.setString(1, firstname.getText());
		ps.setString(2, lastname.getText());
		ps.setString(3, email.getText());
		ps.setString(4, password.getText());
		ps.setString(5, cpassword.getText());
		ps.setString(6, insurance.getText());
		ps.setDate(7, dobDB);
		ps.setString(8, number.getText());
		ps.setString(9, gender.getText());
		ps.setString(10, pharmacy.getText());
		ps.execute();
		JOptionPane.showMessageDialog(null, "User saved successfully!!");

		try {
			Node node = (Node) event.getSource();
			Stage stage = (Stage) node.getScene().getWindow();

			Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/abcd/login.fxml")));
			stage.setScene(scene);
			stage.show();

		} catch (IOException ex) {
			System.err.println(ex.getMessage());
		}
	}

	private boolean validateNumber() {
		try {
			Integer.parseInt(number.getText());
		} catch (Exception e) {
			return false;
		}
		return true;
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
}
