package levels;

import biuoop.DrawSurface;
import elements.Block;
import elements.Velocity;
import sprites.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Noam Cohen cohennoam48@gmail.com
 * id: 211497037
 */

public class Sky implements LevelInformation {
    private List<Block> blocks;

    private int numberOfBalls = 3;
    private double ballSpeed = 7;
    private int ballRadius = 7;

    private int paddleSpeed = 10;
    private int paddleWidth = 200;
    private Color paddleColor = Color.WHITE;

    private int columns = 15;
    private int rows = 4;
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
    public Sky(int screenWidth, int screenHeight) {
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
        double width = (double) screenWidth / columns;

        // creating rows columns of blocks
        for (int row = 0; row < rows; ++row) {
            for (int col = 0; col < columns; ++col) {

                // finding the upper left corner
                geometry.Point point = new geometry.Point(col * width, rowY + row * blockHeight);

                // creating the block
                Block block = new Block(point, width, blockHeight, colors.get(row));

                // adding to game
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
        return columns * rows;
    }

    // ############################# BackGround ######################################

    @Override
    public Sprite getBackground() {
        return new Background(this);
    }

    @Override
    public void createBackground(DrawSurface d) {
        // background
        d.setColor(new Color(63, 194, 231, 255));
        d.fillRectangle(0, 0, 800, 600);

        leftCloud(d);

        rightCloud(d);

    }

    /**
     * drawing the left cloud.
     *
     * @param d the frame to draw onto.
     */
    private void leftCloud(DrawSurface d) {
        // left side
        int cloudX = 150;
        int cloudY = 400;

        // left rain
        int left = cloudX - 40;
        int right = cloudX + 60;
        int xDelta = 30;
        int lines = 6;

        int delta = (right - left) / lines;

        d.setColor(new Color(23, 109, 255, 255));
        for (int x = left; x <= right; x += delta) {
            d.drawLine(x, cloudY - 15, x + xDelta, 600);
        }

        // left cloud
        d.setColor(new Color(120, 139, 142, 255));
        d.fillCircle(cloudX - 40, cloudY - 25, 25);

        d.setColor(new Color(120, 139, 142, 255));
        d.fillCircle(cloudX - 25, cloudY, 25);

        d.setColor(new Color(146, 162, 163, 255));
        d.fillCircle(cloudX, cloudY - 32, 32);

        d.setColor(new Color(146, 168, 172, 255));
        d.fillCircle(cloudX + 30, cloudY - 35, 32);

        d.setColor(new Color(146, 168, 172, 255));
        d.fillCircle(cloudX + 15, cloudY, 27);
    }

    /**
     * drawing the right cloud.
     *
     * @param d the frame to draw onto.
     */
    private void rightCloud(DrawSurface d) {
        // left side
        int cloudX = 600;
        int cloudY = 350;

        // left rain

        int left = cloudX - 40;
        int right = cloudX + 60;
        int xDelta = -40;
        int lines = 6;

        int delta = (right - left) / lines;

        d.setColor(new Color(71, 129, 232, 255));
        for (int x = left; x <= right; x += delta) {
            d.drawLine(x, cloudY + 15, x + xDelta, 600);
        }

        // left cloud
        d.setColor(new Color(145, 185, 191, 255));
        d.fillCircle(cloudX + 40, cloudY + 25, 25);

        d.setColor(new Color(145, 185, 191, 255));
        d.fillCircle(cloudX + 25, cloudY, 27);

        d.setColor(new Color(136, 168, 173, 255));
        d.fillCircle(cloudX, cloudY + 32, 32);

        d.setColor(new Color(123, 148, 151, 255));
        d.fillCircle(cloudX - 30, cloudY + 35, 32);

        d.setColor(new Color(123, 148, 151, 255));
        d.fillCircle(cloudX - 25, cloudY, 27);
    }

}
