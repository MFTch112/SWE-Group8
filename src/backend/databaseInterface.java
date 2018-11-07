package backend;


import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;


public class databaseInterface {
 

    private Connection connect() {
        String url = "jdbc:sqlite:./files/project.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
 

    public void insert(String user, String password) {
        String sql = "INSERT INTO main(user, password) VALUES(?,?)";
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        
        
        try (Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user);
            pstmt.setString(2, hashedPassword);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    } 
    
    public int verify(String user, String password) {
    	
    	//get password from database
    	String selectSQL = "SELECT * FROM main WHERE user='" + user + "'" ;
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
 
        String hashedPassword = null;
        
        try {
            conn = connect();
            pstmt = conn.prepareStatement(selectSQL);
            rs = pstmt.executeQuery();
                      
            hashedPassword = rs.getString(2);
    
        }catch (SQLException e) {
        	return 0;
            //System.out.println(e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
 
                if (conn != null) {
                    conn.close();
                }
 
            } catch (SQLException e) {
            	return 0;
              
            }
        }
    
        
        //System.out.println(password);
        //System.out.println(hashedPassword);
        
        //System.out.println(BCrypt.checkpw(password, hashedPassword));
        
        
    	if (BCrypt.checkpw(password, hashedPassword)) {
    		return 1;
    	}
    	
    	else {
    		//System.out.println("FAILURE");
    		return 0;
    	}
    	
    		
    }
    
    public void insertFile( String user, byte[] file) {
    	
    	//convert the byte array into a string for the database
    	
    	String sql = "INSERT INTO files(user, password) VALUES(?,?)";
    	String convertedFile = new String(file);
    	
        try (Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user);
            pstmt.setString(2, convertedFile);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
    }
}