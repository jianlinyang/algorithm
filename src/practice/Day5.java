package practice;

/**
 * @author yang
 * @date 2019/8/17 13:34
 */
public class Day5 {
    public static void main(String[] args) {
        String s = "sdhabdakdoaijdASDAA";
        int[] res = new int[130];
        for (int i = 0; i < s.length(); i++) {
            int j= s.charAt(i);
            res[j]++;
        }
        for (int i = 0; i < res.length; i++) {
            if (res[i] != 0) {
                System.out.println((char) i + ":" + res[i]);
            }
        }
    }
}
