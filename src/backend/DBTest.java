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
import java.util.ArrayList;
import java.util.Scanner;

public class DBTest {
	
	public DBTest() {
		String filename = new String();
		filename="SWE_Project.db";
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
	  /*
	  public  void query(String user) {
		  String sql = "Select password WHERE ";
	        //System.out.println("pre-test");
	        try (Connection conn = this.connect("SWE_Project.db");
	        	PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        	// set parameters
	        	//pstmt.setInt(1, 1);
	        	for(int i=0;i<5;i++) {
	        		pstmt.setString(i+1, stuff.get(i));
	        	}
	        	//System.out.println("int check");
	        	//pstmt.setBinaryStream(1, input, (int) stuff.length());
	            //System.out.println("Reading file " + stuff.getAbsolutePath());
	            System.out.println("Store file in the database.");
	            pstmt.executeUpdate();
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	  }
	  */
	  
	  public static void createNewTable(String file) {
	        // SQLite connection string
	        String url = "jdbc:sqlite:./"+file;
	        
	        // SQL statement for creating a new table
	        String sql = "CREATE TABLE storage (\r\n" + 
	        		"    id    INTEGER         PRIMARY KEY AUTOINCREMENT\r\n" + 
	        		"                          NOT NULL\r\n" + 
	        		"                          UNIQUE,\r\n" + 
	        		"    user  VARCHAR (10000) REFERENCES user (email) \r\n" + 
	        		"                          NOT NULL,\r\n" + 
	        		"    html  BLOB (100),\r\n" + 
	        		"    image BLOB (10000) \r\n" + 
	        		");\r\n" + 
	        		"";
	        String sql2 = "CREATE TABLE user (\r\n" + 
	        		"    email    VARCHAR (1000) CONSTRAINT [NOT NULL] PRIMARY KEY\r\n" + 
	        		"                            NOT NULL\r\n" + 
	        		"                            UNIQUE,\r\n" + 
	        		"    password VARCHAR (1000) NOT NULL,\r\n" + 
	        		"    country  VARCHAR (1000) NOT NULL,\r\n" + 
	        		"    state    VARCHAR (2)    NOT NULL,\r\n" + 
	        		"    zip      VARCHAR (5)    NOT NULL\r\n" + 
	        		");";
	        
	        try (Connection conn = DriverManager.getConnection(url);
	                Statement stmt = conn.createStatement()) {
	            // create a new table
	            stmt.execute(sql);
	            stmt.execute(sql2);
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	    }
	  
	  public void insertUser(ArrayList<String> stuff) {
		  String sql = "INSERT INTO user (email, password, country, state, zip) VALUES(?, ?, ?, ?, ?)";
	        //System.out.println("pre-test");
	        try (Connection conn = this.connect("SWE_Project.db");
	        	PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        	// set parameters
	        	//pstmt.setInt(1, 1);
	        	for(int i=0;i<5;i++) {
	        		pstmt.setString(i+1, stuff.get(i));
	        	}
	        	//System.out.println("int check");
	        	//pstmt.setBinaryStream(1, input, (int) stuff.length());
	            //System.out.println("Reading file " + stuff.getAbsolutePath());
	            System.out.println("Store file in the database.");
	            pstmt.executeUpdate();
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	  }
	 /* 
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
	*/
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
