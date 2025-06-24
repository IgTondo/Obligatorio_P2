package tads.list;

import java.util.Arrays;
import java.util.Iterator;

public class ArrayList<T extends Comparable<T>> implements List<T>, Iterable<T> {
    private T[] data;
    private int length;
    private static final int INITIAL_CAPACITY = 10;

    public ArrayList() {
        data = (T[]) new Comparable[INITIAL_CAPACITY];
        length = 0;
    }

    public ArrayList(int capacidad){
        data = (T[]) new Comparable[capacidad];
        length = 0;
    }

    @Override
    public void add(T value) {
        ensureCapacity();
        data[length++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > length)
            throw new IndexOutOfBoundsException("Index: " + index);

        ensureCapacity();
        for (int i = length; i > index; i--) {
            data[i] = data[i - 1];
        }
        data[index] = value;
        length++;
    }

    public T get(int index) {
        checkIndex(index);
        return data[index];
    }

    public void set(int index, T value) {
        checkIndex(index);
        data[index] = value;
    }

    public T remove(int index) {
        checkIndex(index);
        T removed = data[index];
        for (int i = index; i < length - 1; i++) {
            data[i] = data[i + 1];
        }
        data[--length] = null;
        return removed;
    }

    @Override
    public int length() {
        return length;
    }

    public boolean isEmpty() {
        return length == 0;
    }

    public boolean contains(T value) {
        for (int i = 0; i < length; i++) {
            if (data[i].equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void addFirst(T value) {
        add(value, 0);
    }

    @Override
    public void addLast(T value) {
        add(value);
    }

    private void ensureCapacity() {
        if (length == data.length) {
            int newCapacity = data.length * 2;
            data = Arrays.copyOf(data, newCapacity);
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= length)
            throw new IndexOutOfBoundsException("Index: " + index);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < length; i++) {
            sb.append(data[i]);
            if (i < length - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayListIterator<>(this.data, this.length);
    }


    public void sort() {
        quickSort(0, length - 1);
    }

    private void quickSort(int low, int high) {
        if (low < high) {
            int pivotIndex = partition(low, high);
            quickSort(low, pivotIndex - 1);
            quickSort(pivotIndex + 1, high);
        }
    }

    private int partition(int low, int high) {
        T pivot = data[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (data[j].compareTo(pivot) <= 0) {
                i++;
                swap(i, j);
            }
        }

        swap(i + 1, high);
        return i + 1;
    }

    private void swap(int i, int j) {
        T temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }
}
