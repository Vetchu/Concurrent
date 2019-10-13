import common.Counter;
import common.Decrementer;
import common.Incrementer;
import common.Semafor;
import org.junit.Assert;
import zad2.BSemafor;

import java.util.ArrayList;

public class BSemIfTest {
    @org.junit.Test
    public void BSemIfTest() {
        Semafor sem = new BSemafor();
        Counter counter = new Counter(0);
        ArrayList<Thread> dec = new ArrayList<>();
        ArrayList<Thread> inc = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            dec.add(new Decrementer(sem, counter));
            dec.get(i).start();
            inc.add(new Incrementer(sem, counter));
            inc.get(i).start();
        }
//        sem.notify();

        try {
            for (int i = 0; i < 1000; i++) {
                dec.get(i).join();
                inc.get(i).join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(counter.counter);
        Assert.assertNotEquals(0, counter.counter);
    }
}
