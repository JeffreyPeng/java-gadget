package jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * VM Args: -XX:PermSize=10M -XX:MaxPermSize=10M -XX:+HeapDumpOnOutOfMemoryError -XX:+PrintGCDetails
 * VM version: 1.6 1.7/1.8
 */
public class RuntimeConstantPoolOOM {

    public static void main(String[] args) {
        String str1 = new StringBuilder("computer").append("software").toString();
        System.out.println(str1.intern() == str1);

        String strNotUse = "network_";

        String str2 = new StringBuilder("net").append("work_").toString();
        System.out.println(str2.intern() == str2);

        List<String> list = new ArrayList<String>();
        int i = 0;
        while (true) {
            list.add(String.valueOf(i++).intern());
        }
    }
}
