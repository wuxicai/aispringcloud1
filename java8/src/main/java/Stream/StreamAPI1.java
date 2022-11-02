package Stream;

import org.aspectj.lang.annotation.AfterReturning;
import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/*
* 1.获取Stream
* 2.中间操作
* 3.终止操作
* */
public class StreamAPI1 {
//    @AfterReturning
//    @Test
//    //创建stream
//    public void test1(){
//        //1.通过collection    系列集合提供的stream（）串行流  或者 parallelStream() 并行流
//        ArrayList<String> list = new ArrayList<>();
//        Stream<String> stream = list.stream();
//        Stream<String> stringStream = list.parallelStream();
//        //2.通过Arrays中的静态方法Stream()获取数组流
//        Employee[] employees=new Employee[5];
//        Stream<Employee> stream1 = Arrays.stream(employees);
//        //3.通过Stream中的静态方法of()
//        Stream.of("few","fewf",3343);
//        //4.创建无限流
//        //迭代
//        Stream<Integer> iterate = Stream.iterate(0, x -> x +2);
//        iterate.limit(6).forEach(System.out::println);
//        //生成
//        Stream.generate(()->Math.random()).limit(22).forEach(System.out::println);
//    }
////筛选和切片
//    @Test
//    public void test2(){
//        List<Employee> list=Arrays.asList(
//                new Employee("非生物1",23,7855),
//                new Employee("非生物2",26,7583),
//                new Employee("非生物3",63,3588),
//                new Employee("非生物4",33,78366),
//                new Employee("非生物4",33,78366),
//                new Employee("非生物4",33,78366),
//                new Employee("非生物5",13,1244)
//        );
//        List<Employee> list1=new ArrayList<>();
//        Predicate p1=a->a.equals("eee");
//        list.stream().filter(e->e.getAge()>1).forEach(e->{list1.add(e);});
//        for (Employee employee : list1) {
//            System.out.println(list1.size());
//            System.out.println(employee);
//        }
//
//        System.out.println("================================");
//        //filter接受lambda，从流中排出元素
//        Stream<Employee> employeeStream = list.stream().filter(e -> {
//            System.out.println("中间操作");
//            return e.getAge() > 25;}).limit(4);//中间操作  没有任何数据
//        employeeStream.forEach(System.out::println);//终止操作  惰性求值  内部迭代
//        //limit
//        //employeeStream.limit(2).forEach(System.out::println);
//        //skip(n)跳过前N个，与limit互补
//        System.out.println("-------------------跳过---------------------");
//        Stream<Employee> employeeStream1 = list.stream().filter(e -> {
//            System.out.println("中间操作");
//            return e.getAge() > 25;}).limit(2).skip(1);
//        employeeStream1.forEach(System.out::println);
//        //distinct去重：通过流生成元素的hashcode和equals去除重复元素
//        System.out.println("-------------------去重---------------------");
//        list.stream().filter(e -> {
//            System.out.println("中间操作");
//            return e.getAge() > 25;}).distinct().forEach(System.out::println);
//
//    }
//
//    //映射map 接收lambda，将元素转换成其他形式或提取信息
//    @Test
//    public void test3(){
//        List<String> strings = Arrays.asList("few", "fe", "gww", "few", "fevf", "ffa");
//        strings.stream().map(x->x.toUpperCase()).forEach(System.out::println);
//        List<Employee> list=Arrays.asList(
//                new Employee("非生物1",23,7855),
//                new Employee("非生物2",26,7583),
//                new Employee("非生物3",63,3588),
//                new Employee("非生物4",33,78366),
//                new Employee("非生物4",33,78366),
//                new Employee("非生物4",33,78366),
//                new Employee("非生物5",13,1244)
//        );
//        list.stream().map(Employee::getName).forEach(System.out::println);
//        System.out.println("------------------------map and flatmap--------------------------------");
//        Stream<Stream<Character>> streamStream = strings.stream().map(StreamAPI1::filterCharacter);
//
//        streamStream.forEach(m->m.forEach(System.out::print));
//        System.out.println("------------------");
//        Stream<Character> characterStream = strings.stream().flatMap(StreamAPI1::filterCharacter);//多个流整合成一个流
//        characterStream.forEach(System.out::print);
//    }
//
//    public static Stream<Character> filterCharacter(String s){
//        List<Character> list= new ArrayList<>();
//        for (char c : s.toCharArray()) {
//            list.add(c);
//        }
//        return list.stream();
//    }
//
//    List<Employee> list=Arrays.asList(
//            new Employee("非生物1",23,7855, Employee.Status.BUSY),
//            new Employee("非生物2",26,7583, Employee.Status.BUSY),
//            new Employee("非生物3",63,3588, Employee.Status.FREE),
//            new Employee("非生物4",33,78366, Employee.Status.VOCATION),
//            new Employee("非生物1",33,78366, Employee.Status.BUSY),
//            new Employee("非生物11",33,78366, Employee.Status.BUSY),
//            new Employee("非生物11",33,78366, Employee.Status.BUSY),
//            new Employee("非生物5",13,1244, Employee.Status.FREE)
//    );
//    //sorted 自然排序  sorted(Comparator com) 定制排序
//    @Test
//    public void test5(){
//        List<String> strings = Arrays.asList("few", "fe", "gww", "few", "fevf", "ffa");
//        strings.stream().sorted().forEach(System.out::println);
//        list.stream().sorted((x,y)->{
//            if(x.getAge()==y.getAge()){
//                return x.getName().compareTo(y.getName());
//
//            }else return x.getAge().compareTo(y.getAge());
//        }).forEach(System.out::println);
//    }
//    /*
//    * 终止操作
//    *
//    * */
//    @Test
//    public void test6(){
//        boolean b = list.stream().allMatch(e -> e.getStatus().equals(Employee.Status.BUSY));//所有元素都匹配allMatch
//        System.out.println(b);
//        boolean b1 = list.stream().anyMatch(e -> e.getStatus().equals(Employee.Status.BUSY));//有任意一个匹配即可anyMatch
//        System.out.println(b1);
//        boolean b2 = list.stream().noneMatch(e -> e.getStatus().equals(Employee.Status.BUSY));//都不匹配noneMatch
//        System.out.println(b2);
//        Optional<Employee> first = list.stream().sorted((x, y) -> Integer.compare(x.getSalary(), y.getSalary())).findFirst();
//        System.out.println(first.get());
//        Optional<Employee> any = list.parallelStream().filter(e -> e.getStatus().equals(Employee.Status.FREE)).findAny();
//        System.out.println(any.get());
//        System.out.println(list.stream().count());//总数
//        Optional<Employee> max = list.stream().max((x, y) -> x.getAge().compareTo(y.getAge()));//最大值
//        System.out.println(max.get());
//        Optional<Employee> min = list.stream().min((x, y) -> x.getSalary().compareTo(y.getSalary()));//最小值
//        System.out.println(min.get());
//    }
//
//
//    /*
//    * 规约 reduce（identity(起始值),BinaryOperator） reduce（BinaryOperator）
//    * */
//    @Test
//    public void test8(){
//        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5);
//        System.out.println(list1.stream().reduce(10, (x, y) -> x + y));
//        Optional<Integer> reduce = list.stream().map(Employee::getAge).reduce(Integer::sum);
//        System.out.println(reduce.get());
//    }
//    /*
//     * 收集 collect
//     * */
//    @Test
//    public void test9(){
//        List<String> collect = list.stream().map(Employee::getName).collect(Collectors.toList());
//        collect.forEach(System.out::println);
//        System.out.println("-----------------------set--------------------");
//        Set<String> collect1 = list.stream().map(Employee::getName).collect(Collectors.toSet());
//        collect1.forEach(System.out::println);
//        System.out.println("-----------------------hashset--------------------");
//        HashSet<String> collect2 = list.stream().map(Employee::getName).collect(Collectors.toCollection(HashSet::new));
//        collect2.forEach(System.out::println);
//        System.out.println("-----------------------总数--------------------");
//        System.out.println(list.stream().collect(Collectors.counting()));
//        System.out.println("-----------------------平均值--------------------");
//        System.out.println(list.stream().collect(Collectors.averagingDouble(Employee::getSalary)));
//        System.out.println("-----------------------总和--------------------");
//        System.out.println(list.stream().collect(Collectors.summingLong(Employee::getSalary)));
//        System.out.println("-----------------------最大值--------------------");
//        System.out.println(list.stream().collect(Collectors.maxBy((x, y) -> Integer.compare(x.getSalary(), y.getSalary()))));
//        System.out.println("-----------------------最小值--------------------");
//        System.out.println(list.stream().collect(Collectors.minBy((x, y) -> Integer.compare(x.getAge(), y.getAge()))));
//        System.out.println("-----------------------分组--------------------");
//        Map<Employee.Status, List<Employee>> collect3 = list.stream().collect(Collectors.groupingBy(Employee::getStatus));
//        for (Map.Entry<Employee.Status, List<Employee>> statusListEntry : collect3.entrySet()) {
//            for (Employee employee : statusListEntry.getValue()) {
//                System.out.println(statusListEntry.getKey()+":  "+employee);
//            }
//
//        }
//        System.out.println("-----------------------多级分组--------------------");
//        Map<Employee.Status, Map<String, List<Employee>>> collect4 = list.stream().collect(Collectors.groupingBy(Employee::getStatus,
//                Collectors.groupingBy(e->{
//                    if(e.getAge()>=60) {return "老年";}else if(e.getAge()<60&&e.getAge()>=30) {return "中年";}else return "青少年";
//                })));
//        for (Map.Entry<Employee.Status, Map<String, List<Employee>>> statusMapEntry : collect4.entrySet()) {
//
//            for (Map.Entry<String, List<Employee>> stringListEntry : statusMapEntry.getValue().entrySet()) {
//                for (Employee employee : stringListEntry.getValue()) {
//                    System.out.println(statusMapEntry.getKey()+": "+stringListEntry.getKey() +" :"+employee);
//                }
//            }
//
//        }
//        System.out.println("-----------------------分区（分片）--------------------");
//        Map<Boolean, Map<Boolean, List<Employee>>> collect5 = list.stream().collect(Collectors.partitioningBy(e -> e.getAge() > 30, Collectors.partitioningBy(e -> e.getSalary() > 5000)));
//        for (Map.Entry<Boolean, Map<Boolean, List<Employee>>> booleanMapEntry : collect5.entrySet()) {
//            System.out.println((booleanMapEntry.getKey() ? "高龄：" : "非高龄："));
//            for (Map.Entry<Boolean, List<Employee>> booleanListEntry : booleanMapEntry.getValue().entrySet()) {
//                System.out.println((booleanListEntry.getKey() ? "    高收入：" : "    低收入："));
//                for (Employee employee : booleanListEntry.getValue()) {
//                    System.out.println("        "+employee);
//                }
//            }
//        }
//
//        System.out.println("-----------------------聚合函数--------------------");
//        IntSummaryStatistics collect6 = list.stream().collect(Collectors.summarizingInt(Employee::getSalary));
//        System.out.println(collect6.getAverage());
//        System.out.println(collect6.getCount());
//        System.out.println(collect6.getMax());
//        System.out.println(collect6.getMin());
//        System.out.println(collect6.getSum());
//
//        System.out.println("-----------------------拼接--------------------");
//        System.out.println(list.stream().map(Employee::getName).collect(Collectors.joining(",","begin:  ","  end!")));
//        System.out.println("-----------------------聚合--------------------");
//        Optional<Integer> reduce = list.stream().map(x -> 1).reduce(Integer::sum);
//        System.out.println(reduce.get());
//        System.out.println("-----------------------拼接--------------------");
//        System.out.println(list.stream().map(Employee::getName).reduce("", (x,y)->x.concat(y)));
//        System.out.println("-----------------------拼接--------------------");
//        System.out.println(list.stream().map(Employee::getName).sorted().reduce("", String::concat));
//        System.out.println("-----------------------最大值--------------------");//先map，返回的是getAge流否则是Employee流
//        list.stream().map(Employee::getAge).max(Integer::compare);
//    }
//    /*
//    * 并行流
//    * */
    @Test
    public void test11(){
        System.out.println("------------------------forkjoin-------------------------");
        Instant start = Instant.now();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkjoin tesk = new forkjoin(0, 10000000000l);
        Long sum = forkJoinPool.invoke(tesk);
        System.out.println(sum);
        Instant end = Instant.now();
        System.out.println(Duration.between(start, end).toMillis());

        System.out.println("------------------------并行流-------------------------");
        Instant start1 = Instant.now();
        LongStream.rangeClosed(0,100000000000l).sequential()
                  .parallel()//并行流  .sequential()串行流
                  .reduce(0,Long::sum);
        Instant end1 = Instant.now();
        System.out.println(Duration.between(start1, end1).toMillis());
    }

}
