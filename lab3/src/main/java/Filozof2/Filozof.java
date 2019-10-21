package Filozof2;

import java.util.concurrent.Semaphore;

import static java.lang.Thread.sleep;

public class Filozof extends Thread {
    private final int count;
    private Semaphore left, right, table;
    private Integer mynumber;

    Filozof(Semaphore left, Semaphore right, int num, Semaphore table,int count) {
        this.left = left;
        this.right = right;
        this.table = table;
        this.mynumber = num;
        this.count=count;
    }

    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
            try {
                while (true) {
                    table.acquire();
                    if((left.availablePermits() == 1 && right.availablePermits() == 1)){
                        left.acquire();
                        right.acquire();
                        table.release();
                        break;
                    }
                    table.release();
                }

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