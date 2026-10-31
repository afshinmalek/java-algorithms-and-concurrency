import java.util.Random;
import java.util.concurrent.ForkJoinPool;

/**
 * Manages arrays of points and computes distances between them.
 */
public class DistanceCalculator {

    private static final int SIZE = 10_000_000;
    private final Point[] p;
    private final Point[] q;
    private final double[] distances;
    private final Random random;

    /**
     * Initializes arrays and random number generator.
     */
    public DistanceCalculator() {
        p = new Point[SIZE];
        q = new Point[SIZE];
        distances = new double[SIZE];
        random = new Random();
        generatePoints();
    }

    /**
     * Generates random coordinates for points in arrays p and q.
     */
    private void generatePoints() {
        for (int i = 0; i < SIZE; i++) {
            p[i] = new Point(random.nextDouble() * 100, random.nextDouble() * 100, random.nextDouble() * 100);
            q[i] = new Point(random.nextDouble() * 100, random.nextDouble() * 100, random.nextDouble() * 100);
        }
    }

    /**
     * Computes distances sequentially using a single thread.
     * 
     * @return execution time in seconds
     */
    public double computeSingleThreaded() {
        long start = System.nanoTime();
        for (int i = 0; i < SIZE; i++) {
            distances[i] = p[i].distanceTo(q[i]);
        }
        long end = System.nanoTime();
        return (end - start) / 1_000_000_000.0;
    }

    /**
     * Computes distances using Fork/Join framework with given task size.
     * 
     * @param threshold task size for splitting
     * @return execution time in seconds
     */
    public double computeMultiThreaded(int threshold) {
        ForkJoinPool pool = new ForkJoinPool();
        long start = System.nanoTime();
        DistanceTask task = new DistanceTask(p, q, distances, 0, SIZE, threshold);
        pool.invoke(task);
        long end = System.nanoTime();
        return (end - start) / 1_000_000_000.0;
    }

    /**
     * Returns the distances array (for verification if needed).
     * 
     * @return distances
     */
    public double[] getDistances() {
        return distances;
    }
}
