package application;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.scene.control.TextField;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import backend.fileEncrypt;
import javafx.event.ActionEvent;
import javafx.scene.control.RadioButton;
import javafx.scene.control.PasswordField;

public class fileGUIController implements Initializable {
	@FXML
	private Button btnfc;
	@FXML
	private PasswordField pw;
	@FXML
	private Button fbtn;
	@FXML
	private RadioButton encryptButton;
	@FXML
	private ToggleGroup group1;
	@FXML
	private RadioButton decryptButton;
	@FXML
	private TextField test;
	@FXML
	private TextField dirLocation;
	@FXML
	private JFXButton dirButton;
	
	public void browse(ActionEvent event){
		FileChooser fileChooser = new FileChooser();
		File selectedFile = fileChooser.showOpenDialog(btnfc.getScene().getWindow());
		if(selectedFile!=null) {
			test.setText(selectedFile.getAbsolutePath());
		}

	}
	public void setDir(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		File selectedFile = fileChooser.showOpenDialog(btnfc.getScene().getWindow());
		if(selectedFile!=null) {
			dirLocation.setText(selectedFile.getAbsolutePath());
		}
	}
	public void submit(ActionEvent event) {
		if(!test.getText().isEmpty() && !pw.getText().isEmpty()) {
			if(encryptButton.isSelected()) {
				if(!dirLocation.getText().isEmpty()) {
					encrypt(dirLocation.getText());
					getMessage("Encryption complete. File can be found in source folder");
				}
				else {
					encrypt();
					getMessage("Encryption complete. File can be found in source folder");
				}
				
			}
			else if(decryptButton.isSelected()) {
				if(test.getText().substring(test.getText().length()-4).equals(".enc")){
					if(!dirLocation.getText().isEmpty()) {
						decrypt(dirLocation.getText());
						getMessage("Decryption complete. File can be found in source folder");
					}
					else {
						decrypt();
						getMessage("Decryption complete. File can be found in source folder");
					}
					
				}
				else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Warning");
					alert.setHeaderText("Information Dialog");
					alert.setContentText("Please select a valid encryption file");
					alert.showAndWait();
				}
			}
			else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Warning");
				alert.setHeaderText("Information Dialog");
				alert.setContentText("Please select an option to encrypt or decrypt");
				alert.showAndWait();
			}
		}
		else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Error");
			alert.setHeaderText("Information Dialog");
			alert.setContentText("Please enter a password and choose a file to encrypt");

			alert.showAndWait();
		}
	}
	public void encrypt(){
		try {
			 File fileMan = new File(test.getText());
			 fileEncrypt e=new fileEncrypt();
			 e.initEnc(fileMan.getPath(),pw.getText(), fileMan);
		} 
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public void encrypt(String dir){
		try {
			 File fileMan = new File(test.getText());
			 fileEncrypt e=new fileEncrypt();
			 e.initEnc(fileMan.getPath(),dir,pw.getText(), fileMan);
		} 
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public void decrypt() {
		try {
			 File fileMan = new File(test.getText());
			 fileEncrypt e=new fileEncrypt();
			 e.initDec(fileMan.getPath(),pw.getText(), fileMan);
		} 
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public void decrypt(String dir) {
		try {
			 File fileMan = new File(test.getText());
			 fileEncrypt e=new fileEncrypt();
			 e.initDec(fileMan.getPath(),dir,pw.getText(), fileMan);
		} 
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public void getMessage(String msg) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Warning");
		alert.setHeaderText("Information Dialog");
		alert.setContentText(msg);
		alert.showAndWait();
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		test.setEditable(false);
		dirLocation.setEditable(false);
	}


}
