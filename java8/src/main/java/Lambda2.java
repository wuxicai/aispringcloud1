import org.junit.Test;

import java.util.*;
import java.util.function.Consumer;

/*
* lambda表达式语法  箭头操作符 “ -> ”
* 左侧 ：lambda参数列表
*
* 右侧 ：lambda体（功能）
*
* 语法格式一： 无参数  无返回值  （）-> system.out.printLn("fdfd")
*
* 语法格式二： 一个参数  无返回值  （d）-> system.out.printLn("fdfd")  参数小括号可以省略
*
* 语法格式三： 多个参数  体中有多条语句（体必须有花括号）  有返回值  （d）-> system.out.printLn("fdfd")
*
* 语法格式四： 多个参数  体中有一条语句（体不必须有花括号，return可以省略）  有返回值  （d）-> system.out.printLn("fdfd")
*
* 语法格式五： 参数列表的数据类型可以不写，因为JVM通过上下文推断出数据类型，叫做类型推断
*
* 左右遇一括号省 左侧推断类型省 lambda表达式需要函数式接口的支持：接口中只有一个抽象方法时
* ，@FunctionalInterface注解可以检查接口是不是函数式接口
*
* */
public class Lambda2 {
    @Test
    public void test1(){
        int num=1;
        Runnable r= new Runnable() {
            @Override
            public void run() {
                System.out.println("hello"+num);
            }
        };
        r.run();


        System.out.println("-------------------------------------------");

        Runnable r1=()-> System.out.println("hello");//语法糖，匿名内部类
        r1.run();
    }
    @Test
    public void test2(){
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s.length());
            }
        };
        consumer.accept("fdsfds");

        Consumer con=e-> System.out.println(e+"吴喜财");
        con.accept("fdjsjlkjljfe");
    }
    @Test
    public void test3(){
        Comparator<Integer> cp= (x, y)-> {
            System.out.println("比较");
            return Integer.compare(x,y);
        };

        cp.compare(3,4);
    }
    List<Employee> list= Arrays.asList(
            new Employee("非生物1",23,7855),
            new Employee("非生物2",26,7583),
            new Employee("非生物3",63,3588),
            new Employee("非生物4",33,78366),
            new Employee("非生物5",33,1244)
    );

    @Test
    public void test4(){
        Collections.sort(list,(a,b)->{
            if(a.getAge()==b.getAge()){
                return a.getName().compareTo(b.getName());
            }else return -Integer.compare(a.getAge(),b.getAge());
        });

        for (Employee employee : list) {
            System.out.println(employee);
        }
    }





    public String strHandler(String s,MyFunction mf) {
        return mf.getValue(s);
    }
    @Test
    public void test5(){
        String s1 = strHandler(" fd  sfewFFEhuhuH  hH", (m) -> m.replace(" ",""));
        String s2 = strHandler(" fd  sfewFFEhuhuH  hH", (m) -> m.toUpperCase());
        System.out.println(s2);
    }

    @Test
    public void test6(){
        longHandler(43l,22l,(x,y)->(x+y)*2);
        longHandler(232l,232l,(x,y)-> x*y);

    }
    public void longHandler(long s,long m,MyFunction2<Long,Long> mf) {
        System.out.println(mf.getValue(s,m));
    }


    @Test
    public void tewst(){
        String a= null+"";
    }
}
