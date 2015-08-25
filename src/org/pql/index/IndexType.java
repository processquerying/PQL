package org.pql.index;

/**
 * An enumeration of index types:
 * 
 * PREDICATES	- compute and store all behavioral predicates
 * 
 * @author Artem Polyvyanyy
 */
public enum IndexType {
	PREDICATES(0);
	
	private final int type;
	
	IndexType(int type) {
        this.type = type;
    }
	
	/**
	 * Get code of this index type.
	 * 
	 * @return Code of this index type.
	 */
	public int getIndexStatusType() {
		return this.type;
	}
}
