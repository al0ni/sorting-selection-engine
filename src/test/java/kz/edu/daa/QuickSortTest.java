package kz.edu.daa;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.Test;

class QuickSortTest {
    @Test
    void randomArrays() {
        Random r = new Random(20);
        for (int t = 0; t < 100; t++) {
            int[] a = new int[r.nextInt(500)];
            for (int i = 0; i < a.length; i++) {
                a[i] = r.nextInt(2001) - 1000;
            }
            int[] expected = Arrays.copyOf(a, a.length);
            Arrays.sort(expected);
            QuickSort.sort(a, new Metrics());
            assertArrayEquals(expected, a);
        }
    }

    @Test
    void edgeCases() {
        check(new int[]{});
        check(new int[]{7});
        check(new int[]{4, 4, 4, 4, 4});
        check(new int[]{-3, 0, 2, 5, 9});
    }

    @Test
    void recursionDepth() {
        int n = 100_000;
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = i;
        }
        Metrics m = new Metrics();
        QuickSort.sort(a, m);
        double limit = 2 * (Math.log(n) / Math.log(2));
        assertTrue(m.getMaxDepth() <= limit);
    }

    private void check(int[] a) {
        int[] expected = Arrays.copyOf(a, a.length);
        Arrays.sort(expected);
        QuickSort.sort(a, new Metrics());
        assertArrayEquals(expected, a);
    }
}
