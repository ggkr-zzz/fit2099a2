package game.actors;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;

public class LoneWolfEnemy extends Enemy {
    public LoneWolfEnemy() {
        super("Lone Wolf", 'h', 102, EnemyType.CANINE_TYPE, new IntrinsicWeapon(97, "bites", 95));
    }
}
