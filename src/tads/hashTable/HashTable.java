package tads.hashTable;
import tads.exceptions.ElementAlreadyExistsException;

public interface HashTable {
    void put(String key, Object value) throws ElementAlreadyExistsException;
    boolean contains(String key);
    void delete(String key);
}
