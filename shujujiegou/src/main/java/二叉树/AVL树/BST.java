package 二叉树.AVL树;

import lombok.ToString;

import java.util.Comparator;

@ToString
@SuppressWarnings("unchecked")
public class BST<E> extends BinaryTree<E> {
    private Comparator<E> comparator;
    public BST() {
        this(null);
    }

    public BST(Comparator<E> comparator) {
        this.comparator = comparator;
    }
    private int compare(E e1, E e2) {
        if (comparator != null) {
            return comparator.compare(e1, e2);
        }
        return ((Comparable) e1).compareTo(e2);
    }
    public void add(E element) {
        elementNotNullCheck(element);
        //第一个节点
        if (root == null) {
            root = createNode(element, null);
            size++;
            afterAdd(root);
            return;
        }
        Node<E> parent = null;
        Node<E> node = this.root;
        int cmp = 0;
        while (node != null) {
            cmp = compare(element, node.element);
            parent = node;
            if (cmp > 0) {
                node = node.right;
            } else if (cmp < 0) {
                node = node.left;
            } else {
                //相等则让新元素替换旧的元素，覆盖为了对象是最新的
                node.element = element;
                return;
            }
        }
        Node<E> newNode = createNode(element, parent);
        if (cmp > 0) {
            parent.right = newNode;
        } else parent.left = newNode;
        size++;
        afterAdd(newNode);
    }
    protected void afterAdd(Node<E> node){}
    protected void afterRemove(Node<E> node){}
    protected Node<E> createNode(E element, Node<E> parent){
        return new Node<>(element, parent);
    }
    public boolean contains(E element) {
        return node(element)!=null;
    }
    protected Node<E> node(E element) {
        Node<E> node = this.root;
        while (node != null) {
            int cmp = compare(element, node.element);
            if (cmp == 0) return node;
            if (cmp > 0) node = node.right;
            else node = node.left;
        }
        return null;
    }
    public void remove(E element) {
        remove(node(element));
    }

    private void remove(Node<E> node) {
        if (node == null) return;
        size--;
        if (node.hasTwoChildren()) {
            //后继节点
            Node<E> s = successor(node);
            node.element = s.element;//后继节点的元素覆盖被删除的节点元素
            //删除后继节点
            node = s;
        }
        //此处的node的度必然是1或0
        Node<E> replacement = node.left != null ? node.left : node.right;
        if (replacement != null) {
            replacement.parent = node.parent;
            //node 度为1 且是跟节点
            if (node.parent == null) {
                root = replacement;
            } else if (node == node.parent.left) node.parent.left = replacement;
            else node.parent.right = replacement;
            afterRemove(node);
        } else if (node.parent == null) {
            root = null;
            afterRemove(node);
        } else {
            if (node == node.parent.left) node.parent.left = null;
            else node.parent.right = null;
            afterRemove(node);
        }
    }



    /*
     * 反转二叉树
     * */
    public BST turn() {
        return turnover(this.root);
    }
    private BST turnover(Node<E> node) {
        if (node == null) return null;

        Node temp = node.left;
        node.left = node.right;
        node.right = temp;
        turnover(node.left);
        turnover(node.right);
        BST tree = new BST();
        tree.root = node;
        return tree;
    }

    private void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element must not be null");
        }
    }
}
