package backend;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DBTest {
	
	public DBTest() {
		String filename = new String();
		filename="test.db";
        createNewDatabase(filename);
        createNewTable(filename);
        //String c="blsicnaosbcasuou";
        //File file=new File("./h.txt");
        //a.insert(file);
	}
	 private Connection connect(String filename) {
	        // SQLite connection string
	        String url = "jdbc:sqlite:./"+filename;
	        Connection conn = null;
	        try {
	            conn = DriverManager.getConnection(url);
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	        return conn;
    }
	  public static void createNewDatabase(String fileName) {
		  
	        String url = "jdbc:sqlite:./" + fileName;
	 
	        try (Connection conn = DriverManager.getConnection(url)) {
	            if (conn != null) {
	                DatabaseMetaData meta = conn.getMetaData();
	                System.out.println("The driver name is " + meta.getDriverName());
	        System.out.println("A new database has been created.");
	        }
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }
	}
	  
	  public static void createNewTable(String file) {
	        // SQLite connection string
	        String url = "jdbc:sqlite:./"+file;
	        
	        // SQL statement for creating a new table
	        String sql = "CREATE TABLE IF NOT EXISTS storage(\n"
	                + "	  ID INTEGER PRIMARY KEY autoincrement NOT Null,\n"
	                + "	html blob NOT NULL\n"
	                + ");";
	        String sql2 = "CREATE TABLE IF NOT EXISTS user(\n"
	                + "	  ID INTEGER PRIMARY KEY autoincrement NOT Null,\n"
	                + "	html blob NOT NULL\n"
	                + ");";
	        
	        try (Connection conn = DriverManager.getConnection(url);
	                Statement stmt = conn.createStatement()) {
	            // create a new table
	            stmt.execute(sql);
	            stmt.execute(sql2);
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	    }
	  
	  public void insert(File stuff) throws FileNotFoundException {
	        String sql = "INSERT INTO abc (html) VALUES(?)";
	        System.out.println("pre-test");
	        try (Connection conn = this.connect("test.db");
	        	PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        	System.out.println("post-test");
	        	FileInputStream input = new FileInputStream(stuff);
	        	 
	        	// set parameters
	        	System.out.println("inputstream check");
	        	//pstmt.setInt(1, 1);
	        	System.out.println("int check");
	        	pstmt.setBinaryStream(1, input, (int) stuff.length());
	            System.out.println("Reading file " + stuff.getAbsolutePath());
	            System.out.println("Store file in the database.");
	            pstmt.executeUpdate();
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	    }
	 
/*
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		DBTest a=new DBTest();
		String filename = new String();
		filename="test.db";
        a.createNewDatabase(filename);
        a.createNewTable(filename, "abc");
        String c="blsicnaosbcasuou";
        File file=new File("./h.txt");
       a.insert(file);
     
       
	}
*/

}
