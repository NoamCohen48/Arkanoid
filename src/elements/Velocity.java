package elements;

import geometry.Line;
import geometry.Point;
import tools.Tools;

import java.util.Objects;

/**
 * @author Noam Cohen cohennoam48@gmail.com
 * id: 211497037
 */

public class Velocity {
    private double dx;
    private double dy;

    // ###################################################################################

    /**
     * creates a velocity instance from dx and dy.
     *
     * @param dx the change in x value.
     * @param dy the change in y value.
     */
    public Velocity(double dx, double dy) {

        this.dx = dx;
        this.dy = dy;
    }

    /**
     * creates a velocity instance from angle and speed.
     *
     * @param angle the angle in degrees.
     * @param speed the speed.
     * @return returns elements.Velocity instance with the speed and angle
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {

        // fixing the angle so that 0 is up
        angle -= 90;

        // converting to dx, dy components
        double dx = Math.cos(Math.toRadians(angle)) * speed;
        double dy = Math.sin(Math.toRadians(angle)) * speed;

        //creating the instance
        return new Velocity(dx, dy);
    }

    /**
     * creates a velocity instance from 2 points.
     *
     * @param start first point.
     * @param end   second point.
     * @return returns elements.Velocity instance.
     */
    public static Velocity from2Points(Point start, Point end) {
        double dx = end.getX() - start.getX();
        double dy = end.getY() - start.getX();
        return new Velocity(dx, dy);
    }

    // ###################################################################################

    /**
     * return the change in x.
     *
     * @return double change in x.
     */
    public double getDx() {

        return this.dx;
    }

    /**
     * assign new x value.
     *
     * @param newDx the new x value.
     */
    public void setDx(double newDx) {
        dx = newDx;
    }

    /**
     * return the change in y.
     *
     * @return double change in y.
     */
    public double getDy() {

        return this.dy;
    }

    /**
     * assign new y value.
     *
     * @param newDy the new y value.
     */
    public void setDy(double newDy) {
        dy = newDy;
    }

    // ###################################################################################

    /**
     * return the angle of the velocity.
     *
     * @return angle as double.
     */
    public double getAngle() {

        return Math.toDegrees(Math.atan(dy / dx));
    }

    /**
     * return the size of the velocity.
     *
     * @return size as double.
     */
    public double getSize() {

        return Math.sqrt(dx * dx + dy * dy);
    }

    // ###################################################################################

    /**
     * checks if 2 velocities are the same.
     *
     * @param o the other velocity to check against.
     * @return true if are the same false if not.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Velocity velocity = (Velocity) o;
        return Tools.compare(velocity.getDx(), getDx()) == 0 && Tools.compare(velocity.getDy(), getDy()) == 0;
    }

    /**
     * return an integer that represent the object.
     *
     * @return integer.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getDx(), getDy());
    }

    /**
     * Take a point and return a new point after moving it.
     *
     * @param p the old point.
     * @return a new point with new position.
     */
    public Point applyToPoint(Point p) {

        return new Point(p.getX() + dx, p.getY() + dy);
    }

    /**
     * turns the velocity to a line instance.
     *
     * @param b the ball the velocity belongs to.
     * @return a line.
     */
    public Line velocityToLine(Ball b) {

        return new Line(b.getCenter(), b.getVelocity().applyToPoint(b.getCenter()));
    }

    /**
     * changes the velocity magnitude while keeping its angle.
     *
     * @param size the new size of the velocity.
     * @return a new velocity instance with the same angle.
     */
    public Velocity rescaleVelocity(double size) {
        double ratio = size / this.getSize();
        return new Velocity(ratio * dx, ratio * dy);
    }
}
