package animation;

import biuoop.DrawSurface;
import game.Counter;

import java.awt.Color;

/**
 * @author Noam Cohen cohennoam48@gmail.com
 * id: 211497037
 */

public class EndScreen implements Animation {
    private boolean lost;
    private Counter score;

    /**
     * creates an EndScreen animation instance.
     *
     * @param lost  if the player lost.
     * @param score the score of the player.
     */
    public EndScreen(boolean lost, Counter score) {
        this.lost = lost;
        this.score = score;
    }

    /**
     * performing one frame of the animation.
     *
     * @param d the frame to draw onto.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        // drawing the background
        d.setColor(new Color(107, 189, 127));
        d.fillRectangle(0, 0, 800, 600);

        //drawing the text
        String message = lost ? "Game Over. " : "You Win! ";
        d.setColor(new Color(53, 106, 73));
        d.drawText(d.getWidth() / 2 - 250, d.getHeight() / 2, message + "Your score is " + score.getValue(), 32);
    }

    /**
     * if the animation need to be stopped.
     *
     * @return true if need to stop, false if not.
     */
    @Override
    public boolean shouldStop() {
        return false;
    }
}
