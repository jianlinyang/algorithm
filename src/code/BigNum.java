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
        String s = "";
        for (int i = 0; i < ret.length; i++) {
            if (i == 0 && ret[i] == 0) continue;
            s += ret[i];
        }
        System.out.println(s);
    }

}
