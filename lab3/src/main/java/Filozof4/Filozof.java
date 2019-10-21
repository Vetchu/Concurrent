package Filozof4;

import java.util.concurrent.Semaphore;
import utils.TimeCounter;

public class Filozof extends Thread {
    private final int count;
    private TimeCounter counter;
    private Semaphore left, right, arbiter;
    private Integer mynumber;

    Filozof(Semaphore left, Semaphore right, int num, Semaphore arbiter,int count) {
        this.left = left;
        this.right = right;
        this.arbiter = arbiter;
        this.mynumber = num;
        this.count=count;
        counter = new TimeCounter(this.mynumber);
    }

    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
//            System.out.println("Filozof Mysli " + mynumber);
            counter.startCount();
            try {
                arbiter.acquire();
                left.acquire();
                right.acquire();
                counter.endCount();
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            System.out.println("FILOZOF Je " + mynumber);
            left.release();
            right.release();
            arbiter.release();
        }
    }
}