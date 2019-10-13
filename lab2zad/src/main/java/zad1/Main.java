package zad1;

import common.Counter;
import common.Decrementer;
import common.Incrementer;
import common.Semafor;

import static java.lang.Thread.sleep;


public class Main {
    public static void main(String[] args) {
        Semafor sem = new BSemafor();
        Counter counter = new Counter(0);
        long startTime = System.currentTimeMillis();

        Thread a = new Decrementer(sem, counter);
        Thread b = new Incrementer(sem, counter);

        b.start();
        a.start();

        try {
            a.join();
            b.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println("time elapsed " + estimatedTime);
    }
}