import org.junit.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.*;

/*
* 方法引用：若lambda体中的内容有方法已经实现了，我们可以使用方法引用，（lambda表达式的另外一种表现形式）
* 主要有三种语法格式(lambda体中调用方法的参数列表和返回值要与函数式接口中抽象方法的参数列表和返回值类型保持一致)
*
* 对象：：实例方法名
*
* 类：：静态方法名
*
* 类：：实例方法名
*
*
* 构造器引用：调用的构造器的参数列表要和函数式接口中的抽象方法参数列表保持一致
*
* 类名：：new
*
*
* 数组引用：type ：：new
*
* */
public class fangfayinyong {

    @Test
    public void test8(){
        Function<Integer,String[]> fun= x->new String[x];
        String[] apply = fun.apply(4);
        System.out.println(apply.length);
        Function<Integer,Employee[]> fun1=Employee[]::new;
        System.out.println(fun1.apply(343).length);
    }

    @Test
    public void test6(){
        Supplier<Employee> sup = ()-> new Employee();
        Supplier<Employee> sup1 = Employee::new;//构造器引用（无参构造器）
    }
    @Test
    public void test7(){
        Function<String,Employee> sup = (x)-> new Employee();
        Function<String,Employee> sup1=Employee::new;
        System.out.println(sup.apply("fefw"));//一个参数构造器
        System.out.println(sup1.apply("fefef分为非我方"));
        BiFunction<String,Integer,Employee> bif=Employee::new; //两个参数构造器
        System.out.println(bif.apply("ffe个额外", 33));

    }
    @Test
    //对象：：实例方法名
    public void test1(){
        //Consumer<String> con=x-> System.out.println(x);
        PrintStream out = System.out;
        Consumer<String> x = out::println;
        Consumer<String> x1=System.out::println;
        x.accept("fewff");
        x1.accept("fewewfewfwggrewgew");

    }
    @Test
    //对象：：实例方法名
    public void test2(){
        Employee ff = new Employee("ff", 2432, 432);
        Supplier<Integer> sup= ()->ff.getAge();
        System.out.println(sup.get());

        Supplier<Integer> getAge = ff::getAge;
        System.out.println(getAge.get());
    }
    @Test
    //类：：静态方法名
    public void test3(){
        Comparator<Integer> com=(x,y)->Integer.compare(x,y);
        Comparator<Integer> com1=Integer::compare;
    }
    @Test
    //类：：实例方法名
    public void test4(){
        BiPredicate<String,String> bip=(x,y)->x.equals(y);
        BiPredicate<String,String> bip1=String::equals;//第一个参数是调用者，第二个参数是实例方法的参数时

    }
}
