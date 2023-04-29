package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.NumberRange;
import game.RandomNumberGenerator;
import game.actors.*;

/**
 * A Puddle of Water Ground
 * Created by:
 * @author Abdus Sami
 * Modified by:
 *
 */
public class PuddleOfWater extends Ground {

    public PuddleOfWater() {
        super('~');
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        return true;
    }

    public void tick(Location location) {
        int chance = RandomNumberGenerator.getRandomInt(1, 100);
        int xLocation = location.x();
        GameMap map = location.map();
        NumberRange xValues = map.getXRange();
        int middle = (xValues.min() + xValues.max()) / 2;
        if (xLocation <= middle) {
            if ((chance <= 2) && !(location.containsAnActor())) {
                location.addActor(new GiantCrab());
            }
        } else {
            if ((chance <= 1) && !(location.containsAnActor())) {
                location.addActor(new GiantCrayfish());
            }

        }

    }
}
