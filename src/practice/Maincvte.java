package practice;


import java.util.*;


/**
 * @author yang
 */
public class Maincvte {

    public static void main(String[] args) {
        Maincvte main = new Maincvte();
        HashMap<String, Integer> map = new HashMap<>();
        map.put("小明", 90);
        map.put("小黄", 80);
        map.put("小红", 80);
        List<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(9);
        list.add(2);
        list.add(8);
        list.sort((x, y) -> y - x);
        for (Integer integer : list) {
            System.out.println(integer);
        }
//        System.out.println(main.opNum(2, 30));
    }

    public int opNum(int a, int b) {
        int count = 0;
        while (a != b) {
            if (a * 2 > b && a < b) {
                return count + sub(a, b);
            }
            count++;
            if (a > b) {
                a -= 2;
            } else {
                if (a <= 2) {
                    if (b == 4) a *= 2;
                    a += 3;
                } else {
                    a *= 2;
                }
            }
        }
        return count;
    }

    private int sub(int a, int b) {
        int count = 1, tmp = a;
        int count2 = 0;
        a *= 2;
        while (a != b) {
            count++;
            if (a > b) {
                a -= 2;
            } else {
                a += 3;
            }
        }
        a = tmp;
        while (a != b) {
            count2++;
            if (a > b) {
                a -= 2;
            } else {
                a += 3;
            }
        }
        return Math.min(count, count2);
    }

    public Map<String, Integer> solution(Map<String, Integer> map) {
        return sortDescend(map);
    }

    public static <K, V extends Comparable<? super V>> Map<K, V> sortDescend(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort((o1, o2) -> -(o1.getValue()).compareTo(o2.getValue()));
        Map<K, V> returnMap = new LinkedHashMap<K, V>();
        for (Map.Entry<K, V> entry : list) {
            returnMap.put(entry.getKey(), entry.getValue());
        }
        return returnMap;
    }
}