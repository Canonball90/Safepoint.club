package com.example.examplemod.Utils;

public class TimerUtil {
    private long previousMS = 0L;

    public void reset() {
        this.previousMS = this.getTime();
    }

    public boolean hasReached(double d) {
        return (double) (this.getTime() - this.previousMS) >= d;
    }

    public long getTime() {
        return System.nanoTime() / 1000000L;
    }

    private long time = -1L;
    long startTime;
    long delay;
    boolean paused;

    public TimerUtil() {
        this.startTime = System.currentTimeMillis();
        this.delay = 0L;
        this.paused = false;
    }

    public boolean passedMs(long ms) {
        return this.getMs(System.nanoTime() - this.time) >= ms;
    }

    public long getPassedTimeMs() {
        return this.getMs(System.nanoTime() - this.time);
    }

    public long getMs(long time) {
        return time / 1000000L;
    }

    private static long ms = getCurrentMS();

    private static long getCurrentMS() {
        return System.currentTimeMillis();
    }

    public static boolean hasReached(float milliseconds) {
        return getCurrentMS() - ms > milliseconds;
    }

    public static void reset1() {
        ms = getCurrentMS();
    }

}
