package tads.hashTable;

import java.util.Objects;

public class HashEntry implements Comparable<HashEntry>{
    String key;
    Object value;

    public HashEntry(String key, Object value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HashEntry hashEntry = (HashEntry) o;
        return Objects.equals(key, hashEntry.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }

    @Override
    public int compareTo(HashEntry h){
        return this.key.compareTo(h.key);
    }
}
