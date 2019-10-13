package zad2;

import common.Semafor;

public class BSemafor implements Semafor {
    private boolean stan = true;
    private int czeka = 0;

    public synchronized void P() {
        if (!stan) {
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
        notify();
    }
}