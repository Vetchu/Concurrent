package Filozof4;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Semaphore arbiter=new Semaphore(4);
        ArrayList<Semaphore> sem=new ArrayList<>();
        ArrayList<Filozof> fil=new ArrayList<>();

        for (int i=0;i<5;i++){
            sem.add(new Semaphore(1));
        }

        for (int i = 0; i < 5; i++) {
            fil.add(new Filozof(sem.get(i), sem.get((i+1)%5),i,arbiter,10));
            fil.get(i).start();
        }
    }
}