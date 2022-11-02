import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Listtest {
    @Test
    public void test1(){
        int i = (int)((1349823707000l>>>10) - (1593470812000l>>>10));
        System.out.println(i);

        System.out.println(1349823707000l>>>10);
        ArrayList<Object> a = new ArrayList<>();
        a.add(1);
        a.add(2);
        a.add(1);
        a.add(2);
        a.add(1);
        a.add(2);
        List<Object> objects = a.subList(1, 6);


        for (Object object : objects) {
            System.out.println(object);
        }
    }
}
