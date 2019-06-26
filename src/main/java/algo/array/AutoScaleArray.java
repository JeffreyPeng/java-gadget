package algo.array;

import org.junit.Test;

import java.util.stream.IntStream;

public class AutoScaleArray<T> {

    private Object[] arr = new Object[10];

    private int size = 0;

    public T get(int i) {
        return (T) arr[i];
    }

    public void add(T t) {
        checkSize(1);
        arr[size] = t;
        size++;
    }

    private void checkSize(int n) {
        if (size + n > arr.length) {
            Object[] scaled = new Object[arr.length * 2];
            for (int i = 0; i < arr.length; i++) {
                scaled[i] = arr[i];
            }
            arr = scaled;
        }
    }

    @Test
    public void test() {
        AutoScaleArray<Integer> array = new AutoScaleArray<Integer>();
        IntStream.range(0, 100).forEach(array::add);

        System.out.println(array.size);
        System.out.println(array.arr.length);
        for (int i = 0; i < array.size; i++) {
            System.out.println(array.get(i));
        }
    }

}
