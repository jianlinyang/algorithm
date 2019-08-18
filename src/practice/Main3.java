package practice;

import java.util.Scanner;

/**
 * @author yang
 * @date 2019/8/16 14:09
 */
public class Main3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int row = scanner.nextInt();
        int col = scanner.nextInt();
        int k = scanner.nextInt();
        int[][] arr = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                arr[i][j] = scanner.nextInt();
            }
        }
        find(arr, k);
    }

    public static void find(int[][] arr, int k) {
        int row = arr.length - 1;
        if (row == 0) {
            System.out.println("No");
            return;
        }
        int col = 0;
        while (row >= 0 && col < arr[0].length) {
            if (arr[row][col] == k) {
                System.out.println("Yes");
                return;
            }
            if (arr[row][col] < k) {
                col++;
            } else {
                row--;
            }
        }
        System.out.println("No");
    }
}
