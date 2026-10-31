import java.util.concurrent.RecursiveAction;

/**
 * Fork/Join task to compute distances for a range of points.
 */
public class DistanceTask extends RecursiveAction {
    private final Point[] p;
    private final Point[] q;
    private final double[] distances;
    private final int start;
    private final int end;
    private final int threshold;

    /**
     * Constructs a task for a range of indices.
     * 
     * @param p         first array of points
     * @param q         second array of points
     * @param distances array to store distances
     * @param start     starting index
     * @param end       ending index (exclusive)
     * @param threshold task size threshold
     */
    public DistanceTask(Point[] p, Point[] q, double[] distances, int start, int end, int threshold) {
        this.p = p;
        this.q = q;
        this.distances = distances;
        this.start = start;
        this.end = end;
        this.threshold = threshold;
    }

    @Override
    protected void compute() {
        if (end - start <= threshold) {
            // Compute distances directly
            for (int i = start; i < end; i++) {
                distances[i] = p[i].distanceTo(q[i]);
            }
        } else {
            // Split into two tasks
            int mid = start + (end - start) / 2;
            DistanceTask left = new DistanceTask(p, q, distances, start, mid, threshold);
            DistanceTask right = new DistanceTask(p, q, distances, mid, end, threshold);
            left.fork(); // Execute left task in parallel
            right.compute(); // Compute right task in current thread
            left.join(); // Wait for left task to complete
        }
    }
}
