package aed.filter;

import java.util.Iterator;
import java.util.function.Predicate;

import es.upm.aedlib.indexedlist.ArrayIndexedList;
import es.upm.aedlib.indexedlist.IndexedList;


public class Utils {

	public static <E> Iterable<E> filter(Iterable<E> d, Predicate<E> pred) throws IllegalArgumentException{
		if(d==null) {
			throw new IllegalArgumentException();
		}
		
		IndexedList<E> res= new ArrayIndexedList<E>();
		Iterator<E> it= d.iterator();
		
		while(it.hasNext()) {
			E x = it.next();
			if(x!=null &&  pred.test(x)){
				res.add(res.size(), x);
			}
		}
		return res;
	}
}

