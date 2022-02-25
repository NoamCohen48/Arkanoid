package listeners;

import elements.Ball;
import elements.Block;
import game.Counter;
import game.GameLevel;

/**
 * @author Noam Cohen cohennoam48@gmail.com
 * id: 211497037
 */

public class BallRemover implements HitListener {
    private final GameLevel gameLevel;
    private final Counter remainingBalls;

    // ############################# Constructors ##############################

    /**
     * constructor.
     * creates a ball remover listener.
     *
     * @param gameLevel      the game the listener connected to.
     * @param remainingBalls counter to the amount of blocks remaining
     */
    public BallRemover(GameLevel gameLevel, Counter remainingBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = remainingBalls;
    }

    // ############################# Hit ######################################

    /**
     * removing the ball on hit.
     *
     * @param beingHit the block which the hit happened.
     * @param hitter   the ball to hit.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(gameLevel);
        remainingBalls.decrease(1);
    }
}

