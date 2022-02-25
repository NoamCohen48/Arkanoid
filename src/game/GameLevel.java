package game;

import animation.AnimationRunner;
import animation.Animation;
import animation.CountdownAnimation;
import animation.KeyPressStoppableAnimation;
import animation.PauseScreen;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import collision.Collidable;
import collision.GameEnvironment;
import elements.Ball;
import elements.Block;
import elements.Paddle;
import geometry.Point;
import geometry.Rectangle;
import levels.LevelInformation;
import listeners.BallRemover;
import listeners.BlockRemover;
import listeners.HitListener;
import listeners.ScoreTrackingListener;
import sprites.Sprite;
import sprites.SpriteCollection;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Noam Cohen cohennoam48@gmail.com
 * id: 211497037
 */

public class GameLevel implements Animation {
    // variables
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private KeyboardSensor keyboard;
    private AnimationRunner runner;
    private boolean running;
    private LevelInformation level;
    private List<Ball> balls;

    // screen
    private int screenWidth = 800;
    private int screenHeight = 600;
    private Color backgroundColor = new Color(146, 146, 146);

    // counters
    private Counter blocksCounter;
    private Counter ballCounter;
    private Counter score;

    // ############################# Constructors ######################################

    /**
     * constructor of game.
     *
     * @param gui             the gui of the game.
     * @param levelInfo       the current level.
     * @param keyboardSensor  the keyboard sensor
     * @param animationRunner the runner of the animation.
     * @param score           the current score of the player.
     */
    public GameLevel(GUI gui, LevelInformation levelInfo, KeyboardSensor keyboardSensor,
                     AnimationRunner animationRunner, Counter score) {
        // lists
        sprites = new SpriteCollection();
        environment = new GameEnvironment();

        this.gui = gui;
        level = levelInfo;
        keyboard = keyboardSensor;
        runner = animationRunner;
        this.score = score;

        // counters
        blocksCounter = new Counter(0);
        ballCounter = new Counter(0);
    }

    // ############################# Initialize ######################################

    /**
     * initializing all parameters and elements.
     */
    public void initialize() {
        // creating the frame border
        createFrameBorder();

        // creating the score indicator
        createScoreIndicator();

        // creating the ball at the center
        createBalls();

        // creating paddle
        createPaddle();

        // creating the blocks
        createBlocks();
    }

    /**
     * creates the border.
     */
    private void createFrameBorder() {
        // creating the background
        addSprite(level.getBackground());

        double delta = 36;

        // variables
        Point upperLeft;
        Block block;

        // creating top block
        upperLeft = new Point(0, 0);
        block = new Block(upperLeft, screenWidth, delta, backgroundColor);
        addCollidable(block);
        addSprite(block);

        // creating bottom block
        upperLeft = new Point(0, screenHeight);
        block = new Block(upperLeft, screenWidth, delta, backgroundColor);
        HitListener listener = new BallRemover(this, ballCounter);
        block.addHitListener(listener);
        addCollidable(block);

        // creating left block
        upperLeft = new Point(-delta, 0);
        block = new Block(upperLeft, delta, screenHeight, backgroundColor);
        addCollidable(block);

        // creating right block
        upperLeft = new Point(screenWidth, 0);
        block = new Block(upperLeft, delta, screenHeight, backgroundColor);
        addCollidable(block);
    }

    /**
     * create the balls.
     */
    private void createBalls() {
        balls = new ArrayList<>();
        for (int i = 0; i < level.numberOfBalls(); ++i) {
            Point center = new Point(screenWidth / 2, 3 * screenHeight / 4);
            Ball ball = new Ball(center, level.ballsRadius());

            ball.setEnvironment(environment);

            // setting the velocity
            ball.setVelocity(level.initialBallVelocities().get(i));

            // adding to game balls
            balls.add(ball);
            ball.addToGame(this);
        }
        ballCounter.increase(level.numberOfBalls());
    }

    /**
     * creates a paddle on the frame and adds it to the game.
     */
    private void createPaddle() {
        final double paddleHeight = 15;

        // creating the paddle
        int x = screenWidth / 2;
        double y = screenHeight - paddleHeight / 2;
        Rectangle rec = Rectangle.rectangleCenter(new Point(x, y), level.paddleWidth(), paddleHeight);
        Paddle paddle = new Paddle(rec, level.paddleColor(), level.paddleSpeed());

        // setting its parameters
        paddle.setScreenWidth(screenWidth);
        paddle.setKeyboard(keyboard);
        paddle.setBalls(balls);

        // adding to game
        paddle.addToGame(this);
    }

    /**
     * create the blocks.
     */
    private void createBlocks() {
        // creating the listeners
        HitListener blockRemover = new BlockRemover(this, blocksCounter);
        HitListener scoreTracking = new ScoreTrackingListener(score);

        for (Block block : level.blocks()) {
            // adding Listener
            block.addHitListener(blockRemover);
            block.addHitListener(scoreTracking);

            // adding to game
            block.addToGame(this);
        }
        blocksCounter.increase(level.numberOfBlocksToRemove());
    }

    /**
     * creates a score indicator.
     */
    private void createScoreIndicator() {
        ScoreIndicator scoreIndicator = new ScoreIndicator(36, score, level.levelName());
        scoreIndicator.addToGame(this);
    }

    // ############################# Animation Loop #####################################

    /**
     * animate the game.
     */
    public void run() {
        // running the countdown
        runner.run(new CountdownAnimation(2, 3, sprites));

        // running the game
        this.running = true;
        runner.run(this);

        // adding score for clearing the level
        if (blocksCounter.getValue() == 0) {
            score.increase(100);
        }
    }

    /**
     * performing one frame of the animation.
     *
     * @param surface the frame to draw onto.
     */
    @Override
    public void doOneFrame(DrawSurface surface) {
        // drawing the elements
        this.sprites.drawAllOn(surface);

        // moving all
        this.sprites.notifyAllTimePassed();

        // pause screen
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY, new PauseScreen()));
        }

        // checking if need to end game
        if (blocksCounter.getValue() <= 0 || ballCounter.getValue() <= 0) {
            this.running = false;
        }
    }

    /**
     * if the animation need to be stopped.
     *
     * @return true if need to stop, false if not.
     */
    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * return if the player lost.
     *
     * @return true if the player lost, false otherwise.
     */
    public boolean lost() {
        return balls.size() == 0;
    }

    // ############################# Adding to Lists ####################################

    /**
     * add element to collidable environment.
     *
     * @param s the element to add
     */
    public void addSprite(Sprite s) {

        sprites.addSprite(s);
    }

    /**
     * add element to collidable environment.
     *
     * @param c the element to add
     */
    public void addCollidable(Collidable c) {

        environment.addCollidable(c);
    }

    // ############################# Removing #####################################

    /**
     * removing a given collidable.
     *
     * @param c the collidable to remove.
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }

    /**
     * removing a given sprite.
     *
     * @param s the sprite to remove.
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }

    /**
     * removing a given ball.
     *
     * @param b the ball to remove.
     */
    public void removeBall(Ball b) {
        balls.remove(b);
    }
}