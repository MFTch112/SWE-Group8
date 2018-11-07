package server;

import javax.swing.JFileChooser;
import javax.swing.UIManager;

public class fileChooserEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
	   try {
	        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	    }catch(Exception ex) {
	        ex.printStackTrace();
	    }
	
		
		JFileChooser fileChooser= new JFileChooser();
		int returnValue= fileChooser.showOpenDialog(null);
		if(returnValue==JFileChooser.APPROVE_OPTION) {
			System.out.println("test");
			System.out.println(fileChooser.getSelectedFile().getPath());
		}
	}
	
}	

