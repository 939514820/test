package atomicclass;

import java.util.concurrent.atomic.LongAdder;

public class LongAddTest {
    public static void main(String[] args) {
        LongAdder longAdder=new LongAdder();
        longAdder.add(1);
    }
}
