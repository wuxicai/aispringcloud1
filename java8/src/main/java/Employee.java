import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class Employee {
    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Employee(String name) {

        this.name = name;
    }
    public Employee() {

    }
    private String name;
    private int age;
    private int salary;
}
