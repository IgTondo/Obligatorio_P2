package tads.queue;

import tads.exceptions.EmptyQueueException;

public interface MyQueue<T extends Comparable<T>>{
    void enqueue(T element);
    T dequeue() throws EmptyQueueException;
    boolean isEmpty();
}
