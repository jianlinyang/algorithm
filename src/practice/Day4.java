package practice;

import code.KMP;

/**
 * kmp
 *
 * @author yang
 * @date 2019/7/21 19:09
 */
public class Day4 {

    public static int kmp(String s, String m) {
        if (s == null || m == null || m.length() < 1 || s.length() < m.length()) {
            return -1;
        }
        int[] next = next(m);
        int si = 0, mi = 0;
        while (si < s.length() && mi < m.length()) {
            if (s.charAt(si) == m.charAt(mi)) {
                si++;
                mi++;
            } else if (next[mi] == -1) {
                si++;
            } else {
                mi = next[mi];
            }
        }
        return mi == m.length() ? si - mi : -1;
    }

    private static int[] next(String ms) {
        if (ms.length() == 1) {
            return new int[]{-1};
        }
        int[] next = new int[ms.length()];
        next[0] = -1;
        next[1] = 0;
        int cur = 2;
        int cn = 0;
        while (cur < ms.length()) {
            if (ms.charAt(cur - 1) == ms.charAt(cn)) {
                next[cur++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[cur++] = 0;
            }
        }
        String s = "aa";
        String[] as = s.split("a");
        return next;
    }

    public static int getNum(String s, String m) {
        int count = 0;
        int kmp = kmp(s, m);
        while (kmp != -1) {
            count++;
            s = s.substring(kmp + 1);
            kmp = kmp(s, m);
        }
        return count;
    }

    public static void main(String[] args) {
        String s = "abcabcabc";
        String m = "abcabc";
        System.out.println(getNum(s, m));
    }
}
