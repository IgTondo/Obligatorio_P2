package tads.tree;

public class Node<K extends Comparable<K>, T> implements Comparable<Node<K, T>>{
    private K key;
    private T data;
    private Node<K, T> leftChild;
    private Node<K, T> rightChild;

    public Node(K key, T data) {
        this.key = key;
        this.data = data;
        this.leftChild = null;
        this.rightChild = null;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node<K, T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node<K, T> leftChild) {
        this.leftChild = leftChild;
    }

    public Node<K, T> getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node<K, T> rightChild) {
        this.rightChild = rightChild;
    }

    @Override
    public int compareTo(Node<K, T> node){
        return this.key.compareTo(node.key);
    }
}

