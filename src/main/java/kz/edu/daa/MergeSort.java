package kz.edu.daa;

public class MergeSort {
    private static final int LIMIT = 15;

    public static void sort(int[] a, Metrics m) {
        if (a == null || m == null) {
            throw new IllegalArgumentException("Array and metrics must not be null");
        }
        m.start();
        if (a.length > 0) {
            int[] temp = new int[a.length];
            sort(a, temp, 0, a.length - 1, m, 1);
        }
        m.stop();
    }

    private static void sort(int[] a, int[] temp, int left, int right, Metrics m, int depth) {
        m.checkDepth(depth);
        if (right - left + 1 <= LIMIT) {
            insertionSort(a, left, right, m);
            return;
        }

        int mid = left + (right - left) / 2;
        sort(a, temp, left, mid, m, depth + 1);
        sort(a, temp, mid + 1, right, m, depth + 1);
        merge(a, temp, left, mid, right, m);
    }

    private static void insertionSort(int[] a, int left, int right, Metrics m) {
        for (int i = left + 1; i <= right; i++) {
            int x = a[i];
            int j = i - 1;
            while (j >= left) {
                m.addComparison();
                if (a[j] <= x) {
                    break;
                }
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = x;
        }
    }

    private static void merge(int[] a, int[] temp, int left, int mid, int right, Metrics m) {
        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            m.addComparison();
            if (a[i] <= a[j]) {
                temp[k++] = a[i++];
            } else {
                temp[k++] = a[j++];
            }
        }

        while (i <= mid) {
            temp[k++] = a[i++];
        }

        while (j <= right) {
            temp[k++] = a[j++];
        }

        for (i = left; i <= right; i++) {
            a[i] = temp[i];
        }
    }
}
