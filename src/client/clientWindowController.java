package client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.URL;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class clientWindowController implements Initializable{ 
	@FXML
	private JFXTextArea chatBox1;
	@FXML 
	private JFXButton submit;
	@FXML 
	private TextField inputField;
	private String name;
	private DatagramSocket socket;
	private InetAddress address;
	private int port;
	private boolean running;
	
	
	
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		//Client("localhost", 52868, userId);	
	}
	
	/*
	 * Delete below if not working
	 */
	public void Client(String address, int port, String name) {
		try {
			this.address = InetAddress.getByName(address);
			this.port = port;
			this.name=name;
			socket = new DatagramSocket();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		running=true;
		listen();
		send("\\con:"+name);
		
	}
	public void send(String message) {
		try {
			message += "\\e";
			byte[] data = message.getBytes();
			DatagramPacket packet  = new DatagramPacket(data,  data.length, address, port);
			socket.send(packet);
			System.out.println("Sent message to -client to server-, "+address.getHostAddress()+":"+port);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public void listen() {
		Thread listenThread = new Thread("Client Listener") {
			public void run() {				
				try {	
					
					while(running) {
						byte[] data=new byte[1024];
						DatagramPacket packet= new DatagramPacket(data, data.length);
						socket.receive(packet);
						String message = new String(data);
						message = message.substring(0, message.indexOf("\\e"));
						
						//MANAGE MESSAGE
						if(!isCommand(message, packet)) {
							//broadcast(message);
							consolePrint(message);
							//outerMessage(message);
							System.out.println(message);
						}
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}; listenThread.start();
	}
	private static boolean isCommand(String message, DatagramPacket packet) {
		if(message.startsWith("\\con:")) {
			return true;
		}
		else 
			return false;
	}
	private void outerMessage(String message) throws IOException {
		//FXMLLoader loader = new FXMLLoader(getClass().getResource("clientWindow.fxml"));
		//FXMLLoader loader = new FXMLLoader();
		//loader.setLocation(getClass().getResource("./clientWindow.fxml"));
		//Parent root = loader.load();
		//clientWindowController controller = loader.<clientWindowController>getController();
		//clientWindowController controller = loader.getController();
		//controller.consolePrint(message);
	}
	
	/*
	 * 
	 * Delete above if not working
	 * 
	 * 
	 * 
	 */
	
	public  void consolePrint(String message) {
		//chatBox1.setText(chatBox1.getText()+message+"\n");
		chatBox1.appendText(message+"\n");
	}
	
	
	

}
