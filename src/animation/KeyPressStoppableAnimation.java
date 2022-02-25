package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Noam Cohen cohennoam48@gmail.com
 * id: 211497037
 */

public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor sensor;
    private String key;
    private Animation animation;

    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * creates a StoppableAnimation instance.
     *
     * @param sensor    the keyboard sensor.
     * @param key       the key to stop when pressed.
     * @param animation the animation to add stop.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;

        stop = false;
    }

    /**
     * performing one frame of the animation.
     *
     * @param d the frame to draw onto.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        animation.doOneFrame(d);

        // if player pressed anything
        if (!isAlreadyPressed && sensor.isPressed(key)) {
            this.stop = true;
        }

        if (!sensor.isPressed(key)) {
            isAlreadyPressed = false;
        }
    }

    /**
     * if the animation need to be stopped.
     *
     * @return true if need to stop, false if not.
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
