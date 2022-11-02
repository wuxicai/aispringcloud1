import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/*
* java8中内置的四大核心函数式接口
*
*  Consumer<T> 消费接口 void accept（T t）
*
*  Supplier<T> 供给接口 T get()
*
*  Function<T,R> 函数型接口  T 参数  R 返回值    R apply(T t);
*
*  Predicate<T> 断言型接口   Boolean test（T t）
* */
public class Lambda3 {
    //Consumer<T> 消费接口 void accept（T t）
    @Test
    public void test1(){
        happy(29992.33,m-> System.out.println("洗澡消费了"+m));
    }
    public void happy(double money, Consumer<Double> con){
        con.accept(money);
    }
    //Supplier<T> 供给接口 T get()
    @Test
    public void test2(){
        List<Integer> nums = getNums(1000, () -> (int) (Math.random() * 100000));
        for (Integer num : nums) {
            System.out.println(num);
        }

    }
    public List<Integer> getNums(int num, Supplier<Integer> sup){
        List<Integer> list=new ArrayList<>();
        for (int i = 0; i < num; i++) {
            Integer integer = sup.get();
            list.add(integer);
        }
        return list;
    }
    //Function<T,R> 函数型接口  T 参数  R 返回值    R apply(T t);
    @Test
    public void test3(){
        Integer fdsfefe = strHandler("fdsfefe", s -> s.length());
        strHandler("fsfe",s -> s.substring(2,4).length());
        System.out.println(fdsfefe);
    }
    public Integer strHandler(String str, Function<String,Integer> fun){
        return fun.apply(str);
    }

    //Predicate<T> 断言型接口   Boolean test（T t）
    //将满足条件的字符串添加到集合中

    @Test
    public void test4(){
        ArrayList<String> strings = new ArrayList<>();
        strings.add("fwfw");
        strings.add("fdw");
        strings.add("few");
        strings.add("fef");
        strings.add("evwe");
        strings.add("fafa");
        List<String> e = strFilter(strings, s -> !s.contains("e"));
        for (String s : e) {
            System.out.println(s);
        }
    }

    public List<String> strFilter(List<String> str, Predicate<String> pre){
        ArrayList<String> strings = new ArrayList<>();
        for (String s : str) {
            if (pre.test(s)){
                strings.add(s);
            }
        }
        return strings;
    }
}
