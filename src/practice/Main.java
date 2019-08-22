package practice;

import java.util.*;

/**
 * @author yang
 * @date 2019/8/22 16:16
 */
public class Main {
    private static HashMap<Character, Integer> map = new HashMap<>();
    private static HashMap<Character, Boolean> flag = new HashMap<>();

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
//        String s = scan.nextLine();
        String s = "rt rwf er et rft";
        solution(s);
    }

    private static void solution(String s) {
        String[] s1 = s.split(" ");
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), i);
        }
        char[] res = new char[map.size() - 1];
        int i = 0;
        for (Character character : map.keySet()) {
            if (character != ' ') {
                res[i++] = character;
                map.put(character, i - 1);
                flag.put(character, false);
            }
        }
        for (String s2 : s1) {
            for (int j = 1; j < s2.length(); j++) {
                Integer sj = map.get(s2.charAt(j - 1));
                Integer ej = map.get(s2.charAt(j));
                if (sj > ej) {
                    if (flag.get(s2.charAt(j - 1)) && flag.get(s2.charAt(j))) {
                        System.out.println("inviolate");
                        return;
                    }
                    swap2(sj, ej, res);
                    flag.put(res[sj], true);
                    flag.put(res[ej], true);
                }
            }
        }
        System.out.println(Arrays.toString(res));
    }

    private static void swap1(int e, int s, char[] chars) {
        char tmp = chars[e];
        while (s < e) {
            chars[e] = chars[e - 1];
            map.put(chars[e], e);
            e--;
        }
        chars[s] = tmp;
        map.put(chars[s], s);
    }

    private static void swap2(int e, int s, char[] chars) {
        char tmp = chars[s];
        while (s < e) {
            chars[s] = chars[s + 1];
            map.put(chars[s], s);
            s++;
        }
        chars[e] = tmp;
        map.put(chars[e], e);
    }
}
