package code;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author yang
 * @date 2019/8/16 13:24
 */
public class BigNum {

    public static void main(String[] args) {
        String num1 = "41";
        String num2 = "411";
        int[] ret = new int[num1.length() + num2.length()];
        for (int i = num1.length() - 1; i >= 0; i--) {
            int x = num1.charAt(i) - '0';
            for (int j = num2.length() - 1; j >= 0; j--) {
                int y = num2.charAt(j) - '0';
                ret[i + j] += (ret[i + j + 1] + x * y) / 10;
                ret[i + j + 1] = (ret[i + j + 1] + x * y) % 10;
            }
        }
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < ret.length; i++) {
            if (i == 0 && ret[i] == 0) continue;
            s.append(ret[i]);
        }
        System.out.println(s.toString());
        add(num1, num2);
    }

    public static void add(String a, String b) {
        int la = a.length();
        int lb = b.length();
        int length = Math.max(la, lb);
        int[] res = new int[length + 1];
        for (int i = 0; i < length; i++) {
            int x = i < la ? a.charAt(la - i - 1) - '0' : 0;
            int y = i < lb ? b.charAt(lb - i - 1) - '0' : 0;
            res[length - i - 1] += (res[length - i] + x + y) / 10;
            res[length - i] = (res[length - i] + x + y) % 10;
        }
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < res.length; i++) {
            if (i == 0 && res[i] == 0) continue;
            s.append(res[i]);
        }
        System.out.println(s.toString());
    }

}
