package animation;

import biuoop.DrawSurface;

/**
 * @author Noam Cohen cohennoam48@gmail.com
 * id: 211497037
 */

public interface Animation {

    /**
     * performing one frame of the animation.
     *
     * @param d the frame to draw onto.
     */
    void doOneFrame(DrawSurface d);

    /**
     * if the animation need to be stopped.
     *
     * @return true if need to stop, false if not.
     */
    boolean shouldStop();
}

