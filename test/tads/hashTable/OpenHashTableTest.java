package tads.hashTable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import tads.exceptions.ElementAlreadyExistsException;

public class OpenHashTableTest {

    private OpenHashTable hashTable;

    // Este método se ejecuta antes de cada prueba.
    // Asegura que cada prueba comience con una tabla hash limpia.
    @BeforeEach
    void setUp() {
        hashTable = new OpenHashTable(10);
    }

    @Test
    @DisplayName("Debe agregar elementos correctamente y aumentar el tamaño")
    void testPutAndSize() throws ElementAlreadyExistsException {
        assertEquals(0, hashTable.size());
        assertTrue(hashTable.isEmpty());

        hashTable.put("key1", "value1");
        assertEquals(1, hashTable.size());
        assertFalse(hashTable.isEmpty());
        assertTrue(hashTable.contains("key1"));
        assertEquals("value1", hashTable.get("key1"));

        hashTable.put("key2", 123);
        assertEquals(2, hashTable.size());
        assertTrue(hashTable.contains("key2"));
        assertEquals(123, hashTable.get("key2"));
    }

    @Test
    @DisplayName("Debe lanzar ElementAlreadyExistsException al agregar clave duplicada")
    void testPutDuplicateKeyThrowsException() throws ElementAlreadyExistsException {
        hashTable.put("testKey", "initialValue");
        assertEquals(1, hashTable.size());

        // Verifica que se lance la excepción cuando se intenta agregar la misma clave
        assertThrows(ElementAlreadyExistsException.class, () -> {
            hashTable.put("testKey", "newValue");
        });
        // Asegura que el tamaño no cambie y el valor original permanezca
        assertEquals(1, hashTable.size());
        assertEquals("initialValue", hashTable.get("testKey"));
    }

    @Test
    @DisplayName("Debe devolver true para una clave existente")
    void testContainsExistingKey() throws ElementAlreadyExistsException {
        hashTable.put("apple", "red");
        assertTrue(hashTable.contains("apple"));
    }

    @Test
    @DisplayName("Debe devolver false para una clave no existente")
    void testContainsNonExistingKey() {
        assertFalse(hashTable.contains("banana"));
    }

    @Test
    @DisplayName("Debe eliminar un elemento correctamente y reducir el tamaño")
    void testDeleteExistingKey() throws ElementAlreadyExistsException {
        hashTable.put("removeMe", "data");
        assertEquals(1, hashTable.size());
        assertTrue(hashTable.contains("removeMe"));

        hashTable.delete("removeMe");
        assertEquals(0, hashTable.size());
        assertFalse(hashTable.contains("removeMe"));
        assertNull(hashTable.get("removeMe"));
    }

    @Test
    @DisplayName("No debe hacer nada al intentar eliminar una clave no existente")
    void testDeleteNonExistingKey() throws ElementAlreadyExistsException {
        hashTable.put("existing", "value");
        assertEquals(1, hashTable.size());

        hashTable.delete("nonExisting"); // No debería causar error ni cambiar el tamaño
        assertEquals(1, hashTable.size());
        assertTrue(hashTable.contains("existing"));
    }

    @Test
    @DisplayName("Debe obtener el valor correcto para una clave existente")
    void testGetExistingKey() throws ElementAlreadyExistsException {
        hashTable.put("productA", 10.5);
        hashTable.put("productB", "description");
        assertEquals(10.5, hashTable.get("productA"));
        assertEquals("description", hashTable.get("productB"));
    }

    @Test
    @DisplayName("Debe devolver null para una clave no existente en get")
    void testGetNonExistingKey() {
        assertNull(hashTable.get("randomKey"));
    }

    @Test
    @DisplayName("Debe manejar colisiones y aun así almacenar y recuperar datos")
    void testCollisions() throws ElementAlreadyExistsException {
        // Asumiendo que 10 y 0 colisionan (hashCode % 10 = 0)
        // O elige claves que sabes que colisionarán con tu hash function
        hashTable.put("key_a", "value_a"); // hash = ... % 10 = X
        hashTable.put("key_b", "value_b"); // hash = ... % 10 = X (colisiona con key_a)
        hashTable.put("key_c", "value_c"); // hash = ... % 10 = Y (otro bucket)

        // Nota: Si tu función hash es muy simple, puedes forzar colisiones fácilmente.
        // Por ejemplo, si usas key.hashCode() % capacity:
        // "clave0" -> hash 0
        // "clave10" -> hash 0
        // "clave20" -> hash 0
        hashTable.put("clave0", "valor0");
        hashTable.put("clave10", "valor10"); // Colisiona con "clave0"

        assertEquals(5, hashTable.size()); // 2 + 3 anteriores
        assertTrue(hashTable.contains("key_a"));
        assertTrue(hashTable.contains("key_b"));
        assertTrue(hashTable.contains("clave0"));
        assertTrue(hashTable.contains("clave10"));

        assertEquals("valor0", hashTable.get("clave0"));
        assertEquals("valor10", hashTable.get("clave10"));
        assertEquals("value_a", hashTable.get("key_a"));
        assertEquals("value_b", hashTable.get("key_b"));

        hashTable.delete("clave0");
        assertEquals(4, hashTable.size());
        assertFalse(hashTable.contains("clave0"));
        assertTrue(hashTable.contains("clave10")); // "clave10" sigue ahí
        assertEquals("valor10", hashTable.get("clave10"));

        hashTable.put("clave0", "nuevoValor0"); // Volvemos a agregarla
        assertEquals(5, hashTable.size());
        assertTrue(hashTable.contains("clave0"));
        assertEquals("nuevoValor0", hashTable.get("clave0"));
    }

    @Test
    @DisplayName("Debe inicializarse con la capacidad correcta")
    void testInitialCapacity() {
        OpenHashTable customHashTable = new OpenHashTable(5);
        assertEquals(5, customHashTable.capacity());
        assertEquals(0, customHashTable.size());
        assertTrue(customHashTable.isEmpty());
    }

    @Test
    @DisplayName("Debe lanzar IllegalArgumentException para capacidad no positiva")
    void testInvalidInitialCapacity() {
        assertThrows(IllegalArgumentException.class, () -> new OpenHashTable(0));
        assertThrows(IllegalArgumentException.class, () -> new OpenHashTable(-5));
    }

    @Test
    @DisplayName("Debe manejar claves nulas en contains")
    void testContainsNullKey() {
        assertFalse(hashTable.contains(null));
    }

    @Test
    @DisplayName("Debe manejar claves nulas en get")
    void testGetNullKey() {
        assertNull(hashTable.get(null));
    }

    @Test
    @DisplayName("No debe hacer nada al intentar eliminar una clave nula")
    void testDeleteNullKey() throws ElementAlreadyExistsException {
        hashTable.put("somekey", "somevalue");
        hashTable.delete(null); // No debe lanzar excepción ni cambiar el estado
        assertEquals(1, hashTable.size());
        assertTrue(hashTable.contains("somekey"));
    }

    @Test
    @DisplayName("Debe lanzar IllegalArgumentException al agregar clave nula")
    void testPutNullKeyThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            hashTable.put(null, "someValue");
        });
        assertEquals(0, hashTable.size()); // Asegura que no se haya agregado nada
    }

    // --- Pruebas adicionales para escenarios específicos ---

    @Test
    @DisplayName("Debe funcionar correctamente después de múltiples adiciones y eliminaciones")
    void testComplexOperations() throws ElementAlreadyExistsException {
        for (int i = 0; i < 100; i++) {
            hashTable.put("key" + i, "value" + i);
        }
        assertEquals(100, hashTable.size());

        for (int i = 0; i < 100; i += 2) { // Eliminar pares
            hashTable.delete("key" + i);
        }
        assertEquals(50, hashTable.size()); // 100 - 50

        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) { // Las claves pares deberían haber sido eliminadas
                assertFalse(hashTable.contains("key" + i));
                assertNull(hashTable.get("key" + i));
            } else { // Las impares deberían seguir ahí
                assertTrue(hashTable.contains("key" + i));
                assertEquals("value" + i, hashTable.get("key" + i));
            }
        }

        // Volver a agregar algunas claves eliminadas
        hashTable.put("key0", "newValue0");
        hashTable.put("key2", "newValue2");
        assertEquals(52, hashTable.size());
        assertTrue(hashTable.contains("key0"));
        assertEquals("newValue0", hashTable.get("key0"));
        assertTrue(hashTable.contains("key2"));
        assertEquals("newValue2", hashTable.get("key2"));
    }
}