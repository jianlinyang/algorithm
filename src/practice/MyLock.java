package practice;

/**
 * @author yang
 * @date 2019/8/14 18:41
 */
public class MyLock implements Runnable {
    private Object o1;
    private Object o2;

    public MyLock(Object o1, Object o2) {
        this.o1 = o1;
        this.o2 = o2;
    }

    @Override
    public void run() {
        synchronized (o1){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (o2){

            }
        }
    }
}
