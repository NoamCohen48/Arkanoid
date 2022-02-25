package levels;

import biuoop.DrawSurface;
import elements.Block;
import elements.Velocity;
import geometry.Point;
import geometry.Rectangle;
import sprites.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Noam Cohen cohennoam48@gmail.com
 * id: 211497037
 */

public class DirectHit implements LevelInformation {
    private List<Block> blocks;

    private int numberOfBalls = 1;
    private double ballSpeed = 7;
    private int ballRadius = 7;

    private int paddleSpeed = 10;
    private int paddleWidth = 200;
    private Color paddleColor = Color.WHITE;

    private int numberOfBlocks = 1;
    private double blockWidth = 40;
    private double blockHeight = 40;

    private String levelName = "Direct Hit";

    // ############################# Constructors ######################################

    /**
     * creates a GameFlow instance.
     *
     * @param screenWidth  the width of the screen.
     * @param screenHeight the height of the screen.
     */
    public DirectHit(int screenWidth, int screenHeight) {
        blocks = new ArrayList<>();
        createBlocks(screenWidth, screenHeight);
    }

    // ############################# Constructors ######################################

    /**
     * creates the blocks.
     *
     * @param screenWidth  the width of the screen.
     * @param screenHeight the height of the screen.
     */
    private void createBlocks(int screenWidth, int screenHeight) {
        int x = screenWidth / 2;
        int y = screenHeight / 3;
        Point center = new Point(x, y);
        Rectangle body = Rectangle.rectangleCenter(center, blockWidth, blockHeight);
        Block block = new Block(body, new Color(201, 0, 0));
        blocks.add(block);
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(Velocity.fromAngleAndSpeed(0, ballSpeed));
        return velocities;
    }

    // ############################# Constructors ######################################

    @Override
    public int numberOfBalls() {
        return numberOfBalls;
    }

    @Override
    public int ballsRadius() {
        return ballRadius;
    }

    @Override
    public int paddleSpeed() {
        return paddleSpeed;
    }

    @Override
    public int paddleWidth() {
        return paddleWidth;
    }

    @Override
    public Color paddleColor() {
        return paddleColor;
    }

    @Override
    public String levelName() {
        return levelName;
    }

    @Override
    public List<Block> blocks() {
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return numberOfBlocks;
    }

    // ############################# BackGround ######################################
    @Override
    public Sprite getBackground() {
        return new Background(this);
    }

    @Override
    public void createBackground(DrawSurface d) {
        // background
        d.setColor(new Color(197, 250, 236));
        d.fillRectangle(0, 0, 800, 600);

        int blockX = d.getWidth() / 2;
        int blockY = d.getHeight() / 3;

        // creating the circle
        int r1 = 150;
        int r2 = 115;
        int r3 = 75;
        d.setColor(new Color(255, 47, 47));
        d.drawCircle(blockX, blockY, r1);
        d.drawCircle(blockX, blockY, r2);
        d.drawCircle(blockX, blockY, r3);

        // creating lines
        int extra = 20;
        d.setColor(new Color(255, 47, 47));
        d.drawLine(blockX - r1 - extra, blockY, blockX + r1 + extra, blockY);
        d.drawLine(blockX, blockY - r1 - extra, blockX, blockY + r1 + extra);
    }

}
