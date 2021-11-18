package application;

import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class actionNurseVitals {

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
	Label patientName = new Label();
	
	@FXML
	Label nurseName = new Label();
	
	Statement st = null;

	ResultSet rs = null;

	public DBConnection frm = new DBConnection();
	
	 public void setLabelText(String text) throws SQLException{
	  patientName.setText(text);
		 
	  st = frm.con.createStatement();
	  String firstSql = "Select weight,height,temp,bp,allergy,symptoms from patient_medical_info where  patientName='" + patientName.getText() + "'";
		rs = st.executeQuery(firstSql);
		if (rs.next()) {
			weight.setText(String.valueOf(rs.getInt(1)));
			height.setText(String.valueOf(rs.getInt(2)));
			bodyTemp.setText(String.valueOf(rs.getInt(3)));
			bp.setText(String.valueOf(rs.getInt(4)));
			allergy.setText(rs.getString(5));
			symptoms.setText(rs.getString(6));
		}
		rs.close();
	  }
	
	public void save(ActionEvent event) throws SQLException {
		
		String name[] = patientName.getText().split(" ");
		Date dbDate =null;
		st = frm.con.createStatement();
		  String firstSql = "Select dob from patient_info where  firstname='" + name[0] + "'"
		  		+ " and lastname='" + name[1] +"'";
			rs = st.executeQuery(firstSql);
			if (rs.next()) {
				dbDate = rs.getDate(1);
			}
		Date todayDate = new Date(System.currentTimeMillis());
		
		LocalDate dobDate = dbDate.toLocalDate();
		LocalDate toDate = todayDate.toLocalDate();
		long diff = java.time.temporal.ChronoUnit.YEARS.between( dobDate , toDate );
		
		if(diff > 12) {
			try {
				Float.parseFloat(bp.getText());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Blood-Pressure is mandatory above 12 and it is not valid!!");
				return;
			}
		}
		
		if(!validateInputRecords()) {
			JOptionPane.showMessageDialog(null, "Either of fields weight, height, temp not valid!!");
			return;
		}
		
		int count = 0;
		firstSql = "Select count(1) as count from patient_medical_info where patientName='" + patientName.getText() + "'";
		rs = st.executeQuery(firstSql);
		if (rs.next()) {
			count = rs.getInt(1);
		}
		if (count != 0) {
			String sql = "update patient_medical_info set "
					+ "patientName =?,weight=?,height=?,temp=?,bp=?,allergy=?,symptoms=?,name=? "
					+ " where patientName='" + patientName.getText() + "'";
			PreparedStatement ps = frm.con.prepareStatement(sql);
			ps.setString(1, patientName.getText());
			ps.setInt(2, Integer.parseInt(weight.getText()));
			ps.setInt(3, Integer.parseInt(height.getText()));
			ps.setFloat(4, Integer.parseInt(bodyTemp.getText()));
			ps.setInt(5, bp.getText().equals("")? 0 : Integer.parseInt(bp.getText()));
			ps.setString(6, allergy.getText());
			ps.setString(7, symptoms.getText());
			ps.setString(8, nurseName.getText());
			ps.execute();
		} else {
			String sql = "Insert into patient_medical_info"
					+ "(patientName,weight,height,temp,bp,allergy,symptoms,name)" + " values(?,?,?,?,?,?,?,?)";
			PreparedStatement ps = frm.con.prepareStatement(sql);
			ps.setString(1, patientName.getText());
			ps.setInt(2, Integer.parseInt(weight.getText()));
			ps.setInt(3, Integer.parseInt(height.getText()));
			ps.setFloat(4, Integer.parseInt(bodyTemp.getText()));
			ps.setInt(5, bp.getText().equals("")? 0 : Integer.parseInt(bp.getText()));
			ps.setString(6, allergy.getText());
			ps.setString(7, symptoms.getText());
			ps.setString(8, nurseName.getText());
			ps.execute();
		}
		JOptionPane.showMessageDialog(null, "Details saved successfully!!");

	}

	private boolean validateInputRecords() {
		try {
			Float.parseFloat(weight.getText());
		} catch (Exception e) {
			return false;
		}
		try {
			Float.parseFloat(height.getText());
		} catch (Exception e) {
			return false;
		}
		try {
			Float.parseFloat(bodyTemp.getText());
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
	
	public void message(ActionEvent event) throws SQLException {
		try {
			Node node = (Node) event.getSource();
			Stage stage = (Stage) node.getScene().getWindow();

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/abcd/message.fxml"));
			Parent root = loader.load();

			actionMessage nurseVitals = loader.getController();
			nurseVitals.setPatName(patientName.getText());
			nurseVitals.setNurseName(nurseName.getText());

			stage.setScene(new Scene(root));
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
			nurseVitals.setNurseName(nurseName.getText());

			stage.setScene(new Scene(root));
			stage.show();

		} catch (IOException ex) {
			System.err.println(ex.getMessage());
		}
	}

	public void setName(String docNurseInfo) {
		nurseName.setText(docNurseInfo);
		
	}


}
