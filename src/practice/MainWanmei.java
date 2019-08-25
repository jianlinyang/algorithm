package practice;

import java.util.Scanner;

/**
 * @author yang
 */
public class MainWanmei {
    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int[][] arr = new int[6][6];
//        for (int i = 0; i < 6; i++) {
//            for (int j = 0; j < 6; j++) {
//                int tmp = scanner.nextInt();
//                arr[i][j] = tmp == -1 ? Integer.MAX_VALUE : tmp;
//            }
//        }
        int[][] arr = {
                {0, 1, 12, 500, 500, 500},
                {500, 0, 9, 3, 500, 500},
                {500, 500, 0, 500, 5, 500},
                {500, 500, 4, 0, 13, 15},
                {500, 500, 500, 500, 0, 4},
                {500, 500, 500, 500, 500, 0},
        };
        solution(arr);
    }

    private static void solution(int[][] arr) {
        int[] dp = new int[6];
        for (int i = 1; i < 6; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] = dp[j] + Math.min(arr[i][j], arr[j][i]);
            }
            System.out.println(dp[i]);
        }
        int[] dp2 = new int[6];
        for (int i = 1; i < 6; i++) {
            for (int j = 0; j < i; j++) {
                dp2[i] = dp2[j] + Math.min(arr[i][j], arr[j][i]);
            }
            System.out.println(dp2[i]);
        }
    }
}
