package 动态数组;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;


public class ArrayList<E> extends AbstractList<E>{
    private E[] elements;
    private static final int DEFAULT_CAPATICY = 2;
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i]=null;
        }
        size = 0;
    }

    public void add(int index, E element) {
        if(element==null) return;
        rangeCheckForAdd(index);
        ensureCapaticy(size + 1);
        for (int i = size - 1; i >= index; i--) {
            elements[i + 1] = elements[i];
        }
        elements[index] = element;
        size++;
    }

    private void ensureCapaticy(int capaticy) {
        int old = elements.length;
        if (old >= capaticy) return;
        int newCapaticy = old + (old >> 1);
        E[] newElements = (E[]) new Object[newCapaticy];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;
        System.out.println(old + "扩容为：" + newCapaticy);
    }

    public ArrayList(int capaticy) {
        capaticy = capaticy < DEFAULT_CAPATICY ? DEFAULT_CAPATICY : capaticy;
        elements = (E[]) new Object[capaticy];
    }

    public ArrayList() {
        this(DEFAULT_CAPATICY);
    }
    public E get(int index) {
        rangeCheck(index);
        return elements[index];
    }

    public E set(int index, E element) {
        rangeCheck(index);
        E old = elements[index];
        elements[index] = element;
        return old;
    }

    public int indexOf(E element) {
        if(element==null){
            for (int i = 0; i < size; i++) {
                if (elements[i]==null) return i;
            }
        }else {
            for (int i = 0; i < size; i++) {
                if (element.equals(elements[i])) return i;
            }
        }

        return ELEMENT_NOT_FOUND;
    }
    public E remove(int index) {
        rangeCheck(index);
        E old = elements[index];
        for (int i = index + 1; i < size; i++) {
            elements[i - 1] = elements[i];

        }
        elements[--size]=null;
        return old;
    }
    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();

        string.append("size=").append(size).append(",[");
        for (int i = 0; i < size; i++) {
            string.append(elements[i]);
            if (i != size - 1) {
                string.append(", ");
            }
        }
        string.append("]");
        return string.toString();
    }
}
