package algo.sort;

import java.util.Arrays;

public class MergeSort {

    public static void main(String[] args) {
        int[] arr = new int[]{4, 5, 6, 3, 2, 7};
        System.out.println(Arrays.toString(arr));
        new MergeSort().sort(arr, arr.length);
        System.out.println(Arrays.toString(arr));
    }

    public void sort(int[] arr, int n) {
        recur(arr, 0, n - 1);
    }

    private void recur(int[] arr, int p, int q) {
        if (p >= q) {
            return;
        }
        int r = p + (q - p) / 2;
        recur(arr, p, r);
        recur(arr, r + 1, q);
        merge(arr, p, q, r);
    }

    private void merge(int[] arr, int p, int q, int r) {
        int[] tmp = new int[q - p + 1];
        int i = 0;
        int j = p;
        int k = r + 1;
        while (j <= r && k <= q) {
            if (arr[j] <= arr[k]) {
                tmp[i++] = arr[j++];
            } else {
                tmp[i++] = arr[k++];
            }
        }
        while (j <= r) {
            tmp[i++] = arr[j++];
        }
        while (k <= q) {
            tmp[i++] = arr[k++];
        }
        i = 0;
        while (p <= q) {
            arr[p++] = tmp[i++];
        }
    }
}
