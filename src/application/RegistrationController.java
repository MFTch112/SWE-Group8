package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import backend.DBTest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class RegistrationController implements Initializable{
	
	@FXML private JFXComboBox<String> states;
	@FXML private JFXComboBox<String> countries;
	@FXML private JFXTextField email;
	@FXML private JFXTextField zipcode;
	@FXML private JFXPasswordField password;
	@FXML private JFXPasswordField passwordConf;
	@FXML private JFXButton resetButton;
	@FXML private JFXButton submitButton;
	
	public void resetButtonPushed(){
			
	}
	public void submitPushed(ActionEvent event) throws IOException {
		ArrayList<String> arr = new ArrayList<>();
		arr.add(email.getText());
		arr.add(password.getText());
		arr.add(countries.getSelectionModel().getSelectedItem());
		arr.add(states.getSelectionModel().getSelectedItem());
		arr.add(zipcode.getText());
		DBTest a = new DBTest();
		a.insertUser(arr);
		Parent registParent = FXMLLoader.load(getClass().getResource("Sample.fxml"));
		Scene registScene =new Scene(registParent);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(registScene);
		window.show();	
	}

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		states.getItems().add("GA");
		countries.getItems().add("United States");
		
	}

}
