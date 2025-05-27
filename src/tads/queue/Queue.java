package tads.queue;

import tads.exceptions.EmptyQueueException;
import tads.list.Node;

public class Queue<T extends Comparable<T>> implements MyQueue<T> {

    private Node<T> head;
    private Node<T> tail;
    private int length;

    public Queue() {
        this.length = 0;
        this.tail = null;
        this.head = null;
    }

    public Node<T> getHead() {
        return head;
    }

    public Node<T> getTail() {
        return tail;
    }

    public int getLength() {
        return length;
    }

    public void setHead(Node<T> head) {
        this.head = head;
    }

    public void setTail(Node<T> tail) {
        this.tail = tail;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void enqueue(T element){
        Node<T> nuevo = new Node<>(element);
        if (this.length == 0){
            this.tail = nuevo;
        }
        nuevo.setNext(this.head);
        this.head = nuevo;
        this.length++;
    }

    public T dequeue() throws EmptyQueueException {
        if (this.isEmpty()){ throw new EmptyQueueException();}
        return poll();
    }

    public T poll(){
        if (this.isEmpty()){ return null;}
        if (this.length == 1){
            T res = this.head.getValue();
            this.length = 0;
            this.tail = null;
            this.head = null;
            return res;
        }
        Node<T> temp = this.head;
        for (int i = 1; i < this.length-1; i++) {
            temp = temp.getNext();
        }
        T res = temp.getNext().getValue();
        temp.setNext(null);
        this.tail = temp;
        this.length--;
        return res;
    }

    public boolean isEmpty(){
        return this.length == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<T> temp = this.head;
        while (temp != null) {
            sb.append(temp.getValue()).append(" ");
            temp = temp.getNext();
        }
        return sb.toString();
    }

    public int sum(){
        if (!(this.head.getValue() instanceof Number)){
            return 0;
        }
        int sum = 0;
        Node<T> temp = this.head;
        for (int i = 0; i < this.length; i++) {
            int value = ((Number) temp.getValue()).intValue();
            sum += value;
            temp = temp.getNext();
        }
        return sum;
    }
}
