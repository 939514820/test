package thread;

public class InheritedThreadLocalTest {
    public static ThreadLocal<String> threadLocal = new InheritableThreadLocal<>();

    public static void main(String[] args) {
        threadLocal.set("hello world");
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread:" + threadLocal.get());
            }
        });

        thread.start();
        System.out.println("main:" + threadLocal.get());

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread:" + threadLocal.get());
            }
        });

        threadLocal.set("chang ...");
        System.out.println("main:" + threadLocal.get());
        thread1.start();
    }
}
