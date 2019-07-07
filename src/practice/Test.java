package practice;

import sort.Single;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yang
 * @date 2019/7/2 23:42
 */
public class Test implements Runnable {
    private static AtomicInteger count = new AtomicInteger(0);
    private CountDownLatch countDownLatch;
    private Map<Integer, Integer> map;

    public Test(CountDownLatch countDownLatch, Map<Integer, Integer> map) {
        this.countDownLatch = countDownLatch;
        this.map = map;
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        CountDownLatch countDownLatch = new CountDownLatch(100);
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < 100; i++) {
            executorService.submit(new Test(countDownLatch,hashMap));
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(count);
        executorService.shutdown();
    }

    @Override
    public void run() {
        count.getAndIncrement();
        map.put(count.get(), count.get());
        System.out.println(map.get(count.get()));
        countDownLatch.countDown();
    }
}
