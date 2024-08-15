package 动态数组;

public interface List<E> {
    static final int ELEMENT_NOT_FOUND = -1;
    E remove(int index);
    int indexOf(E element);
    E set(int index, E element);
    E get(int index);
    boolean isEmpty();
    int size();
    void add(E element);
    void clear();
    boolean contains(E element);
    void add(int index, E element);
}
