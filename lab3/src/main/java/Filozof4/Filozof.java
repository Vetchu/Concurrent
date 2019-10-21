package Filozof4;

import java.util.concurrent.Semaphore;

public class Filozof extends Thread {
    private Semaphore left, right, arbiter;
    private Integer mynumber;

    Filozof(Semaphore left, Semaphore right, int num, Semaphore arbiter) {
        this.left = left;
        this.right = right;
        this.arbiter = arbiter;
        this.mynumber = num;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("Filozof Mysli " + mynumber);
            try {
                arbiter.acquire();
                left.acquire();
                right.acquire();
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("FILOZOF Je " + mynumber);
            left.release();
            right.release();
            arbiter.release();
        }
    }
}