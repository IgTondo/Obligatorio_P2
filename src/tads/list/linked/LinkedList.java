package tads.list.linked;

import tads.list.List;
import tads.list.Node;
import tads.tree.heap.HeapArray;

public class LinkedList<T extends Comparable<T>> implements List<T> {
    private Node<T> head;
    private Node<T> tail;
    private int length;

    public LinkedList(){
        this.head = null;
        this.tail = null;
        this.length = 0;
    }

    @Override
    public void add(T value){
        Node<T> nuevo = new Node<>(value);
        if (this.head == null) {
            this.head = nuevo;
            this.tail = nuevo;
        }else{
            this.tail.setNext(nuevo);
            this.tail = nuevo;
        }
        this.length++;
    }

    @Override
    public void add(T value, int index){
        if (index < 0 || index > this.length) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        if (index == 0) {
            addFirst(value);
        }else{
            Node<T> nuevo = new Node<>(value);
            Node<T> temp = this.head;
            for (int i = 0; i < index - 1; i++) {
                temp = temp.getNext();
            }
            nuevo.setNext(temp.getNext());
            temp.setNext(nuevo);
            this.length++;
        }
    }

    @Override
    public T remove(int index){
        if (index < 0 || index > this.length){
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        Node<T> oldHead;
        if (index == 0){
            oldHead = removeFirst();
        }else {
            Node<T> temp = this.head;
            for (int i = 0; i < index - 1; i++) {
                temp = temp.getNext();
            }
            oldHead = temp.getNext();
            temp.setNext(temp.getNext().getNext());
            oldHead.setNext(null);
            this.length--;
        }
        return oldHead.getValue();
    }

    public void remove(Node<T> n){
        if (!this.contains(n.getValue())){
            throw new RuntimeException("El nodo no se encuentra en la lista.");
        }
        Node<T> temp = this.head;
        for (int i = 0; i < this.length; i++) {
            if (temp.equals(n)){
                this.remove(i);
                return;
            }
            temp = temp.getNext();
        }

    }

    public Node<T> removeFirst(){
        Node<T> newHead = this.head.getNext();
        Node<T> oldHead = this.head;
        this.head.setNext(null);
        this.head = newHead;
        this.length--;
        return oldHead;
    }

    @Override
    public T get(int index){
        if (index < 0 || index >= this.length){
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        Node<T> temp = this.head;
        for (int i = 0; i < index; i++) {
            temp = temp.getNext();
        }
        return temp.getValue();
    }

    @Override
    public int length(){
        return this.length;
    }

    @Override
    public boolean isEmpty(){
        return this.length == 0;
    }

    @Override
    public boolean contains(T value){
        Node<T> temp = this.head;
        Node<T> node = new Node<>(value);
        while (temp != null){
            if (temp.equals(node)){
                return true;
            }
            temp = temp.getNext();
        }
        return false;
    }

    @Override
    public void addFirst(T value){
        Node<T> nuevo = new Node<>(value);
        nuevo.setNext(this.head);
        this.head = nuevo;
        this.length++;
    }

    @Override
    public void addLast(T value){
        Node<T> nuevo = new Node<>(value);
        this.tail.setNext(nuevo);
        this.tail = nuevo;
        this.length++;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        Node<T> temp = this.head;
        while (temp != null){
            sb.append(temp.getValue());
            sb.append(" ");
            temp = temp.getNext();
        }
        return sb.toString();
    }

    /*
    Mejor caso: O(n)      // 1 pasada, n-1 comparaciones
    Peor caso: O(n^2)     // n-1 pasadas, n(n-1)/2 comparaciones
    Espacio: O(1)
     */
    public void bubbleSort(){
        boolean sorted;
        for (int i = 0; i < this.length-1; i++) {
            sorted = false;
            for (int j = 0; j < this.length-1-i; j++) {
                if (this.get(j).compareTo(this.get(j+1)) > 0){
                    T n = this.remove(j);
                    this.add(n, j+1);
                    sorted = true;
                }
            }
            if (!sorted){break;}
        }
    }

    public void oddEven(){
        boolean sorted = false;
        while (!sorted) {
            sorted = true;
            for (int i = 1; i < this.length - 1; i += 2) {
                if (this.get(i).compareTo(this.get(i + 1)) > 0) {
                    T n = this.remove(i);
                    this.add(n, i + 1);
                    sorted = false;
                }
            }
            for (int i = 0; i < length - 1; i += 2) {
                if (this.get(i).compareTo(this.get(i + 1)) > 0) {
                    T n = this.remove(i);
                    this.add(n, i + 1);
                    sorted = false;
                }
            }
        }
    }

    /*
    Mejor y peor: O(n^2)  // n-1 pasadas y comparaciones iguales
    Bueno para pocos elementos
     */
    public void selectionSort(){
        boolean sorted;
        for (int i = 0; i < this.length-1; i++) {
            sorted = false;
            for (int j = i; j < this.length-1; j++) {
                if (this.get(j).compareTo(this.get(j+1)) > 0){
                    T n = this.remove(j);
                    this.add(n, j+1);
                    sorted = true;
                }
            }
            if (!sorted){break;}
        }
    }

    /*
    Mejor caso: O(n)      // arreglo ya ordenado
    Peor caso: O(n^2)     // arreglo invertido
     */
    public void insertionSort(){
        for (int i = 1; i < this.length; i++) {
            for (int j = i; j > 0; j--) {
                if (this.get(j).compareTo(this.get(j-1))<0){
                    T n = this.remove(j);
                    this.add(n,j-1);
                }
            }
        }
    }

    public void mergeSort() {
        this.head = sort(this.head);
        // update tail reference
        this.tail = updateTail(this.head);
    }

    private Node<T> sort(Node<T> head) {
        if (head == null || head.getNext() == null) {
            return head;
        }

        Node<T> middle = getMiddle(head);
        Node<T> nextToMiddle = middle.getNext();
        middle.setNext(null);  // split the list into two halves

        Node<T> left = sort(head);
        Node<T> right = sort(nextToMiddle);

        return merge(left, right);
    }

    private Node<T> merge(Node<T> left, Node<T> right) {
        if (left == null) return right;
        if (right == null) return left;

        Node<T> result;

        if (left.compareTo(right) <= 0) {
            result = left;
            result.setNext(merge(left.getNext(), right));
        } else {
            result = right;
            result.setNext(merge(left, right.getNext()));
        }

        return result;
    }

    private Node<T> getMiddle(Node<T> head) {
        if (head == null) return head;

        Node<T> slow = head;
        Node<T> fast = head;

        while (fast.getNext() != null && fast.getNext().getNext() != null) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }

        return slow;
    }

    private Node<T> updateTail(Node<T> head) {
        if (head == null) return null;
        Node<T> temp = head;
        while (temp.getNext() != null) {
            temp = temp.getNext();
        }
        return temp;
    }

    /*
    Promedio: O(n log n)
    Peor caso: O(n^2)
    Muy usado por su eficiencia práctica y uso de poca memoria
     */
    public void quickSort() {
        this.head = sortQuick(this.head);
        this.tail = updateTail(this.head);
    }

    private Node<T> sortQuick(Node<T> start) {
        if (start == null || start.getNext() == null) {
            return start;
        }

        T pivot = start.getValue();

        // Sublist heads and tails
        Node<T> lessHead = null, lessTail = null;
        Node<T> equalHead = null, equalTail = null;
        Node<T> greaterHead = null, greaterTail = null;

        Node<T> current = start;

        while (current != null) {
            Node<T> next = current.getNext(); // store next node
            current.setNext(null); // isolate current node

            int cmp = current.getValue().compareTo(pivot);
            if (cmp < 0) {
                if (lessHead == null) lessHead = lessTail = current;
                else {
                    lessTail.setNext(current);
                    lessTail = current;
                }
            } else if (cmp == 0) {
                if (equalHead == null) equalHead = equalTail = current;
                else {
                    equalTail.setNext(current);
                    equalTail = current;
                }
            } else {
                if (greaterHead == null) greaterHead = greaterTail = current;
                else {
                    greaterTail.setNext(current);
                    greaterTail = current;
                }
            }

            current = next;
        }

        // Recursively sort less and greater parts
        lessHead = sortQuick(lessHead);
        greaterHead = sortQuick(greaterHead);

        // Concatenate all three parts: less + equal + greater
        return concatenate(concatenate(lessHead, equalHead), greaterHead);
    }

    private Node<T> concatenate(Node<T> a, Node<T> b) {
        if (a == null) return b;
        Node<T> temp = a;
        while (temp.getNext() != null) {
            temp = temp.getNext();
        }
        temp.setNext(b);
        return a;
    }

    /*
    Mejor y peor: O(n log n)
    Espacio: O(1)
     */
    public void heapSort() {
        if (this.length <= 1) return;

        // Crear heap mínimo usando ArrayList
        HeapArray<T> heap = new HeapArray<>(true); // true = min heap

        // Agregar todos los elementos del LinkedList al heap
        Node<T> temp = this.head;
        while (temp != null) {
            heap.add(temp.getValue());
            temp = temp.getNext();
        }

        // Vaciar la lista actual
        this.head = null;
        this.tail = null;
        this.length = 0;

        // Insertar de nuevo en orden desde el heap
        while (heap.size() > 0) {
            this.add(heap.pop());
        }
    }

}
