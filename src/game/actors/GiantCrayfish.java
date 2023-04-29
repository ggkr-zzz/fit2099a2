package game.actors;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.RandomNumberGenerator;
import game.RuneManager;
import game.Status;

public class GiantCrayfish extends Enemy {
    public GiantCrayfish() {
        super("Giant Crayfish", 'R', 4803, EnemyType.WATER_TYPE, new IntrinsicWeapon(527, "slams", 100));
        this.addCapability(Status.AOE_CAPABLE);
        int runes = RandomNumberGenerator.getRandomInt(500, 2374);
        RuneManager.setRunes(this, runes);
    }
}
