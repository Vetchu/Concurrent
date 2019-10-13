package zad3;

import common.Semafor;
import zad3.BSemafor;

import java.util.PriorityQueue;

public class CountingSemafor implements Semafor {
    private Semafor S1,S2;
    private int czeka = 0;
    private PriorityQueue pQueue;

    CountingSemafor(int copies){
        czeka=copies;
        S1=new BSemafor();
        S2=new BSemafor();
        S2.P();
//        pQueue= new PriorityQueue<Integer>();
//        for(int i=0;i<czeka;i++){
//            pQueue.add(i);
//        }
    }
    public synchronized void P() {
        S1.P();
        czeka--;

        if (czeka<0) {
                S1.V();
                S2.P();
        }
        S1.V();
        czeka--;

//        Integer toRet= pQueue.peek();
//        pQueue.
//        return
    }

    public synchronized void V() {
        S1.P();
        czeka++;
        if (czeka<=0){
            S2.V();
        }
        S1.V();
    }
}