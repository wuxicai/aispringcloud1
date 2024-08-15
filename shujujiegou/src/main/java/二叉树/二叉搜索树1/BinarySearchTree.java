package 二叉树.二叉搜索树1;

import lombok.ToString;
import printer.BinaryTreeInfo;

import java.util.*;

@ToString
public class BinarySearchTree<E> implements BinaryTreeInfo {
    private int size;
    private Node<E> root;
    private Comparator<E> comparator;

    public BinarySearchTree() {
        this(null);
    }

    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

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

    //前序遍历：先 父节点
//    public void preorderTraversal() {
//        preorderTraversal(root);
//    }
//
//    public void preorderTraversal(Node<E> node) {
//        if (node == null) return;
//        System.out.println(node.element);
//        preorderTraversal(node.left);
//        preorderTraversal(node.right);
//
//    }
    public void preorderTraversal(Visitor<E> visitor) {
        if (visitor == null) return;
        preorderTraversal(root, visitor);
    }

    public void preorderTraversal(Node<E> node, Visitor<E> visitor) {
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
    public void inorderTraversal(Visitor<E> visitor) {
        inorderTraversal(root, visitor);
    }

    private void inorderTraversal(Node<E> node, Visitor<E> visitor) {
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
    public void postorderTraversal(Visitor<E> visitor) {
        postorderTraversal(root, visitor);
    }

    public void postorderTraversal(Node<E> node, Visitor<E> visitor) {
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
    public void levelOrder(Visitor<E> visitor) {
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
        String parent=((Node<E>) node).parent!=null?((Node<E>) node).parent.element.toString():"";
        return ((Node<E>) node).element+"(p:"+parent+")";
    }

    public static abstract class Visitor<E> {
        boolean stop;

        abstract boolean visit(E element);
    }

    public void add(E element) {
        elementNotNullCheck(element);
        //第一个节点
        if (root == null) {
            root = new Node<>(element, null);
            size++;
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
        Node<E> newNode = new Node<>(element, parent);
        if (cmp > 0) {
            parent.right = newNode;
        } else parent.left = newNode;
        size++;
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
        } else if (node.parent == null) {
            root = null;
        } else {
            if (node == node.parent.left) node.parent.left = null;
            else node.right = null;
        }
    }

    private Node<E> node(E element) {
        Node<E> node = this.root;
        while (node != null) {
            int cmp = compare(element, node.element);
            if (cmp == 0) return node;
            if (cmp > 0) node = node.right;
            else node = node.left;
        }
        return null;
    }

    public boolean contains(E element) {
        return node(element)!=null;
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

    /*
     * 查找前驱节点
     * */
    public Node<E> predesessor(Node<E> node) {
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

    /*
     * 查找后继节点
     * */
    public Node<E> successor(Node<E> node) {
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

    /*
     * 反转二叉树
     * */
    public BinarySearchTree turn() {
        return turnover(this.root);
    }

    private BinarySearchTree turnover(Node<E> node) {
        if (node == null) return null;

        Node temp = node.left;
        node.left = node.right;
        node.right = temp;
        turnover(node.left);
        turnover(node.right);
        BinarySearchTree tree = new BinarySearchTree();
        tree.root = node;
        return tree;
    }

    private void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element must not be null");
        }
    }

    /***
     * 0 两值相等
     * 正数 左大
     * 负数 右大

     */
    private int compare(E e1, E e2) {
        if (comparator != null) {
            return comparator.compare(e1, e2);
        }
        return ((Comparable) e1).compareTo(e2);
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
    }

    //    public boolean isComplete(){
//
//        return false
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
