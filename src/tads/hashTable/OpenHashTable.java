package tads.hashTable;

import tads.exceptions.ElementAlreadyExistsException;
import tads.list.ArrayList;
import tads.list.linked.LinkedList;

import java.util.Arrays;
import java.util.Iterator;

public class OpenHashTable<K, V extends Comparable<V>> implements HashTable<K, V> {
    private static final int DEFAULT_CAPACITY = 16; // Power of 2 is good for hashing
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private LinkedList<HashEntry<K, V>>[] table;
    private int size;
    private int capacity;
    private float loadFactor;

    public OpenHashTable() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public OpenHashTable(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }

    @SuppressWarnings("unchecked")
    public OpenHashTable(int initialCapacity, float loadFactor) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("La capacidad inicial debe ser un número positivo.");
        }
        if (loadFactor <= 0 || Float.isNaN(loadFactor)) {
            throw new IllegalArgumentException("El factor de carga debe ser un número positivo.");
        }

        this.capacity = roundUpToPowerOf2(initialCapacity);
        this.loadFactor = loadFactor;
        this.table = new LinkedList[this.capacity];
        this.size = 0;
    }

    private int roundUpToPowerOf2(int n) {
        if (n <= 0) return 1; // Or throw IllegalArgumentException
        n--;
        n |= n >> 1;
        n |= n >> 2;
        n |= n >> 4;
        n |= n >> 8;
        n |= n >> 16;
        return n + 1;
    }


    private int hash(K key) {
        return ((key == null ? 0 : key.hashCode()) & (capacity - 1));
    }

    @Override
    public void put(K key, V value) throws ElementAlreadyExistsException {
        if (key == null) {
            throw new IllegalArgumentException("La clave no puede ser nula.");
        }

        if ((float) size / capacity >= loadFactor) {
            resize(capacity * 2); // Double capacity
        }

        int index = hash(key);
        LinkedList<HashEntry<K, V>> bucket = table[index];

        if (bucket == null) { // If this bucket is empty (first time access)
            bucket = new LinkedList<>();
            table[index] = bucket; // Store the new LinkedList in the table
//            bucket.add(new HashEntry<>(key, value));
//            size++;
//            return;
        }
        // Check for existing key only if the bucket already exists
        for (HashEntry<K, V> entry : bucket) {
            if (entry.key.equals(key)) {
                throw new ElementAlreadyExistsException();
            }
        }

        bucket.add(new HashEntry<>(key, value));
        size++;
    }

    @Override
    public boolean contains(K key) {
        if (key == null) {
            return false;
        }

        int index = hash(key);
        LinkedList<HashEntry<K, V>> bucket = table[index];

        if (bucket == null){
            return false;
        }else {
            for (HashEntry<K, V> entry : bucket) {
                if (entry.key.equals(key)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void delete(K key) {
        if (key == null) {
            return;
        }

        int index = hash(key);
        LinkedList<HashEntry<K, V>> bucket = table[index];

        if (bucket != null){
            Iterator<HashEntry<K, V>> iterator = bucket.iterator();
            while (iterator.hasNext()) {
                HashEntry<K, V> entry = iterator.next();
                if (entry.key.equals(key)) {
                    iterator.remove();
                    size--;
                    return;
                }
            }
        }
    }

    public V get(K key) {
        if (key == null) {
            return null;
        }

        int index = hash(key);
        LinkedList<HashEntry<K, V>> bucket = table[index];

        if (bucket == null){
            return null;
        }

        for (HashEntry<K, V> entry : bucket) {
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

    public ArrayList<V> getValues() {
        ArrayList<V> listToReturn = new ArrayList<>(this.size);

        LinkedList<HashEntry<K,V>> listForPosition = null;
        for (LinkedList<HashEntry<K, V>> hashEntries : table) {
            if (hashEntries != null) {
                listForPosition = hashEntries;
                for (HashEntry<K, V> hs : listForPosition) {
                    listToReturn.add(hs.value);
                }
            }
        }

        return listToReturn;
    }

    @SuppressWarnings("unchecked")
    private void resize(int newCapacity) {
        System.out.println("Resize");
        int oldCapacity = this.capacity;
        LinkedList<HashEntry<K, V>>[] oldTable = this.table;

        this.capacity = newCapacity;
        this.table = new LinkedList[this.capacity];
        this.size = 0;

        for (int i = 0; i < oldCapacity; i++) {
            if (oldTable[i] == null){ continue;}
            for (HashEntry<K, V> entry : oldTable[i]) {
                put(entry.key, entry.value);
            }
        }
    }

    @Override
    public String toString() {
        return "OpenHashTable{" +
                "table=" + Arrays.toString(table) +
                '}';
    }
}