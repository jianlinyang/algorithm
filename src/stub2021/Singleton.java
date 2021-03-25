package stub2021;

/**
 * @author YangJianlin
 * @date 2021/3/24 11:12
 */
public class Singleton {
    private Singleton() {
    }

    private static volatile Singleton singleton;

    public static Singleton getSingleTon() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
//        return SingleTonHolder.INSTANCE;
    }

    public static class SingleTonHolder {
        public static Singleton INSTANCE = new Singleton();
    }

    public static void main(String[] args) {
        while (true) {
            new Thread(() -> {
                System.out.println(Singleton.getSingleTon());
            }).start();
            new Thread(() -> {
                System.out.println(Singleton.getSingleTon());
            }).start();
            new Thread(() -> {
                System.out.println(Singleton.getSingleTon());
            }).start();
        }
    }
}
