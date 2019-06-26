package algo.array;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

public class MergeTwoSortedArray {

    public int[] merge(int[] arr1, int[] arr2) {
        int[] result = new int[arr1.length + arr2.length];
        int i = 0, j = 0;
        for (int k = 0; k < result.length; k++) {
            if (i == arr1.length) {
                result[k] = arr2[j++];
                continue;
            }
            if (j == arr2.length) {
                result[k] = arr1[i++];
                continue;
            }
            if (arr1[i] <= arr2[j]) {
                result[k] = arr1[i++];
            } else {
                result[k] = arr2[j++];
            }
        }
        return result;
    }

    @Test
    public void test() {
        int[] arr1 = new Random().ints(5, 0, 10).sorted().toArray();
        int[] arr2 = new Random().ints(5, 0, 10).sorted().toArray();

        Arrays.stream(arr1).forEach(System.out::print);
        System.out.println();
        Arrays.stream(arr2).forEach(System.out::print);
        System.out.println();

        int[] merged = merge(arr1, arr2);

        Arrays.stream(merged).forEach(System.out::print);
        System.out.println();
    }
}
