package edu.escuelaing.arep.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.escuelaing.arep.model.User;


public class ConnectionDao {
	Connection con = null;

    public Connection retriveConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String dbName = "db";
            String userName = "root";
            String password = "willsonDb";
            String hostname = "instancia2.ch00vgal8tpx.us-east-1.rds.amazonaws.com";
            String port = "3306";
            String jdbcUrl = "jdbc:mysql://" + hostname + ":" + port + "/" + dbName + "?user=" + userName + "&password=" + password;            
            con = DriverManager.getConnection(jdbcUrl);
            System.out.println("Se conectó a la base de datos.");
        } catch (Exception e) {
            System.out.println("No se conectó a la base de datos."+ e);
        }
        return con;
    }
    
    public List<User> getUsers(Connection conn){
    	if(conn == null) {
    		conn = retriveConnection();
    	}
    	List<User> userList = new ArrayList<User>();
    	try {
    		Statement stmt = conn.createStatement();
    		ResultSet res = stmt.executeQuery("select * from users");
    		while(res.next()) {
    			User user = new User(
    					Long.parseLong(res.getString("id")),
    					res.getString("firstName"),
    					res.getString("lastName"),
    					res.getString("email")
    					);
    			System.out.println(user.toString());
    			userList.add(user);
    		}    		    		
    	System.out.println("# users " + userList.size());
    	} catch (Exception e) {
			e.printStackTrace();
		}
    	return userList;
    }
    
    public void addUser(Connection conn, User user) {
    	if (conn == null) {
            conn = retriveConnection();
        }
        try {
        	
        	Statement stmt = conn.createStatement();
        	String values = "('" + user.getFirstName() +
        			"','"+ user.getLastName() +
        			"','"+ user.getEmail()+"')"; 
			//System.out.println(values);
        	stmt.executeUpdate("insert into users (firstName, lastName, email)  values" + values );			
		} catch (Exception e) {
			e.printStackTrace();
		}       
    }
    
    public void deleteUser(Connection conn, long id) {
    	if (conn == null) {
            conn = retriveConnection();
        }        
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("DELETE  FROM  users where id = "+ id);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
