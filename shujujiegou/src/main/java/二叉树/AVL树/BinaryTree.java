package 二叉树.AVL树;

import printer.BinaryTreeInfo;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree<E>  implements BinaryTreeInfo {
    protected int size;
    protected Node<E> root;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        root=null;
        size=0;

    }
    public static abstract class Visitor<E> {
        boolean stop;

        abstract boolean visit(E element);
    }
    public void preorderTraversal(Visitor<E> visitor) {
        if (visitor == null) return;
        preorderTraversal(root, visitor);
    }

    public void preorderTraversal(Node<E> node,Visitor<E> visitor) {
        if (node == null) return;
        if (visitor.stop) return;
        visitor.stop = visitor.visit(node.element);
        preorderTraversal(node.left, visitor);
        preorderTraversal(node.right, visitor);

    }

    //中序遍历：先 左或右节点
//    public void inorderTraversal() {
//        inorderTraversal(root);
//    }
//
//    public void inorderTraversal(Node<E> node) {
//        if (node == null) return;
//        inorderTraversal(node.left);
//        System.out.println(node.element);
//        inorderTraversal(node.right);
//
//    }
    public void inorderTraversal(BST.Visitor<E> visitor) {
        inorderTraversal(root, visitor);
    }

    public void inorderTraversal(Node<E> node, BST.Visitor<E> visitor) {
        if (node == null || visitor == null) return;
        inorderTraversal(node.left, visitor);
        if (visitor.stop) return;
        visitor.stop = visitor.visit(node.element);
        inorderTraversal(node.right, visitor);

    }

    //后序遍历：最后父节点
//    public void postorderTraversal() {
//        postorderTraversal(root);
//    }
//
//    public void postorderTraversal(Node<E> node) {
//        if (node == null) return;
//        postorderTraversal(node.left);
//
//        postorderTraversal(node.right);
//        System.out.println(node.element);
//
//    }
    public void postorderTraversal(BST.Visitor<E> visitor) {
        postorderTraversal(root, visitor);
    }

    public void postorderTraversal(Node<E> node, BST.Visitor<E> visitor) {
        if (node == null || visitor == null) return;
        postorderTraversal(node.left, visitor);

        postorderTraversal(node.right, visitor);
        if (visitor.stop) return;
        visitor.stop = visitor.visit(node.element);

    }

    //层序遍历：队列先进先出
    public void levelorderTraversal() {
        if (root == null) return;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            System.out.println(node.element);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }

    //遍历接口
    public void levelOrder(BST.Visitor<E> visitor) {
        if (root == null || visitor == null) return;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            if (visitor.visit(node.element)) return;
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }

    }
    protected Node<E> predesessor(Node<E> node) {
        if (node == null) return null;
        Node<E> left = node.left;
        //从左子树种查找
        if (left != null) {
            while (left.right != null) {
                left = left.right;
            }
            return left;
        }
        while (node.parent != null && node == node.parent.left) {
            node = node.parent;
        }
        return node.parent;
    }
    //    }
    public int hight1() {
        if (root == null) return 0;
        int height = 0;
        int levelSize = 1;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            levelSize--;
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
            if (levelSize == 0) {
                levelSize = queue.size();
                height++;
            }
//            queue.size();
        }
        return height;
    }

    public int hight2() {

        return hight(root);
    }

    private int hight(Node<E> node) {
        if (node == null) return 0;
        return 1 + Math.max(hight(node.left), hight(node.right));
    }
    /*
     * 查找后继节点
     * */
    protected Node<E> successor(Node<E> node) {
        if (node == null) return null;
        Node<E> left = node.right;
        //从左子树种查找
        if (left != null) {
            while (left.left != null) {
                left = left.left;
            }
            return left;
        }
        while (node.parent != null && node == node.parent.right) {
            node = node.parent;
        }
        return node.parent;
    }


    public boolean isComplete() {
        if (root == null) return false;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        boolean leaf = false;
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            if (leaf && !node.isLeaf()) return false;
            if (node.left != null) {
                queue.offer(node.left);
            } else if (node.right != null) return false;
            if (node.right != null) {
                queue.offer(node.right);
            } else leaf = true;
        }
        return true;
    }

    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((Node<E>) node).left;
    }

    @Override
    public Object right(Object node) {
        return ((Node<E>) node).right;
    }

    @Override
    public Object string(Object node) {
//        String parent=((Node<E>) node).parent!=null?((Node<E>) node).parent.element.toString():"";
//        return ((Node<E>) node).element+"(p:"+parent+")";
        return node;
    }
    public static class Node<E> {
        E element;
        Node<E> left;
        Node<E> right;
        Node<E> parent;

        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }

        public boolean isLeaf() {
            return left == null && right == null;
        }

        public boolean hasTwoChildren() {
            return left != null && right != null;
        }

        public boolean isLeftChild(){
            return parent!=null&& this==parent.left;
        }
        public boolean isRightChild(){
            return parent!=null&& this==parent.right;
        }
    }
}
