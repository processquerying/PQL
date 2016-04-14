package org.jbpt.persist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * An implementation of MySQL database connection.
 * 
 * TODO: Manage connections wisely when using non static object, e.g., when querying. 
 * 
 * @author Artem Polyvyanyy
 */
public class MySQLConnection{	
	protected String mysqlURL		= null;
	protected String mysqlUser		= null;
	protected String mysqlPassword	= null;
	protected Connection connection = null;
	
	public MySQLConnection(String url, String user, String password) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		
		this.mysqlURL		= url;
		this.mysqlUser		= user;
		this.mysqlPassword	= password;
		
		if (this.connection==null || this.connection.isClosed()) this.connect(); 
	}
	
	public void connect() throws SQLException {
		if (this.connection==null || this.connection.isClosed())
			this.connection = DriverManager.getConnection(this.mysqlURL+"?useSSL=false",this.mysqlUser,this.mysqlPassword);
		
			}	
	
	public void disconnect() throws SQLException {
		this.connection.close();
	}
	
	public Connection getConnection()
	{
		return this.connection;
	}
}
