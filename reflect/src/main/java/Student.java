import lombok.Data;
@Data
class Person{
    private String name;
    private int age;
    private long id;
}

@Data
public class Student extends Person{
    private int banji;
}
@Data
class Teacher extends Person{
    private String kecheng;
}