package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import backend.databaseInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class SampleController implements Initializable {
	
	@FXML private TextField userField;
	@FXML private PasswordField passField;
	@FXML private Button submitButton;
	
	public void registrationPushed(ActionEvent event) throws IOException {
		Parent registParent = FXMLLoader.load(getClass().getResource("Registration.fxml"));
		Scene registScene =new Scene(registParent);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(registScene);
		window.show();		
	}
	public void buttonPushed(ActionEvent event2) throws IOException {
		if(userField.getText().isEmpty()) {
			getMessage("Please enter your username", "Form Error");
		}
		if(passField.getText().isEmpty()) {
			getMessage("Please enter a password", "Form Error");
		}
	    String name = String.valueOf(userField.getText());
        String password = String.valueOf(passField.getText());
        databaseInterface app = new databaseInterface();
        if (app.verify(name, password) == 1){
    		//go to next screen
    		//secondStage(primaryStage);
    		//encryptionStage(primaryStage);
        	Parent registParent = FXMLLoader.load(getClass().getResource("fileGUI.fxml"));
    		Scene registScene =new Scene(registParent);
    		Stage window = (Stage) ((Node) event2.getSource()).getScene().getWindow();
    		window.setScene(registScene);
    		window.show();	
    	}
    	else {
        	getMessage("Login Unsuccessful", "Try Again");
    	}       		
	}
	public void getMessage(String msg, String err) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(err);
		alert.setHeaderText("Information Dialog");
		alert.setContentText(msg);
		alert.showAndWait();
	}
	/*
	public void submitPushed(ActionEvent event) throws IOException {
		Parent registParent = FXMLLoader.load(getClass().getResource("Placeholder.fxml"));
		Scene registScene =new Scene(registParent);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(registScene);
		window.show();
		
	}
	*/
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO Auto-generated method stub
		
	}

}
