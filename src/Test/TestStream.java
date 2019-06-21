package Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

/**
 * @author yang
 * @date 2019/6/21 14:33
 */
public class TestStream {
    List<Integer> list = new ArrayList<>(100000);

    {
        for (int i = 0; i < 10000000; i++) {
            list.add(i);
        }
    }

    public void test() {
        List<Integer> list1 = new LinkedList<>();
        List<Integer> list2;
        long start = System.currentTimeMillis();
        for (Integer integer : list) {
            if (Math.floorMod(integer, 2) != 0) {
                list1.add(integer);
            }
        }
        long end = System.currentTimeMillis();

        System.out.println(end - start);
        try {
            Thread.currentThread().sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        start = System.currentTimeMillis();
        list2 = list.parallelStream().filter(e -> Math.floorMod(e, 2) != 0).collect(Collectors.toList());
        end = System.currentTimeMillis();
        System.out.println(end - start);
        System.out.println(list1.size() == list2.size());
    }

    public static void main(String[] args) {
        TestStream testStream = new TestStream();
        testStream.test();
    }
}
