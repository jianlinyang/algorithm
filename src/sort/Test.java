package sort;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yang
 * @date 2019/7/2 23:42
 */
public class Test implements Runnable {
    private Object instance = Single.SINGLE.getInstance();
    private static AtomicInteger count = new AtomicInteger(0);
    static int count2 = 0;
    private CountDownLatch countDownLatch;

    public Test(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        CountDownLatch countDownLatch = new CountDownLatch(10000);
        for (int i = 0; i < 10000; i++) {
            executorService.submit(new Test(countDownLatch));
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(count);
        System.out.println(count2);
        executorService.shutdown();
    }

    @Override
    public void run() {
        count2++;
        count.getAndIncrement();
        countDownLatch.countDown();
    }
}
