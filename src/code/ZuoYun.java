package code;

/**
 * @author yang
 * @date 2019/7/15 19:50
 */
public class ZuoYun {
    /**
     * KMP算法
     * @param s
     * @param m
     * @return
     */
    public int getIndexOf(String s, String m) {
        if (s == null || m == null || m.length() < 1 || s.length() < m.length()) {
            return -1;
        }
        char[] ss = s.toCharArray();
        char[] ms = m.toCharArray();
        int si = 0;
        int mi = 0;
        int[] next = getNextArray(ms);
        while (si < ss.length && mi < ms.length) {
            if (ss[si] == ms[mi]) {
                si++;
                mi++;
            } else if (next[mi] == -1) {
                si++;
            } else {
                mi = next[mi];
            }
        }
        return mi == ms.length ? si - mi : -1;
    }

    private int[] getNextArray(char[] ms) {
        if(ms.length==1){
            return new int[]{-1};
        }
        int[] next = new int[ms.length];
        next[0] = -1;
        next[1] = 0;
        int pos = 2;
        int cn = 0;
        while (pos < next.length) {
            if (ms[pos - 1]==ms[cn]) {
                next[pos++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            }else {
                next[pos++] = 0;
            }
        }
        return next;
    }

    public static void main(String[] args) {
        String s = "aaaac1abc1";
        String m = "aac1abc1";
        ZuoYun zuoYun = new ZuoYun();
        int indexOf = zuoYun.getIndexOf(s, m);
        System.out.println(indexOf);
    }
}
