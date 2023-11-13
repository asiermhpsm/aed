package aed.recursion;

import es.upm.aedlib.indexedlist.IndexedList;

public class Main {

	public static void main(String[] args) {
		int list[] = new int[] {1,2,3,4};
		findBottom(list);
	}

	public static <E extends Comparable<E>> int findBottom(int[] l) {
		return findBottomAux(l,0);
	}

	public static int findBottomAux(int[] l, int i) {
		int res=-1;
		if(esHoyo(l,i)) res=i;
		else if(i<l.length) findBottomAux(l,i+1);
		return res;
	}

	private static boolean esHoyo(int[] l, int i){
		return (l[i]<=l[i-1]) && (l[i]<=l[i+1]);
	}

}
