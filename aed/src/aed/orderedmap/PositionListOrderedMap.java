package aed.orderedmap;

import java.util.Comparator;
import java.util.Iterator;

import es.upm.aedlib.Entry;
import es.upm.aedlib.Position;
import es.upm.aedlib.indexedlist.ArrayIndexedList;
import es.upm.aedlib.indexedlist.IndexedList;
import es.upm.aedlib.positionlist.PositionList;
import es.upm.aedlib.positionlist.NodePositionList;

public class PositionListOrderedMap<K,V> implements OrderedMap<K,V> {
	private Comparator<K> cmp;
	private PositionList<Entry<K,V>> elements;

	/* Acabar de codificar el constructor */
	public PositionListOrderedMap(Comparator<K> cmp) {
		this.cmp = cmp;
		elements=new NodePositionList<Entry<K,V>>();
	}

	/* Ejemplo de un posible método auxiliar: */

	/* If key is in the map, return the position of the corresponding
	 * entry.  Otherwise, return the position of the entry which
	 * should follow that of key.  If that entry is not in the map,
	 * return null.  Examples: assume key = 2, and l is the list of
	 * keys in the map.  For l = [], return null; for l = [1], return
	 * null; for l = [2], return a ref. to '2'; for l = [3], return a
	 * reference to [3]; for l = [0,1], return null; for l = [2,3],
	 * return a reference to '2'; for l = [1,3], return a reference to
	 * '3'. */

	private Position<Entry<K,V>> findKeyPlace(K key){
		if (key == null) throw new IllegalArgumentException();
		Position<Entry<K,V>> res = elements.first() ;
		while (res != null && cmp.compare(res.element().getKey(), key) < 0) 
			res = elements.next(res);
		return res;
	}

	/* Podéis añadir más métodos auxiliares */

	public boolean containsKey(K key) {
		return false;
	}

	public V get(K key) {
		return null;
	}

	public V put(K key, V value) {
		return null;
	}

	public V remove(K key) {
		if(findKeyPlace(key)==null) throw new IllegalArgumentException();
		return null;
	}

	public int size() {
		return elements.size();
	}

	public boolean isEmpty() {
		return elements.isEmpty();
	}

	public Entry<K,V> floorEntry(K key) {
		if(key==null) throw new IllegalArgumentException();
		Position<Entry<K, V>> cota = findKeyPlace(key);
		Entry<K,V> res=null;
		if(cota!=null) {
			if(containsKey(key)) {
				res=cota.element();
			}
			else {
				Position<Entry<K, V>> cursor = elements.first();
				while(cursor!=cota) {
					res=cursor.element();
					cursor=elements.next(cursor);
				}
			}
		}
		return res;
	}

	public Entry<K,V> ceilingEntry(K key) {
		if(key==null) throw new IllegalArgumentException();
		return findKeyPlace(key).element();
	}

	public Iterable<K> keys() {
		Position<Entry<K, V>> cursorElements = elements.first ();
		PositionList<K> res = (PositionList<K>) cursorElements.element().getKey();
		
		res.addFirst(cursorElements.element().getKey());
		Position<K> cursorRes = res.first();
		while(cursorElements!=null) {
			res.addAfter(cursorRes, cursorElements.element().getKey());;
		}
		return res;
	}

	public String toString() {
		return elements.toString();
	}


}
