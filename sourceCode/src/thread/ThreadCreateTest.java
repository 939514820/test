package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ThreadCreateTest {
    public static class CallerTask implements Callable {

        @Override
        public Object call() throws Exception {
            return "hello";
        }
    }
    public static void main(String[] args) {
        FutureTask task = new FutureTask(new CallerTask());
        Thread t = new Thread(task);
        t.start();
        try {
            task.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
