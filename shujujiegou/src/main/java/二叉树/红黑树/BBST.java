package 二叉树.红黑树;

import java.util.Comparator;

public class BBST<E> extends BST<E> {
    public BBST() {
        this(null);
    }

    public BBST(Comparator comparator) {
        super(comparator);
    }
    protected void rotateLeft(Node<E> grand){
        Node<E> parent=grand.right;
        Node<E> child = parent.left;
        grand.right=child;
        parent.left=grand;
        afterRotate(grand,parent,child);
    }
    protected void rotateRight(Node<E> grand){
        Node<E> parent=grand.left;
        Node<E> child = parent.right;
        grand.left=child;
        parent.right=grand;
        afterRotate(grand,parent,child);
    }
    protected void afterRotate(Node<E> grand,Node<E> parent,Node<E>child){
        parent.parent=grand.parent;
        if (grand.isLeftChild()){
            grand.parent.left=parent;
        }else if(grand.isRightChild()){
            grand.parent.right=parent;
        }else {
            root=parent;
        }
        if (child!=null) child.parent=grand;
        grand.parent=parent;
    }
    protected void rotate(Node<E> r,Node<E> a,Node<E> b,Node<E> c,Node<E> d,Node<E> e,Node<E> f,Node<E> g){
        // d成为子树根节点
        d.parent=r.parent;
        if (r.isLeftChild())r.parent.left=d;
        else if (r.isRightChild())r.parent.right=d;
        else root=d;
        // abc
        b.left=a;
        if (a!=null)a.parent=b;
        b.right=c;
        if (c!=null)c.parent=b;

        // efg
        f.left=e;
        if (e!=null)e.parent=f;
        f.right=g;
        if (g!=null)g.parent=f;


        d.left=b;
        d.right=f;
        b.parent=d;
        f.parent=d;


    }
}
