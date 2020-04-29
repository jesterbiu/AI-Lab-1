package nPuzzles;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class timeCounter {
    private static Lock mutex = new ReentrantLock();
    private static long startTime = 0, endTime = 0;
    private static boolean isCounting = false;

    static void startCounting()
    {
        if (isCounting) {
            System.out.println("Counter is already counting!");
            return;
        }
        mutex.lock();
        try {
            isCounting = true;
            startTime = System.currentTimeMillis();
        }
        finally {
            mutex.unlock();
        }
    }

    // return duration in seconds
    static double stopCounting()
    {
        if (!isCounting){
            System.out.println("Counter is not counting!");
            return 0;
        }
        mutex.lock();
        try {
            endTime = System.currentTimeMillis();
            isCounting = false;
        }
        finally {
            mutex.unlock();
        }
        return (endTime - startTime) / 1000.0;
    }
}
