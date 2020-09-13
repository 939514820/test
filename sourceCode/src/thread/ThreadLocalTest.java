package thread;

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
// 当前线程持有treadlocal 强引用时候 key被回收
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
            threadLocal1.remove();
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

    /**
     * Set the value associated with key.
     *
     * @param key the thread local object
     * @param value the value to be set
     */
//    private void set(ThreadLocal<?> key, Object value) {
//
//        // We don't use a fast path as with get() because it is at
//        // least as common to use set() to create new entries as
//        // it is to replace existing ones, in which case, a fast
//        // path would fail more often than not.
//
//        ThreadLocal.ThreadLocalMap.Entry[] tab = table;
//        int len = tab.length;
//        int i = key.threadLocalHashCode & (len-1);
//        // 寻找符合条件的 如果找到key相等的 就去赋值value
//        for (ThreadLocal.ThreadLocalMap.Entry e = tab[i];
//             e != null;
//             e = tab[i = nextIndex(i, len)]) {
//            ThreadLocal<?> k = e.get();
//
//            if (k == key) {
//                e.value = value;
//                return;
//            }
//
//            if (k == null) {
//                replaceStaleEntry(key, value, i);
//                return;
//            }
//        }
//
//        tab[i] = new ThreadLocal.ThreadLocalMap.Entry(key, value);
//        int sz = ++size;
//        if (!cleanSomeSlots(i, sz) && sz >= threshold)
//            rehash();
//    }
/*
    private void replaceStaleEntry(ThreadLocal<?> key, Object value,
                                   int staleSlot) {
        ThreadLocal.ThreadLocalMap.Entry[] tab = table;
        int len = tab.length;
        ThreadLocal.ThreadLocalMap.Entry e;

        // Back up to check for prior stale entry in current run.
        // We clean out whole runs at a time to avoid continual
        // incremental rehashing due to garbage collector freeing
        // up refs in bunches (i.e., whenever the collector runs).
        int slotToExpunge = staleSlot;
        for (int i = prevIndex(staleSlot, len);
             (e = tab[i]) != null;
             i = prevIndex(i, len))
            if (e.get() == null)
                slotToExpunge = i;

        // Find either the key or trailing null slot of run, whichever
        // occurs first
        for (int i = nextIndex(staleSlot, len);
             (e = tab[i]) != null;
             i = nextIndex(i, len)) {
            ThreadLocal<?> k = e.get();

            // If we find key, then we need to swap it
            // with the stale entry to maintain hash table order.
            // The newly stale slot, or any other stale slot
            // encountered above it, can then be sent to expungeStaleEntry
            // to remove or rehash all of the other entries in run.
            if (k == key) {
                e.value = value;

                tab[i] = tab[staleSlot];
                tab[staleSlot] = e;

                // Start expunge at preceding stale entry if it exists
                if (slotToExpunge == staleSlot)
                    slotToExpunge = i;
                cleanSomeSlots(expungeStaleEntry(slotToExpunge), len);
                return;
            }

            // If we didn't find stale entry on backward scan, the
            // first stale entry seen while scanning for key is the
            // first still present in the run.
            if (k == null && slotToExpunge == staleSlot)
                slotToExpunge = i;
        }

        // If key not found, put new entry in stale slot
        tab[staleSlot].value = null;
        tab[staleSlot] = new ThreadLocal.ThreadLocalMap.Entry(key, value);

        // If there are any other stale entries in run, expunge them
        if (slotToExpunge != staleSlot)
            cleanSomeSlots(expungeStaleEntry(slotToExpunge), len);
    }
*/
}
