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

public class Sun implements LevelInformation {
    private List<Block> blocks;

    private double ballSpeed = 7;
    private int numberOfBalls = 10;
    private int ballRadius = 7;

    private int paddleSpeed = 10;
    private int paddleWidth = 200;
    private Color paddleColor = Color.WHITE;

    private int numberOfBlocks = 15;
    private double blockHeight = 30;
    private int rowY = 250;

    private int sunX = 120;
    private int sunY = 140;
    private int sunLines = 40;

    private String levelName = "Sunshine";

    // ############################# Constructors ######################################

    /**
     * creates a GameFlow instance.
     *
     * @param screenWidth  the width of the screen.
     * @param screenHeight the height of the screen.
     */
    public Sun(int screenWidth, int screenHeight) {
        blocks = new ArrayList<>();
        createBlocks(screenWidth, screenHeight);
    }

    /**
     * creates the blocks.
     *
     * @param screenWidth  the width of the screen.
     * @param screenHeight the height of the screen.
     */
    private void createBlocks(int screenWidth, int screenHeight) {
        int width = screenWidth / numberOfBlocks;

        List<Color> colors = new ArrayList<>();
        colors.add(Color.red);
        colors.add(Color.orange);
        colors.add(Color.yellow);
        colors.add(Color.green);
        colors.add(Color.blue);
        colors.add(Color.magenta);
        colors.add(Color.pink);

        int j = 0;
        for (int i = 0; i < numberOfBlocks; ++i) {
            Point upperLeft = new Point(width * i, rowY);
            Rectangle body = new Rectangle(upperLeft, width, blockHeight);
            Block block = new Block(body, colors.get(j / 10));
            j += 4;
            blocks.add(block);
        }
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();

        int startAngle = -45;
        int endAngle = 45;
        double deltaAngle = (double) (endAngle - startAngle) / (numberOfBalls - 1);

        for (int i = 0; i < numberOfBalls; ++i) {
            velocities.add(Velocity.fromAngleAndSpeed(startAngle + i * deltaAngle, ballSpeed));
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
        d.setColor(new Color(155, 227, 225, 255));
        d.fillRectangle(0, 0, 800, 600);

        // lines
        int deltaX = 800 / sunLines;
        d.setColor(new Color(219, 212, 1));
        for (int i = 0; i < sunLines; ++i) {
            d.drawLine(sunX, sunY, i * deltaX, rowY);
        }

        // sun
        d.setColor(new Color(246, 246, 158));
        d.fillCircle(sunX, sunY, 60);

        d.setColor(new Color(248, 248, 127));
        d.fillCircle(sunX, sunY, 45);

        d.setColor(new Color(250, 250, 82));
        d.fillCircle(sunX, sunY, 30);
    }

}
