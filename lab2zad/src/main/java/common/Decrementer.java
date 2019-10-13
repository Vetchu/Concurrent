package common;

public class Decrementer extends Thread {
    private Semafor sem;
    private Counter counter;

    public Decrementer(Semafor sem, Counter counter) {
        this.sem = sem;
        this.counter = counter;
    }

    public void run() {
        for (int i = 0; i < 1000; i++) {
            sem.P();
            counter.decrement();
            sem.V();
        }
    }
}