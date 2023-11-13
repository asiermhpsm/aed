package aed.indexedlist;

import es.upm.aedlib.Node;
import es.upm.aedlib.indexedlist.*;

public class Utils<E> {

	public static <E> IndexedList<E> deleteRepeated(IndexedList<E> l) {

		IndexedList<E> m = new ArrayIndexedList<E>();	
		for(int i = 0; i<l.size();i++)
			if(m.indexOf(l.get(i))==-1){
				m.add(m.size(), l.get(i));
			}
		return m;
	}
}