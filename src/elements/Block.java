package elements;

import biuoop.DrawSurface;
import collision.Collidable;
import collision.HitNotifier;
import game.GameLevel;
import geometry.Point;
import geometry.Rectangle;
import listeners.HitListener;
import sprites.Sprite;
import tools.Tools;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Noam Cohen cohennoam48@gmail.com
 * id: 211497037
 */

public class Block implements Collidable, Sprite, HitNotifier {
    private final Rectangle rectangle;
    private final Color color;

    private final List<HitListener> hitListeners;

    // ############################# Constructors ######################################

    /**
     * creates a elements.Block instance from rectangle and color.
     *
     * @param rectangle the rectangle of the block
     * @param color     the color f the block
     */
    public Block(Rectangle rectangle, Color color) {
        this.rectangle = rectangle;
        this.color = color;
        hitListeners = new ArrayList<>();
    }

    /**
     * creates a elements.Block instance from all rectangle elements, and color.
     *
     * @param upperLeft the upper left point of the rectangle
     * @param width     the width of the rectangle
     * @param height    the height of the rectangle
     * @param color     the color of the block
     */
    public Block(Point upperLeft, double width, double height, Color color) {
        rectangle = new Rectangle(upperLeft, width, height);
        this.color = color;
        hitListeners = new ArrayList<>();
    }

    // ############################# Getters ######################################

    /**
     * returns the rectangle.
     *
     * @return the rectangle of the block.
     */
    @Override
    public Rectangle getCollisionRectangle() {

        return this.rectangle;
    }

    /**
     * return the color.
     *
     * @return the color of the block.
     */
    public Color getColor() {

        return color;
    }

    // ############################# Hit ######################################

    /**
     * returns the new velocity of the object that was collided with the block.
     *
     * @param hitter          the object to hit.
     * @param collisionPoint  the collision point of the object and the block.
     * @param currentVelocity the current velocity of the object and the block.
     * @return the new velocity of the object.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {

        // horizontal lines
        // if in Y range
        if (Tools.compare(collisionPoint.getY(), rectangle.getUpperLeft().getY()) == 0
                || Tools.compare(collisionPoint.getY(), rectangle.getLowerRight().getY()) == 0) {

            // if in X range
            if (Tools.compare(rectangle.getUpperLeft().getX(), collisionPoint.getX()) <= 0
                    && Tools.compare(collisionPoint.getX(), rectangle.getLowerRight().getX()) <= 0) {

                // changing velocity y value
                currentVelocity.setDy(-currentVelocity.getDy());
            }
        }

        // vertical lines
        // if in X range
        if (Tools.compare(collisionPoint.getX(), rectangle.getUpperLeft().getX()) == 0
                || Tools.compare(collisionPoint.getX(), rectangle.getLowerRight().getX()) == 0) {

            // if in Y range
            if (Tools.compare(rectangle.getUpperLeft().getY(), collisionPoint.getY()) <= 0
                    && Tools.compare(collisionPoint.getY(), rectangle.getLowerRight().getY()) <= 0) {

                // changing velocity x value
                currentVelocity.setDx(-currentVelocity.getDx());
            }
        }

        this.notifyHit(hitter);
        return currentVelocity;
    }

    // ############################# game.Game And Drawing ######################################

    /**
     * draw the block on the surface.
     *
     * @param surface the surface to draw onto.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        // assigning parameters
        int outlineWidth = 1;
        int x = (int) rectangle.getUpperLeft().getX();
        int y = (int) rectangle.getUpperLeft().getY();
        int width = (int) rectangle.getWidth();
        int height = (int) rectangle.getHeight();

        // drawing outline
        surface.setColor(Color.BLACK);
        surface.fillRectangle(x, y, width, height);

        // drawing the shape
        surface.setColor(color);
        surface.fillRectangle(x + outlineWidth, y + outlineWidth,
                width - 2 * outlineWidth, height - 2 * outlineWidth);
    }

    /**
     * STILL IN PROGRESS.
     */
    @Override
    public void timePassed() {

    }

    /**
     * adds the block to a given game.
     *
     * @param gameLevel the game to add to.
     */
    public void addToGame(GameLevel gameLevel) {

        gameLevel.addCollidable(this);
        gameLevel.addSprite(this);
    }

    /**
     * remove the block from a given game.
     *
     * @param gameLevel the game to remove the block from.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }

    // ############################# Hit Notify ######################################

    /**
     * notify all listeners that there was a hit.
     *
     * @param hitter the ball to hit.
     */
    public void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);

        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * adding a listener to the list.
     *
     * @param hl the listener to add.
     */
    @Override
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    /**
     * removing a listener to the list.
     *
     * @param hl the listener to remove.
     */
    @Override
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }
}
