package collision;

import elements.Ball;
import elements.Velocity;
import geometry.Point;
import geometry.Rectangle;

/**
 * @author Noam Cohen cohennoam48@gmail.com
 * id: 211497037
 */

public interface Collidable {

    /**
     * returns the rectangle.
     *
     * @return the rectangle of the block.
     */
    Rectangle getCollisionRectangle();

    /**
     * returns the new velocity of the object that was collided with the block.
     *
     * @param hitter          the ball to hit.
     * @param collisionPoint  the collision point of the object and the block.
     * @param currentVelocity the current velocity of the object and the block.
     * @return the new velocity of the object.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
