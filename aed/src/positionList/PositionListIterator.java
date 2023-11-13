package positionList;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.lang.UnsupportedOperationException;

public class PositionListIterator<E> implements Iterator<E> {

  private PositionList<E> list;
  private Position<E> cursor;

  public PositionListIterator(PositionList<E> list) {
    this.list   = list;
    this.cursor = list.first();
  }

  public boolean hasNext() { return cursor != null; }

  public E next() throws NoSuchElementException {
    if (cursor == null) throw new NoSuchElementException();
    E e = cursor.element();
    cursor = list.next(cursor);
    return e;
  }

  public void remove() {
    // students implement this one
    throw new UnsupportedOperationException();
  }
}
