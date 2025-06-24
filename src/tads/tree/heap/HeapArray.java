package tads.tree.heap;

import tads.list.ArrayList;
import tads.list.List;

public class HeapArray<T extends Comparable<T>> implements MyHeap<T>{
    private ArrayList<T> heap;
    private boolean isMinHeap;

    public HeapArray(boolean isMinHeap) {
        this.heap = new ArrayList<>();
        this.isMinHeap = isMinHeap;
    }

    // Agrega un elemento al heap
    public void add(T valor) {
        heap.add(valor);
        upHeap(heap.length() - 1);
    }

    // Elimina y devuelve la raíz (mínimo o máximo)
    public T pop() {
        if (heap.isEmpty()) {
            throw new RuntimeException("El heap está vacío.");
        }

        T raiz = heap.get(0);

        if (heap.length() == 1) {
            // Si solo hay un elemento, simplemente lo removemos
            heap.remove(0);
        } else {
            // Tomar el último elemento
            T ultimo = heap.remove(heap.length() - 1);
            // Ponerlo en la raíz
            heap.set(0, ultimo);  // CORRECCIÓN: usar set() en lugar de add()
            // Reordenar hacia abajo
            downHeap(0);
        }

        return raiz;
    }

    // Devuelve el elemento raíz sin removerlo
    public T peek() {
        if (heap.isEmpty()) {
            throw new RuntimeException("El heap está vacío.");
        }
        return heap.get(0);
    }

    // Verifica si el heap está vacío
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    // Devuelve el tamaño actual
    public int size() {
        return heap.length();
    }

    // Muestra el heap como lista
    public void print() {
        System.out.println(heap);
    }

    // ============ Métodos auxiliares ============

    private void upHeap(int index) {
        while (index > 0) {
            int padre = (index - 1) / 2;
            if (compare(heap.get(index), heap.get(padre))) {
                swap(index, padre);
                index = padre;
            } else {
                break;
            }
        }
    }

    private void downHeap(int index) {
        int size = heap.length();
        while (index < size) {
            int izquierdo = 2 * index + 1;
            int derecho = 2 * index + 2;
            int seleccionado = index;

            if (izquierdo < size && compare(heap.get(izquierdo), heap.get(seleccionado))) {
                seleccionado = izquierdo;
            }
            if (derecho < size && compare(heap.get(derecho), heap.get(seleccionado))) {
                seleccionado = derecho;
            }

            if (seleccionado != index) {
                swap(index, seleccionado);
                index = seleccionado;
            } else {
                break;
            }
        }
    }

    private boolean compare(T a, T b) {
        return isMinHeap ? a.compareTo(b) < 0 : a.compareTo(b) > 0;
    }

    private void swap(int i, int j) {
        T temp = heap.get(i);
        heap.set(i, heap.get(j));  // CORRECCIÓN: usar set() en lugar de add()
        heap.set(j, temp);         // CORRECCIÓN: usar set() en lugar de add()
    }
}