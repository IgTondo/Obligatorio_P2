package tads.list;

import java.util.Arrays;

public class ArrayList<T extends Comparable<T>> implements List<T>{
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

    // Adds to the end
    @Override
    public void add(T value) {
        ensureCapacity();
        data[length++] = value;
    }

    // Adds at a specific index
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

    // Gets the element at index
    public T get(int index) {
        checkIndex(index);
        return data[index];
    }

    // Sets value at index
    public void set(int index, T value) {
        checkIndex(index);
        data[index] = value;
    }

    // Removes the element at index
    public T remove(int index) {
        checkIndex(index);
        T removed = data[index];
        for (int i = index; i < length - 1; i++) {
            data[i] = data[i + 1];
        }
        data[--length] = null;
        return removed;
    }

    // Returns current length
    @Override
    public int length() {
        return length;
    }

    // Checks if list is empty
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


    // Internal capacity increase
    private void ensureCapacity() {
        if (length == data.length) {
            int newCapacity = data.length * 2;
            data = Arrays.copyOf(data, newCapacity);
        }
    }

    // Validates index
    private void checkIndex(int index) {
        if (index < 0 || index >= length)
            throw new IndexOutOfBoundsException("Index: " + index);
    }

    // String representation
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
}

