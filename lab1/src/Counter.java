class Counter {
    public Integer counter;

    Counter(Integer startval) {
        counter = startval;
    }

    public synchronized void increment() {
        System.out.println("i" + counter);
        counter++;
//        }
    }

    public synchronized void decrement() {
        System.out.println("d" + counter);
        counter--;
//        }
    }
}

class Semafor {
    private boolean stan = true;
    private int czeka = 0;

    public Semafor() {

    }

    public synchronized void P() {
        czeka++;
        while (!stan){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        stan=false;
        czeka--;
    }

    public synchronized void V() {

    }
}