package practice;

/**
 * @author yang
 * @date 2019/8/14 18:37
 */
public class DeadLock {

    public static void main(String[] args) {
        Object o1 = new Object();
        Object o2 = new Object();
        MyLock myLock = new MyLock(o1, o2);
        MyLock myLock2 = new MyLock(o2, o1);
        new Thread(myLock).start();
        new Thread(myLock2).start();
    }

}
