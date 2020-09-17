package lock;


import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLock {
    public static void main(String[] args) {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
        writeLock.unlock();
        readLock.lock();
        readLock.unlock();

        int EXCLUSIVE_MASK = (1 << 16) - 1;
        System.out.println(EXCLUSIVE_MASK);
    }
}
