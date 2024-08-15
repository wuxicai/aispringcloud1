package 链表;

import 动态数组.AbstractList;
import 动态数组.List;

public class LinkedList<E> extends AbstractList<E> {

    private Node<E> firstNode;
    private static class Node<E>{
        E element;
        Node<E> next;

        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }
    }

    private Node<E> node (int index){
        rangeCheck(index);
        Node<E> node = this.firstNode;
        for (int i = 0; i < index; i++) {
             node = node.next;
        }
        return node;
    }

    @Override
    public void add(int index, E element) {
        //rangeCheckForAdd(index);
        if(index==0){
            firstNode=new Node<>(element,firstNode);
        }else {
            Node<E> prev = node(index - 1);
            prev.next= new Node<>(element, prev.next);
        }
        size++;
    }

    @Override
    public E remove(int index) {
        E element = node(index).element;
        if(index==0){
            firstNode=node(1);
        }else
        node(index-1).next=node(index+1);
        size--;
        return element;
    }

    @Override
    public int indexOf(E element) {
        Node<E> node = this.firstNode;
        if(element==null){

            for (int i = 0; i < size; i++) {
                if (node.element==null) return i;
                node = node.next;
            }
        }else {
            for (int i = 0; i < size; i++) {
                if (node.element.equals(element)) return i;
                node=node.next;
            }
        }

        return ELEMENT_NOT_FOUND;
    }

    @Override
    public E set(int index, E element) {
        E old = node(index).element;
        node(index).element=element;
        return old;
    }

    @Override
    public E get(int index) {
        return node(index).element;
    }

    @Override
    public void clear() {
        size=0;
        firstNode=null;
    }
    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();

        string.append("size=").append(size).append(",[");
        Node<E> node = firstNode;
        for (int i = 0; i < size; i++) {
            string.append(node.element);
            if (i != size - 1) {
                string.append(", ");
            }
            node=node.next;
        }
        string.append("]");
        return string.toString();
    }

}
