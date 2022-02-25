package animation;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import sprites.SpriteCollection;

import java.awt.Color;

/**
 * @author Noam Cohen cohennoam48@gmail.com
 * id: 211497037
 */

public class CountdownAnimation implements Animation {
    private SpriteCollection gameScreen;

    private double numOfSeconds;
    private int countFrom;
    private Integer count;

    private boolean stop;

    /**
     * creates a countdown animation instance.
     *
     * @param numOfSeconds the number of seconds the countdown last.
     * @param countFrom    from which number to count.
     * @param gameScreen   the background to paint.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;

        stop = false;
        count = countFrom;
    }

    /**
     * performing one frame of the animation.
     *
     * @param d the frame to draw onto.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        // count down to zero
        if (count == 0) {
            this.stop = true;
        }

        // printing to screen
        printCountDown(d);

        // if the first digit
        if (count == countFrom) {
            --count;
            return;
        }

        // freezing the screen
        Sleeper sleeper = new Sleeper();
        long milliSecondToSleep = (long) (numOfSeconds / countFrom * 1000);
        sleeper.sleepFor(milliSecondToSleep);
        --count;
    }

    /**
     * printing to a given frame the countdown.
     *
     * @param d the frame to draw onto.
     */
    private void printCountDown(DrawSurface d) {
        // drawing the level
        gameScreen.drawAllOn(d);

        // parameters
        int x = d.getWidth() / 2 - 110;
        int y = 2 * d.getHeight() / 3;
        String message = "Game starts in " + count;

        // drawing the text
        d.setColor(new Color(28, 26, 26));
        d.drawText(x, y, message, 30);
    }

    /**
     * if the animation need to be stopped.
     *
     * @return true if need to stop, false if not.
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
