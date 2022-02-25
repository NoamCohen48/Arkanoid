package game;

/**
 * @author Noam Cohen cohennoam48@gmail.com
 * id: 211497037
 */

public class Counter {
    private int counter;

    // ############################# Constructors ######################################

    /**
     * creates a Counter animation instance.
     *
     * @param value the starting value of the counter.
     */
    public Counter(int value) {
        this.counter = value;
    }

    // ############################# Changers ######################################

    /**
     * add number to current count.
     *
     * @param number the number to add.
     */
    public void increase(int number) {
        counter += number;
    }

    /**
     * subtract number from current count.
     *
     * @param number the number to subtract.
     */
    public void decrease(int number) {
        counter -= number;
    }

    // ############################# Getters ######################################

    /**
     * get current count.
     *
     * @return current count.
     */
    public int getValue() {
        return counter;
    }
}
