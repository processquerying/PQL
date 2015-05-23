package org.jbpt.persist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Artem Polyvyanyy
 */
public class MySQLConnection {	
	protected String musqlURL		= null;
	protected String mysqlUser		= null;
	protected String mysqlPassword	= null;
		
	protected static Connection connection = null;
	
	protected MySQLConnection(String url, String user, String password) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		
		this.musqlURL		= url;
		this.mysqlUser		= user;
		this.mysqlPassword	= password;
		
		if (connection==null || connection.isClosed()) this.connect(); 
	}
	
	public void connect() throws SQLException {
		if (connection==null || connection.isClosed())
			connection = DriverManager.getConnection(this.musqlURL,this.mysqlUser,this.mysqlPassword);
	}	
	
	public void disconnect() throws SQLException {
		connection.close();
	}
}
