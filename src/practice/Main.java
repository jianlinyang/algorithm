package practice;

import org.jetbrains.annotations.NotNull;

import java.util.Scanner;
import java.util.Arrays;


/**
 * @author yang
 * @date 2019/8/10 13:57
 */
public class Main {
    public static void main(String[] args) {
//        Scanner scan = new Scanner(System.in);
//        int i = Integer.valueOf(scan.nextLine());
//        int[] arr = new int[i];
//        for (int j = 0; j < i; j++) {
//            arr[j] = scan.nextInt();
//        }
        int[] arr = new int[]{5, 1, 6, 8, 2, 4, 5, 10};
        System.out.println(solution(arr));
    }

    public static int solution(@NotNull int[] arr) {
        int res = 0;
        int[] dp = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i] && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}