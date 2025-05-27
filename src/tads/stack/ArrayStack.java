package tads.stack;

import tads.exceptions.EmptyStackException;

import java.util.ArrayList;

public class ArrayStack<T> implements MyStack<T> {
    private ArrayList<T> stack;
    private int length;

    public ArrayStack() {
        this.stack = new ArrayList<>(100000);
        this.length = 0;
    }

    public int getLength() {
        return length;
    }

    public T pop() throws EmptyStackException {
        if (this.isEmpty()){
            throw new EmptyStackException();
        }
        this.length--;
        return this.stack.removeLast();
    }

    public T top() throws EmptyStackException{
        if (this.isEmpty()){
            throw new EmptyStackException();
        }
        return this.stack.getLast();
    }

    public void push(T element){
        this.stack.add(element);
        this.length++;
    }

    public boolean isEmpty(){
        return (this.length == 0);
    }

    public void makeEmpty(){
        if (this.isEmpty()){
            return;
        }
        while(!this.stack.isEmpty()){
            this.stack.removeLast();
        }
        this.length = 0;
    }

}
