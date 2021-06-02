import java.lang.management.MonitorInfo;
import java.util.concurrent.*;

public class Main {

    // Creating object MONITOR.
    private static final Object MONITOR = new Object();

    // Creating static and final needed variables.
    private static final String A = "A";
    private static final String B = "B";
    private static final String C = "C";
    private static String nextLetter = A;

    public static void main(String[] args) {

        // Synchronized thread, that prints "A" 5 times.
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (MONITOR) {
                    for (int i = 0; i < 5; i++) {
                        try {
                            while(!nextLetter.equals(A)){
                                MONITOR.wait();
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.print(A);
                        nextLetter = B;
                        MONITOR.notifyAll();
                    }
                }

            }
        }).start();

        // Synchronized thread, that prints "B" 5 times.
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (MONITOR) {
                    for (int i = 0; i < 5; i++) {
                        try {
                            while(!nextLetter.equals(B)){
                                MONITOR.wait();
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.print(B);
                        nextLetter = C;
                        MONITOR.notifyAll();
                    }
                }
            }
        }).start();

        // Synchronized thread, that prints "C" 5 times.
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (MONITOR) {
                    for (int i = 0; i < 5; i++) {
                        try {
                            while(!nextLetter.equals(C)){
                                MONITOR.wait();
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.print(C);
                        nextLetter = A;
                        MONITOR.notifyAll();
                    }
                }
            }
        }).start();
    }
}
