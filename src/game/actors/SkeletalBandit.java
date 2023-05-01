package game.actors;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.utils.RandomNumberGenerator;
import game.managers.RuneManager;

/**
 * A Skeletal Bandit enemy
 * @author Abdus Sami
 */
public class SkeletalBandit extends EnemyResettable {

    /**
     * A constructor for the SkeletalBandit class
     */
    public SkeletalBandit() {
        super("Skeletal Bandit", 'b', 184, EnemyType.SKELETAL_TYPE, new IntrinsicWeapon( 118, "slashes", 88));
        int runes = RandomNumberGenerator.getRandomInt(35, 892);
        RuneManager.setRunes(this, runes);
    }
}
