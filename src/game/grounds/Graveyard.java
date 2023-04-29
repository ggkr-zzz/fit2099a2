package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.NumberRange;
import game.utils.RandomNumberGenerator;
import game.actors.HeavySkeletalSwordsman;
import game.actors.SkeletalBandit;

/**
 * A Graveyard Ground
 * @author Abdus Sami
 * Modified by:
 *
 */
public class Graveyard extends Ground {

    /**
     * A constructor for the Graveyard class
     */
    public Graveyard() {
        super('n');
    }

    /**
     * A method that allows the Ground to be passable for Actors
     * @param actor the Actor to check
     * @return true, indicating terrain is passable
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return true;
    }

    /**
     * A method that allows the Ground to spawn enemies every turn depending
     * on a spawn chance and which side of the map the ground is on
     * @param location The location of the Ground
     */
    public void tick(Location location) {
        int chance = RandomNumberGenerator.getRandomInt(1, 100);
        int xLocation = location.x();
        GameMap map = location.map();
        NumberRange xValues = map.getXRange();
        int middle = (xValues.min() + xValues.max()) / 2;
        if (xLocation <= middle) {
            if ((chance <= 27) && !(location.containsAnActor())) {
                location.addActor(new HeavySkeletalSwordsman());
            }
        } else {
            if ((chance <= 27) && !(location.containsAnActor())) {
                location.addActor(new SkeletalBandit());
            }

        }
    }
}
