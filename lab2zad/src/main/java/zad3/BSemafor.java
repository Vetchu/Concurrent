package zad3;

import common.Semafor;

public class BSemafor implements Semafor {
    private boolean stan = true;

    public synchronized void P() {
        while (!stan) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (!stan) {
            System.out.println("Error: I should not be here.");
        }
        stan = false;
    }

    public synchronized void V() {
        stan = true;
        this.notify();
    }
}