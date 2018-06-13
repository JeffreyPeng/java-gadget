package jvm;

/**
 * VM Args: -server -Xss128k -XX:+HeapDumpOnOutOfMemoryError -XX:+PrintGCDetails
 * VM Args: -client -Xss128k -XX:+HeapDumpOnOutOfMemoryError -XX:+PrintGCDetails
 * VM Args: -client -Xss256k -XX:+HeapDumpOnOutOfMemoryError -XX:+PrintGCDetails
 */

public class VMStackOF {

    private static int stackLength = 1;

    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) {
        VMStackOF oom = new VMStackOF();
        try {
            oom.stackLeak();
        } catch (Throwable e) {
            System.out.println("catch error: " + e.getClass().getSimpleName());
            System.out.println(stackLength);
        }
    }
}
