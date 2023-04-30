package game.actors;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.managers.RuneManager;
import game.utils.RandomNumberGenerator;

public class GiantCrab extends Enemy {
    public GiantCrab() {

        super("Giant Crab", 'C', 407, EnemyType.WATER_TYPE, new IntrinsicWeapon(208, "slams", 90));
        int runes = RandomNumberGenerator.getRandomInt(318, 4961);
        RuneManager.setRunes(this, runes);
        this.addCapability(Status.AOE_CAPABLE);
    }


}
