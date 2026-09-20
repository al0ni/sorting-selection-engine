package kz.edu.daa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.Test;

class QuickSelectTest {
    @Test
    void randomArrays() {
        Random r = new Random(30);
        for (int t = 0; t < 100; t++) {
            int[] a = new int[r.nextInt(499) + 1];
            for (int i = 0; i < a.length; i++) {
                a[i] = r.nextInt(2001) - 1000;
            }
            int[] expected = Arrays.copyOf(a, a.length);
            Arrays.sort(expected);
            int k = r.nextInt(a.length);
            assertEquals(expected[k], QuickSelect.select(a, k, new Metrics()));
        }
    }

    @Test
    void edgeCases() {
        assertEquals(7, QuickSelect.select(new int[]{7}, 0, new Metrics()));
        assertEquals(4, QuickSelect.select(new int[]{4, 4, 4, 4}, 2, new Metrics()));
        assertEquals(2, QuickSelect.select(new int[]{1, 2, 3, 4}, 1, new Metrics()));
    }

    @Test
    void invalidInput() {
        assertThrows(IllegalArgumentException.class, () -> QuickSelect.select(new int[]{}, 0, new Metrics()));
        assertThrows(IllegalArgumentException.class, () -> QuickSelect.select(new int[]{1, 2}, -1, new Metrics()));
        assertThrows(IllegalArgumentException.class, () -> QuickSelect.select(new int[]{1, 2}, 2, new Metrics()));
    }
}
