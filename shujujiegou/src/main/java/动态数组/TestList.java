package 动态数组;

import org.junit.Test;
import 链表.LinkedList;

import java.awt.*;

public class TestList {
    @Test
    public void test1(){
        ArrayList<Integer> list= new ArrayList();
        list.add(1);
        list.add(3);
        list.add(1);
        list.add(2);
        list.remove(2);
        list.add(2,2003);
        list.add(1,20033);
        list.set(4,333);
        System.out.println(list);

    }
    @Test
    public void test2(){
        ArrayList<Person> list= new ArrayList();
        for (int i = 0; i < 100; i++) {
            list.add(new Person(1,"王菲"));
        }
        System.out.println(list);
        System.out.println(list.indexOf(new Person(1,"王菲")));
        list.clear();
        System.gc();
    }
    @Test
    public void test3(){
        List<Person> list= new LinkedList<>();
        list.add(new Person(43,"彼得堡"));
        for (int i = 0; i < 3; i++) {

            list.add(new Person(1,"王菲"));
        }
        list.add(new Person(32,"非生物"));
        list.add(new Person(43,"飞洒啊"));
        System.out.println(list);
        System.out.println(list.indexOf(new Person(1,"王菲")));
        System.out.println(list.get(5));
        System.out.println(list.size());
        list.remove(0);
        System.out.println(list.size());
    }


}
