package tads.list;

public interface List<T> {
    public void add(T num);
    public void add(T num, int index);
    public T get(int index);
    public T remove(int index);
    public int length();
    public boolean isEmpty();
    public boolean contains(T num);
    public void addFirst(T num);
    public void addLast(T num);

}
