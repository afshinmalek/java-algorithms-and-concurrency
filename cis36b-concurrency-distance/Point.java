/**
 * Represents an immutable 3D point with x, y, z coordinates.
 */
public class Point {
    private final double x;
    private final double y;
    private final double z;

    /**
     * Constructs a point with given coordinates.
     * 
     * @param x x-coordinate
     * @param y y-coordinate
     * @param z z-coordinate
     */
    public Point(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Computes the Euclidean distance to another point.
     * 
     * @param other the other point
     * @return the distance
     */
    public double distanceTo(Point other) {
        double dx = this.x - other.x;
        double dy = this.y - other.y;
        double dz = this.z - other.z;
        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }
}
