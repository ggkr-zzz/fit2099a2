package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actors.Status;
import game.managers.RuneManager;

/**
 * A curved sword carried around by the Skeletal Bandit
 * Created by:
 * @author Abdus Sami
 * Modified by:
 *
 */
public class Scimitar extends WeaponItem {
    /**
     * Constructor
     */
    public Scimitar() {
        super("Scimitar", 's', 118, "slashes", 88);
        RuneManager.setBuyPrice(this, 600);
        RuneManager.setSellPrice(this, 100);
        this.addCapability(Status.AOE_CAPABLE_WEAPON);
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {}
}
