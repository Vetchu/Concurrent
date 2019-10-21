package Filozof3;

import utils.TimeCounter;

import java.util.concurrent.Semaphore;

public class Filozof extends Thread {
    private final TimeCounter counter;
    private Semaphore left, right;
    private Integer mynumber;
    private int count;

    Filozof(Semaphore left, Semaphore right, int num,int count) {
        this.left = left;
        this.right = right;
        this.mynumber=num;
        this.count=count;
        counter = new TimeCounter(this.mynumber);
    }

    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
//            System.out.println("Filozof Mysli "+mynumber);
            counter.startCount();
            try {
                if(mynumber%2==1){
                    left.acquire();
                    right.acquire();
                }else{
                    right.acquire();
                    left.acquire();
                }
                counter.endCount();
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            System.out.println("FILOZOF Je " + mynumber);
            left.release();
            right.release();
        }
    }
}