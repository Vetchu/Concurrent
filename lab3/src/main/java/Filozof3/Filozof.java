package Filozof3;

import java.util.concurrent.Semaphore;

public class Filozof extends Thread {
    private Semaphore left, right;
    private Integer mynumber;

    Filozof(Semaphore left, Semaphore right, int num) {
        this.left = left;
        this.right = right;
        this.mynumber=num;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("Filozof Mysli "+mynumber);
            try {
                if(mynumber%2==1){
                    left.acquire();
                    right.acquire();
                }else{
                    right.acquire();
                    left.acquire();
                }
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("FILOZOF Je " + mynumber);
            left.release();
            right.release();
        }
    }
}