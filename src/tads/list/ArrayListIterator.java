package tads.list;

import java.util.Iterator;

public class ArrayListIterator<T> implements Iterator<T> {

    private T[] array;
    private int pointerToLastValue;
    private int currentPosition;


    public ArrayListIterator(T[] array, int pointerToLastValue) {
        this.array = array;
        this.pointerToLastValue = pointerToLastValue;
        this.currentPosition = 0;
    }


    @Override
    public boolean hasNext() {
        return (currentPosition < pointerToLastValue);
    }

    @Override
    public T next() {
        T valueToReturn = this.array[currentPosition];
        currentPosition++;
        return valueToReturn;
    }

}
