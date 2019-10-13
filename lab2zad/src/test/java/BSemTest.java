import common.Counter;
import common.Decrementer;
import common.Incrementer;
import common.Semafor;
import org.junit.Assert;
import zad1.BSemafor;

public class BSemTest {
    @org.junit.Test
    public void BSemTest() {
        Semafor sem = new BSemafor();
        Counter counter = new Counter(0);
        Thread a = new Decrementer(sem, counter);
        Thread b = new Incrementer(sem, counter);
        b.start();
        a.start();
        try {
            a.join();
            b.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(0, counter.counter);
    }
}
