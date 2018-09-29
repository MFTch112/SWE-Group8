package guiStuff;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GUI_Test extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	@Override
	
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("Hello There");
		Button button =  new Button();
		button.setText("Click Me");
		StackPane layout = new StackPane();
		layout.getChildren().add(button);
		Scene basicScene = new Scene(layout, 300, 250);
		primaryStage.setScene(basicScene);
		primaryStage.show();
		
	}


}
