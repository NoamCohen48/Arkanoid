package sprites;

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Noam Cohen cohennoam48@gmail.com
 * id: 211497037
 */

public class SpriteCollection {

    private final List<Sprite> sprites;

    // ###############################################################

    /**
     * creates an empty game environment.
     */
    public SpriteCollection() {

        sprites = new ArrayList<>();
    }

    // ###################################################################################

    /**
     * returns a specific object in collection.
     *
     * @param index the index of the object.
     * @return the object.
     */
    public Sprite getIndex(int index) {

        return sprites.get(index);
    }

    // ###################################################################################

    /**
     * adds an object to the collection.
     *
     * @param s the object to add.
     */
    public void addSprite(Sprite s) {

        sprites.add(s);
    }

    /**
     * remove a given object from the collection.
     *
     * @param s the object to remove.
     */
    public void removeSprite(Sprite s) {
        sprites.remove(s);
    }

    // ###################################################################################

    /**
     * notify the sprites that time has passed.
     */
    public void notifyAllTimePassed() {

        List<Sprite> temp = new ArrayList<>(sprites);

        for (Sprite sprite : temp) {
            sprite.timePassed();
        }
    }

    /**
     * draws all objects on the surface.
     *
     * @param surface the surface to draw onto.
     */
    public void drawAllOn(DrawSurface surface) {

        List<Sprite> temp = new ArrayList<>(sprites);

        for (Sprite sprite : temp) {
            sprite.drawOn(surface);
        }
    }
}