package org.pql.index;

/**
 * An enumeration of index statuses:
 * 
 * UNINDEXED	- indexing has not started
 * INDEXING		- indexing in progress
 * INDEXED		- indexing completed
 * CANNOTINDEX	- indexing not possible (at this stage)
 * 
 * @author Artem Polyvyanyy
 */
public enum IndexStatus {
	UNINDEXED(-1),
	INDEXING(0),
	INDEXED(1),
	CANNOTINDEX(2);
	
	private final int status;
	
	IndexStatus(int status) {
        this.status = status;
    }
	
	/**
	 * Get code of this index status.
	 * 
	 * @return Code of this index status.
	 */
	public int getIndexStatusCode() {
		return this.status;
	}
}
