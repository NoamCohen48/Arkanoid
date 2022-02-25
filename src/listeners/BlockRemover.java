package listeners;

import elements.Ball;
import elements.Block;
import game.Counter;
import game.GameLevel;

/**
 * @author Noam Cohen cohennoam48@gmail.com
 * id: 211497037
 */

public class BlockRemover implements HitListener {
    private final GameLevel gameLevel;
    private final Counter remainingBlocks;

    // ############################# Constructors ######################################

    /**
     * constructor.
     * creates a ball remover listener.
     *
     * @param gameLevel       the game the listener connected to.
     * @param remainingBlocks counter to the amount of blocks remaining
     */
    public BlockRemover(GameLevel gameLevel, Counter remainingBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = remainingBlocks;
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
        beingHit.removeFromGame(gameLevel);
        remainingBlocks.decrease(1);
    }
}
