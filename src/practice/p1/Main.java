package practice.p1;

import java.util.*;

/**
 * @author yang
 */
public class Main {
    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int c = scanner.nextInt();
//        if(c==1) {
//            System.out.println(1);
//            return;
//        }
//        long[] a = new long[c];
//        for (int i = 0; i < c; i++) {
//            a[i] = scanner.nextLong();
//        }
        long[] a = {69079936, 236011312, 77957850, 653604087, 443890802, 277126428, 755625552, 768751840, 993860213, 882053548};
//        int[] a = {2, 1, 3, 2};
        solution(a);
    }

    private static void solution(long[] a) {
        int count = 1;
        for (int i = 1; i < a.length; i++) {
            if (a[i - 1] < a[i]) {
                count++;
            }
        }
        System.out.println(count);
    }
}
