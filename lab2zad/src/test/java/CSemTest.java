import common.Counter;
import common.Decrementer;
import common.Incrementer;
import common.Semafor;
import org.junit.Assert;
import zad3.CountingSemafor;

import java.util.ArrayList;

public class CSemTest {
    @org.junit.Test
    public void CSemTest() {
        Semafor sem = new CountingSemafor(1);
        Counter counter = new Counter(0);

        ArrayList<Thread> dec = new ArrayList<>();
        ArrayList<Thread> inc = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            dec.add(new Decrementer(sem, counter));
            dec.get(dec.size()-1).start();
            inc.add(new Incrementer(sem, counter));
            inc.get(inc.size()-1).start();
        }

        try {
            for (int i = 0; i < 1000; i++) {
                dec.get(i).join();
                inc.get(i).join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(0, counter.counter);
    }
}
