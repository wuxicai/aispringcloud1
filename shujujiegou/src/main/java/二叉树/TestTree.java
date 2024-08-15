package 二叉树;

import org.junit.Test;
import printer.BinaryTrees;
import 二叉树.AVL树.AVLTree;
import 二叉树.二叉搜索树1.BinarySearchTree;
import 二叉树.二叉树搜索树.BinaryTree;
import 二叉树.红黑树.RBTree;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

public class TestTree {
    public static class PersonComparator implements Comparator<Person> {

        @Override
        public int compare(Person person1, Person person2) {
            return person1.getAge() - person2.getAge();
        }
    }

    public static class PersonComparator2 implements Comparator<Person> {

        @Override
        public int compare(Person person1, Person person2) {
            return person2.getAge() - person1.getAge();
        }
    }

    @Test
    public void test1() {
        Set<?> set = new TreeSet<>();
        Integer[] data = {7, 4, 9, 2, 1, 5, 8, 11};
//        BST<Integer> bst = new BST();
//        for (Integer datum : data) {
//            bst.add(datum);
//        }
        BinarySearchTree<Person> bst2 = new BinarySearchTree(new PersonComparator());
        bst2.add(new Person(12));
        bst2.add(new Person(22));
        BinarySearchTree<Person> bst3 = new BinarySearchTree(new PersonComparator2());
        bst2.add(new Person(12));
        bst2.add(new Person(22));
        BinarySearchTree<Person> bst4 = new BinarySearchTree();
        bst2.add(new Person(12));
        bst2.add(new Person(22));
        //匿名类
        BinarySearchTree<Person> bst5 = new BinarySearchTree((Comparator<Person>) (o1, o2) -> o1.getAge() - o2.getAge());
        bst2.add(new Person(12));
        bst2.add(new Person(22));
        System.out.println(bst2 + "  " + bst2.size() + "      " + bst2.isEmpty());
    }

    @Test
    public void test2() {
        Integer[] data = {7, 4, 9, 2, 1, 5, 8, 11, 0};
        BinarySearchTree<Integer> bst = new BinarySearchTree();
        for (Integer datum : data) {
            bst.add(datum);
        }

//        bst.levelOrder(new BinaryTree.Visitor<Integer>() {
//            public void visit(Integer element){
//                System.out.println(element);
//            }
//
//        });
        System.out.println();
        System.out.println(bst.isComplete());
        System.out.println(bst.turn());
        System.out.println(bst);
        System.out.println(bst.hight2());
        System.out.println(bst.hight1());
    }

    int i = 3;

    public void test4(int i) {
        if (i == 0) return;
        System.out.println(i);
        test4(i - 1);

    }

    @Test
    public void test3() {
        test4(i);
        int a = 18;
        if (a > 10) System.out.println("ff");
        else if (a > 5) System.out.println("tt");
    }

    @Test
    public void test5() {
        Integer[] data = {7, 1, 4, 9, 2, 1, 5, 8, 11, 0, 3, 33, 22, 12};
        BinarySearchTree<Integer> bst = new BinarySearchTree();
        for (Integer datum : data) {
            bst.add(datum);
        }
        BinaryTrees.println(bst);

        BinaryTrees.println(bst.turn());
        System.out.println(bst);
        System.out.println("-----------------------------------------");
        System.out.println(bst.turn());
    }

    @Test
    public void test6() {
        Integer[] data = {7, 4, 19, 2, 1, 5, 8, 11, 0, 3, 33, 22, 12, 23, 34, 6, 24, 25};
        Integer[] data1 = {7, 6, 2};
        BinarySearchTree<Integer> bst = new BinarySearchTree();
        for (Integer datum : data1) {
            bst.add(datum);
        }
        BinaryTrees.println(bst);
        System.out.println("-------------------------------------------------------------");
        bst.remove(4);
        BinaryTrees.println(bst);

    }

    @Test
    public void test7() {
        Integer[] data = {7, 4, 19, 2, 1, 5, 8, 11, 0, 3, 33, 22, 12, 23, 34, 6, 24, 25};
        Integer[] data1 = {85, 19, 69, 3, 7, 99, 95, 2, 1, 70, 44, 58, 11, 21, 14, 93, 57, 4, 56};
        AVLTree<Integer> bst = new AVLTree<>();
        BinarySearchTree<Integer> bst1 = new BinarySearchTree();
        for (Integer datum : data1) {
            bst.add(datum);
            BinaryTrees.println(bst);
            System.out.println("--------------------------------------------------------------------");
            bst1.add(datum);
        }

        BinaryTrees.println(bst);
        System.out.println("-------------------------------------------------------------");
        BinaryTrees.println(bst1);

    }

    @Test
    public void test8() {
        Integer[] data1 = {85, 19, 69, 3, 7, 99, 95, 2, 1, 70, 44, 58, 11, 21, 14, 93, 57, 4, 56};
        AVLTree<Integer> bst = new AVLTree<>();
        BinarySearchTree<Integer> bst1 = new BinarySearchTree();
        for (Integer datum : data1) {
            bst.add(datum);
            bst1.add(datum);
        }
        BinaryTrees.println(bst);
        System.out.println("-------------------------------------------------------------");
        bst.remove(95);
        BinaryTrees.println(bst);
        System.out.println("-------------------------------------------------------------");
    }

    @Test
    public void test9() {
        Integer[] data1 = {12, 58, 4, 44, 47, 35, 57, 45, 94, 49, 56};
        RBTree<Integer> rb = new RBTree<>();
        for (Integer datum : data1) {
            rb.add(datum);
        }
        BinaryTrees.println(rb);
        rb.remove(58);
        BinaryTrees.println(rb);
//        for (int i = 0; i < data1.length; i++) {
//            rb.remove(data1[i]);
//            System.out.println("==="+data1[i]+"===");
//            BinaryTrees.println(rb);
//        }
    }

    public String test10(String s, String t) {
        if (t.length() > s.length()) {
            return "";
        }
        String substr = "";
        boolean containsAll=false;
        for (int i = t.length(); i <= s.length(); i++) {
            for (int j = 0; j - i < s.length(); j++) {
                substr = s.substring(j, j + i);
                for (int m = 0; m < t.length(); m++) {
                    if (!substr.contains(t.substring(m, m + 1))) {
                        break;
                    }
                }
                if (containsAll){
                    return substr;
                }
            }
        }
        return "";
    }

    @Test
    public void test12() {
        String a = "fu92fh3u320h339032f8328fh832h8dh832h88h3hdh31dwajovndsuavy8rnwbvrueqhoralfajkfewfw";
        String b = "92fh";
        String s = test10(a, b);
        System.out.println(s);
    }
}
