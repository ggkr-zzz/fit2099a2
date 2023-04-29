package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actors.Status;
import game.managers.RuneManager;

/**
 * A simple weapon that can be used to attack the enemy.
 * It deals 103 damage with 80% hit rate
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 *
 */
public class Grossmesser extends WeaponItem {
    /**
     * Constructor
     */
    public Grossmesser() {
        super("Grossmesser", '?', 115, "attacks", 85);
        RuneManager.setBuyPrice(this, 99999999);
        RuneManager.setSellPrice(this, 100);
        this.addCapability(Status.AOE_CAPABLE_WEAPON);
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {}
}
