package tads.list.linked;

import java.util.Iterator;
import java.util.NoSuchElementException;
import tads.list.Node;

public class LinkedListIterator<T extends Comparable<T>> implements Iterator<T> {
    private Node<T> current;
    private Node<T> lastReturned;
    private LinkedList<T> list;

    public LinkedListIterator(Node<T> head, LinkedList<T> list) {
        this.current = head;
        this.lastReturned = null;
        this.list = list;
    }

    @Override
    public boolean hasNext() {
        return current != null;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        lastReturned = current;
        T value = current.getValue();
        current = current.getNext();
        return value;
    }

    @Override
    public void remove() {
        if (lastReturned == null) {
            throw new IllegalStateException("next() has not yet been called, or remove() has already been called after the last call to next().");
        }
        list.remove(lastReturned);
        lastReturned = null; // Resetear lastReturned
    }
}