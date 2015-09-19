package org.pql.api;

import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * An implementation of a thread safe hash set. 
 * 
 * This initial code is taken from http://stackoverflow.com/questions/6992608/why-there-is-no-concurrenthashset-against-concurrenthashmap
 */
public class ConcurrentHashSet<E> extends AbstractSet<E> implements Set<E>{
	
	private final ConcurrentMap<E,Object> theMap;
	
	private static final Object dummy = new Object();
	
	public ConcurrentHashSet() {
		theMap = new ConcurrentHashMap<E, Object>();
	}
	
	@Override
	public int size() {
		return theMap.size();
	}

	@Override
	public Iterator<E> iterator() {
		return theMap.keySet().iterator();
	}
	
	@Override
	public boolean isEmpty() {
		return theMap.isEmpty();
	}

	@Override
	public boolean add(final E o) {
		return theMap.put(o, ConcurrentHashSet.dummy) == null;
	}
	
	@Override
	public boolean addAll(Collection<? extends E> arg0) {
		boolean result = false;
		
		for (E o : arg0) 
			result |= this.add(o);
		
		return result;
	}

	@Override
	public boolean contains(final Object o) {
		return theMap.containsKey(o);
	}
	
	@Override
	public void clear() {
		theMap.clear();
	}
	
	@Override
	public boolean remove(final Object o) {
		return theMap.remove(o) == ConcurrentHashSet.dummy;
	}

	public boolean addIfAbsent(final E o) {
		Object obj = theMap.putIfAbsent(o, ConcurrentHashSet.dummy);
		return obj == null;
	}
}

