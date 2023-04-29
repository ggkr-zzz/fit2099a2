package game.actors;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.weapons.Grossmesser;

public class HeavySkeletalSwordsman extends Enemy {
    public HeavySkeletalSwordsman() {
        super("Heavy Skeletal Swordsman", 'q', 153, EnemyType.SKELETAL_TYPE, new IntrinsicWeapon( 115, "spin", 85));        // need weapon class?

        this.addWeaponToInventory(new Grossmesser());
    }
}
