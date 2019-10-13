public class Main {
    public static void main(String[] args) {
        Buffer buf = new Buffer();
        for (int i = 0; i < 5; i++) {
            Producer p = new Producer(buf);
            Consumer c = new Consumer(buf);
            p.start();
            c.start();
        }
        buf.taken = false;

    }
}

class Producer extends Thread {
    private final Buffer buf;
    Producer(Buffer buf) {
        this.buf = buf;
    }

    public void run() {
        for (int i = 0; i < 100; ++i) {
            synchronized (buf) {
                while (buf.taken || !(buf.pointer < 100)) {
                    try {
                        buf.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                buf.taken = true;
                buf.put(i);
                buf.taken = false;
            }
        }
    }
}

class Consumer extends Thread {
    private final Buffer buf;
    Consumer(Buffer buf) {
        this.buf = buf;
    }

    public void run() {
        for (int i = 0; i < 100; ++i) {
            synchronized (buf) {
                while (buf.pointer < 0) {
                    try {
                        buf.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(buf.get());
            }
        }
    }
}

class Buffer {
    private int n = 100;
    private int[] a = new int[n];
    public int pointer = 0;
    public boolean taken;

    public synchronized void put(int i) {
        a[pointer++] = i;
        notifyAll();
    }

    public synchronized int get() {
        int ret = a[0];
        for (int j = 0; j < n - 1; j++) {
            a[j] = a[j + 1];
        }
        pointer--;
        notifyAll();
        return ret;
    }
}
