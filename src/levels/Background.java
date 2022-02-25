package levels;

import biuoop.DrawSurface;
import sprites.Sprite;

/**
 * @author Noam Cohen cohennoam48@gmail.com
 * id: 211497037
 */

public class Background implements Sprite {
    private LevelInformation level;

    /**
     * create a background instance.
     *
     * @param level the level to draw.
     */
    public Background(LevelInformation level) {
        this.level = level;
    }

    /**
     * draw the object on the surface.
     *
     * @param d the surface to draw onto.
     */
    @Override
    public void drawOn(DrawSurface d) {
        level.createBackground(d);
    }

    /**
     * notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {

    }
}
