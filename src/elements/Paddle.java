package elements;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import collision.Collidable;
import game.GameLevel;
import geometry.Point;
import geometry.Rectangle;
import sprites.Sprite;
import tools.Tools;

import java.awt.Color;
import java.util.List;

/**
 * @author Noam Cohen cohennoam48@gmail.com
 * id: 211497037
 */

public class Paddle implements Sprite, Collidable {
    private final Block block;
    private final double speed;
    private biuoop.KeyboardSensor keyboard;
    private List<Ball> balls;
    private int screenWidth;

    // ############################# Constructors ######################################

    /**
     * creates a paddle instance from given block and color.
     *
     * @param block the block of the paddle.
     * @param speed the speed of the paddle.
     */
    public Paddle(Block block, double speed) {
        this.block = block;
        this.speed = speed;
    }

    /**
     * creates a paddle instance from all block elements and speed.
     *
     * @param rec   the rectangle of the block.
     * @param color the color of the block.
     * @param speed the speed of the paddle.
     */
    public Paddle(Rectangle rec, Color color, double speed) {
        block = new Block(rec, color);
        this.speed = speed;
    }

    // ############################# Setters ###########################################

    /**
     * sets the screen width.
     *
     * @param width screen width to set.
     */
    public void setScreenWidth(int width) {
        this.screenWidth = width;
    }

    /**
     * sets the keyboard.
     *
     * @param newKeyboard the keyboard to set.
     */
    public void setKeyboard(KeyboardSensor newKeyboard) {

        this.keyboard = newKeyboard;
    }

    /**
     * sets the balls.
     *
     * @param newBalls the balls to set.
     */
    public void setBalls(List<Ball> newBalls) {

        this.balls = newBalls;
    }

    // ############################# Moving the paddle ###################################

    /**
     * move the paddle to the left.
     */
    public void moveLeft() {
        // checking if there is a ball to the side
        Double ballToSide = isThereBallToSide(-1);
        // calculating the new paddle location
        double oldPaddleX = block.getCollisionRectangle().getUpperLeft().getX();
        double newPaddleX = newPaddleX(oldPaddleX, 0, -1, ballToSide);
        double y = block.getCollisionRectangle().getUpperLeft().getY();
        Point newUpperLeft = new Point(newPaddleX, y);

        // pushing balls to the left
        pushBalls(newPaddleX, -1, ballToSide);

        // changing paddle location
        block.getCollisionRectangle().setUpperLeft(newUpperLeft);
    }

    /**
     * move the paddle to the right.
     */
    public void moveRight() {
        // checking if there is a ball to the side
        Double ballToSide = isThereBallToSide(1);
        // calculating the new paddle location
        double oldPaddleX = block.getCollisionRectangle().getLowerRight().getX();
        double x = newPaddleX(oldPaddleX, screenWidth, 1, ballToSide) - block.getCollisionRectangle().getWidth();
        double y = block.getCollisionRectangle().getUpperLeft().getY();
        Point newUpperLeft = new Point(x, y);

        // pushing all balls
        pushBalls(x + block.getCollisionRectangle().getWidth(), 1, ballToSide);

        // changing paddle location
        block.getCollisionRectangle().setUpperLeft(newUpperLeft);
    }

    /**
     * calculate the new x value of the edge of the paddle.
     *
     * @param paddleX    the edge of the paddle.
     * @param edgeX      the edge of the screen.
     * @param direction  1 for right -1 for left.
     * @param ballToSide the radius of the largest ball in the side, null if there is none.
     * @return new x value of the paddle.
     */
    private double newPaddleX(double paddleX, double edgeX, int direction, Double ballToSide) {
        // calculating the paddle new x.
        double x = paddleX + direction * speed;

        // if paddle wants to go to the edge
        if (direction * x >= direction * edgeX) {
            x = edgeX;
            // if there is a ball to the side
            if (ballToSide != null) {
                x = edgeX - direction * ballToSide * 2;
            }
        }
        return x;
    }

    /**
     * pushes all balls on the side of the paddle to the new paddle position.
     *
     * @param newX       new x value to push the balls into.
     * @param direction  1 for right -1 for left.
     * @param ballToSide the radius of the largest ball in the side, null if there is none.
     */
    private void pushBalls(double newX, int direction, Double ballToSide) {
        // if there is no bal to the side
        if (ballToSide == null) {
            return;
        }

        newX += direction * ballToSide;
        double y;

        for (Ball ball : balls) {
            // check if ball to the right side
            if (ballToTheSide(ball) != direction) {
                continue;
            }

            // calculating new center
            y = ball.getY();
            ball.setCenter(new Point(newX, y));

            // changing velocity to all balls that go into the paddle
            Velocity ballVelocity = ball.getVelocity();
            if (Tools.compare(direction * ballVelocity.getDx(), 0) <= 0) {
                ball.setVelocity(new Velocity(-ballVelocity.getDx(), ballVelocity.getDy()));
            }
        }
    }

    /**
     * checks if there is at least one ball to the side of the paddle.
     *
     * @param direction 1 for right -1 for left.
     * @return largest radius of a ball to the side, null if there is none to the side
     */
    private Double isThereBallToSide(int direction) {
        Double maxRadius = null;
        for (Ball ball : balls) {
            // if ball to the side
            if (ballToTheSide(ball) == direction) {
                // if radius larger then maximum
                if (maxRadius == null || ball.getSize() > maxRadius) {
                    maxRadius = ball.getSize();
                }
            }
        }
        return maxRadius;
    }

    /**
     * checks of the ball is speed distance from the sides od the paddle.
     * return 1 if to the right, -1 if o the left and 0 if none.
     *
     * @param ball the ball to check.
     * @return 1 if to the right, -1 if o the left and 0 if none.
     */
    private int ballToTheSide(Ball ball) {
        // check if in Y is not in range
        if (!(Tools.compare(block.getCollisionRectangle().getUpperLeft().getY(), ball.getY()) <= 0
                && Tools.compare(ball.getY(), block.getCollisionRectangle().getLowerRight().getY()) <= 0)) {

            return 0;
        }

        // check to the left
        double left = block.getCollisionRectangle().getUpperLeft().getX();
        // if ball is on the left
        if (Tools.compare(ball.getX(), left) <= 0) {
            // if ball will collide after moving
            if (Tools.compare(left - ball.getX() - ball.getSize(), speed) <= 0) {
                return -1;
            }
        }

        // check if to the right
        double right = block.getCollisionRectangle().getLowerRight().getX();
        // if ball is on the right
        if (Tools.compare(right, ball.getX()) <= 0) {
            // if ball will collide after moving
            if (Tools.compare(ball.getX() - ball.getSize() - right, speed) <= 0) {
                return 1;
            }
        }

        // not to the sides
        return 0;
    }

    // ############################# Animation ######################################

    /**
     * moving the paddle if needed.
     */
    @Override
    public void timePassed() {
        // left key
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }

        // right key
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * draw the paddle on a given surface.
     *
     * @param surface the surface to draw onto.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        block.drawOn(surface);
    }

    // ############################# Hit ###########################################

    /**
     * returns the rectangle of the paddle.
     *
     * @return rectangle of the paddle.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return block.getCollisionRectangle();
    }

    /**
     * returns the new velocity of the object that was collided with the block.
     *
     * @param hitter          the ball to hit.
     * @param collisionPoint  the collision point of the object and the block.
     * @param currentVelocity the current velocity of the object and the block.
     * @return the new velocity of the object.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Rectangle rec = block.getCollisionRectangle();

        double minX = rec.getUpperLeft().getX();
        double maxX = rec.getLowerRight().getX();
        double minY = rec.getUpperLeft().getY();
        double maxY = rec.getLowerRight().getY();
        double collisionX = collisionPoint.getX();
        double collisionY = collisionPoint.getY();
        int sections = 5;
        double deltaAngle = 30;

        // top line
        if (Tools.compare(collisionY, minY) == 0) {

            // will divide to 3 parts
            int part = partOfX(collisionX, minX, maxX, sections);
            if (part != 0) {
                double angle = part * deltaAngle;
                return Velocity.fromAngleAndSpeed(angle, currentVelocity.getSize());
            }
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        }

        // bottom line
        if (Tools.compare(collisionY, maxY) == 0) {
            if (Tools.compare(minX, collisionX) <= 0 && Tools.compare(collisionX, maxX) <= 0) {
                // changing velocity y value
                return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
            }
        }


        // if in vertical lines
        if (Tools.compare(minY, collisionY) <= 0 && Tools.compare(collisionY, maxY) <= 0) {

            if (Tools.compare(collisionX, minX) == 0 || Tools.compare(collisionX, maxX) == 0) {
                // changing velocity angle
                return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
            }
        }
        // if did not hit
        return currentVelocity;
    }

    /**
     * checks in which section of the top part the ball hit.
     * 3 - did not hit, 0 1 and 2 for the part from left to right.
     *
     * @param x        the collision x value.
     * @param minX     left most side.
     * @param maxX     right most side.
     * @param sections amount of sections
     * @return the part the x is in.
     */
    private int partOfX(double x, double minX, double maxX, int sections) {
        // check if the ball in range
        if (!(Tools.compare(minX, x) <= 0 && Tools.compare(x, maxX) <= 0)) {
            return 0;
        }
        double length = (maxX - minX) / sections;
        return (int) ((x - minX) / length) - (sections / 2);
    }

    // ############################# game.Game ###########################################

    /**
     * adds the paddle to a given game.
     *
     * @param gameLevel the game to add to.
     */
    public void addToGame(GameLevel gameLevel) {

        gameLevel.addCollidable(this);
        gameLevel.addSprite(this);
    }

}
