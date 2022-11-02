import org.junit.Test;

import java.util.Optional;

public class Optionaltest {
    @Test
    public void test1(){
        System.out.println("-------------------创建实例--------------------");
        Optional<Employee> employee = Optional.of(new Employee("few二分法",22,3232));
        System.out.println(employee.get());
        System.out.println("-------------------创建空实例--------------------");
        Optional<Object> empty = Optional.empty();
      //  System.out.println(empty.get());
        Optional<Employee> employee1 = Optional.ofNullable(new Employee());
        System.out.println(employee1.get());
//        System.out.println(Optional.ofNullable(null).get());
        Optional<Object> o = Optional.ofNullable(null);
        if(o.isPresent()){
            System.out.println(o.get());
        }
        Object o1 = o.orElse(new Employee("orElse", 22, 3232));
        System.out.println(o1);

        System.out.println("-------------------orElseGet--------------------");
        Object o2 = o.orElseGet(() -> new Employee("few二分法", 22, 3232));
        System.out.println(o2);
        System.out.println("-------------------map--------------------");
        Optional<Employee> map = Optional.of(new Employee("map", 22, 3232));
        System.out.println(map.map(x -> x.getName()).get());
        System.out.println("-------------------flatMap--------------------");
        System.out.println(map.flatMap(x -> Optional.of(x.getAge())).get());

    }
}
