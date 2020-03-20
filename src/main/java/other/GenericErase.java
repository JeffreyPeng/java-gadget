package other;

import java.util.HashMap;
import java.util.Map;

public class GenericErase {

    private static void put(Map map, String key, Object val) {
        map.put(key, val);
    }

    public static void main(String[] args) {
        System.out.println("1".compareTo("2"));
        System.out.println("111".compareTo("2"));

        Map<String, String> map = new HashMap<>();
        map.put("a", "1");
        System.out.println(map.get("a"));
        put(map, "b", "b");
        System.out.println(map.get("b"));
        put(map, "c", new Object());
        // ClassCastException
        //System.out.println(map.get("c"));
        System.out.println(((Map)map).get("c"));
    }
}
