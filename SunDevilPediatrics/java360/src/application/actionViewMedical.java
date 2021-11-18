package application;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class actionViewMedical {
	
	public class Patient {
		private StringProperty commentsCell;
		private StringProperty prescriptionCell;
		private StringProperty allergyCell;
		private StringProperty symptomsCell;
		private StringProperty docCell;
		
		
		
		public Patient(String commentsCell, String prescriptionCell, String allergyCell,
				String symptomsCell, String docCell) {
			this.commentsCell = new SimpleStringProperty(commentsCell);
			this.prescriptionCell = new SimpleStringProperty(prescriptionCell);
			this.allergyCell = new SimpleStringProperty(allergyCell);
			this.symptomsCell = new SimpleStringProperty(symptomsCell);
			this.docCell = new SimpleStringProperty(docCell);
		}

		public String getCommentsCell() {
			return commentsCell.get();
		}

		public void setCommentsCell(String commentsCell) {
			this.commentsCell.set(commentsCell);
			;
		}

		public String getPrescriptionCell() {
			return prescriptionCell.get();
		}

		public void setPrescriptionCell(String prescriptionCell) {
			this.prescriptionCell.set(prescriptionCell);
		}

		public String getAllergyCell() {
			return allergyCell.get();
		}

		public void setAllergyCell(String allergyCell) {
			this.allergyCell.set(allergyCell);
		}

		public String getSymptomsCell() {
			return symptomsCell.get();
		}

		public void setSymptomsCell(String symptomsCell) {
			this.symptomsCell.set(symptomsCell);
		}
		
		public String getDocCell() {
			return docCell.get();
		}

		public void setDocCell(String docCell) {
			this.docCell.set(docCell);
		}
		
		
		
	}

	@FXML
	TableView<Patient> table = new TableView<>();
	@FXML
	TableColumn<Patient,String> comments = new TableColumn<>();
	@FXML
	TableColumn<Patient,String>  prescription = new TableColumn<>();
	@FXML
	TableColumn<Patient,String>  allergy = new TableColumn<>();
	@FXML
	TableColumn<Patient,String>  symptoms = new TableColumn<>();
	@FXML
	TableColumn<Patient,String>  docName = new TableColumn<>();
	
	@FXML
	Label patientName = new Label();
	
	@FXML
	Label name = new Label();
	
	String email = "";
	
	String role= "";
	
	Statement st = null;

	ResultSet rs = null;

	public DBConnection frm = new DBConnection();
	
	ObservableList<Patient> data = FXCollections.observableArrayList();

	@FXML
	public void initialize() throws SQLException {
		
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

	public void back(ActionEvent event) throws SQLException {
		try {
			Node node = (Node) event.getSource();
			Stage stage = (Stage) node.getScene().getWindow();
			if(role.equals("doc")) {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/abcd/doctorfunction.fxml"));
				Parent root = loader.load();

				actionDoctorFucntion nurseVitals = loader.getController();
				nurseVitals.setLabelText(patientName.getText());
				nurseVitals.setName(name.getText());

				stage.setScene(new Scene(root));
				stage.show();
			}else if(role.equals("nurse")){
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/abcd/nursevitals.fxml"));
				Parent root = loader.load();

				actionNurseVitals nurseVitals = loader.getController();
				nurseVitals.setLabelText(patientName.getText());
				nurseVitals.setName(name.getText());

				stage.setScene(new Scene(root));
				stage.show();
			}else {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/abcd/login.fxml"));
				Parent root = loader.load();

				stage.setScene(new Scene(root));
				stage.show();
			}

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
			if(role.equals("doc")) {
				nurseVitals.setPatName(patientName.getText());
				nurseVitals.setDocName(name.getText());
			}else if(role.equals("nurse")) {
				nurseVitals.setPatName(patientName.getText());
				nurseVitals.setNurseName(name.getText());
			}else {
				nurseVitals.setPatNameFromUser(patientName.getText());
			}

			stage.setScene(new Scene(root));
			stage.show();

		} catch (IOException ex) {
			System.err.println(ex.getMessage());
		}
	}

	public void logout(ActionEvent event) {
		try {
			Node node = (Node) event.getSource();
			Stage stage = (Stage) node.getScene().getWindow();
			System.out.println("hello world");

			Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/abcd/login.fxml")));
			stage.setScene(scene);
			stage.show();

		} catch (IOException ex) {
			System.err.println(ex.getMessage());
		}
	}

	public void setNurseName(String nurseName) {
		role ="nurse";
		this.name.setText(nurseName);
		
	}

	public void setDocName(String docName) {
		role ="doc";
		this.name.setText(docName);
		
	}

	public void setPatName(String patName) throws SQLException {
		patientName.setText(patName);
		
		st = frm.con.createStatement();
		
		String firstSql = "Select comments, prescription, allergy,symptoms,name from patient_medical_info where  patientName='" + patientName.getText() + "'";
		rs = st.executeQuery(firstSql);
		if (rs.next()) {
			data.add(new Patient(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5)));
		}
		rs.close();
		st.close();
		table.setItems(data);
		comments.setCellValueFactory(new PropertyValueFactory<Patient, String>("commentsCell"));
		prescription.setCellValueFactory(new PropertyValueFactory<Patient, String>("prescriptionCell"));
		allergy.setCellValueFactory(new PropertyValueFactory<Patient, String>("allergyCell"));
		symptoms.setCellValueFactory(new PropertyValueFactory<Patient, String>("symptomsCell"));
		docName.setCellValueFactory(new PropertyValueFactory<Patient, String>("docCell"));
		
		
	}

}
