package game.actors;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.utils.RandomNumberGenerator;
import game.managers.RuneManager;

public class LoneWolf extends EnemyResettable {
    public LoneWolf() {
        super("Lone Wolf", 'h', 102, EnemyType.CANINE_TYPE, new IntrinsicWeapon(97, "bites", 95));
        int runes = RandomNumberGenerator.getRandomInt(55, 1470);
        RuneManager.setRunes(this, runes);
    }
}
