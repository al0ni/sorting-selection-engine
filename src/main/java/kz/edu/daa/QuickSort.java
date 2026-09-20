package kz.edu.daa;

import java.util.concurrent.ThreadLocalRandom;

public class QuickSort {
    public static void sort(int[] a, Metrics m) {
        if (a == null || m == null) {
            throw new IllegalArgumentException("Array and metrics must not be null");
        }
        m.start();
        if (a.length > 0) {
            sort(a, 0, a.length - 1, m, 1);
        }
        m.stop();
    }

    private static void sort(int[] a, int left, int right, Metrics m, int depth) {
        while (left < right) {
            m.checkDepth(depth);
            int[] p = partition(a, left, right, m);
            int leftSize = p[0] - left;
            int rightSize = right - p[1];

            if (leftSize < rightSize) {
                if (left < p[0] - 1) {
                    sort(a, left, p[0] - 1, m, depth + 1);
                }
                left = p[1] + 1;
            } else {
                if (p[1] + 1 < right) {
                    sort(a, p[1] + 1, right, m, depth + 1);
                }
                right = p[0] - 1;
            }
        }
        m.checkDepth(depth);
    }

    static int[] partition(int[] a, int left, int right, Metrics m) {
        int pivot = a[ThreadLocalRandom.current().nextInt(left, right + 1)];
        int i = left;
        int j = left;
        int k = right;

        while (j <= k) {
            m.addComparison();
            if (a[j] < pivot) {
                swap(a, i, j);
                i++;
                j++;
            } else {
                m.addComparison();
                if (a[j] > pivot) {
                    swap(a, j, k);
                    k--;
                } else {
                    j++;
                }
            }
        }
        return new int[]{i, k};
    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
