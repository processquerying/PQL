package org.pql.core;

/**
 * Artem Polyvyanyy
 */
public class PQLAttribute {	
	private String	attributeName = null;
	
	/**
	 * Empty constructor is used to construct the universe attribute.
	 */
	public PQLAttribute() {}
	
	/**
	 * Constructor of the PQLAttribute object.
	 * 
	 * @param name Attribute name. 
	 */
	public PQLAttribute(String name) {		
		this.attributeName = name;
	}
	
	/**
	 * Test if this attribute is the universe attribute.
	 * 
	 * @return {@code true} if this attribute is the universe attribute; {@code false} otherwise.
	 */
	public boolean isUniverse() {
		return this.attributeName==null;
	}
	
	/**
	 * Test if this attribute is a name attribute.
	 * 
	 * @return {@code true} if this attribute is a name attribute; {@code false} otherwise.
	 */
	public boolean isAttributeName() {
		return this.attributeName!=null;
	}
	
	/**
	 * Get name of this attribute.
	 * 
	 * @return Name of this attribute or {@code null} in case of the universe attribute.
	 */
	public String getAttributeName() {
		return this.attributeName;
	}
	
	@Override
	public int hashCode() {
		return this.attributeName == null ? 0 : this.attributeName.hashCode();
	}
	
	@Override
	public String toString() {
		return this.isUniverse() ? "UNIVERSE" : this.getAttributeName(); 
	}
}
