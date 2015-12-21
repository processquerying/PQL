package org.pql.core;

/**
 * Artem Polyvyanyy
 */
public class PQLLocation {	
	private String		address		= null;
	private boolean		isDirectory	= false;
	
	public PQLLocation() {	
	}
	
	/**
	 * Constructor of a Location object.
	 * 
	 * @param address A string that signifies a location.
	 * @param isDirectory A flag that indicates if this location is a directory. 
	 */
	public PQLLocation(String address, boolean isDirectory) {
		this.address		= address;
		this.isDirectory	= isDirectory;
	}
	
	/**
	 * Test if this location is a directory.
	 * 
	 * @return {@code true} if this location is a directory; {@code false} otherwise.
	 */
	public boolean isDirectory() {
		if (this.isUniverse()) return false;
		
		return isDirectory;
	}
	
	/**
	 * Test if this location is a reference to a single model.
	 * 
	 * @return {@code true} if this location is a reference to a single model; {@code false} otherwise.
	 */
	public boolean isModel() {
		if (this.isUniverse()) return false;
		
		return !isDirectory;
	}
	
	/**
	 * Test if this location signifies a universe, i.e., any possible location.
	 * 
	 * @return {@code true} if this location signifies a universe; {@code false} otherwise.
	 */
	public boolean isUniverse() {
		return address==null;
	}
	
	@Override
	public int hashCode() {
		int result = address == null ? 0 : address.hashCode(); 
		if (this.isModel()) result *= -1;
		
		return result;
	}
	
	@Override
	public String toString() {
		if (this.isUniverse()) return "UNIVERSE";
		if (this.isDirectory()) return String.format("\"%s\"", this.address);
		
		return this.address;
	}
}
