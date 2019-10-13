package common;

public class Counter {
    public int counter;

    public Counter(int startval) {
        counter = startval;
    }

    public  void increment() {
        counter++;
//        System.out.println("i" + counter);
    }

    public  void decrement() {
        counter--;
//        System.out.println("d" + counter);
    }
}