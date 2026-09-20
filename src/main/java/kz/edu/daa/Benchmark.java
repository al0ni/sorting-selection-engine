package kz.edu.daa;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Locale;
import java.util.Random;

public class Benchmark {
    private static final int[] SIZES = {1_000, 10_000, 100_000, 1_000_000};
    private static final String[] INPUTS = {"random", "sorted", "duplicates"};
    private static final String[] ALGORITHMS = {"MergeSort", "QuickSort", "QuickSelect"};

    public static void main(String[] args) throws IOException {
        BufferedWriter out = Files.newBufferedWriter(Path.of("results.csv"));
        out.write("algorithm,input,n,time_ms,comparisons,max_depth");
        out.newLine();

        for (String algorithm : ALGORITHMS) {
            for (String input : INPUTS) {
                for (int n : SIZES) {
                    out.write(run(algorithm, input, n));
                    out.newLine();
                }
            }
        }

        out.close();
        System.out.println("Results were saved to results.csv");
    }

    private static String run(String algorithm, String input, int n) {
        long[] times = new long[5];
        long[] comparisons = new long[5];
        int[] depths = new int[5];
        int[] original = makeArray(input, n);

        for (int i = 0; i < 5; i++) {
            int[] arr = Arrays.copyOf(original, original.length);
            Metrics m = new Metrics();

            if (algorithm.equals("MergeSort")) {
                MergeSort.sort(arr, m);
            } else if (algorithm.equals("QuickSort")) {
                QuickSort.sort(arr, m);
            } else {
                QuickSelect.select(arr, n / 2, m);
            }

            times[i] = m.getTimeNanos();
            comparisons[i] = m.getComparisons();
            depths[i] = m.getMaxDepth();
        }

        Arrays.sort(times);
        Arrays.sort(comparisons);
        Arrays.sort(depths);

        double time = times[2] / 1_000_000.0;
        System.out.printf(Locale.US, "%s %s %d: %.3f ms%n", algorithm, input, n, time);
        return String.format(Locale.US, "%s,%s,%d,%.3f,%d,%d",
                algorithm, input, n, time, comparisons[2], depths[2]);
    }

    private static int[] makeArray(String input, int n) {
        int[] arr = new int[n];
        Random r = new Random(31L * n + input.hashCode());

        for (int i = 0; i < n; i++) {
            if (input.equals("sorted")) {
                arr[i] = i;
            } else if (input.equals("duplicates")) {
                arr[i] = r.nextInt(10);
            } else {
                arr[i] = r.nextInt();
            }
        }
        return arr;
    }
}
