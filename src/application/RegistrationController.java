package application;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXComboBox;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class RegistrationController implements Initializable{
	
	@FXML private JFXComboBox<String> states;
	@FXML private JFXComboBox<String> countries;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		states.getItems().add("GA");
		countries.getItems().add("United States");
	}

}
