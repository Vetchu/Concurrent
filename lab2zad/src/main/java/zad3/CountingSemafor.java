package zad3;

import common.Semafor;

public class CountingSemafor implements Semafor {
    private Semafor S1, S2;
    private int czeka = 0;

    public CountingSemafor(int copies) {
        czeka = copies;
        S1 = new BSemafor();
        S2 = new BSemafor();
        S2.P();
    }

    public void P() {
        S1.P();
        czeka--;

        if (czeka < 0) {
            S1.V();
            S2.P();
        } else {
            S1.V();
        }
    }

    public synchronized void V() {
        S1.P();
        czeka++;
        if (czeka <= 0) {
            S2.V();
        }
        S1.V();
    }
}