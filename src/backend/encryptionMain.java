package backend;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.FileWriter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;


public class encryptionMain {
	
	public static Document getWebpage(String URL) throws IOException {
		//Gets the webpage and stores in the database.
		
		
		//Attempts to pull the website into a document
        Document doc = Jsoup.connect(URL).timeout(5000).get();
        // webpageEncode(doc, key);
        //Uncomment above to encode into webpage
        
        //Add future functionality to store into the database
        
        return doc;
    }
	
	//Key actually refers to the encrypted message
	public static void encryptWebpage(Document webpage, ArrayList<Integer> key, Stage primaryStage) {
		Elements text = webpage.select("p");
		
		//A counter to count the index of the key to prepend to the individual <p> tags
		int keyCounter = 0;
		int webpageCounter = 0;
		
		int keyLength = key.size();
		int pageLength = text.size();
		
		
				
			
		//For all the elements in the webpage, this will add to the lines.
		while(keyCounter < keyLength) {
			
			
			Element currentLine = text.get(webpageCounter);
			
			if(webpageCounter == pageLength) {
				webpageCounter = 0;
				
			}
			
			
			//Get the code, and then convert to string and prepend to the <p> lines
			String keyCode = (key.get(keyCounter)).toString();
			currentLine.prepend("^%" + keyCode + "^%");
			
			//Increment the counter
			webpageCounter++;
			keyCounter++;
		
		}
		//System.out.print(text);
		String convertedWebpage = webpage.toString();
		
		try{
			Files.write(Paths.get("./Encrypted Webpage.html"), convertedWebpage.getBytes());
		}catch (IOException e) {
            e.printStackTrace();
		}
		
	}
	
	
	//This is for Images
public String encryptionKey1 (String input, String imagePath, Stage primaryStage){
	//creates a new QuantumRandom, which will parse the ANU website and provide a true random integer
	QuantumRandom qrandom = new QuantumRandom(5000);
	ArrayList<Integer> key = new ArrayList<Integer>();
	ArrayList<Integer> encryptedMessage = new ArrayList<Integer>();
	
	//Convert the input to a charArray to iterate over
	char[] inputArray = input.toCharArray();
	
	for( char x : inputArray) {
		int quantumDigit = 0;
		
		try{
			quantumDigit = qrandom.nextInt(50);
		}catch (IOException e) {
            e.printStackTrace();
        }
		
		//Adds the random integer to the char code
		int charCode = x;
		int encryptedChar = charCode + quantumDigit;
		
		//Append to the key
		key.add(quantumDigit);
		encryptedMessage.add(encryptedChar);
			
	}
	
	StringBuilder strbul  = new StringBuilder();
     Iterator<Integer> iter = key.iterator();
     while(iter.hasNext())
     {
         strbul.append(iter.next());
        if(iter.hasNext()){
         strbul.append(",");
        }
     }
    String output = strbul.toString();
	
	imageEncrypt(encryptedMessage, imagePath, primaryStage);
    System.out.println(output);
	return output;
	
}

	
	//This is for webpages
public String encryptionKey2 (String input, String urlPath, Stage primaryStage){
		
		//creates a new QuantumRandom, which will parse the ANU website and provide a true random integer
		QuantumRandom qrandom = new QuantumRandom(5000);
		ArrayList<Integer> key = new ArrayList<Integer>();
		ArrayList<Integer> encryptedMessage = new ArrayList<Integer>();
		
		//Convert the input to a charArray to iterate over
		char[] inputArray = input.toCharArray();
		
		for( char x : inputArray) {
			int quantumDigit = 0;
			
			try{
				quantumDigit = qrandom.nextInt(50);
			}catch (IOException e) {
	            e.printStackTrace();
	        }
			
			//Adds the random integer to the char code
			int charCode = x;
			int encryptedChar = charCode + quantumDigit;
			
			//Append to the key
			key.add(quantumDigit);
			encryptedMessage.add(encryptedChar);
				
		}
		System.out.println(key);
		
		StringBuilder strbul  = new StringBuilder();
	     Iterator<Integer> iter = key.iterator();
	     while(iter.hasNext())
	     {
	         strbul.append(iter.next());
	        if(iter.hasNext()){
	         strbul.append(",");
	        }
	     }
	     
	    String output = strbul.toString();
	    //System.out.println(output);
		try{
			Document webpage = getWebpage(urlPath);
			encryptWebpage(webpage, encryptedMessage, primaryStage);
						
			
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		    
		return output;
		
	}

	//in order to decrypt, go through all the lines up to "^%", and store the value in an arraylist
	//Then subtract all values from the key
	//Convert from charcode into the char and then toString the whole array






	
public String decryptWebpage(String fileLocation, String key) {
	
	
	//Opens a file dialogue to choose the html file, maybe should have a database option.
	//JFileChooser fileChooser = new JFileChooser();
	//if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
	File sourceHtml = new File(fileLocation);
	
	//Scanner scan = new Scanner(System.in);
	
    ArrayList<Integer> decryptionKey = new ArrayList<Integer>();
	String[] keyStringArray = (key.split(","));
	for(String j: keyStringArray) {
		
		int number = Integer.parseInt(j);
		decryptionKey.add(number);
	}
    
    //System.out.print("Enter Decryption Key ");
    //System.out.println("(EOF or non-integer to terminate): ");

    //while(scan.hasNextInt()){
    	//decryptionKey.add(scan.nextInt());
    //}

   	     
	
	try{
	
		Document doc = Jsoup.parse(sourceHtml, "UTF-8");
		String rawText = doc.select("p").text();
		String[] text = rawText.split(Pattern.quote("^%"));
					
		ArrayList<Integer> encodedMessage = new ArrayList<Integer>();
		String outputMessage = "";
		//System.out.println(rawText);
		
		
//add functionality for really long messages		
		
		
			
			for(String line: text) {

				//String currentLine = line.toString();
				//String cleanString = currentLine.replaceAll("<p>", "");
				//String cleanestString = cleanString.replaceAll("</p>", "");
				//String[] splitString = cleanestString.split("^%");
				
				
				
				if((line.length()) == 3) {
				//System.out.println(line);
				int encodedChar = Integer.parseInt(line);
				encodedMessage.add(encodedChar);
				//System.out.println(line);
				}
				
						
		}
		int keyCounter = 0;
		
		
			
		for(int encryptedInt: encodedMessage) {
			
			int decodedCharCode = encryptedInt - decryptionKey.get(keyCounter);
			char decodedChar = (char)decodedCharCode;
			//System.out.println(decodedCharCode);
			outputMessage += decodedChar;
			keyCounter++;
		}
		
		
		//System.out.println("Encrypted Message:");
		//System.out.println(outputMessage);
		
		return outputMessage;
		
		
		
	}catch (IOException e) {
        e.printStackTrace();
    }	
	
	return "There has been an error.";
	}

	
	
public void imageEncrypt(ArrayList<Integer> encryptedMessage, String imagePath, Stage primaryStage) {
		
		//JFileChooser fileChooser = new JFileChooser();
		//if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
		//File img = fileChooser.getSelectedFile();
	
		System.out.println(encryptedMessage);
		try{
			BufferedImage input = ImageIO.read(new File(imagePath));			
			
			//BufferedImage newImage = new BufferedImage(input.getWidth(), input.getHeight(), BufferedImage.TYPE_INT_ARGB);
			
			
			int imageWidth = input.getWidth();
			int pixelCounter = 0;
			int rowCounter = 0;
			
			
			for( int encodedInt : encryptedMessage) {
				if(pixelCounter > imageWidth) {
					pixelCounter = 0;
					rowCounter++;
				}
				
				
				int p = input.getRGB(pixelCounter, rowCounter);
				
			    
			    int a = (p>>24) & 0xff;
			    int r = encodedInt;
			    int g = (p>>8) & 0xff;
			    int b = p & 0xff;
			    
			    p = (a<<24) | (r<<16) | (g<<8) | b;
				
				input.setRGB(pixelCounter, rowCounter, p);
				
				
				//Checked value for debugging purposes
				//int check = input.getRGB(pixelCounter, rowCounter);
				//System.out.println(check);
				pixelCounter += 3;
				
				
			}
			

			ImageIO.write(input, "png", new File("Encrypted Image.png"));
			


			FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save Image");
            
            //Image output4Dialog = new Image(getClass().getResourceAsStream("Encrypted Image.png"));
            FileInputStream is = new FileInputStream("Encrypted Image.png");
            Image output4Dialog = new Image(is);
            File file = fileChooser.showSaveDialog(primaryStage);
            if (file != null) {
                try {
                    ImageIO.write(SwingFXUtils.fromFXImage(output4Dialog,
                        null), "png", file);
                } 
                catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            

            }
				
		}
		catch(IOException e) { 
			 e.printStackTrace();
		}
			
	}
	
	
	
	public String imageDecrypt(String fileLocation, String key) {
		
		//JFileChooser fileChooser = new JFileChooser();
		//if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
		//File img = fileChooser.getSelectedFile();
		
		
		//Scanner scan = new Scanner(System.in);
		
	    ArrayList<Integer> decryptionKey = new ArrayList<Integer>();
	    
	    //System.out.println("Enter Decryption Key ");
	    //System.out.println("(EOF or non-integer to terminate): ");

	    //while(scan.hasNextInt()){
	    //	decryptionKey.add(scan.nextInt());
	    //}
	    

		String[] keyStringArray = (key.split(","));
		for(String j: keyStringArray) {
			int number = Integer.parseInt(j);
			decryptionKey.add(number);
		}
		
		
		System.out.println(decryptionKey);
	    
	    String outputMessage = "";
	    
		
		try{
			BufferedImage input = ImageIO.read(new File(fileLocation));			
			//System.out.print("Successful Image Open");
					
			int imageWidth = input.getWidth();
			int pixelCounter = 0;
			int rowCounter = 0;
			
			
			for( int encodedInt : decryptionKey) {
				if(pixelCounter > imageWidth) {
					pixelCounter = 0;
					rowCounter++;
				}
				
				int encodedPixel = input.getRGB(pixelCounter, rowCounter);
				int alphaValue = (encodedPixel>>16) & 0xff;
				int decodedPixel = alphaValue - encodedInt;

				System.out.println(decodedPixel);
				char decodedChar = (char)decodedPixel;
				outputMessage += decodedChar;
				pixelCounter += 3;
				
			}

			
			//System.out.println("Encrypted Message:");
			//System.out.println(outputMessage);
			
			System.out.println(outputMessage);
			return outputMessage;
		
			
			
		}catch(IOException e) { 
			 e.printStackTrace();
		}
		
		return "There has been an error.";
		}
	
	
	
	
/*
    public static void main(String[] args) {

    	//ArrayList<Integer> key = encryptionKey2("hello", "https://stackoverflow.com/questions/22715086/scheduling-python-script-to-run-every-hour-accurately");
    	
    	//imageEncrypt(key);
    	//imageDecrypt();
    	
    	
    	
        //try{
    		//Document webpage = getWebpage("https://stackoverflow.com/questions/22715086/scheduling-python-script-to-run-every-hour-accurately", key);
    		//encryptWebpage(webpage, key);
    		
    		//decryptWebpage();
    		
    		
        //} catch (IOException e) {
            //e.printStackTrace();
        //}
        
       
    }
    */
}
