import org.junit.Test;

import java.util.*;


public class lambda {

    @Test
    //原来的匿名内部类
    public void test1(){
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };

        TreeSet<Integer> integers = new TreeSet<>(comparator);
    }
    @Test
    //lambda
    public void test2(){
        Comparator<Integer> com = (x, y) -> Integer.compare(x,y);
        TreeSet<Integer> integers = new TreeSet(com);
    }

    List<Employee> list=Arrays.asList(
            new Employee("非生物1",23,7855),
            new Employee("非生物2",26,7583),
            new Employee("非生物3",63,3588),
            new Employee("非生物4",33,78366),
            new Employee("非生物5",13,1244)
    );
    @Test
    public void test3(){
        List<Employee> employees = employees(list);
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    public List<Employee> employees(List<Employee> list){
        ArrayList<Employee> rs = new ArrayList<>();
        for (Employee employee : list) {
            if(employee.getAge()>30){
                rs.add(employee);
            }
        }
        return rs;
    }


    public List<Employee> filterEmployee(List<Employee> list, MyPredicate<Employee> mp){
        ArrayList<Employee> employees = new ArrayList<>();
        for (Employee employee : list) {
            if(mp.test(employee)){
                employees.add(employee);
            }
        }
        return employees;
    }
    //优化一  策略设计模式
    @Test
    public void test4(){
        List<Employee> employees = filterEmployee(list, new FilterEmployeeByAge());
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    //优化二 匿名内部类
    @Test
    public void test5(){
        List<Employee> employees = filterEmployee(list, new MyPredicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getAge() < 35;
            }
        });
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }
    //优化三 lambda
    @Test
    public void test6(){
        List<Employee> employees = filterEmployee(list, (e) -> e.getAge() < 28);
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    //优化四 stream API
    @Test
    public void test7(){
        list.stream().filter((e)->e.getAge()>5).limit(100).forEach(System.out::println);
    }
}
