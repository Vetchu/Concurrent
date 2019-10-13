import java.util.stream.IntStream;

import static java.lang.Thread.sleep;


public class Main {
    public static void main(String[] args) {
        Counter counter = new Counter(0);
        long startTime = System.currentTimeMillis();

            Thread a = new Thread(() -> {
                for (int i = 0; i < 1000; i++) {
                    counter.decrement();
                }
            });
            Thread b = new Thread(() -> {
                for (int i = 0; i < 1000; i++) {
                    counter.increment();
                }
            });
            a.start();
            b.start();

            try {
                a.join();
                b.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println("time elapsed "+estimatedTime);

//        int ncounter = 0;
//        while (true) {
//            try {
//                System.out.println("nowy watek" + ncounter);
//                new Thread() {
//                    public void run() {
//                        while (true) {
//                            try {
//                                sleep(1000000000);
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }
//                }.start();
//                ncounter++;
//            } catch (Exception e) {
//                e.printStackTrace();
//                System.out.println(ncounter);
//            }
//        }
    }
}