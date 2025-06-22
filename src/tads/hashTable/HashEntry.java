package tads.hashTable;

import java.util.Objects;

public class HashEntry<K, V> implements Comparable<HashEntry<K, V>>{
    K key;
    V value;

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

    @Override
    public String toString() {
        return "HashEntry{" +
                "key=" + key +
                '}';
    }
}
