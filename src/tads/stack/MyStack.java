package tads.stack;

import tads.exceptions.EmptyStackException;

public interface MyStack<T> {
    T pop () throws EmptyStackException;
    T top() throws EmptyStackException;
    void push(T element);
    boolean isEmpty ();
    void makeEmpty();
}
