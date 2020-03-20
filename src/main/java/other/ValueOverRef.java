package other;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ValueOverRef {
    public static void main(String[] args) {
        int a = 1;
        Integer b = 1;
        Integer[] c = new Integer[]{1};
        List<Integer> d = new ArrayList();
        Collections.addAll(d, c);
        method(a, b, c, d);
        System.out.println("a:" + a);
        System.out.println("b:" + b);
        System.out.println("c:" + c[0]);
        System.out.println("d:" + d.get(0));
    }
    // 基本类型直接值传递，其他的是引用传递（或叫变量的值传递）
    // 但是 Integer 和 String 等不可变对象其实无法改变对象
    static void method(int a, Integer b, Integer[] c, List<Integer> d) {
        a = a * 2;
        b = b * 2;
        // 注意！等号只是变量赋值，不是操作对象
        c[0] = c[0] * 2;
        Integer tmp = c[0];
        tmp = tmp * 3;
        d.set(0, d.get(0) * 2);
    }
}
