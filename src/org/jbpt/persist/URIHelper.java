package org.jbpt.persist;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Library for URI manipulation.
 * 
 * @author <a href="mailto:simon.raboczi@uqconnect.edu.au">Simon Raboczi</a>
 */
abstract public class URIHelper {	

	/**
	 * Assign a query property within a URI.
	 *
	 * <table>
	 * <tr><th>URI</th>                            <th>Property</th><th>Value</td>    <th>Result</th></tr>
	 * <tr><td>http://example.com</td>             <td>"prop"</td>  <td>null</td>     <td>http://example.com?prop</td></tr>
	 * <tr><td>http://example.com</td>             <td>"prop"</td>  <td>"true"</td>   <td>http://example.com?prop=true</td></tr>
	 * <tr><td>http://example.com?prop1=apple</td> <td>"prop2"</td> <td>"orange"</td> <td>http://example.com?prop1=apple&prop2=orange</td></tr>
	 * </table>
	 * 
	 * This implementation doesn't remove duplicates or overwritten preexisting values.
	 *
	 * @param uri  an arbitrary URI
	 * @param property  a property to set in the query part of the <var>uri</var>
	 * @param value  the value to set the <var>property</var>, or <code>null</code> for no value
         * @throws IllegalArgumentException if <var>uri</var> or <var>property</var> are <code>null</code>
	 */
	static URI addQueryProperty(final URI uri, final String property, final String value) {

		// Validate input
		if (uri == null) { throw new IllegalArgumentException("Null URI"); }
		if (property == null) { throw new IllegalArgumentException("Tried to set null property to " + null + " in URI " + uri); }

		// Determine the updated query part
		String query = property;
		if (value != null) {
			query += "=" + value;
		}
		if (uri.getQuery() != null) {
			query = uri.getQuery() + "&" + query;
		}

		// Compose and return the modified URI
		try {
			return new URI(uri.getScheme(), uri.getAuthority(), uri.getPath(), query, uri.getFragment());
		} catch (URISyntaxException e) {
			// This is an error rather than an exception, because we guarantee to produce legal URIs
			throw new Error("Implementation error in " + URIHelper.class + ": generated invalid URI for inputs uri=" + uri + " property=" + property + " value=" + value, e);
		}
	}
}
