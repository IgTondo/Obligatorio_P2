package tads.tree;

import tads.list.ArrayList;

public interface MyTree<K extends Comparable<K>, T> {
    T find(K key);
    void insert(K key, T data, K parentKey);
    void delete(K key);

    int size();
    int countLeaf();
    int countCompleteElements();

    ArrayList<K> inOrder();
    ArrayList<K> preOrder();
    ArrayList<K> postOrder();
    ArrayList<K> levelOrder();

//    void loadPostFijaExpression(String sPostFija);
}
