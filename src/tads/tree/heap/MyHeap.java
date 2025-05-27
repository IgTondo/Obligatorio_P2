package tads.tree.heap;

public interface MyHeap<T extends Comparable<T>> {
    void add(T valor);
    T pop();
    int size();
    void print(); // imprime la estructura del heap
}
