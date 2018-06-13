package jvm;

/**
 * VM Args: -server -Xms10m -Xmx10m -XX:-DoEscapeAnalysis -XX:+PrintGC
 * VM Args: -server -Xms10m -Xmx10m -XX:+DoEscapeAnalysis -XX:+PrintGC
 * VM version: 1.6/1.7/1.8
 */
public class EscapeAnalysis {
    int i = 100;
    int j = 200;
    long k = 300L;

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000 * 100 * 100; ++i) {
            EscapeAnalysis foo = new EscapeAnalysis();
        }
        long end = System.currentTimeMillis();
        System.out.println("Time cost is " + (end - start));
    }

}