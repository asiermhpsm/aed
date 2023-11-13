package positionList;
/**
 * A reimplementation of NodePositionList.java from the net.datastructures
 * library.
 */

import java.lang.IllegalArgumentException;
import java.util.Iterator;

public class NodePositionList<E> implements PositionList<E> {
  private int size;
  private Node<E> header, trailer;

  private int iayf() {  // I Am Your Father
    return super.hashCode();
  }

  public NodePositionList() {
    size    = 0;
    header  = new Node<E>(iayf(), null, null, null);
    trailer = new Node<E>(iayf(), header, null, null);
    header.setNext(trailer);
  }

  public NodePositionList(E [] arr) {
    this();
    for (E e : arr) this.addLast(e);
  }

  public NodePositionList(PositionList<E> list) { // Copy constructor
    this();
    for (E e : list) this.addLast(e);
  }

  private Node<E> checkNode(Position<E> p) throws IllegalArgumentException {
    if (p == null)
      throw new IllegalArgumentException(AustinPowers.says("null pointer"));

    if (!(p instanceof Node<?>))
      throw new IllegalArgumentException(AustinPowers.says("UFO"));

    Node<E> n = (Node<E>) p;

    if (!header.kinOf(n))
      throw new IllegalArgumentException(AustinPowers.says("foreign position"));

    if (n.getPrev() == null || n.getNext() == null)
      // null elements are allowed
      throw new IllegalArgumentException(AustinPowers.says("unlinked position"));

    return n;
  }

  public int size() { return size; }

  public boolean isEmpty() { return size == 0; }

  public Position<E> first() { return isEmpty() ? null : header.getNext();  }

  public Position<E> last()  { return isEmpty() ? null : trailer.getPrev(); }

  public Position<E> next(Position<E> p) throws IllegalArgumentException {
    Node<E> n = checkNode(p);
    return n.getNext() == trailer ? null : n.getNext();
  }

  public Position<E> prev(Position<E> p) throws IllegalArgumentException {
    Node<E> n = checkNode(p);
    return n.getPrev() == header ? null : n.getPrev();
  }

  public void addFirst(E elem) {
    Node<E> newNode = new Node<E>(iayf(), header, elem, header.getNext());
    header.getNext().setPrev(newNode);
    header.setNext(newNode);
    size++;
  }

  public void addLast(E elem) {
    Node<E> newNode = new Node<E>(iayf(), trailer.getPrev(), elem, trailer);
    trailer.getPrev().setNext(newNode);
    trailer.setPrev(newNode);
    size++;
  }


  public void addBefore(Position<E> p, E elem) throws IllegalArgumentException {
    Node<E> n = checkNode(p);
    Node<E> newNode = new Node<E>(iayf(), n.getPrev(), elem, n);
    n.getPrev().setNext(newNode);
    n.setPrev(newNode);
    size++;
  }

  public void addAfter(Position<E> p, E elem) throws IllegalArgumentException {
    Node<E> n = checkNode(p);
    Node<E> newNode = new Node<E>(iayf(), n, elem, n.getNext());
    n.getNext().setPrev(newNode);
    n.setNext(newNode);
    size++;
  }

  public E remove(Position<E> p) throws IllegalArgumentException {
    Node<E> n = checkNode(p);
    E e = n.element();
    n.getPrev().setNext(n.getNext());
    n.getNext().setPrev(n.getPrev());
    n.setNext(null);   // help the gc
    n.setPrev(null);
    n.setElem(null);
    size--;
    return e;
  }

  public E set(Position<E> p, E elem) throws IllegalArgumentException {
    Node<E> n = checkNode(p);
    E e = n.element();
    n.setElem(elem);
    return e;
  }

  public Iterator<E> iterator() { return new PositionListIterator<E>(this); }

  // Class-specific methods

  public String toString() {
    String s = "[";
    for (Position<E> cursor = first(); cursor != null; cursor = next(cursor)) {
      if (cursor.element() == null)
          s += "null";
      else
          s += cursor.element().toString();
      if (cursor != last()) s += ", ";
    }
    s += "]";
    return s;
  }

  /* Equals using no iterator */

  /*
  public boolean equals(Object o) {
    if (o == this) return true;
    if (o instanceof PositionList<?>) {
      PositionList<E> l = (PositionList<E>) o;
      Position<E> p, q;
      for (p = this.first(), q = l.first();
           p != null && q != null && p.element().equals(q.element());
           p = this.next(p), q = l.next(q) )
        ;
       return p == q; // both empty or both traversed
    } else return false;  // also, o == null
  }
  */


  /* Equals using iterator */

  public boolean equals(Object o) {
    if (o == this) return true;
    if (o instanceof PositionList<?>) {
      Iterator<E> p = this.iterator();
      Iterator<E> q = ((PositionList<E>)o).iterator();
      boolean equal = true;
      while ( p.hasNext() && q.hasNext() && (equal = p.next().equals(q.next())) )
        ;
      return p.hasNext() == q.hasNext() && equal;
    } else return false;  // also, o == null
  }

  public Object [] toArray() {
    Object [] arr = new Object[this.size()];
    int i = 0;
    Iterator<E> it = this.iterator();
    while (it.hasNext())
      arr[i++] = it.next();
    return arr;
  }
}
