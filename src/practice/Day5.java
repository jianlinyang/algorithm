package practice;

import java.util.Arrays;

/**
 * @author yang
 * @date 2019/8/17 13:34
 */
public class Day5 {
    public static void main(String[] args) {
        int[] arr = {4, 3, 8, 1, 2, 3};
    }

    public static void swap(int[] arr) {
        int i = 0;
        int s;
        while (i < arr.length) {
            if (arr[i] % 2 == 0) {
                s = i++;
                while (i < arr.length) {
                    if (arr[i] % 2 == 1) {
                        subSwap(arr, s, i);
                        i = s;
                        break;
                    }
                    i++;
                }
            }
            i++;
        }
    }

    private static void subSwap(int[] arr, int s, int e) {
        int tmp = arr[e];
        while (e > s) {
            arr[e] = arr[--e];
        }
        arr[s] = tmp;
    }


}
