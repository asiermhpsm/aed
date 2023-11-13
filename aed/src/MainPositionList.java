import java.util.Iterator;

import positionList.*;

public class MainPositionList {

	public static void main (String argv []) {
		/*
		PositionList<Integer> l1= new NodePositionList<Integer>();
		l1.addLast(1);
		l1.addLast(2);
		l1.addLast(4);
		PositionList<Integer> l2= new NodePositionList<Integer>();
		l2.addLast(4);
		l2.addLast(5);
		PositionList<Integer> l3= new NodePositionList<Integer>();
		l3.addLast(7);
		l3.addLast(3);
		l3.addLast(2);
		l3.addLast(1);
		PositionList<PositionList<Integer>> list=new NodePositionList<PositionList<Integer>>();
		list.addLast(l1);
		list.addLast(l2);
		list.addLast(l3);
		PositionList<Integer> sumList=sumaListas(list);
		Iterator<Integer> it=sumList.iterator();
		while(it.hasNext()) {
			System.out.print(it.next()+'-');
		}
		*/
		
		/*
		PositionList<Integer> l1= new NodePositionList<Integer>();
		l1.addLast(5);
		l1.addLast(4);
		l1.addLast(2);
		l1.addLast(6);
		l1.addLast(7);
		l1.addLast(2);
		l1.addLast(1);
		l1.addLast(3);
		
		System.out.println(hayEnRango(l1,2,4));
		System.out.println(hayEnRango(l1,8,10));
		System.out.println(hayEnRango(l1,7,10));
		*/
		
		PositionList<Integer> l1= new NodePositionList<Integer>();
		l1.addLast(1);
		l1.addLast(5);
		l1.addLast(2);
		l1.addLast(4);
		PositionList<Long> list=getDistancias(l1);
		for(Long i:list) {
			System.out.print(i+'-');
		}
		
		
	}

	public <E> void removeAll(PositionList<E> list, E e) {
		Iterator<E> it=list.iterator();
		while(it.hasNext()) {
			if(it.next().equals(e))	it.remove();
		}
	}

	public PositionList<Integer> fairOrderMerge
	(PositionList<Integer> l1, PositionList<Integer> l2){
		PositionList<Integer> res=new NodePositionList<Integer>();
		Position<Integer> cursor1=l1.first();
		Position<Integer> cursor2=l2.first();
		while(!(cursor1==null	&&	cursor2==null)) {
			if(cursor1==null) {
				res.addLast(cursor2.element());
				cursor2=l2.next(cursor2);
			}
			else if(cursor2==null) {
				res.addLast(cursor1.element());
				cursor1=l1.next(cursor1);
			}
			else {
				if(cursor1.element()<=cursor2.element()) {
					res.addLast(cursor1.element());
					cursor1=l1.next(cursor1);
				}
				else {
					res.addLast(cursor2.element());
					cursor2=l2.next(cursor2);
				}
			}
		}
		return res;
	}



	public static PositionList<Integer> sumaListas 
	(PositionList<PositionList<Integer>> lista){
		PositionList<Integer> res= new NodePositionList<Integer>();
		Iterator<PositionList<Integer>> it=lista.iterator();
		while(it.hasNext()) {
			res.addLast(sum(it.next()));
		}
		return res;
	}

	private static int  sum(PositionList<Integer> lista) {
		int res=0;
		Iterator<Integer> it=lista.iterator();
		while(it.hasNext()) {
			res=res+it.next();
		}
		return res;
	}


	public static boolean hayEnRango(PositionList<Integer> list, int a, int b) {
		if(b<a	||	list.size()==0)	return false;
		return hayEnRangoAux(list, a, b, list.first());
	}

	private static boolean hayEnRangoAux
	(PositionList<Integer> list, int a, int b, Position<Integer> pos) {
		if(pos!=null) {
			if(a<=pos.element()	&&	pos.element()<=b)	return true;
			else	return hayEnRangoAux(list, a, b, list.next(pos));
		}
		return false;
	}

	public static PositionList<Long> getDistancias 
	(Iterable<Integer> iterable) throws IllegalArgumentException{
		int size = 0;
		for(int i :iterable)	size++;
		if(size<2)	throw new IllegalArgumentException();
		PositionList<Long> res=new NodePositionList<Long>();
		Iterator<Integer> it=iterable.iterator();
		int x;
		int y=it.next();
		while(it.hasNext()) {
			x=y;
			y=it.next();
			res.addLast((long) abs(x-y));
		}
		return res;
	}

	private static int abs(int i) {
		return (i<0)?-i:i;
	}











}
