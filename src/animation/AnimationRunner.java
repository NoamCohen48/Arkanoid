package animation;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * @author Noam Cohen cohennoam48@gmail.com
 * id: 211497037
 */

public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;

    /**
     * creates a AnimationRunner instance.
     *
     * @param gui             the gui of the game.
     * @param framesPerSecond the frames to run.
     */
    public AnimationRunner(GUI gui, int framesPerSecond) {
        this.gui = gui;
        this.framesPerSecond = framesPerSecond;
    }

    /**
     * running a given animation.
     *
     * @param animation the animation to run.
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / framesPerSecond;
        Sleeper sleeper = new Sleeper();

        while (!animation.shouldStop()) {
            // get current time
            long startTime = System.currentTimeMillis();

            // drawing the animation;
            DrawSurface d = gui.getDrawSurface();

            // drawing the elements
            animation.doOneFrame(d);
            gui.show(d);

            // freeze animation
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}

