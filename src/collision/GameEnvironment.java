package collision;

import geometry.Line;
import geometry.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Noam Cohen cohennoam48@gmail.com
 * id: 211497037
 */

public class GameEnvironment {

    private final List<Collidable> blocks;

    // ############################# Constructors ######################################

    /**
     * creates an empty game environment.
     */
    public GameEnvironment() {

        blocks = new ArrayList<>();
    }

    // ############################# Adding And Removing ######################################

    /**
     * adding an element to the environment.
     *
     * @param c the element to add
     */
    public void addCollidable(Collidable c) {
        blocks.add(c);
    }

    /**
     * remove a given object from the collection.
     *
     * @param c the object to remove.
     */
    public void removeCollidable(Collidable c) {
        blocks.remove(c);
    }

    // ############################# Collision ######################################

    /**
     * returns the information about the nearest collision.
     *
     * @param trajectory the trajectory of the ball
     * @return collision.CollisionInfo - collision point and object
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        // if there are no blocks
        if (blocks.size() == 0) {
            return null;
        }

        // assigning default values
        CollisionInfo closest = null;

        // iterating through all blocks to check collision
        for (Collidable block : blocks) {

            // get collision point
            Point collisionPoint = trajectory.closestIntersectionToStartOfLine(block.getCollisionRectangle());
            if (collisionPoint == null) {
                continue;
            }

            // if is closer collision
            if (closest == null || collisionPoint.distance(trajectory.start())
                    < closest.collisionPoint().distance(trajectory.start())) {

                closest = new CollisionInfo(collisionPoint, block);
            }
        }
        return closest;
    }
}
