package Filozof1;

import java.util.concurrent.Semaphore;

public class Filozof extends Thread {
    private boolean stan = true;
    private Semaphore left, right;
    private Integer mynumber;

    Filozof(Semaphore left, Semaphore right, int num) {
        this.left = left;
        this.right = right;
        mynumber=num;
    }

    @Override
    public void run() {
        while (true) {
            try {
                left.acquire();
                right.acquire();
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("FILOZOF JEM" + mynumber.toString());
            left.release();
            right.release();
        }
    }
}