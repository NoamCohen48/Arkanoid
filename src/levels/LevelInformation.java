package levels;

import biuoop.DrawSurface;
import elements.Block;
import elements.Velocity;
import sprites.Sprite;

import java.awt.Color;
import java.util.List;

/**
 * @author Noam Cohen cohennoam48@gmail.com
 * id: 211497037
 */

public interface LevelInformation {
    /**
     * return the number of balls in the level.
     *
     * @return number of balls.
     */
    int numberOfBalls();

    /**
     * return the balls radius.
     *
     * @return balls radius.
     */
    int ballsRadius();

    /**
     * creates a list of the initial velocity of each ball.
     *
     * @return list of the initial velocity.
     */
    List<Velocity> initialBallVelocities();

    /**
     * return the paddle width.
     *
     * @return paddle width.
     */
    int paddleWidth();

    /**
     * return the paddle color.
     *
     * @return paddle color.
     */
    Color paddleColor();

    /**
     * return the paddle speed.
     *
     * @return paddle speed.
     */
    int paddleSpeed();

    /**
     * return the level name.
     *
     * @return level name.
     */
    String levelName();

    /**
     * return a Sprite of the background.
     *
     * @return Sprite of the background.
     */
    Sprite getBackground();

    /**
     * draw background to a given frame.
     *
     * @param d the frame to draw onto.
     */
    void createBackground(DrawSurface d);

    /**
     * creates a list of the blocks.
     *
     * @return list of the blocks.
     */
    List<Block> blocks();

    /**
     * return the number of blocks to remove.
     *
     * @return number of blocks to remove.
     */
    int numberOfBlocksToRemove();
}