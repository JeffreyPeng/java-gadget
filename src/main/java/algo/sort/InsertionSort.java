package algo.sort;

import java.util.Arrays;

public class InsertionSort {

    public void sort(int[] arr) {
        if (arr.length <= 1) return;

        for (int i = 1; i < arr.length; i++) {
            int v = arr[i];
            int j = i - 1;
            while (j >= 0) {
                if (arr[j] <= v) {
                    break;
                } else {
                    arr[j + 1] = arr[j];
                    j--;
                }
            }
            arr[j + 1] = v;
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{4, 5, 6, 3, 2, 7};
        System.out.println(Arrays.toString(arr));
        new InsertionSort().sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
