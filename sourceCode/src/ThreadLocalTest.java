/**
 * @author luoyani 2020/8/19 18:10
 */
public class ThreadLocalTest {
    // Threadlocal是一个介质，获取线程内部变量threadLocalMap的值,key为当前threadLocal对象,Entity对象根据key的hash值确定位置
    // 由于key是弱引用，threadLocal如果没有对象强引用，就会被回收，value
    // 如何避免泄漏
    //既然Key是弱引用，那么我们要做的事，就是在调用ThreadLocal的get()、set()方法时完成后再调用remove方法，将Entry节点和Map的引用关系移除，这样整个Entry对象在GC Roots分析后就变成不可达了，下次GC的时候就可以被回收。
    //将Entry的Key设置成弱引用，在配合线程池使用的情况下可能会有内存泄露的风险。
    // 之设计成弱引用的目的是为了更好地对ThreadLocal进行回收，当我们在代码中将ThreadLocal的强引用置为null后，
    // 这时候Entry中的ThreadLocal理应被回收了，但是如果Entry的key被设置成强引用则该ThreadLocal就不能被回收，
    // 这就是将其设置成弱引用的目的
    static ThreadLocal<String> threadLocal2 = new ThreadLocal<>();
    static ThreadLocal<String> threadLocal1 = new ThreadLocal<>();

    public static void main(String[] args) {

        MyThread1 t1 = new MyThread1();
        t1.start();
        System.out.println(t1);
        MyThread2 t2 = new MyThread2();
        t2.start();
        System.out.println(t2);

    }

    public static class MyThread1 extends Thread {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
            threadLocal1.set("张三");
            threadLocal2.set("张三二");
            System.out.println(threadLocal1.get());
            System.out.println(threadLocal2.get());
        }
    }

    public static class MyThread2 extends Thread {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
            threadLocal1.set("李四");
            threadLocal2.set("李四二");
            System.out.println(threadLocal1.get());
            System.out.println(threadLocal2.get());
        }
    }
}
