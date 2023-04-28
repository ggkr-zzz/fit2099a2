package game;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.LoneWolfEnemy;

import java.util.List;

/**
 * A Graveyard Ground
 * Created by:
 * @author Abdus Sami
 * Modified by:
 *
 */
public class Graveyard extends Ground {

    public Graveyard() {
        super('n');
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        return true;
    }

    public void tick(Location location) {
        int chance = RandomNumberGenerator.getRandomInt(1, 100);
        if ((chance <= 27) && !(location.containsAnActor())) {
            location.addActor(new LoneWolfEnemy());
        }

    }
}
