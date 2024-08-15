import org.springframework.context.ApplicationContextAware;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class wuf {
    public wuf(){
        System.out.println("1");
    }
    public wuf(String a){
        this();
        System.out.println("2");

    }
    public wuf(String a,int b){
        this("2");
        System.out.println("3");

    }

    public static void main(String[] args) {
        ArrayList<String> a = new ArrayList<>();
        wuf bb = new wuf();
        bb.add(a,new Date());
        wuf wuf = new wuf("3",3);
    }

    public  void add(List a,Object b){
        a.add(b);
        System.out.println(a.get(0));
    }
}
