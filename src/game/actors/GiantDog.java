package game.actors;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.utils.RandomNumberGenerator;
import game.managers.RuneManager;

/**
 * A Giant Dog enemy
 * @author Abdus Sami
 */
public class GiantDog extends EnemyResettable {

    /**
     * A constructor for the GiantDog class
     */
    public GiantDog() {
        super("Giant Dog", 'G', 693, EnemyType.CANINE_TYPE, new IntrinsicWeapon(314, "slams", 90));
        this.addCapability(Status.AOE_CAPABLE);
        int runes = RandomNumberGenerator.getRandomInt(313, 1808);
        RuneManager.setRunes(this, runes);
    }
}
