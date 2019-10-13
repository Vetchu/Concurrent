package zad1;

import common.Semafor;

public class BSemafor implements Semafor {
    private boolean stan = true;

    public synchronized void P() {
        while (!stan) {
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
        notify();
    }
}