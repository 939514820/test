package thread;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;

public class RandomTest {
    public static void main(String[] args) {
//        Random random=new Random();
//        for (int i = 0; i <10 ; i++) {
//            System.out.println(random.nextInt(5));
//        }
        // 获取当前线程的随机数生成器
        ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int i = 0; i <10 ; i++) {
            System.out.println(random.nextInt(5));
        }
    }
}
