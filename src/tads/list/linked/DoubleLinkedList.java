package tads.list.linked;

import tads.list.List;
import tads.list.Node;

public class DoubleLinkedList<T extends Comparable<T>> implements List<T> {
    private Node<T> head;
    private Node<T> tail;
    private int length;

    public DoubleLinkedList(){
        this.head = null;
        this.tail = null;
        this.length = 0;
    }

    @Override
    public void add(T value){
        Node<T> temp = new Node<>(value);
        if (this.head == null) {
            this.head = temp;
            this.tail = temp;
        }else{
            this.tail.setNext(temp);
            temp.setPrev(this.tail);
            this.tail = temp;
        }
        this.length++;
    }

    @Override
    public void add(T value, int index){
        if (index < 0 || index > this.length) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        if (index == 0) {
            addFirst(value);
        }else{
            Node<T> newNode = new Node<>(value);
            Node<T> temp = this.head;
            for (int i = 0; i < index - 1; i++) {
                temp = temp.getNext();
            }
            newNode.setNext(temp.getNext());
            temp.setNext(newNode);
            newNode.setPrev(temp);
            newNode.getNext().setPrev(newNode);
            this.length++;
        }
    }

    @Override
    public T remove(int index){
        if (index < 0 || index > this.length){
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        Node<T> toRemove = new Node<>();
        if (index == 0){
            Node<T> newHead = this.head.getNext();
            this.head.setNext(null);
            toRemove = this.head;
            this.head = newHead;
            this.head.setPrev(null);
        }else {
            Node<T> temp = this.head;
            for (int i = 0; i < index - 1; i++) {
                temp = temp.getNext();
            }
            toRemove = temp.getNext();
            temp.setNext(toRemove.getNext());
            toRemove.getNext().setPrev(temp);
            toRemove.setNext(null);
            toRemove.setPrev(null);
        }
        this.length--;
        return toRemove.getValue();
    }

    @Override
    public T get(int index){
        if (index < 0 || index > this.length){
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        Node<T> temp = this.head;
        for (int i = 0; i < index; i++) {
            temp = temp.getNext();
        }
        return temp.getValue();
    }

    @Override
    public int length(){
        return this.length;
    }

    @Override
    public boolean isEmpty(){
        return this.length == 0;
    }

    @Override
    public boolean contains(T value){
        Node<T> temp = this.head;
        while (temp != null){
            if (temp.getValue().equals(value)){
                return true;
            }
            temp = temp.getNext();
        }
        return false;
    }

    public void addFirst(T num){
        Node<T> newNode = new Node<>(num);
        newNode.setNext(this.head);
        this.head.setPrev(newNode);
        this.head = newNode;
        this.length++;
    }

    public void addLast(T value){
        Node<T> newNode = new Node<>(value);
        this.tail.setNext(newNode);
        newNode.setPrev(this.tail);
        this.tail = newNode;
        this.length++;
    }

//    public void sort(){
//
//        Node<T> actual = this.head;
//        Node<T> temp;
//        for (int i = 0; i < this.length; i++) {
//            if(actual.siguiente != null && actual.valor.compareTo(actual.siguiente.valor)<=0){
//                temp = actual.siguiente;
//                actual.siguiente = temp.siguiente;
//                temp.siguiente.anterior=actual;
//                temp.siguiente = actual;
//
//            }
//        }
//
//    }

    public void addInOrder(T value){
        Node<T> newNode = new Node<>(value);
        if (this.length == 0){
            addFirst(value);
        }else if (this.head.compareTo(newNode)>=0){     //Si el primer elemento es más grande lo agrego antes
            newNode.setNext(this.head);
            this.head.setPrev(newNode);
            this.head = newNode;
        }else if (this.tail.compareTo(newNode)<=0){     //Si el último elemento es más chico lo agrego después
            this.tail.setNext(newNode);
            newNode.setPrev(this.tail);
            this.tail = newNode;
        }else{
            Node<T> temp = this.head;
            while (temp.getNext() != null && temp.getNext().compareTo(newNode)<=0){
                temp = temp.getNext();
            }
            newNode.setNext(temp.getNext());
            temp.setNext(newNode);
            newNode.setPrev(temp);
            newNode.getNext().setPrev(newNode);
        }
        this.length++;
    }
}
