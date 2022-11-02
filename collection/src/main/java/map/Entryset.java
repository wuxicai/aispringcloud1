package map;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Entryset {
    public static void main(String[] args) {
        Map<Object, Object> map = new ConcurrentHashMap();
        map.put(1,"a");
        map.put(2,"b");
        Set<Map.Entry<Object, Object>> entries = map.entrySet();
        for (Map.Entry a:entries
             ) {
            System.out.println(a.getKey()+"---"+a.getValue());
        }
    }
}
