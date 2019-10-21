package Filozof1;

import utils.TimeCounter;

import java.util.concurrent.Semaphore;

public class Filozof extends Thread {
    private final TimeCounter counter;
    private Semaphore left, right;
    private Integer mynumber;
    private int count;

    Filozof(Semaphore left, Semaphore right, int num,int count ) {
        this.left = left;
        this.right = right;
        mynumber=num;
        this.count=count;
        counter = new TimeCounter(this.mynumber);
    }

    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
            counter.startCount();
            try {
                left.acquire();
                right.acquire();
                counter.endCount();
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            System.out.println("FILOZOF JEM" + mynumber.toString());
            left.release();
            right.release();
        }
    }
}