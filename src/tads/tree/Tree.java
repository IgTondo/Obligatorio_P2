package tads.tree;

import tads.list.ArrayList;
import tads.queue.Queue;
import tads.stack.Stack;

public class Tree<K extends Comparable<K>, T> implements MyTree<K, T> {
    private Node<K, T> root;

    @Override
    public void insert(K key, T data, K parentKey) {
        if (root == null) {
            root = new Node<>(key, data);
        } else {
            insertRecursive(root, key, data, parentKey);
        }
    }

    private boolean insertRecursive(Node<K, T> current, K key, T data, K parentKey) {
        if (current == null) return false;

        if (current.getKey().equals(parentKey)) {
            if (current.getLeftChild() == null) {
                current.setLeftChild(new Node<>(key, data));
            } else if (current.getRightChild() == null) {
                current.setRightChild(new Node<>(key, data));
            } else { return false;}
            return true;
        }

        return insertRecursive(current.getLeftChild(), key, data, parentKey) ||
                insertRecursive(current.getRightChild(), key, data, parentKey);
    }

    @Override
    public T find(K key) {
        return findRecursive(root, key);
    }

    private T findRecursive(Node<K, T> node, K key) {
        if (node == null) return null;
        if (node.getKey().equals(key)) return node.getData();

        T leftResult = findRecursive(node.getLeftChild(), key);
        return (leftResult != null) ? leftResult : findRecursive(node.getRightChild(), key);
    }

    @Override
    public void delete(K key) {
        root = deleteRecursive(root, key);
    }

    private Node<K, T> deleteRecursive(Node<K, T> node, K key) {
        if (node == null) return null;

        if (node.getKey().equals(key)) {
            return null; // elimina el nodo
        }

        node.setLeftChild(deleteRecursive(node.getLeftChild(), key));
        node.setRightChild(deleteRecursive(node.getRightChild(), key));
        return node;
    }

    @Override
    public int size() {
        return sizeRecursive(root);
    }

    private int sizeRecursive(Node<K, T> node) {
        if (node == null) return 0;
        return 1 + sizeRecursive(node.getLeftChild()) + sizeRecursive(node.getRightChild());
    }

    @Override
    public int countLeaf() {
        return countLeafRecursive(root);
    }

    private int countLeafRecursive(Node<K, T> node) {
        if (node == null) return 0;
        if (node.getLeftChild() == null && node.getRightChild() == null) return 1;
        return countLeafRecursive(node.getLeftChild()) + countLeafRecursive(node.getRightChild());
    }

    @Override
    public int countCompleteElements() {
        return countCompleteRecursive(root);
    }

    private int countCompleteRecursive(Node<K, T> node) {
        if (node == null) return 0;
        int count = (node.getLeftChild() != null && node.getRightChild() != null) ? 1 : 0;
        return count + countCompleteRecursive(node.getLeftChild()) + countCompleteRecursive(node.getRightChild());
    }

    @Override
    public ArrayList<K> inOrder() {
        ArrayList<K> result = new ArrayList<>();
        inOrderRecursive(root, result);
        return result;
    }

    private void inOrderRecursive(Node<K, T> node, ArrayList<K> result) {
        if (node != null) {
            inOrderRecursive(node.getLeftChild(), result);
            result.add(node.getKey());
            inOrderRecursive(node.getRightChild(), result);
        }
    }

    @Override
    public ArrayList<K> preOrder() {
        ArrayList<K> result = new ArrayList<>();
        preOrderRecursive(root, result);
        return result;
    }

    private void preOrderRecursive(Node<K, T> node, ArrayList<K> result) {
        if (node != null) {
            result.add(node.getKey());
            preOrderRecursive(node.getLeftChild(), result);
            preOrderRecursive(node.getRightChild(), result);
        }
    }

    @Override
    public ArrayList<K> postOrder() {
        ArrayList<K> result = new ArrayList<>();
        postOrderRecursive(root, result);
        return result;
    }

    private void postOrderRecursive(Node<K, T> node, ArrayList<K> result) {
        if (node != null) {
            postOrderRecursive(node.getLeftChild(), result);
            postOrderRecursive(node.getRightChild(), result);
            result.add(node.getKey());
        }
    }

    @Override
    public ArrayList<K> levelOrder() {
        ArrayList<K> result = new ArrayList<>();
        if (root == null) return result;

        Queue<Node<K, T>> queue = new Queue<>();
        queue.enqueue(root);

        while (!queue.isEmpty()) {
            Node<K, T> current = queue.poll();
            result.add(current.getKey());
            if (current.getLeftChild() != null) queue.enqueue(current.getLeftChild());
            if (current.getRightChild() != null) queue.enqueue(current.getRightChild());
        }
        return result;
    }

//    @Override
//    public void loadPostFijaExpression(String sPostFija) {
//        Stack<String> stack = new Stack<>();
//        String[] tokens = sPostFija.trim().split("\\s+");
//        for (String token : tokens){
//            stack.push(token);
//        }
//
//        for (String token : tokens) {
//            if (isOperator(token)) {
//
//                Node<String, String> right = stack.pop();
//                Node<String, String> left = stack.pop();
//                Node<String, String> operator = new Node<>(token, token);
//                operator.setLeftChild(left);
//                operator.setRightChild(right);
//                stack.push(operator);
//            } else {
//                stack.push(new Node<>(token, token));
//            }
//        }
//
//        // Cast genérico: asumimos que el árbol es de tipo <String, String>
//        this.root = (Node<K, T>) stack.pop();
//    }
//
//    private boolean isOperator(String token) {
//        return "+-*/".contains(token);
//    }
}
