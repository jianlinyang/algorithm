package practice.p2;

import java.util.*;

/**
 * @author yang
 * @date 2019/8/24 15:42
 */
public class Main {
    private static Set<Integer> setb = new HashSet<>();
    private static Set<Integer> setg = new HashSet<>();
    private static Set<Integer> tmp = new HashSet<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        for (int i = 0; i < m; i++) {
            solution(scanner.nextInt(), scanner.nextInt(), n);
        }
        print();
    }

    private static void solution(int b, int g, int n) {
        if (b > n) return;
        if (g > n && g <= 2 * n) {
            setb.add(b);
            setg.add(g);
            if (setg.contains(g)) {
                setb.remove(b);
                tmp.add(g);
            }
        }
    }

    private static void print() {

        int i = setb.size()+tmp.size();
        System.out.println(i);
        if (i == 0) return;
        StringBuilder stringBuilder = new StringBuilder();
        for (Integer integer : setb) {
            stringBuilder.append(integer).append(" ");
        }
        for (Integer integer : tmp) {
            stringBuilder.append(integer).append(" ");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        System.out.println(stringBuilder.toString());
    }
}
