package positionList;
/**
 * A reimplementation of DNode.java from the net.datastructures library.
 *
 */

import java.lang.IllegalArgumentException;

public class Node<E> implements Position<E> {
  private final int owner;
  private Node<E> prev, next;
  private E elem;

  public Node(int owner, Node<E> prev, E elem, Node<E> next) {
    this.owner = owner;
    this.prev  = prev;
    this.elem  = elem;
    this.next  = next;
  }

  // No getter for owner!

  public Node<E> getPrev() { return prev; }
  public Node<E> getNext() { return next; }
  public E element()       { return elem; }

  // Only nodes can tell if they have the same owner
  public boolean kinOf(Node<E> n) {
    return n != null && n.owner == this.owner;
  }

  // No setter for owner!

  public void setPrev(Node<E> prev) throws IllegalArgumentException {
    if (prev == null || this.kinOf(prev)) this.prev = prev;
    else throw new IllegalArgumentException();
  }

  public void setNext(Node<E> next) throws IllegalArgumentException {
    if (next == null || this.kinOf(next)) this.next = next;
    else throw new IllegalArgumentException();
  }

  public void setElem(E elem) { this.elem = elem; }

}
