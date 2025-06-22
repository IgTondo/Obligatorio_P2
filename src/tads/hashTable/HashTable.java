package tads.hashTable;
import tads.exceptions.ElementAlreadyExistsException;

public interface HashTable<K, V> {
    void put(K key, V value) throws ElementAlreadyExistsException;
    boolean contains(K key);
    void delete(K key);
}
