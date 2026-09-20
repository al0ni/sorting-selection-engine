package kz.edu.daa;

public class QuickSelect {
    public static int select(int[] a, int k, Metrics m) {
        if (a == null || a.length == 0) {
            throw new IllegalArgumentException("Array must not be empty");
        }
        if (k < 0 || k >= a.length) {
            throw new IllegalArgumentException("k must be from 0 to " + (a.length - 1));
        }
        if (m == null) {
            throw new IllegalArgumentException("Metrics must not be null");
        }
        m.start();
        int value = select(a, k, 0, a.length - 1, m, 1);
        m.stop();
        return value;
    }

    private static int select(int[] a, int k, int left, int right, Metrics m, int depth) {
        m.checkDepth(depth);
        if (left == right) {
            return a[left];
        }

        int[] p = QuickSort.partition(a, left, right, m);
        if (k < p[0]) {
            return select(a, k, left, p[0] - 1, m, depth + 1);
        }
        if (k > p[1]) {
            return select(a, k, p[1] + 1, right, m, depth + 1);
        }
        return a[k];
    }
}
