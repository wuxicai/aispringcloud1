package 二叉树.AVL树;

import 二叉树.二叉搜索树1.BinarySearchTree;

import java.util.Comparator;

public class AVLTree<E> extends BST<E> {
    public AVLTree() {
        this(null);
    }

    public AVLTree(Comparator comparator) {
        super(comparator);
    }

    @Override
    protected void afterAdd(Node<E> node) {
        while ((node=node.parent)!=null){
            if (isBalance(node)){
                updateHight(node);
            }else {
                rebalance(node);
                break;
            }

        }
    }
    @Override
    protected void afterRemove(Node<E> node){
        while ((node=node.parent)!=null){
            if (isBalance(node)){
                updateHight(node);
            }else {
                rebalance(node);
            }
        }
    }

    @Override
    protected Node<E> createNode(Object element, Node parent) {
        return new AVLNode(element, parent);
    }
    private boolean isBalance(Node<E> node){
        return Math.abs(((AVLNode<E>)node).balanceFactor())<=1;

    }
    private void updateHight(Node<E> node){
        ((AVLNode<E>) node).updateHight();
    }
    /*
    * node :高度最低的不平衡节点
    *
    * */
    private void rebalance2(Node<E> grand){
        Node<E> parent=((AVLNode<E>)grand).tallerChild();
        Node<E> node=((AVLNode<E>)parent).tallerChild();
        if (parent.isLeftChild()){
            if (node.isLeftChild()){
                rotateRight(grand);
            }else {
                rotateLeft(parent);
                rotateRight(grand);
            }
        }else {
            if (node.isLeftChild()){
                rotateRight(parent);
                rotateLeft(grand);
            }else {
                rotateLeft(grand);
            }
        }
    }
    /*
     * node :高度最低的不平衡节点
     *
     * */
    private void rebalance(Node<E> grand){
        Node<E> parent=((AVLNode<E>)grand).tallerChild();
        Node<E> node=((AVLNode<E>)parent).tallerChild();
        if (parent.isLeftChild()){
            if (node.isLeftChild()){
                rotate(grand,node.left,node,node.right,parent,parent.right,grand,grand.right);
            }else {
                rotate(grand,parent.left,parent,node.left,node,node.right,grand,grand.right);
            }
        }else {
            if (node.isLeftChild()){
                rotate(grand,grand.left,grand,node.left,node,node.right,parent,parent.right);
            }else {
                rotate(grand,grand.left,grand,parent.left,parent,node.left,node,node.right);
            }
        }

    }
    private void rotate(Node<E> r,Node<E> a,Node<E> b,Node<E> c,Node<E> d,Node<E> e,Node<E> f,Node<E> g){
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
        updateHight(b);
        // efg
        f.left=e;
        if (e!=null)e.parent=f;
        f.right=g;
        if (g!=null)g.parent=f;
        updateHight(f);

        d.left=b;
        d.right=f;
        b.parent=d;
        f.parent=d;
        updateHight(d);

    }
    private void rotateLeft(Node<E> grand){
        Node<E> parent=grand.right;
        Node<E> child = parent.left;
        grand.right=child;
        parent.left=grand;
        afterRotate(grand,parent,child);
    }
    private void rotateRight(Node<E> grand){
        Node<E> parent=grand.left;
        Node<E> child = parent.right;
        grand.left=child;
        parent.right=grand;
        afterRotate(grand,parent,child);
    }
    private void afterRotate(Node<E> grand,Node<E> parent,Node<E>child){
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
        updateHight(grand);
        updateHight(parent);
    }
    private static class AVLNode<E> extends Node<E>{
        int hight=1;
        public AVLNode(E element, Node<E> parent) {
            super(element, parent);
        }
        public int balanceFactor(){
            int leftHight=left==null?0:((AVLNode<E>)left).hight;
            int rightHight=right==null?0:((AVLNode<E>)right).hight;
            return leftHight-rightHight;
        }
        private void updateHight(){
            int leftHight=left==null?0:((AVLNode<E>)left).hight;
            int rightHight=right==null?0:((AVLNode<E>)right).hight;
            hight=1+Math.max(leftHight,rightHight);
        }
        private Node<E> tallerChild(){
            int leftHight=left==null?0:((AVLNode<E>)left).hight;
            int rightHight=right==null?0:((AVLNode<E>)right).hight;
            if (leftHight>rightHight) return left;
            if (leftHight<rightHight) return right;
            return isLeftChild()?left:right;
        }
        @Override
        public String toString() {
//        String parent=((Node<E>) node).parent!=null?((Node<E>) node).parent.element.toString():"";
//        return ((Node<E>) node).element+"(p:"+parent+")";
//            return node;
            String parentString="null";
            if (parent!=null) parentString=parent.element.toString();
            return element+"_p("+parentString+")_h("+hight+")";
        }
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        toString(root, sb, "");
        return sb.toString();
    }
    public void toString(Node<E> node, StringBuilder sb, String prefix) {
        if (node == null) return;
        sb.append(prefix).append(node.element).append("\n");
        toString(node.left, sb, prefix + "L---");
        toString(node.right, sb, prefix + "R---");
    }
}
