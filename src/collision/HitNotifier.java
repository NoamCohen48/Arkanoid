package collision;

import listeners.HitListener;

/**
 * @author Noam Cohen cohennoam48@gmail.com
 * id: 211497037
 */

public interface HitNotifier {
    /**
     * adding a listener to the list.
     *
     * @param hl the listener to add.
     */
    void addHitListener(HitListener hl);

    /**
     * removing a listener to the list.
     *
     * @param hl the listener to remove.
     */
    void removeHitListener(HitListener hl);
}
