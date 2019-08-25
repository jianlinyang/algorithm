package practice;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author yang
 * @date 2019/8/23 20:57
 */
public class Main7 {
    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int i = scanner.nextInt();
//        int[] a = new int[i];
//        for (int j = 0; j < i; j++) {
//            a[j] = scanner.nextInt();
//        }
        int[] a = {1, 2, 3, 4, 5, 6};
        solution(a);
    }

    private static void solution(int[] a) {
        Arrays.sort(a);
        int length = a.length;
        int sum = 0;
        for (int i : a) {
            sum += i;
        }
        int tmp = 0;
        int tmp2 = 0;
        int count = 0;
        int min = Integer.MAX_VALUE;
        for (int i = a.length - 1; i >= 0; i--) {
            tmp += a[i];
            count++;
            min = Math.min(min,Math.abs(sum - 2*tmp) );
        }
        System.out.println(min + " " + (length - count - 1));
    }
}
