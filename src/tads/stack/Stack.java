package tads.stack;

import tads.exceptions.EmptyStackException;

public class Stack<T> implements MyStack<T> {
    public static class Node<T>{
        private T value;
        private Node<T> next;

        public Node(T value){
            this.value = value;
            this.next = null;
        }

        public T getValue(){
            return this.value;
        }

        public Node<T> getNext() {
            return this.next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }

        public void setValue(T value) {
            this.value = value;
        }
    }

    private Node<T> head;
    private int length;

    public Stack() {
        this.head = null;
        this.length = 0;
    }

    public Node<T> getHead() {
        return head;
    }

    public void setHead(Node<T> head) {
        this.head = head;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public T pop() throws EmptyStackException {
        if (this.isEmpty()){
            throw new EmptyStackException();
        }
        T temp = this.head.getValue();
        this.head = this.head.getNext();
        this.length--;
        return temp;
    }

    public T top() throws EmptyStackException{
        if (this.isEmpty()){
            throw new EmptyStackException();
        }
        return  this.head.getValue();
    }

    public void push(T element){
        Node<T> newNode = new Node<>(element);
        newNode.setNext(this.head);
        this.head = newNode;
        this.length++;
    }

    public boolean isEmpty(){
        return (this.length == 0);
    }

    public void makeEmpty(){
        if (this.isEmpty()){
            return;
        }

        while(this.length > 0){
            try {
                this.pop();
            }catch (Exception e){return;}
        }
        this.length = 0;
    }
}
