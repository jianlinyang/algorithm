package practice;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author yang
 * @date 2019/8/23 19:34
 */
public class Main5 {
    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        String s1 = scanner.nextLine();
//        int k = s1.charAt(s1.length() - 1) - '0';
//        String s = scanner.nextLine();
        String s = "ab";
        int k = 4;
        solution(s, k);
    }

    private static void solution(String s, int k) {
        int i = s.length() / 2 - 1, j = s.length() - 1;
        int count = 0;
        boolean flag = false;
        while (i >= 0) {
            if (s.charAt(j) == s.charAt(i)) {
                j--;
                i--;
                count++;
                flag = true;
            } else {
                i--;
                if (flag) break;
            }
        }
        String substring = null;
        if (i == -1) {
            substring = s.substring(count);
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(s);
        while (k-- > 1) {
            stringBuilder.append(substring);
        }
        System.out.println(stringBuilder.toString());
    }
}
