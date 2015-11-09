package org.pql.alignment;

import java.util.Vector;

public class HittingSetsMain {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) {
		Vector v = new Vector<>();
		Vector a = new Vector<>();
		Vector b = new Vector<>();
		Vector c = new Vector<>();
		
		a.add("a");
		a.add("b");
		
		b.add("b");
		b.add("c");
		
		c.add("c");
		c.add("d");
		c.add(2);

		
		v.add(a);
		v.add(b);
		v.add(c);
		HittingSets hs = new HittingSets(v);
		System.out.println(hs.getSets());

	}

}
