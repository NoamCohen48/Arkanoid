import animation.AnimationRunner;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import game.GameFlow;
import levels.DirectHit;
import levels.LevelInformation;
import levels.Sky;
import levels.Sun;
import levels.Building;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Noam Cohen cohennoam48@gmail.com
 * id: 211497037
 */

public class Ass6Game {

    /**
     * a main to run the game.
     *
     * @param args arguments.
     */
    public static void main(String[] args) {
        int screenWidth = 800;
        int screenHeight = 600;

        GUI gui = new GUI("pong", screenWidth, screenHeight);
        AnimationRunner animationRunner = new AnimationRunner(gui, 60);
        KeyboardSensor keyboardSensor = gui.getKeyboardSensor();

        List<LevelInformation> levels = new ArrayList<>();

        for (String lvl : args) {
            switch (lvl) {
                case "1" -> levels.add(new DirectHit(screenWidth, screenHeight));
                case "2" -> levels.add(new Sun(screenWidth, screenHeight));
                case "3" -> levels.add(new Building(screenWidth, screenHeight));
                case "4" -> levels.add(new Sky(screenWidth, screenHeight));
            }
        }

        if (levels.size() == 0) {
            levels.add(new DirectHit(screenWidth, screenHeight));
            levels.add(new Sun(screenWidth, screenHeight));
            levels.add(new Building(screenWidth, screenHeight));
            levels.add(new Sky(screenWidth, screenHeight));
        }


        GameFlow gameFlow = new GameFlow(gui, animationRunner, keyboardSensor);
        gameFlow.runLevels(levels);
    }
}
