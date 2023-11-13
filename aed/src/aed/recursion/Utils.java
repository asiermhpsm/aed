package aed.recursion;

import es.upm.aedlib.Pair;
import es.upm.aedlib.Position;
import es.upm.aedlib.indexedlist.*;
import es.upm.aedlib.positionlist.*;


public class Utils {

	public static int multiply(int a, int b) {
		return ((a<0)?-1:1)*multiplyAux(a,b,0);
	}

	private static int multiplyAux(int a, int b,int sum) {
		if(a!=0) {
			if(a%2!=0)  sum=sum+b;
			sum=multiplyAux(a/2,b*2,sum);
		}
		return sum;
	}

	public static <E extends Comparable<E>> int findBottom(IndexedList<E> list) {
		return findBottomAux(list,0, list.size()-1);
	}

	private static <E extends Comparable<E>> int findBottomAux(IndexedList<E> list, int starts, int ends){
		int res=-1;
		int medio=starts+((ends-starts)/2);
		switch(ends-starts+1){
		case 1:
			res=starts;
			break;
		case 2:
			res=(list.get(starts).compareTo(list.get(ends))<=0)?starts:ends;
			break;
		default:
			//el punto medio es un hoyo
			if(list.get(medio).compareTo(list.get(medio-1))<=0
			&&   list.get(medio).compareTo(list.get(medio+1))<=0) {
				res=medio;
			}
			//la inclinación en el medio va hacia el inicio de la lista
			else if(list.get(medio-1).compareTo(list.get(medio))<=0){
				res=findBottomAux(list,starts,medio-1);
			}
			//la inclinacion va hacia el final de la lista
			else{
				res=findBottomAux(list,medio+1,ends);
			}
		}
		return res;
	}

	public static <E extends Comparable<E>> NodePositionList<Pair<E,Integer>>
	joinMultiSets(NodePositionList<Pair<E,Integer>> l1,	NodePositionList<Pair<E,Integer>> l2) {
		NodePositionList<Pair<E,Integer>> res = new NodePositionList<>();
		return joinMultiSetsAux(l1,l2,res,l1.first(),l2.first());
	}


	public static <E extends Comparable<E>> NodePositionList<Pair<E,Integer>>
	joinMultiSetsAux(NodePositionList<Pair<E,Integer>> l1,	NodePositionList<Pair<E,Integer>> l2,
			NodePositionList<Pair<E,Integer>> res,
			Position<Pair<E,Integer>> cursor1,	Position<Pair<E,Integer>> cursor2) {

		//Finalizo si ambos cursores son nulos(he llegado al final de las listas)
		if(cursor1!=null	||	cursor2!=null){

			//añado al resultado el elemento del cursor 2 si he llegado al final del cursor 2
			if(cursor1==null) {
				res.addLast(cursor2.element());
				res=joinMultiSetsAux(l1,l2,res,cursor1,l2.next(cursor2));
			}

			//añado al resultado el elemento del cursor 1 si he llegado al final del cursor 2
			else if(cursor2==null) {
				res.addLast(cursor1.element());
				res=joinMultiSetsAux(l1,l2,res,l1.next(cursor1),cursor2);
			}

			//si ningun cursor es nulo tenemos que comparar los elementos de los cursores
			else {

				int orden=cursor1.element().getLeft().compareTo(cursor2.element().getLeft());

				/*
				 * añado al resultado el elemento del cursor 2 si
				 * el elemento del cursor 2 es menor que el elemento del cursor 1
				 */
				if(orden >0) {
					res.addLast(cursor2.element());
					res=joinMultiSetsAux(l1,l2,res,cursor1,l2.next(cursor2));
				}
				
				/*
				 * añado al resultado el elemento del cursor 1 si
				 * el elemento del cursor 1 es menor que el elemento del cursor 2
				 */
				
				else if(orden <0) {
					res.addLast(cursor1.element());
					res=joinMultiSetsAux(l1,l2,res,l1.next(cursor1),cursor2);
				}

				//si los elementos de ambos cursores son iguales, los añado a la lista y sumo su numero
				else {
					Pair<E,Integer> parSuma=new Pair(cursor1.element().getLeft(),
							cursor1.element().getRight( )+ cursor2.element().getRight());
					res.addLast(parSuma);
					res=joinMultiSetsAux(l1,l2,res,l1.next(cursor1),l2.next(cursor2));
				}

			}

		}
		return res;
	}
}
