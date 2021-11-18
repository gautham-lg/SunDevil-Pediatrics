package application;

import java.io.IOException;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class actionFetchPatient {

	@FXML
	TextField lastname = new TextField();
	@FXML
	TextField firstname = new TextField();
	@FXML
	DatePicker dob = new DatePicker();

	Statement st = null;

	ResultSet rs = null;

	public DBConnection frm = new DBConnection();
	
	String role ="";
	
	String docNurseInfo = "";

	public void search(ActionEvent event) throws SQLException {
		int count = 0;

		if (firstname.getText().equals("") || lastname.getText().equals("")
				|| dob.getValue() == null) {
			JOptionPane.showMessageDialog(null, "Please enter all four details!!");
			return;
		}

		java.sql.Date dobDB = new java.sql.Date(
				Date.from(dob.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime());

		st = frm.con.createStatement();
		String firstSql = "Select count(1) as count from patient_info where  firstname='" + firstname.getText() + "'"
				+ " and lastname='" + lastname.getText() + "'" + " and dob='" + dobDB + "'";
		rs = st.executeQuery(firstSql);
		if (rs.next()) {
			count = rs.getInt("count");
		}
		rs.close();
		if (count == 0) {
			JOptionPane.showMessageDialog(null, "No Patient found!!");
			return;
		}
		if (role.equalsIgnoreCase("Doctor")) {
			try {
				Node node = (Node) event.getSource();
				Stage stage = (Stage) node.getScene().getWindow();

				FXMLLoader loader = new FXMLLoader(getClass().getResource("/abcd/doctorfunction.fxml"));
				Parent root = loader.load();

				actionDoctorFucntion nurseVitals = loader.getController();
				nurseVitals.setLabelText(firstname.getText() +" " + lastname.getText());
				nurseVitals.setName(docNurseInfo);

				stage.setScene(new Scene(root));
				stage.show();

			} catch (IOException ex) {
				System.err.println(ex.getMessage());
			}
		} else {
			try {
				Node node = (Node) event.getSource();

				Stage stage = (Stage) node.getScene().getWindow();

				FXMLLoader loader = new FXMLLoader(getClass().getResource("/abcd/nursevitals.fxml"));
				Parent root = loader.load();

				actionNurseVitals nurseVitals = loader.getController();
				nurseVitals.setLabelText(firstname.getText() +" " + lastname.getText());
				nurseVitals.setName(docNurseInfo);

				stage.setScene(new Scene(root));
				stage.show();

			} catch (IOException ex) {
				System.err.println(ex.getMessage());
			}

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

	public void setLabelText(String selectedItem) {
		role = selectedItem;
		
	}

	public void setInfo(String fullname) {
		docNurseInfo = fullname;
		
	}

}
