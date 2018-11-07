package client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
				/*
				Parent root = FXMLLoader.load(getClass().getResource("./clientWindow.fxml"));
		        Scene scene = new Scene(root);
		        primaryStage.setScene(scene);
		        primaryStage.setTitle("Chat Window");
		        primaryStage.show();
		        */
		    	FXMLLoader loader = new FXMLLoader(getClass().getResource("./clientWindow.fxml"));
		        Parent root = loader.load();
		        Scene scene = new Scene(root);
		        primaryStage.setScene(scene);
		        primaryStage.show();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	
	}

	public static void main(String[] args) {
		launch(args);
	}
}
