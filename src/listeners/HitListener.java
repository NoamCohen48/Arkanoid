package listeners;

import elements.Ball;
import elements.Block;

/**
 * @author Noam Cohen cohennoam48@gmail.com
 * id: 211497037
 */

public interface HitListener {

    /**
     * removing the ball on hit.
     *
     * @param beingHit the block which the hit happened.
     * @param hitter   the ball to hit.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
