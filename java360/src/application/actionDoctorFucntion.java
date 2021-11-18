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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class actionDoctorFucntion {

	@FXML
	TextField weight = new TextField();
	
	@FXML
	TextField height = new TextField();
	
	@FXML
	TextField bodyTemp = new TextField();
	
	@FXML
	TextField allergy = new TextField();
	
	@FXML
	TextField symptoms = new TextField();
	
	@FXML
	TextField bp = new TextField();
	
	@FXML
	TextArea comments = new TextArea();
	
	@FXML
	TextArea prescription = new TextArea();
	
	@FXML
	Label patientName = new Label();
	
	@FXML
	Label doctorName = new Label();
	
	Statement st = null;

	ResultSet rs = null;

	public DBConnection frm = new DBConnection();

	public void setLabelText(String text) throws SQLException {
		patientName.setText(text);
		
		st = frm.con.createStatement();
		String firstSql = "Select weight,height,temp,bp,allergy,symptoms,comments,prescription from patient_medical_info where  patientName='" + patientName.getText() + "'";
		rs = st.executeQuery(firstSql);
		if (rs.next()) {
			weight.setText(String.valueOf(rs.getInt(1)));
			height.setText(String.valueOf(rs.getInt(2)));
			bodyTemp.setText(String.valueOf(rs.getInt(3)));
			bp.setText(String.valueOf(rs.getInt(4)));
			allergy.setText(rs.getString(5));
			symptoms.setText(rs.getString(6));
			comments.setText(rs.getString(7));
			prescription.setText(rs.getString(8));
		}
		rs.close();
	}

	public void save(ActionEvent event) throws SQLException {

		String sql = "update patient_medical_info set comments='" + comments.getText() +"',"
				+" prescription='" + prescription.getText() +"' "
						+ ", name='" + doctorName.getText() +"'"
						+ " where patientName='" + patientName.getText() +"'";
						
		st.executeUpdate(sql);
		JOptionPane.showMessageDialog(null, "Details updated successfully!!");
		st.close();
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

	public void logout(ActionEvent event) {
		try {
			Node node = (Node) event.getSource();
			Stage stage = (Stage) node.getScene().getWindow();

			Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/abcd/doctor_nurse_login.fxml")));
			stage.setScene(scene);
			stage.show();

		} catch (IOException ex) {
			System.err.println(ex.getMessage());
		}
	}

	public void viewMedical(ActionEvent event) throws SQLException {
		try {
			Node node = (Node) event.getSource();
			Stage stage = (Stage) node.getScene().getWindow();

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/abcd/view_medicalhistory.fxml"));
			Parent root = loader.load();

			actionViewMedical nurseVitals = loader.getController();
			nurseVitals.setPatName(patientName.getText());
			nurseVitals.setDocName(doctorName.getText());

			stage.setScene(new Scene(root));
			stage.show();

		} catch (IOException ex) {
			System.err.println(ex.getMessage());
		}
	}
	
	public void message(ActionEvent event) throws SQLException {
		try {
			Node node = (Node) event.getSource();
			Stage stage = (Stage) node.getScene().getWindow();

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/abcd/message.fxml"));
			Parent root = loader.load();

			actionMessage nurseVitals = loader.getController();
			nurseVitals.setPatName(patientName.getText());
			nurseVitals.setDocName(doctorName.getText());

			stage.setScene(new Scene(root));
			stage.show();

		} catch (IOException ex) {
			System.err.println(ex.getMessage());
		}
	}

	public void setName(String docNurseInfo) {
		doctorName.setText(docNurseInfo);
		
	}

}
