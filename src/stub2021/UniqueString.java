package stub2021;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author YangJianlin
 * @date 2021/3/25 9:20
 */
public class UniqueString {

    public static void main(String[] args) {
        Set<String> input = getRandomInput(10000);
        Map<String, String> resultMap = new HashMap<>();
        System.out.println("begin");
        long startTime = System.currentTimeMillis();
        for (String s : input) {
            if (s == null || s.length() == 0) {
                continue;
            }
            for (int splitLen = 1; splitLen <= s.length(); splitLen++) {
                for (int start = 0; start < s.length(); start++) {
                    int end = Math.min(start + splitLen, s.length());
                    String substring = s.substring(start, end);
                    //keySet里有的一定不是唯一表示
                    if (resultMap.containsKey(substring)) {
                        continue;
                    }
                    if (notContainString(substring, input, s)) {
                        resultMap.put(substring, s);
                        //只要包含就跳出循环
                        splitLen = s.length() + 1;
                        break;
                    }
                }
            }
        }
        System.out.println(resultMap.toString());
        System.out.println("用时 " + (System.currentTimeMillis() - startTime) + "毫秒");
    }

    private static Set<String> getRandomInput(int num) {
        Set<String> set = new HashSet<>();
        String str = "zxcvbnmlkjhgfdsaqwertyuiopQWERTYUIOPASDFGHJKLZXCVBNM1234567890";
        Random random = ThreadLocalRandom.current();
        for (int i = 0; i < num; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            int len = random.nextInt(10);
            for (int j = 0; j < len; j++) {
                //产生0-61的数字
                int number = random.nextInt(62);
                stringBuilder.append(str.charAt(number));
            }
            set.add(stringBuilder.toString());
        }
        return set;
    }

    private static boolean notContainString(String s, Set<String> set, String exclude) {
        for (String input : set) {
            if (exclude.equals(input)) {
                continue;
            }
            if (input.contains(s)) {
                return false;
            }
        }
        return true;
    }
}
