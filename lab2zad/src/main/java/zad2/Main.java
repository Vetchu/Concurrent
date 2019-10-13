package zad2;


import common.Counter;
import common.Decrementer;
import common.Incrementer;
import common.Semafor;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Semafor sem = new BSemafor();
        Counter counter = new Counter(0);
        long startTime = System.currentTimeMillis();

        ArrayList<Thread> dec=new ArrayList<>();
        ArrayList<Thread> inc=new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            dec.add(new Decrementer(sem, counter));
            dec.get(dec.size()-1).start();
            inc.add(new Incrementer(sem, counter));
            inc.get(dec.size()-1).start();
        }

        try {
            for (int i = 0; i < 1000; i++) {
                dec.get(i).join();
                inc.get(i).join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println(counter.counter);
        System.out.println("time elapsed " + estimatedTime);
    }
}