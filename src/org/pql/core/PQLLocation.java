package org.pql.core;

/**
 * Artem Polyvyanyy
 */
public class PQLLocation {	
	private String		locationPath	= null;
	
	/**
	 * Empty constructor is used to construct the universe location.
	 */
	public PQLLocation() {}
	
	/**
	 * Constructor of the PQLLocation object.
	 * 
	 * @param path A string that signifies a path. 
	 */
	public PQLLocation(String path) {
		this.locationPath = path;
	}
	
	/**
	 * Test if this location is a path.
	 * 
	 * @return {@code true} if this location is a path; {@code false} otherwise.
	 */
	public boolean isLocationPath() {		
		return this.locationPath!=null;
	}
	
	/**
	 * Test if this location is the universe location.
	 * 
	 * @return {@code true} if this location is the universe location; {@code false} otherwise.
	 */
	public boolean isUniverse() {
		return locationPath==null;
	}
	
	/**
	 * Get location path.
	 * 
	 * @return Path of this location or {@code null} in case of the universe location.
	 */
	public String getLocationPath() {
		return this.locationPath;
	}
	
	@Override
	public int hashCode() {
		return this.locationPath == null ? 0 : this.locationPath.hashCode();
	}
	
	@Override
	public String toString() {
		return this.isUniverse() ? "UNIVERSE" : this.getLocationPath();
	}
}
