package com.banking;


import java.sql.*;
import java.util.*;



public class ConnectionPool { 
	
	
	public Hashtable<Connection , Boolean> connections;
	private int increment;
	private String dbURL, user, password;
	public ConnectionPool(String dbURL, String user, String password, String driverClassName, int initialConnections, int increment) throws SQLException, ClassNotFoundException {
		
		// Load the specified driver class 
		
		Class.forName(driverClassName);
		
		this.dbURL = dbURL;
		this.user = user;
		this.password = password;
		this.increment = increment;
		
		connections = new Hashtable<Connection, Boolean>();
		
		// Put our pool of Connections in the Hashtable 
		// The FALSE value indicates they're unused 
		
		for(int i = 0; i < initialConnections; i++) {
			connections.put(DriverManager.getConnection(dbURL, user, password), Boolean.FALSE);
			}
	
	}
	
	
	public Connection getConnection() throws SQLException {
	
		Connection con = null;
		Enumeration<Connection> cons = connections.keys();
		synchronized (connections) {
		
			while(cons.hasMoreElements()) {
				con = (Connection)cons.nextElement();
				Boolean b = (Boolean)connections.get(con);
				
				if (b == Boolean.FALSE) {
				
					// So we found an unused connection.
					// Test its integrity with a quick setAutoCommit(true) call.
					// For production use, more testing should be performed, 
					
					// such as executing a simple query. 
					try { 
						con.setAutoCommit(true);
					
					} catch(SQLException e) {
					
							// Problem with the connection, replace it. 
						
							con = DriverManager.getConnection(dbURL, user, password);
							
						} 
					// Update the Hashtable to show this one's taken 
					
					connections.put(con, Boolean.TRUE);
					// Return the connection 
					
					return con; 
					
				} 
				}
			} 
		// If we get here, there were no free connections.
		// We've got to make more. 
	
		for(int i = 0; i < increment; i++) {
			connections.put(DriverManager.getConnection(dbURL, user, password), Boolean.FALSE);
			
		}	
		// Recurse to get one of the new connections. 
		
		return getConnection();
		} 
	
	public void returnConnection(Connection returned) {
		Connection con;
		Enumeration<Connection> cons = connections.keys();
		while (cons.hasMoreElements()) {
			con = (Connection)cons.nextElement();
			if (con == returned) {
				connections.put(con, Boolean.FALSE);
				break; 
				} } } 
	


public static void main(String args) {
	try {
 ConnectionPool cp=new ConnectionPool("jdbc:oracle:thin:@192.168.4.187:1521:db122", "xxsplashtesting", "xxsplashtesting", "oracle.jdbc.driver.OracleDriver", 10, 5);
    Connection c = cp.getConnection();
	}catch(Exception e) {
		System.out.println(e);
	}
} 
}
					