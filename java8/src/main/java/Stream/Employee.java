package Stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
public class Employee {
    private String name;
    private Integer age;
    private Integer salary;
    private Status status;

    private enum  Status{
        FREE,BUSY,VOCATION;
    }
}