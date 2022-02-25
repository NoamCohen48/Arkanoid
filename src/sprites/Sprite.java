package sprites;

import biuoop.DrawSurface;

/**
 * @author Noam Cohen cohennoam48@gmail.com
 * id: 211497037
 */

public interface Sprite {

    /**
     * draw the object on the surface.
     *
     * @param d the surface to draw onto.
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     */
    void timePassed();
}
