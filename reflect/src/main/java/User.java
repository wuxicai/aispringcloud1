import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@table("wuxicai")
public class User {
    @field(columnName = "name",type = "vc",length = 10)
    private String name;
    @field(columnName = "age",type = "int",length = 10)
    private int age;
    @field(columnName = "id",type = "long",length = 10)
    private long id;
    @field(columnName = "name1",type = "vc",length = 10)
    public  String name1;
}
