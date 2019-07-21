package practice;

/**
 * 乘法表
 *
 * @author yang
 * @date 2019/7/21 19:09
 */
public class Day4 {
    public static void main(String[] args) {
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(i + "x" + j + "=" + i * j + ",");
            }
            System.out.println();
        }
    }
}
