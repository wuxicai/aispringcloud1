package 二叉树;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@AllArgsConstructor
@NoArgsConstructor
public class Person implements Comparable<Person>{
    private int age;

    @Override
    public int compareTo(Person person) {
        return age-person.age;
    }
//    private String name;
//    @Override
//    public boolean equals(Object obj){
//        Person person = (Person) obj;
//        return this.age==person.age&&this.name.equals(person.name);
//    }
//    @Override
//    protected void finalize() throws Throwable {
//        super.finalize();
//        System.out.println("Person- finalize");
//    }

}
