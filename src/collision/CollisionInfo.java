package collision;

import geometry.Point;

/**
 * @author Noam Cohen cohennoam48@gmail.com
 * id: 211497037
 */

public class CollisionInfo {
    private final Point collisionPoint;
    private final Collidable collisionObject;

    // ############################# Constructors ######################################

    /**
     * creates a Collision instance.
     *
     * @param collisionPoint  the point at which the collision occurred.
     * @param collisionObject the object which the collision occurred with.
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    // ############################# Getters ###########################################

    /**
     * returns the point at which the collision occurs.
     *
     * @return the point.
     */
    public Point collisionPoint() {
        return collisionPoint;
    }

    /**
     * returns the object involved in the collision.
     *
     * @return the object.
     */
    public Collidable collisionObject() {
        return collisionObject;
    }
}
