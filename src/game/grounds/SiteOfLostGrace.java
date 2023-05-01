package game.grounds;

import edu.monash.fit2099.engine.positions.Ground;
import game.actors.Status;

public class SiteOfLostGrace extends Ground {
    /**
     * Constructor.
     *
     */
    public SiteOfLostGrace() {
        super('U');
        this.addCapability(Status.RESET_GAME);
    }
}
