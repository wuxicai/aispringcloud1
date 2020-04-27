import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class test {
    public double weight;

    public test() {
    }

    public void info(){
        System.out.println(weight);
    }
}
class test1 extends test{
    public test1() {
    }
    public double weight;
    public static void main(String[] args) {

        Map<String,Integer> map=new TreeMap();
        map.put("a",1);
        map.put("a",2);
        map.entrySet();
        System.out.println(map);
    }
}
