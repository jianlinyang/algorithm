package practice;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author yang
 * @date 2019/8/23 20:19
 */
public class Main6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int w = scanner.nextInt();
        int[] value = new int[n];
        int[] vo = new int[n];
        for (int i = 0; i < n; i++) {
            value[i] = scanner.nextInt();
            vo[i] = scanner.nextInt();
        }
//        int w = 100;
//        int[] value = {3, 3, 1, 1};
//        int[] vo = {40, 6, 1, 5};
        solution(w, value, vo);
    }

    private static void solution(int w, int[] value, int[] vo) {
        int num = 0;
        for (int i : value) {
            num += i;
        }
        int[] vo2 = new int[num];
        num = 0;
        int k = 0;
        for (int i = 0; i < vo.length; i++) {
            num += value[i];
            while (k < num) {
                vo2[k++] = vo[i];
            }
        }
        int[][] dp = new int[num + 1][w + 1];
        for (int i = 1; i <= num ;i++) {
            int wi = vo2[i - 1];
            int vi = 1;
            for (int j = 1; j <= w; j++) {
                if (wi <= j) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - wi] + vi);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        System.out.println(dp[vo2.length][w]);
    }
}
