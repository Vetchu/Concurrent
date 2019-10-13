package zad2;

import common.Semafor;

public class BSemafor implements Semafor {
    private boolean stan = true;
    private int czeka = 0;

    public synchronized void P() {
        if(!stan) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        stan = false;
    }

    public synchronized void V() {
        stan = true;
        notifyAll();
    }
}