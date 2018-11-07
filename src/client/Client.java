package client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import server.ClientInfo;

public class Client {
	private String name;
	private DatagramSocket socket;
	private InetAddress address;
	private int port;
	private boolean running;
	
	public Client(String address, int port, String name) {
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
							//clientWindowController.consolePrint(message);
							outerMessage(message);
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
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("./clientWindow.fxml"));
		Parent root = loader.load();
		//clientWindowController controller = loader.<clientWindowController>getController();
		clientWindowController controller = loader.getController();
		controller.consolePrint(message);
	}
}
