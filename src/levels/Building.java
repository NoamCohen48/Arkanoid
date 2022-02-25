package levels;

import biuoop.DrawSurface;
import elements.Block;
import elements.Velocity;
import geometry.Point;
import sprites.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Noam Cohen cohennoam48@gmail.com
 * id: 211497037
 */

public class Building implements LevelInformation {
    private List<Block> blocks;

    private int numberOfBalls = 2;
    private double ballSpeed = 7;
    private int ballRadius = 7;

    private int paddleSpeed = 10;
    private int paddleWidth = 200;
    private Color paddleColor = Color.WHITE;

    private int blockColumns = 15;
    private int blockRwos = 4;
    private double blockHeight = 25;
    private int rowY = 150;

    private String levelName = "Sun Shine";

    // ############################# Constructors ######################################

    /**
     * creates a GameFlow instance.
     *
     * @param screenWidth  the width of the screen.
     * @param screenHeight the height of the screen.
     */
    public Building(int screenWidth, int screenHeight) {
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

        List<Color> colors = new ArrayList<>();
        colors.add(Color.red);
        colors.add(Color.orange);
        colors.add(Color.yellow);
        colors.add(Color.green);
        colors.add(Color.blue);
        colors.add(Color.magenta);
        colors.add(Color.pink);

        // parameters about block creation
        int width = screenWidth / blockColumns;

        // creating rows columns of blocks
        for (int row = 0; row < blockRwos; ++row) {
            for (int col = row + blockColumns / 3; col < blockColumns; ++col) {

                // finding the upper left corner
                Point point = new Point(col * width, rowY + row * blockHeight);

                // creating the block
                Block block = new Block(point, width, blockHeight, colors.get(row));

                // adding to list
                blocks.add(block);
            }
        }
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();

        int startAngle = -45;
        int endAngle = 45;
        double deltaAngle = (double) (endAngle - startAngle) / (numberOfBalls - 1);

        for (int angle = startAngle; angle <= endAngle; angle += deltaAngle) {
            velocities.add(Velocity.fromAngleAndSpeed(angle, ballSpeed));
        }
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
        return blockColumns * blockRwos;
    }

    // ############################# BackGround ######################################
    @Override
    public Sprite getBackground() {
        return new Background(this);
    }

    @Override
    public void createBackground(DrawSurface d) {
        // background
        d.setColor(new Color(192, 250, 112, 255));
        d.fillRectangle(0, 0, 800, 600);

        // building
        int top = 400;
        int left = 50;
        int width = 100;
        int height = 800;
        int columns = 5;
        int space = 5;

        //building body
        d.setColor(new Color(45, 42, 42, 255));
        d.fillRectangle(left, top, width, height);

        // windows
        int windowHeight = 17;
        int windowWidth = (width - (columns + 1) * space) / columns;

        d.setColor(new Color(236, 226, 226, 255));
        for (int x = left + space; x < left + width; x += windowWidth + space) {
            for (int y = top + space; y <= 600; y += windowHeight + space) {
                d.fillRectangle(x, y, windowWidth, windowHeight);
            }
        }

        // antenna
        int x = left + width / 2;

        width = 30;
        height = 50;
        int y = top - height;
        d.setColor(new Color(57, 52, 52, 255));
        d.fillRectangle(x - width / 2, y, width, height);

        width = 10;
        height = 150;
        y = y - height;
        d.setColor(new Color(78, 71, 71, 255));
        d.fillRectangle(x - width / 2, y, width, height);

        // light
        d.setColor(new Color(246, 112, 112));
        d.fillCircle(x, y, 20);

        d.setColor(new Color(255, 25, 25, 255));
        d.fillCircle(x, y, 12);

        d.setColor(new Color(255, 255, 255, 255));
        d.fillCircle(x, y, 5);
    }
}
