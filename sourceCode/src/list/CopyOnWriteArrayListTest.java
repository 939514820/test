package list;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListTest {
    public static void main(String[] args) {
        CopyOnWriteArrayList test = new CopyOnWriteArrayList();
        test.add(new Object());
        Iterator iterator = test.iterator();
        while(iterator.hasNext()){
            Object next = iterator.next();
        }
    }
}
