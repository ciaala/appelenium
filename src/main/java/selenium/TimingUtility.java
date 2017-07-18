package selenium;

/**
 * Created by crypt on 5/22/2016.
 */
public class TimingUtility {

    private TimingUtility() {
        super();
    }

    static public void waitMillis(long waitTime) {
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() < start + waitTime) {
            Thread.yield();
        }
    }

    static public void waitMillis() {
        waitMillis(600);
    }
}
