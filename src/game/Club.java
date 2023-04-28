package game;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;

/**
 * A simple weapon that can be used to attack the enemy.
 * It deals 103 damage with 80% hit rate
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 *
 */
public class Club extends WeaponItem {

    private final int buyPrice = 600;
    private final int sellPrice = 100;
    /**
     * Constructor
     */
    public Club() {
        super("Club", '!', 103, "bonks", 80);
        RuneManager.setBuyPrice(this, 600);
        RuneManager.setSellPrice(this, 100);
    }

    public int getPrice(){
        return buyPrice;
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {}
}
