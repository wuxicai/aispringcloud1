package 动态数组;

public abstract class AbstractList<E> implements List<E>{
    protected int size;
    @Override
    public boolean isEmpty() {
        return size==0;
    }
    @Override
    public void add(E element) {
        add(size,element);
    }
    @Override
    public int size() {
        return size;
    }
    @Override
    public boolean contains(E element) {
        return indexOf(element)!=ELEMENT_NOT_FOUND;
    }

    protected void outOfBounds(int index) {
        throw new IndexOutOfBoundsException("index:" + index + ",size:" + size);

    }

    protected void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            outOfBounds(index);
        }
    }

    protected void rangeCheckForAdd(int index) {
        if (index < 0 || index > size) {
            outOfBounds(index);
        }
    }
}
