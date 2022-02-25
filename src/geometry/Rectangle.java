package geometry;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Noam Cohen cohennoam48@gmail.com
 * id: 211497037
 */

public class Rectangle {

    private final double width;
    private final double height;
    private Point upperLeft;

    // ############################# Constructors ######################################

    /**
     * creates a rectangle instance from a given upper left point.
     *
     * @param upperLeft the upper left point.
     * @param width     the width of the rectangle.
     * @param height    the height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * return a rectangle instance from a given center point.
     *
     * @param center center point.
     * @param width  the width of the rectangle
     * @param height the height of the rectangle.
     * @return rectangle instance.
     */
    public static Rectangle rectangleCenter(Point center, double width, double height) {
        double x = center.getX() - width / 2;
        double y = center.getY() - height / 2;
        return new Rectangle(new Point(x, y), width, height);
    }

    // ############################# Getters ##########################################

    /**
     * return the width of the rectangle.
     *
     * @return width of the rectangle.
     */
    public double getWidth() {

        return width;
    }

    /**
     * return the height of the rectangle.
     *
     * @return height of the rectangle.
     */
    public double getHeight() {

        return height;
    }

    /**
     * return the upper-left point of the rectangle.
     *
     * @return upper-left point of the rectangle.
     */
    public Point getUpperLeft() {

        return upperLeft;
    }

    /**
     * sets the upper-left point of the rectangle.
     *
     * @param newUpperLeft the new upper-left point of the rectangle.
     */
    public void setUpperLeft(Point newUpperLeft) {

        upperLeft = newUpperLeft;
    }

    // ############################# Setters ##########################################

    /**
     * return the lower-right point of the rectangle.
     *
     * @return lower-right point of the rectangle.
     */
    public Point getLowerRight() {

        return new Point(upperLeft.getX() + width, upperLeft.getY() + height);
    }

    // ############################# Intersection ##########################################

    /**
     * Return a list of intersection points with the specified line.
     * if no intersection found returns an empty list.
     *
     * @param line the line to check intersection with.
     * @return list of intersection.
     */
    public List<Point> intersectionPoints(Line line) {
        List<Point> intersections = new ArrayList<>();
        Line side;
        Point p1, p2, intersection;

        // top line
        p1 = upperLeft;
        p2 = new Point(p1.getX() + width, p1.getY());
        side = new Line(p1, p2);
        intersection = line.intersectionWith(side);
        if (intersection != null) {
            intersections.add(intersection);
        }

        // bottom line
        p1 = getLowerRight();
        p2 = new Point(p1.getX() - width, p1.getY());
        side = new Line(p1, p2);
        intersection = line.intersectionWith(side);
        if (intersection != null) {
            intersections.add(intersection);
        }

        // left side
        p1 = upperLeft;
        p2 = new Point(p1.getX(), p1.getY() + height);
        side = new Line(p1, p2);
        intersection = line.intersectionWith(side);
        if (intersection != null) {
            intersections.add(intersection);
        }

        // right side
        p1 = getLowerRight();
        p2 = new Point(p1.getX(), p1.getY() - height);
        side = new Line(p1, p2);
        intersection = line.intersectionWith(side);
        if (intersection != null) {
            intersections.add(intersection);
        }

        return intersections;
    }
}
