package listeners;

import elements.Ball;
import elements.Block;
import game.Counter;

/**
 * @author Noam Cohen cohennoam48@gmail.com
 * id: 211497037
 */

public class ScoreTrackingListener implements HitListener {
    private final int blockHitScore = 5;
    private final Counter currentScore;

    /**
     * constructor.
     * creates a score listener.
     *
     * @param scoreCounter the counter of the score
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * removing the ball on hit.
     *
     * @param beingHit the block which the hit happened.
     * @param hitter   the ball to hit.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(blockHitScore);
    }
}
