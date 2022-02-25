package geometry;

import java.util.Objects;

import tools.Tools;

/**
 * @author Noam Cohen cohennoam48@gmail.com
 * id: 211497037
 */

public class Point {

    private final double x;
    private final double y;

    // ############################# Constructors ######################################

    /**
     * construct a point instance.
     *
     * @param x the x value of the point coordinates.
     * @param y the y value of the point coordinates.
     */
    public Point(double x, double y) {

        this.x = x;
        this.y = y;
    }

    // ############################# Getters ##########################################

    /**
     * Return the x value of this point.
     *
     * @return the x value as double.
     */
    public double getX() {

        return this.x;
    }

    /**
     * Return the y value of this point.
     *
     * @return the y value as double.
     */
    public double getY() {

        return this.y;
    }

    /**
     * return the distance of this point to the other point.
     *
     * @param other the other point to measure distance to.
     * @return the distance as double.
     */
    public double distance(Point other) {
        // calculating according to pythagorean theorem
        double dx = this.x - other.getX();
        double dy = this.y - other.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    // ###################################################################################

    /**
     * return true if the points are equal, false otherwise.
     *
     * @param other the other point to check against.
     * @return true if are the same false otherwise.

    public boolean equals(geometry.Point other) {

    if (other == null) {
    return false;
    }
    return this.x == other.getX() && this.y == other.getY();
    }
     */

    /**
     * return true if the points are equal, false otherwise.
     *
     * @param o the other point to check against.
     * @return true if are the same false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Point other = (Point) o;
        return Tools.compare(other.getX(), getX()) == 0 && Tools.compare(other.getY(), getY()) == 0;
    }

    /**
     * return the hash code of the object.
     *
     * @return hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY());
    }
}