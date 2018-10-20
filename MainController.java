package application;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.stage.FileChooser;
/**
 *
 * @author chima
 */
public class MainController implements Initializable {
    
    @FXML
    private ListView listview;
    private ListView fpath;
    
    @FXML
    private Button btnfc;
    
    
    
    
    
   
    //file chooser method
    public void Button1Action(ActionEvent event) throws InterruptedException {
        FileChooser fc = new FileChooser();
        File seletedFile = fc.showOpenDialog(null);
            if (seletedFile != null) {
          
            Thread t1= new Thread () {
            	public void run() {
            
            	listview.getItems().add(seletedFile.getName());
            	}};
            	t1.start();
         //location need to be fixed
            	/*  	Thread t2 = new Thread() {
            		
            		public void run() {
            		
            			fpath.getItems().add(seletedFile.getAbsolutePath());
            		}};
            		t1.join();
           */
            	 }else {
                System.out.println("File is not valid");
            }           
                
    }   
    //password field
    String password = "test";
    @FXML
    private PasswordField pw;
    @FXML
    private Button pbtn;
    
    //validates the password 
    @FXML
    private void validate(ActionEvent event) {
    String Password=pw.getText();
    if(password.isEmpty()) {
    	Alert alert = new Alert(AlertType.ERROR);
    	alert.setContentText("Incorrect Password");
    	alert.showAndWait();
    }else {
    	if(password.equals(pw)) {
    		Alert alert = new Alert(AlertType.INFORMATION);
        	alert.setHeaderText(null);
    		alert.setContentText("Success");
        	alert.showAndWait();
    	}else {
     
            		Alert alert = new Alert(AlertType.INFORMATION);
                	alert.setHeaderText(null);
            		alert.setContentText("Incorrect");
                	alert.showAndWait();
    	}
                 
    
   
   
    }
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}}