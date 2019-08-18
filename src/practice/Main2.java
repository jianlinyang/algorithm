package practice;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author yang
 * @date 2019/8/16 14:00
 */
public class Main2 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int i = scan.nextInt();
        int[] arr = new int[i];
        for (int j = 0; j < i; j++) {
            arr[j] = scan.nextInt();
        }
        int k = scan.nextInt();
        int[] arr2 = new int[k];
        for (int j = 0; j < k; j++) {
            arr2[j] = scan.nextInt();
        }
        candy(arr, arr2);
    }

    public static void candy(int[] h, int[] w) {
        Arrays.sort(h);
        Arrays.sort(w);
        int i = 0, j = 0, count = 0;
        while (j < w.length && i < h.length) {
            if (w[j] >= h[i]) {
                count++;
                i++;
            }
            j++;
        }
        System.out.println(count);
    }
}
