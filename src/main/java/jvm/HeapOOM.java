package jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * VM Args: -server -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError -XX:+PrintGCDetails
 * VM Args: -client -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError -XX:+PrintGCDetails
 */

public class HeapOOM {

    static class OOMObject {
        public OOMObject(String str) {
            this.objectName = str;
        }

        private String objectName;
    }

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<OOMObject>();

        try {
            while (true) {
                list.add(new OOMObject("abc"));
            }
        } catch (OutOfMemoryError e) {
            System.out.println("catch error: " + e.getClass().getSimpleName());
        }
        System.out.println("main exit");
    }
}
