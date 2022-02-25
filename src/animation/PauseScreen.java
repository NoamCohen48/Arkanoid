package animation;

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Noam Cohen cohennoam48@gmail.com
 * id: 211497037
 */

public class PauseScreen implements Animation {
    /**
     * performing one frame of the animation.
     *
     * @param d the frame to draw onto.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        // background
        d.setColor(new Color(143, 94, 161));
        d.fillRectangle(0, 0, 800, 600);

        // text
        d.setColor(new Color(255, 255, 255));
        String message = "paused -- press space to continue";
        d.drawText(d.getWidth() / 2 - 250, d.getHeight() / 2, message, 32);
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
