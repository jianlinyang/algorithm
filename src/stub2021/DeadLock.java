package stub2021;

/**
 * @author YangJianlin
 * @date 2021/3/24 12:24
 */
public class DeadLock {
    private static Object lock1 = new Object();
    private static Object lock2 = new Object();

    public static void main(String[] args) {
        new Thread(() -> task(lock1, lock2)).start();
        new Thread(() -> task(lock2, lock1)).start();
    }

    private static void task(Object lock1, Object lock2) {
        synchronized (lock1) {
            System.out.println(Thread.currentThread() + "获得" + lock1 + "请求" + lock2);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock2) {
            }
        }
    }
}
