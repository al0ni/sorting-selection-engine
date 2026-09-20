package kz.edu.daa;

public class Metrics {
    private long comparisons;
    private int maxDepth;
    private long startTime;
    private long endTime;

    public void start() {
        comparisons = 0;
        maxDepth = 0;
        startTime = System.nanoTime();
        endTime = startTime;
    }

    public void stop() {
        endTime = System.nanoTime();
    }

    public void addComparison() {
        comparisons++;
    }

    public void checkDepth(int depth) {
        if (depth > maxDepth) {
            maxDepth = depth;
        }
    }

    public long getComparisons() {
        return comparisons;
    }

    public int getMaxDepth() {
        return maxDepth;
    }

    public long getTimeNanos() {
        return endTime - startTime;
    }

    public double getTimeMillis() {
        return getTimeNanos() / 1_000_000.0;
    }
}
