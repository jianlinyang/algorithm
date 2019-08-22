package practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * @author yang
 */
public class MainMeituan {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String s = scan.nextLine();
//        String s = "MPMPCPMCMDEFEGDEHINHKLIN";
        List<Integer> solution = solution(s);
        StringBuilder stringBuilder = new StringBuilder();
        for (Integer integer : solution) {
            stringBuilder.append(integer).append(" ");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        System.out.println(stringBuilder.toString());
    }

    public static List<Integer> solution(String s) {
        ArrayList<Integer> res = new ArrayList<>();
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), i);
        }
        int i = 0;
        while (i < s.length()) {
            Integer endIndex = map.get(s.charAt(i));
            for (int j = 1; j < endIndex; j++) {
                if (map.get(s.charAt(j)) > endIndex) {
                    endIndex = map.get(s.charAt(j));
                }
            }
            res.add(endIndex - i + 1);
            i = endIndex + 1;
        }
        return res;
    }
}
