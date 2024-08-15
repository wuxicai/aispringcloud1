package 二叉树.红黑树;

import java.util.Comparator;

public class RBTree<E> extends BBST<E> {
    private static final boolean RED=false;
    private static final boolean BLACK=true;
    public RBTree() {
        this(null);
    }

    public RBTree(Comparator comparator) {
        super(comparator);
    }

    @Override
    protected void afterRemove(Node<E> node) {

        if (isRed(node)){
            black(node);
            return;
        }
        Node<E> parent = node.parent;
        if (parent==null)return;
        //删除黑色叶子节点
        boolean left=parent.left==null||node.isLeftChild();
        Node<E> sibling = left ? parent.right : parent.left;

        if (left){//被删除的节点在左边，兄弟节点在右边

            if (isRed(sibling)){//兄弟节点是红色
                black(sibling);
                red(parent);
                rotateLeft(parent);
                //更换兄弟
                sibling=parent.right;
            }
            //兄弟节点必然是黑色
            if (isBlack(sibling.left)&&isBlack(sibling.right)){//兄弟节点没有红色子节点，父节点向下合并
                boolean parentBlack=isBlack(parent);
                black(parent);
                red(sibling);
                if (parentBlack)afterRemove(parent);
            }else {//兄弟节点有红色子节点,借元素
                if (isBlack(sibling.right)){
                    rotateRight(sibling);
                    sibling=parent.right;
                }
                color(sibling,colorOf(parent));
                black(sibling.right);
                black(parent);
                rotateLeft(parent);
            }

        }else {//被删除的节点在右边，兄弟节点在左边
            if (isRed(sibling)){//兄弟节点是红色
                black(sibling);
                red(parent);
                rotateRight(parent);
                //更换兄弟
                sibling=parent.left;
            }
            //兄弟节点必然是黑色
            if (isBlack(sibling.left)&&isBlack(sibling.right)){//兄弟节点没有红色子节点，父节点向下合并
                boolean parentBlack=isBlack(parent);
                black(parent);
                red(sibling);
                if (parentBlack)afterRemove(parent);
            }else {//兄弟节点有红色子节点,借元素
                if (isBlack(sibling.left)){
                    rotateLeft(sibling);
                    sibling=parent.left;
                }
                color(sibling,colorOf(parent));
                black(sibling.left);
                black(parent);
                rotateRight(parent);
            }
        }
    }
//    protected void afterRemove(Node<E> node,Node<E> replacement) {
//        if (isRed(node)) return;
//
//        if (isRed(replacement)){
//            black(replacement);
//            return;
//        }
//        Node<E> parent = node.parent;
//        if (parent==null)return;
//        //删除黑色叶子节点
//        boolean left=parent.left==null||node.isLeftChild();
//        Node<E> sibling = left ? parent.right : parent.left;
//
//        if (left){//被删除的节点在左边，兄弟节点在右边
//
//            if (isRed(sibling)){//兄弟节点是红色
//                black(sibling);
//                red(parent);
//                rotateLeft(parent);
//                //更换兄弟
//                sibling=parent.right;
//            }
//            //兄弟节点必然是黑色
//            if (isBlack(sibling.left)&&isBlack(sibling.right)){//兄弟节点没有红色子节点，父节点向下合并
//                boolean parentBlack=isBlack(parent);
//                black(parent);
//                red(sibling);
//                if (parentBlack)afterRemove(parent,null);
//            }else {//兄弟节点有红色子节点,借元素
//                if (isBlack(sibling.right)){
//                    rotateRight(sibling);
//                    sibling=parent.right;
//                }
//                color(sibling,colorOf(parent));
//                black(sibling.right);
//                black(parent);
//                rotateLeft(parent);
//            }
//
//        }else {//被删除的节点在右边，兄弟节点在左边
//            if (isRed(sibling)){//兄弟节点是红色
//                black(sibling);
//                red(parent);
//                rotateRight(parent);
//                //更换兄弟
//                sibling=parent.left;
//            }
//            //兄弟节点必然是黑色
//            if (isBlack(sibling.left)&&isBlack(sibling.right)){//兄弟节点没有红色子节点，父节点向下合并
//                boolean parentBlack=isBlack(parent);
//                black(parent);
//                red(sibling);
//                if (parentBlack)afterRemove(parent,null);
//            }else {//兄弟节点有红色子节点,借元素
//                if (isBlack(sibling.left)){
//                    rotateLeft(sibling);
//                    sibling=parent.left;
//                }
//                color(sibling,colorOf(parent));
//                black(sibling.left);
//                black(parent);
//                rotateRight(parent);
//            }
//        }
//    }

    @Override
    protected void afterAdd(Node<E> node) {
        Node<E> parent = node.parent;
        if (parent==null){
            black(node);
            return;
        }
        if (isBlack(parent))return;
        Node<E> uncle = parent.sibling();
        Node<E> grand = parent.parent;
        //叔父节点是红色，上溢
        if (isRed(uncle)){
            black(parent);
            black(uncle);
            afterAdd(red(grand));
            return;
        }
        //叔父节点不是红色
        if (parent.isLeftChild()){
            red(grand);
            if (node.isLeftChild()){
                black(parent);
            }else {
                black(node);
                rotateLeft(parent);
            }
            rotateRight(grand);
        }else {
            red(grand);
            if (node.isLeftChild()){
                black(node);
                rotateRight(parent);
            }else {
                black(parent);
            }
            rotateLeft(grand);
        }

    }

    private Node<E> color(Node<E> node,boolean color){
        if (node==null)return node;
        ((RBNode<E>)node).color=color;
        return node;
    }

    private Node<E> red(Node<E> node){
        return color(node,RED);
    }

    private Node<E> black(Node<E> node){
        return color(node,BLACK);
    }

    private boolean colorOf(Node<E> node){
        return node==null?BLACK:((RBNode<E>)node).color;
    }

    private boolean isBlack(Node<E> node){
        return colorOf(node)==BLACK;
    }

    private boolean isRed(Node<E> node){
        return colorOf(node)==RED;
    }

    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new RBNode<E>(element, parent);
    }

    private static class RBNode<E> extends Node<E>{
        boolean color=RED;
        public RBNode(E element, Node<E> parent) {
            super(element, parent);
        }
        @Override
        public String toString(){
            String str="";
            if (color==RED){
                str="R_";
            }
            return str+element.toString();
        }
    }

}
