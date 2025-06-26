package tads.hashTable;

import java.util.Objects;

public class HashEntry<K, V> implements Comparable<HashEntry<K, V>>{
    protected K key;
    protected V value;

    public HashEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HashEntry<?, ?> hashEntry = (HashEntry<?, ?>) o;
        return Objects.equals(key, hashEntry.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }


    @Override
    public int compareTo(HashEntry hashEntry) {
        return 0;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "HashEntry{" +
                "key=" + key +
                '}';
    }

}
