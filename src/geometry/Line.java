package geometry;

import tools.Tools;

import java.util.List;
import java.util.Objects;

/**
 * @author Noam Cohen cohennoam48@gmail.com
 * id: 211497037
 */

public class Line {
    private final Point start;
    private final Point end;

    // ############################# Constructors ######################################

    /**
     * construct a line with 2 points.
     * the start point will be the one with the smaller x value,
     * if the x is the same, than according to the smaller y
     *
     * @param start a point.
     * @param end   a point.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * construct a line with 4 double values.
     * the start point will be the one with the smaller x value,
     * if the x is the same, than according to the smaller y.
     *
     * @param x1 the x value of the first point.
     * @param y1 the y value of the first point.
     * @param x2 the x value of the second point.
     * @param y2 the y value of the second point.
     */
    public Line(double x1, double y1, double x2, double y2) {

        //first by smaller x
        if (x1 < x2) {
            this.start = new Point(x1, y1);
            this.end = new Point(x2, y2);
            return;
        }
        if (x1 > x2) {
            this.start = new Point(x2, y2);
            this.end = new Point(x1, y1);
            return;
        }

        // if both has the same x than by y
        if (y1 < y2) {
            this.start = new Point(x1, y1);
            this.end = new Point(x2, y2);
        } else {
            this.start = new Point(x2, y2);
            this.end = new Point(x1, y1);
        }
    }

    // ############################# Getters ######################################

    /**
     * Return the length of this the line.
     *
     * @return length of this the line as double.
     */
    public double length() {

        return this.start.distance(this.end);
    }

    /**
     * calculate and return the slope of the line.
     * the m in the equation y=mx+b.
     *
     * @return the slope of the line as Double, null if not exist.
     */
    public Double slope() {

        //line is vertical
        if (start.getY() == end.getY()) {
            return 0.0;
        }

        //line is horizontal
        if (start.getX() == end.getX()) {
            return null;
        }

        //calculating the slope
        return (end.getY() - start.getY()) / (end.getX() - start.getX());
    }

    /**
     * calculate and return the free element.
     * the b in the equation y=mx+b.
     *
     * @return free element as double.
     */
    public Double freeElement() {

        return this.start.getY() - this.slope() * this.start.getX();
    }

    /**
     * calculate and return the middle of the line.
     *
     * @return middle of the line as geometry.Point.
     */
    public Point middle() {

        // calculating the point coordination
        double x = (start.getX() + end.getX()) / 2;
        double y = (start.getY() + end.getY()) / 2;

        // creating the returning the point
        return new Point(x, y);
    }

    /**
     * return the start point of the line.
     *
     * @return start geometry.Point.
     */
    public Point start() {

        return this.start;
    }

    /**
     * return the end point of the line.
     *
     * @return end geometry.Point.
     */
    public Point end() {

        return this.end;
    }

    /**
     * returns the size of the line.
     *
     * @return size of the line.
     */
    public double size() {
        return start.distance(end);
    }

    /**
     * return true is the lines are equal, false otherwise.
     *
     * @param o the other line to check with.
     * @return true if the lines are the same, false if not.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Line other = (Line) o;
        return start.equals(other.start) && this.end.equals(other.end);
    }

    /**
     * return an integer that represent the object.
     *
     * @return integer.
     */
    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }

    // ############################# Intersection ######################################

    /**
     * Returns true if the lines intersect, false otherwise.
     *
     * @param other the other line to check with.
     * @return boolean true if are intersecting false otherwise.
     */
    public boolean isIntersecting(Line other) {

        if (other == null) {
            return false;
        }
        return intersectionWith(other) != null;
    }

    /**
     * Returns the intersection point if the lines intersect, null otherwise.
     *
     * @param other the other line to check with.
     * @return intersection geometry.Point if the lines intersect, null otherwise.
     */
    public Point intersectionWith(Line other) {

        // if one of the lines is actually a point
        if (this.start.equals(this.end)) {
            return other.linePointIntersection(this.start) ? this.start : null;
        }
        if (other.start.equals(other.end)) {
            return this.linePointIntersection(other.start) ? this.start : null;
        }

        // if lines are the same
        if (this.equals(other)) {
            return null;
        }

        // if lines are touching at the ends
        if (this.start.equals(other.end)) {
            return this.start;
        }
        if (this.end.equals(other.start)) {
            return this.end;
        }

        // if both lines are vertical
        if (this.slope() == null && other.slope() == null) {
            return null;
        }

        // if one line is vertical
        if (this.slope() == null) {
            double y = other.slope() * this.start.getX() + other.freeElement();
            if (this.pointInYRange(y) && other.pointInXRange(this.start.getX())) {
                return new Point(this.start.getX(), y);
            }
            return null;
        }
        if (other.slope() == null) {
            double y = this.slope() * other.start.getX() + this.freeElement();
            if (this.pointInXRange(other.start.getX()) && other.pointInYRange(y)) {
                return new Point(other.start.getX(), y);
            }
            return null;
        }

        // if both lines are horizontal
        if (this.slope() == 0 && other.slope() == 0) {
            return null;
        }

        // calculating the intersection
        double px = (this.freeElement() - other.freeElement()) / (other.slope() - this.slope());
        double py = this.slope() * px + this.freeElement();

        // checking if point in the segments
        if (this.pointInXRange(px) && other.pointInXRange(px) && this.pointInYRange(py) && other.pointInYRange(py)) {
            return new Point(px, py);
        }
        return null;
    }

    /**
     * return the intersection point when one line is actually a point.
     *
     * @param p a point that represent the line that is actually a point
     * @return intersection geometry.Point if the lines intersect, null otherwise.
     */
    public boolean linePointIntersection(Point p) {

        // if both are points
        if (this.start.equals(this.end)) {

            // if are the same point
            return this.start.equals(p);
        }

        // checking point is in range
        if (!(this.pointInXRange(p.getX()) && this.pointInYRange(p.getY()))) {
            return false;
        }

        // if the line is vertical or horizontal than the point is on the line
        if (this.slope() == null || this.slope() == 0) {
            return true;
        }

        // checking if the point is on the line
        return p.getY() == this.slope() * p.getX() + this.freeElement();
    }

    /**
     * return true if x is in the line segment x range, false otherwise.
     *
     * @param x value to check if in range.
     * @return true if x is in the line segment x range, false otherwise.
     */
    private boolean pointInXRange(double x) {

        if (this.start.getX() < this.end.getX()) {
            return Tools.compare(this.start.getX(), x) <= 0 && Tools.compare(x, this.end.getX()) <= 0;
        }
        return Tools.compare(this.start.getX(), x) >= 0 && Tools.compare(x, this.end.getX()) >= 0;
    }

    /**
     * return true if y is in the line segment y range, false otherwise.
     *
     * @param y value to check if in range.
     * @return true if y is in the line segment y range, false otherwise.
     */
    private boolean pointInYRange(double y) {

        if (this.start.getY() < this.end.getY()) {
            return Tools.compare(this.start.getY(), y) <= 0 && Tools.compare(y, this.end.getY()) <= 0;
        }
        return Tools.compare(this.start.getY(), y) >= 0 && Tools.compare(y, this.end.getY()) >= 0;
    }

    // ###################################################################################

    /**
     * returns the closest intersection point between the line and a given rectangle.
     * returns null if none was found.
     *
     * @param rect the rectangle to check with.
     * @return the closest intersection point, null if none found.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> intersections = rect.intersectionPoints(this);
        if (intersections.size() == 0) {
            return null;
        }

        // value to store the closest
        Point closest = null;

        // going through all intersections
        for (Point p : intersections) {
            // checking if new point is closer
            if (closest == null || p.distance(this.start) < closest.distance(this.start)) {
                closest = p;
            }
        }
        return closest;
    }

    /**
     * return the y value of a given x value on the line.
     *
     * @param x the x value
     * @return geometry.Point with a corresponding y value.
     */
    private Point pointOnLine(double x) {

        double y = this.slope() * x + this.freeElement();
        return new Point(x, y);
    }

    /**
     * changes the geometry.Line magnitude while keeping its angle.
     *
     * @param size the new size of the velocity.
     * @return a new geometry.Line instance with the same angle.
     */
    public Line rescaleLine(double size) {
        double ratio = size / this.size();
        double x = ratio * (end.getX() - start().getX()) + start().getX();
        double y = ratio * (end.getY() - start().getY()) + start().getY();
        return new Line(start, new Point(x, y));
    }


}