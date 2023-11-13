package positionList;
/**
 * A reimplementation of PositionList.java from the net.datastructures library.
 */

import java.lang.IllegalArgumentException;
import java.lang.Iterable;

public interface PositionList<E> extends Iterable<E> {

  /** Returns the number of nodes in the list. */
  public int size();


  /** Returns true if the list is empty (has no nodes), false if it's not. */
  public boolean isEmpty();


  /** Returns the first node of the list, or null if the list is empty. */
  public Position<E> first();


  /** Returns the last node of the list, or null if the list is empty. */
  public Position<E> last();


  /** Either returns the next node to the right of parameter node 'p' in the
   *  list, or returns null if 'p' doesn't have a next node to the right.
   *  Raises the exception if 'p' is null or is not a node of the list.
   */
  public Position<E> next(Position<E> p) throws IllegalArgumentException;


  /** Either returns the previous node to the left of parameter node 'p' in
   *  the list, or returns null if 'p' doesn't have a previous node to the
   *  left. Raises the exception if 'p' is null or is not a node of the list.
   */
  public Position<E> prev(Position<E> p) throws IllegalArgumentException;


  /** Inserts a new first node to the list with 'elem' as element. */
  public void addFirst(E elem);


  /** Inserts a new last node to the list with 'elem' as element.  */
  public void addLast(E elem);


  /** Inserts a new node with 'elem' as element to the list right before
   *  parameter node 'p'. Raises the exception if 'p' is null or is not a node
   *  of the list.
   */
  public void addBefore(Position<E> p, E elem) throws IllegalArgumentException;


  /** Inserts a new node with 'elem' as element to the list right after
   *  parameter node 'p'. Raises the exception if 'p' is null or is not a node
   *  of the list.
   */
  public void addAfter(Position<E> p, E elem) throws IllegalArgumentException;


  /** Removes node 'p' from the list and returns its element. Raises the
   *  exception if 'p' is null or is not a node of the list.
   */
  public E remove(Position<E> p) throws IllegalArgumentException;


  /** Sets the element of node 'p' on the list to 'elem' and returns the old
   *  element in 'p'. Raises the exception if 'p' is null or is not a node of
   *  the list.
   */
  public E set(Position<E> p, E elem) throws IllegalArgumentException;


  /** Returns an array with all the elements of the list or an empty array
   *  of length zero if the list is empty.
   */
  public Object [] toArray();
}
