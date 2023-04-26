package game.actors;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;

public class GiantCrab extends Enemy {
    public GiantCrab() {
        super("Giant Crab", 'C', 407, EnemyType.WATER_TYPE, new IntrinsicWeapon(208, "slams", 90));
    }
}
