package elements;

import tools.Tools;
import biuoop.DrawSurface;
import collision.CollisionInfo;
import collision.GameEnvironment;
import game.GameLevel;
import geometry.Line;
import geometry.Point;
import sprites.Sprite;

import java.awt.Color;

/**
 * @author Noam Cohen cohennoam48@gmail.com
 * id: 211497037
 */

public class Ball implements Sprite {
    // ball parameters
    private Point center;
    private double radius;
    private Color color = Color.GREEN;
    private Velocity velocity;

    // Game related
    private GameEnvironment environment;

    // ############################# Constructors ######################################

    /**
     * creates a elements.Ball instance.
     *
     * @param center of the ball as geometry.Point.
     * @param r      the radius of the ball.
     */
    public Ball(Point center, int r) {

        this.center = center;
        this.radius = r;
    }

    /**
     * creates a elements.Ball instance.
     *
     * @param center the middle of the circle as geometry.Point.
     * @param r      the radius of the circle as int.
     * @param color  the color of the circle as Color.
     */
    public Ball(Point center, int r, Color color) {

        this.center = center;
        this.radius = r;
        this.color = color;
    }

    /**
     * creates a elements.Ball instance.
     *
     * @param x     the x value of the middle of the circle as int.
     * @param y     the y value of the middle of the circle as int.
     * @param r     the radius of the circle as int.
     * @param color the color of the circle as Color.
     */
    public Ball(double x, double y, int r, Color color) {

        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
    }

    // ############################# Getters ######################################

    /**
     * returns the X value of the circle.
     *
     * @return X value of the circle as int.
     */
    public double getX() {

        return this.center.getX();
    }

    /**
     * returns the Y value of the circle.
     *
     * @return Y value of the circle as int.
     */
    public double getY() {

        return this.center.getY();
    }

    /**
     * returns the center of the circle.
     *
     * @return center of circle as geometry.Point.
     */
    public Point getCenter() {

        return this.center;
    }

    /**
     * assign a Center to the ball.
     *
     * @param newCenter the Center to assign.
     */
    public void setCenter(Point newCenter) {

        center = newCenter;
    }

    /**
     * returns the radius of the circle.
     *
     * @return radius of the circle as int.
     */
    public double getSize() {

        return this.radius;
    }

    /**
     * returns the color of the circle.
     *
     * @return color of the circle as Color.
     */
    public Color getColor() {

        return this.color;
    }

    /**
     * returns the velocity of the circle.
     *
     * @return velocity of the circle as elements.Velocity.
     */
    public Velocity getVelocity() {

        return this.velocity;
    }

    /**
     * assign a given elements.Velocity to the ball.
     *
     * @param v elements.Velocity instance to assign.
     */
    public void setVelocity(Velocity v) {

        this.velocity = v;
    }

    // ############################# Setters ######################################

    /**
     * return the environment the ball belongs to.
     *
     * @return environment
     */
    public GameEnvironment getEnvironment() {

        return environment;
    }

    /**
     * assign a environment to the ball.
     *
     * @param newEnvironment the environment to assign.
     */
    public void setEnvironment(GameEnvironment newEnvironment) {

        this.environment = newEnvironment;
    }

    /**
     * assign a elements.Velocity to the ball.
     *
     * @param dx the x value of the elements.Velocity.
     * @param dy the y value of the elements.Velocity.
     */
    public void setVelocity(double dx, double dy) {

        this.velocity = new Velocity(dx, dy);
    }

    // ############################# game.Game And Drawing ######################################

    /**
     * draw the ball on a given Surface.
     *
     * @param surface surface to draw onto.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        // assigning parameters
        int outlineWidth = 1;
        int x = (int) (int) this.getX();
        int y = (int) (int) this.getY();
        int r = (int) this.radius;

        // drawing outline
        surface.setColor(Color.BLACK);
        surface.fillCircle(x, y, r);

        // drawing the shape
        surface.setColor(color);
        surface.fillCircle(x, y, r - outlineWidth);
    }

    /**
     * moving the ball.
     */
    @Override
    public void timePassed() {

        moveOneStep();
    }

    /**
     * adds the ball to a given game.
     *
     * @param gameLevel the game to add to.
     */
    public void addToGame(GameLevel gameLevel) {

        gameLevel.addSprite(this);
    }

    // ############################# Moving Animation ######################################

    /**
     * moving the ball according to its velocity.
     * if hits the edges changes its velocity.
     */
    public void moveOneStep() {
        // checking velocity exists
        if (this.velocity == null) {
            return;
        }

        // collision with the blocks
        Line trajectory = new Line(this.center, getVelocity().applyToPoint(this.center));
        Velocity nextFrameVelocity = gameCollision(trajectory);

        // moving the ball
        this.center = this.getVelocity().applyToPoint(this.center);

        // setting next frame velocity
        this.setVelocity(nextFrameVelocity);
    }

    /**
     * checking if the ball collided with the environment,
     * and change the velocity accordingly.
     *
     * @param trajectory the velocity trajectory.
     * @return next frame velocity.
     */
    private Velocity gameCollision(Line trajectory) {

        // getting the collision
        CollisionInfo collision = environment.getClosestCollision(trajectory);

        // if there is a collision
        if (collision == null) {
            return this.velocity;
        }

        // after collision velocity
        Velocity nextFrameVelocity = collision.collisionObject().hit(this, collision.collisionPoint(), velocity);

        // calculating distance from collision point
        double distance = center.distance(collision.collisionPoint());

        // if collision inside ball than change the velocity now
        if (Tools.compare(distance, radius) <= 0) {
            // calculating new velocity and check has no collision.
            this.setVelocity(nextFrameVelocity);
            trajectory = new Line(this.center, getVelocity().applyToPoint(this.center));
            nextFrameVelocity = gameCollision(trajectory);
            return nextFrameVelocity;
        }
        this.setVelocity(this.velocity.rescaleVelocity(distance - radius));
        return nextFrameVelocity;
    }

    // ############################# Removing From game.Game ######################################

    /**
     * removing the ball from a given game.
     *
     * @param gameLevel the game to remove from
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
        gameLevel.removeBall(this);
    }
}
