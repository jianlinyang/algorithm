package practice;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yang
 * @date 2019/8/15 21:22
 */
public class VolatileTest {
    private static AtomicInteger i =new AtomicInteger(0);
    private static volatile int j = 0;

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(20);
        for (int j = 0; j < 2000; j++) {
            Thread thread = new Thread(() -> VolatileTest.in());
            thread.start();
            countDownLatch.countDown();
        }
        System.out.println(i);
        System.out.println(j);
    }

    public static void in() {
        i.getAndIncrement();
        j++;
    }
}
