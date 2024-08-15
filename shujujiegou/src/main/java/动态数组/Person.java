package 动态数组;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

@Data

@AllArgsConstructor
@NoArgsConstructor
public class Person {
    private int age;
    private String name;
    @Override
    public boolean equals(Object obj){
        Person person = (Person) obj;
        return this.age==person.age&&this.name.equals(person.name);
    }
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("Person- finalize");
    }

}
