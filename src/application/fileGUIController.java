package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;

import backend.encryptionMain;
import backend.fileEncrypt;
import client.clientWindowController;
import javafx.event.ActionEvent;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
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
	
	//Encoding Tab
	@FXML
	private Label encURL;
	@FXML
	private JFXButton encIMG;
	@FXML
	private TextField encTextEncodingField;
	@FXML
	private TextField encTextURLField;
	@FXML
	private TextField encTextIMGField;
	@FXML
	private ImageView encIMGPreview;
	@FXML
	private JFXButton encIMGSubmit;
	@FXML
	private JFXButton encURLSubmit;
	@FXML
	private TextArea encKeyText;
	
	//Decoding Tab
	@FXML
	private TextField decKeyEntry;
	@FXML
	private TextField decFileField;
	@FXML
	private JFXButton decFileChooseButton;
	@FXML
	private JFXRadioButton decAsImg;
	@FXML
	private JFXRadioButton decAsHTML;
	@FXML
	private JFXButton decSumissionButton;
	@FXML
	private TextArea decMessage;
	
	//Chat Tab
	@FXML
	private JFXButton clientLaunch;
	@FXML
	private TextField idField;
	
	
	
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
	/*
	 * Encoding Tab
	 * 
	 */
	public void  encodeIMGChooseFile(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		File selectedFile = fileChooser.showOpenDialog(encIMG.getScene().getWindow());
		if(selectedFile!=null) {
			encTextIMGField.setText(selectedFile.getAbsolutePath());
			String fileAsString = selectedFile.toString();
			try{
    			BufferedImage input = ImageIO.read(new File(fileAsString));
    			ImageIO.write(input, "png", new File("tempImage.png"));
            }
	        catch(IOException e) { 
	   			 e.printStackTrace();
	   		}
            
            
            Image asdf = new Image("file:tempImage.png");
            //Image asdf = new Image(getClass().getResourceAsStream("../tempImage.png"));
            //ImageView asdfViewer = new ImageView(asdf);
			//Image testIMG=new Image(getClass().getResourceAsStream("../../tempImage.png"));
            encIMGPreview.setImage(asdf);
            encIMGPreview.setFitWidth(219);
            encIMGPreview.setFitHeight(204);
		}
	}
	public void  encodeURLChooseFile(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		File selectedFile = fileChooser.showOpenDialog(encURL.getScene().getWindow());
		if(selectedFile!=null) {
			encTextURLField.setText(selectedFile.getAbsolutePath());
		}
	}
	public void encodeIMGSubmission(ActionEvent event) {
		if(encTextIMGField.getText().isEmpty()) {
			getMessage("Please enter filepath for your image");
			return;
		}
		try {
			String imagePath = String.valueOf(encTextIMGField.getText());
    		String message = String.valueOf(encTextEncodingField.getText());
            
    		encryptionMain encryptor = new encryptionMain();
    		
    		String keyOutput = encryptor.encryptionKey1(message, imagePath, (Stage) encIMGSubmit.getScene().getWindow());
    		encKeyText.setText("Decryption Key: " + keyOutput);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void encodeURLSubmission(ActionEvent event) {
		String urlPath = String.valueOf(encTextURLField.getText());
		String message = String.valueOf(encTextEncodingField.getText());
		encryptionMain encryptor = new encryptionMain();
		String keyOutput = encryptor.encryptionKey2(message, urlPath, (Stage) encURLSubmit.getScene().getWindow());
		encKeyText.setText("Decryption Key: " + keyOutput);
	}
	
	public void decodeChooseFile(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		File selectedFile = fileChooser.showOpenDialog(decFileField.getScene().getWindow());
		if(selectedFile!=null) {
			decFileField.setText(selectedFile.getAbsolutePath());
		}
	}
	public void decodeFileSubmission() {
		if(decFileField.getText().isEmpty() || decKeyEntry.getText().isEmpty()) {
			getMessage("Please fill out both fields");
			return;
		}
		if(decAsImg.isSelected()) {
			String keyValue = String.valueOf(decKeyEntry.getText());
			String filePath = String.valueOf(decFileField.getText());
			
			encryptionMain decryptor = new encryptionMain();
			
			String keyOutput = decryptor.imageDecrypt(filePath, keyValue);
			decMessage.setText("Decrypted Message: " + keyOutput);
		}
		else if(decAsHTML.isSelected()) {
			String keyValue = String.valueOf(decKeyEntry.getText());
			String filePath = String.valueOf(decFileField.getText());
			
			encryptionMain decryptor = new encryptionMain();
			
			String keyOutput = decryptor.decryptWebpage(filePath, keyValue);
			decMessage.setText("Decrypted Message: " + keyOutput);
		}
		else {
			getMessage("Error, please select one of the decryption options");
		}
	}
	/*
	 * Chat Client and Server
	 * 
	 */
	public void chatLaunch(ActionEvent event) {
        try {
	        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../client/clientWindow.fxml"));
	        Parent root1 = (Parent) fxmlLoader.load();
	        clientWindowController controller = fxmlLoader.<clientWindowController>getController();
	        Stage stage = new Stage();
	        stage.setScene(new Scene(root1));  
	        stage.show();
	        controller.Client("localhost", 52868, idField.getText());
        }	       
        catch (IOException e) {
            e.printStackTrace();
        }
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		test.setEditable(false);
		dirLocation.setEditable(false);
	}


}
