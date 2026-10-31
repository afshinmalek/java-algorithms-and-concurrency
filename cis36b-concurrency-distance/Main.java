/**
 * Runs distance calculations and compares single-threaded and multi-threaded
 * performance.
 */
public class Main {
    public static void main(String[] args) {
        DistanceCalculator calculator = new DistanceCalculator();
        int[] thresholds = { 100, 1000, 10000, 100000, 10_000_000 };

        // Single-threaded computation
        System.out.println("Running single-threaded computation...");
        double singleThreadedTime = calculator.computeSingleThreaded();
        System.out.printf("Single-threaded: %.3f seconds%n%n", singleThreadedTime);

        // Multi-threaded computations
        System.out.println("Running multi-threaded computations...");
        System.out.printf("%-10s %s%n", "Threshold", "Time (seconds)");
        System.out.println("-------------------------");
        for (int threshold : thresholds) {
            double time = calculator.computeMultiThreaded(threshold);
            System.out.printf("%-10d %.3f%n", threshold, time);
        }
    }
}

/*
 * Running single-threaded computation...
 * Single-threaded: 0.103 seconds
 * 
 * Running multi-threaded computations...
 * Threshold Time (seconds)
 * -------------------------
 * 100 0.167
 * 1000 0.068
 * 10000 0.057
 * 100000 0.073
 * 10000000 0.108
 */