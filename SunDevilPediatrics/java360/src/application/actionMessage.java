package application;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class actionMessage {
	
	private static final String PATIENT = "PATIENT";
	private static final String DOCTOR = "Doctor";
	private static final String NURSE = "Nurse";

	public class Message {
		private StringProperty messageListCell;
		private StringProperty sendByCell;
		
		public Message(String messageListCell, String sendByCell) {
			this.messageListCell = new SimpleStringProperty(messageListCell);
			this.sendByCell = new SimpleStringProperty(sendByCell);
		}

		public String getMessageListCell() {
			return messageListCell.get();
		}

		public void setMessageListCell(String messageListCell) {
			this.messageListCell.set(messageListCell);
			;
		}
		
		public String getSendByCell() {
			return sendByCell.get();
		}

		public void setSendByCell(String sendByCell) {
			this.sendByCell.set(sendByCell);
			;
		}
	}

	@FXML
	TableView<Message> table = new TableView<>();
	@FXML
	TableColumn<Message,String> messageList = new TableColumn<>();
	@FXML
	TableColumn<Message,String> sendBy = new TableColumn<>();
	
	@FXML
	Label patientName = new Label();
	
	@FXML
	Label name = new Label();
	
	@FXML
	Label selectRole = new Label();
	
	@FXML
	Label selectDocNurName = new Label();
	
	@FXML
	TextArea messageArea = new TextArea();
	
	String email = "";
	
	String role= "";
	public DBConnection frm = new DBConnection();	
	Statement st = null;

	ResultSet rs = null;
	
	@FXML
	ChoiceBox<String> roles = new ChoiceBox<>();
	
	@FXML
	ChoiceBox<String> docNurse = new ChoiceBox<>();

	ObservableList<Message> data = FXCollections.observableArrayList();

	@FXML
	public void initialize() throws SQLException {

		String[] role = { DOCTOR, NURSE };
		roles.getItems().addAll(role);
		roles.getSelectionModel().selectedIndexProperty().addListener(
				(ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
					docNurse.valueProperty().set(null);
					if (new_val.intValue() == 0) {
						try {
							st = frm.con.createStatement();
							String firstSql = "Select first_name, last_name from doctorinfo";
							rs = st.executeQuery(firstSql);
							while (rs.next()) {
								docNurse.getItems().add(rs.getString(1) + " " + rs.getString(2));
							}
						} catch (SQLException e) {
						}

					} else {
						try {
							st = frm.con.createStatement();
							String firstSql = "Select first_name, last_name from nurseinfo";
							rs = st.executeQuery(firstSql);
							while (rs.next()) {
								docNurse.getItems().add(rs.getString(1) + " " + rs.getString(2));
							}
						} catch (SQLException e) {
						}
					}

				});
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
			
			if(role.equals(DOCTOR)) {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/abcd/doctorfunction.fxml"));
				Parent root = loader.load();

				actionDoctorFucntion nurseVitals = loader.getController();
				nurseVitals.setLabelText(patientName.getText());
				nurseVitals.setName(name.getText());

				stage.setScene(new Scene(root));
				stage.show();
			}else if(role.equals(NURSE)){
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/abcd/nursevitals.fxml"));
				Parent root = loader.load();

				actionNurseVitals nurseVitals = loader.getController();
				nurseVitals.setLabelText(patientName.getText());
				nurseVitals.setName(name.getText());

				stage.setScene(new Scene(root));
				stage.show();
			}else {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/abcd/view_medicalhistory.fxml"));
				Parent root = loader.load();
				actionViewMedical nurseVitals = loader.getController();
				nurseVitals.setPatName(patientName.getText());

				stage.setScene(new Scene(root));
				stage.show();
			}

		} catch (IOException ex) {
			System.err.println(ex.getMessage());
		}
	}

	public void send(ActionEvent event) throws SQLException {
		
		if(messageArea.getText().equals("") || (role.equals("") && roles.getSelectionModel().getSelectedItem() == null)) {
			JOptionPane.showMessageDialog(null, "Please select details to send!!");
			return;
		}
		
		st = frm.con.createStatement();
		
		if(role.equals("") && docNurse.getSelectionModel().getSelectedItem() == null) {
			JOptionPane.showMessageDialog(null, "Please select Doctor or Nurse name!!");
			return;
		}
		
		String sql = "Insert into patient_message" + "(message,pname,role,rname)" + " values(?,?,?,?)";
		PreparedStatement ps = frm.con.prepareStatement(sql);
		ps.setString(1, messageArea.getText());
		ps.setString(2, patientName.getText());
		
		if (role.equals("")) {
			ps.setString(3, PATIENT);
			ps.setString(4, docNurse.getSelectionModel().getSelectedItem());
		} else if (role.equals(DOCTOR)){
			ps.setString(3, DOCTOR);
			ps.setString(4, this.name.getText());
		}
		else if (role.equals(NURSE)) {
			ps.setString(3, NURSE);
			ps.setString(4, this.name.getText());
		}
		
		ps.execute();
		JOptionPane.showMessageDialog(null, "Message send successfully!!");
		
	}

	public void logout(ActionEvent event) {
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

	public void setNurseName(String nurseName) throws SQLException {
		role = NURSE;
		
		if(this.role.equals(DOCTOR) || this.role.equals(NURSE)) {
			roles.setVisible(false);
			docNurse.setVisible(false);
			selectDocNurName.setVisible(false);
			selectRole.setVisible(false);
		}
		
		this.name.setText(nurseName);
		
		table.setItems(null);
		st = frm.con.createStatement();

		String firstSql = "Select message, pname from patient_message where  pname='" + patientName.getText() +"'"
				+ " and role='"+ PATIENT +"' and rname='" + this.name.getText() +"'";

		rs = st.executeQuery(firstSql);
		while (rs.next()) {
			data.add(new Message(rs.getString(1), rs.getString(2)));
		}
		rs.close();
		st.close();
		table.setItems(data);
		messageList.setCellValueFactory(new PropertyValueFactory<Message, String>("messageListCell"));
		sendBy.setCellValueFactory(new PropertyValueFactory<Message, String>("sendByCell"));
		
	}

	public void setDocName(String docName) throws SQLException {
		role = DOCTOR;
		
		if(this.role.equals(DOCTOR) || this.role.equals(NURSE)) {
			roles.setVisible(false);
			docNurse.setVisible(false);
			selectDocNurName.setVisible(false);
			selectRole.setVisible(false);
		}
		
		this.name.setText(docName);
		table.setItems(null);
		st = frm.con.createStatement();

		String firstSql = "Select message, pname from patient_message where  pname='" + patientName.getText() +"'"
				+ " and role='"+ PATIENT +"' and rname='" + this.name.getText() +"'";
		
		rs = st.executeQuery(firstSql);
		while (rs.next()) {
			data.add(new Message(rs.getString(1), rs.getString(2)));
		}
		rs.close();
		st.close();
		table.setItems(data);
		messageList.setCellValueFactory(new PropertyValueFactory<Message, String>("messageListCell"));
		sendBy.setCellValueFactory(new PropertyValueFactory<Message, String>("sendByCell"));
		
	}
	
	public void setPatName(String patName)  {
		patientName.setText(patName);
	}

	public void setPatNameFromUser(String patName) throws SQLException {
		patientName.setText(patName);

		table.setItems(null);
		st = frm.con.createStatement();

		String firstSql = "Select message, rname from patient_message where  pname='" + patientName.getText() +"'"
				+ " and (role='"+ DOCTOR +"' or role='" + NURSE +"')";
		rs = st.executeQuery(firstSql);
		while (rs.next()) {
			data.add(new Message(rs.getString(1), rs.getString(2)));
		}
		rs.close();
		st.close();
		table.setItems(data);
		messageList.setCellValueFactory(new PropertyValueFactory<Message, String>("messageListCell"));
		sendBy.setCellValueFactory(new PropertyValueFactory<Message, String>("sendByCell"));
	}

}
