package tads.hashTable;

import tads.exceptions.ElementAlreadyExistsException;
import tads.list.linked.LinkedList;
import java.util.Iterator;

public class OpenHashTable implements HashTable {
    private LinkedList<HashEntry>[] table;
    private int size;
    private int capacity;

    public OpenHashTable() {
        this(10);
    }

    @SuppressWarnings("unchecked")
    public OpenHashTable(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("La capacidad inicial debe ser un número positivo.");
        }
        this.capacity = initialCapacity;
        this.table = new LinkedList[this.capacity];
        this.size = 0;
        for (int i = 0; i < this.capacity; i++) {
            table[i] = new LinkedList<>();
        }
    }

    private int hash(String key) {
        return Math.abs(key.hashCode() % capacity);
    }

    @Override
    public void put(String key, Object value) throws ElementAlreadyExistsException {
        if (key == null) {
            throw new IllegalArgumentException("La clave no puede ser nula.");
        }

        int index = hash(key);
        LinkedList<HashEntry> bucket = table[index];

        for (HashEntry entry : bucket) {
            if (entry.key.equals(key)) {
                throw new ElementAlreadyExistsException();
            }
        }

        bucket.add(new HashEntry(key, value));
        size++;
    }

    @Override
    public boolean contains(String key) {
        if (key == null) {
            return false;
        }

        int index = hash(key);
        LinkedList<HashEntry> bucket = table[index];

        for (HashEntry entry : bucket) {
            if (entry.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void delete(String key) {
        if (key == null) {
            return;
        }

        int index = hash(key);
        LinkedList<HashEntry> bucket = table[index];

        Iterator<HashEntry> iterator = bucket.iterator();
        while (iterator.hasNext()) {
            HashEntry entry = iterator.next();
            if (entry.key.equals(key)) {
                iterator.remove();
                size--;
                return;
            }
        }
    }

    public Object get(String key) {
        if (key == null) {
            return null;
        }

        int index = hash(key);
        LinkedList<HashEntry> bucket = table[index];

        for (HashEntry entry : bucket) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
        }
        return null;
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return capacity;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}