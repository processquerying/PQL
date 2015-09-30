package org.jbpt.persist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * An implementation of MySQL database connection.
 * 
 * @author Artem Polyvyanyy
 */
public class MySQLConnectionStatic {	
	protected String mysqlURL		= null;
	protected String mysqlUser		= null;
	protected String mysqlPassword	= null;
	
	protected static Connection connection = null;
	
	protected MySQLConnectionStatic(String url, String user, String password) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		
		this.mysqlURL		= url;
		this.mysqlUser		= user;
		this.mysqlPassword	= password;
		
		if (connection==null || connection.isClosed()) this.connect(); 
	}
	
	public void connect() throws SQLException {
		if (connection==null || connection.isClosed())
			connection = DriverManager.getConnection(this.mysqlURL,this.mysqlUser,this.mysqlPassword);
	}	
	
	public void disconnect() throws SQLException {
		connection.close();
	}
}
