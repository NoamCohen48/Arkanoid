package game;

import biuoop.DrawSurface;
import sprites.Sprite;

import java.awt.Color;

/**
 * @author Noam Cohen cohennoam48@gmail.com
 * id: 211497037
 */

public class ScoreIndicator implements Sprite {
    private final int height;
    private final Counter counter;
    private final String levelName;

    // ############################# Constructors ######################################

    /**
     * creates a score indicator instance.
     *
     * @param height    the height of the score indicator.
     * @param score     the score.
     * @param levelName the name of the current level.
     */
    public ScoreIndicator(int height, Counter score, String levelName) {
        this.height = height;
        this.counter = score;
        this.levelName = levelName;
    }

    // ############################# Animation ######################################

    /**
     * draw the object on the surface.
     *
     * @param d the surface to draw onto.
     */
    @Override
    public void drawOn(DrawSurface d) {
        final int y = 7;

        // background
        //d.setColor(new Color(132, 131, 131));
        //d.fillRectangle(0, 0, SCREEN_WIDTH, height);

        // Lives
        //int x = 30;
        //String text = "Lives: ";
        //d.setColor(new Color(31, 30, 30));
        //d.drawText(x, height - y, text, height - 2 * y);

        // Score
        int x = d.getWidth() / 2;
        int deltaX = -40;

        String text = "Score: " + counter.getValue();
        d.setColor(new Color(31, 30, 30));
        d.drawText(x + deltaX, height - y, text, height - 2 * y);


        // Level Name
        x = 2 * d.getWidth() / 3;
        deltaX = 10;

        text = "Level Name: " + levelName;
        d.setColor(new Color(31, 30, 30));
        d.drawText(x + deltaX, height - y, text, height - 2 * y);
    }

    /**
     * notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {

    }

    /**
     * adds the block to a given game.
     *
     * @param gameLevel the game to add to.
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }
}
