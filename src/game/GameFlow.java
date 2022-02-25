package game;

import animation.AnimationRunner;
import animation.EndScreen;
import animation.KeyPressStoppableAnimation;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import levels.LevelInformation;

import java.util.List;

/**
 * @author Noam Cohen cohennoam48@gmail.com
 * id: 211497037
 */

public class GameFlow {
    private GUI gui;
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private Counter score;

    // ############################# Constructors ######################################

    /**
     * creates a GameFlow instance.
     *
     * @param gui the gui.
     * @param ar  the animation runner.
     * @param ks  a keyboard sensor.
     */
    public GameFlow(GUI gui, AnimationRunner ar, KeyboardSensor ks) {
        this.gui = gui;
        this.animationRunner = ar;
        this.keyboardSensor = ks;

        score = new Counter(0);
    }

    // ############################# Animation ######################################

    /**
     * running a list of given levels.
     *
     * @param levels list of levels to run.
     */
    public void runLevels(List<LevelInformation> levels) {
        boolean lost = false;

        // running through all levels to run
        for (LevelInformation levelInfo : levels) {
            // the level to run
            GameLevel level = new GameLevel(gui, levelInfo, this.keyboardSensor, this.animationRunner, this.score);

            // initializing the level
            level.initialize();

            // running the level
            level.run();

            // check if lost
            if (level.lost()) {
                lost = true;
                break;
            }
        }

        // running end screen
        animationRunner.run(new KeyPressStoppableAnimation(keyboardSensor, KeyboardSensor.SPACE_KEY,
                new EndScreen(lost, score)));

        // closing the windows
        gui.close();
    }
}

