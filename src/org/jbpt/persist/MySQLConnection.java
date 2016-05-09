package org.jbpt.persist;

import java.net.URI;
import java.net.URISyntaxException;
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

		this.mysqlURL		= modifyURLToDisableSSL(url);
		this.mysqlUser		= user;
		this.mysqlPassword	= password;
		
		if (this.connection==null || this.connection.isClosed()) this.connect(); 
	}

        /**
         * This method adds <code>useSSL=false</code> to the query part of a MySQL JDBC connection URL.
         *
         * @param url  a MySQL JDBC URL
         * @throws SQLException if <var>url</var> doesn't have <code>jdbc:mysql:</code> as its prefix
         */
        String modifyURLToDisableSSL(String url) throws SQLException {
		try {
			URI jdbcURI = new URI(url);
			if (!"jdbc".equals(jdbcURI.getScheme().toLowerCase())) {
				throw new SQLException("Not a JDBC URL: " + url);
			}

			URI mysqlURI = new URI(jdbcURI.getSchemeSpecificPart());
			if (!"mysql".equals(mysqlURI.getScheme().toLowerCase())) {
				throw new SQLException("Not a MySQL JDBC URL: " + url);
			}

			return new URI(
				jdbcURI.getScheme(),
				URIHelper.addQueryProperty(mysqlURI, "useSSL", "false").toString(),
				jdbcURI.getFragment()
			).toString();

		} catch (URISyntaxException e) {
			throw new SQLException("Unable to parse " + url + " as a MySQL JDBC URL", e);
		}
        }

	public void connect() throws SQLException {
		if (this.connection==null || this.connection.isClosed())
			this.connection = DriverManager.getConnection(this.mysqlURL,this.mysqlUser,this.mysqlPassword);
		
			}	
	
	public void disconnect() throws SQLException {
		this.connection.close();
	}
	
	public Connection getConnection()
	{
		return this.connection;
	}
}
