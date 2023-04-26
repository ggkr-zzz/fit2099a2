package game.behaviours;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.AttackAction;

/**
 * A class that implements AttackAction when hostile enemy is 1 block away
 * @see edu.monash.fit2099.demo.mars.Application
 *
 * Created by:
 * @author gkr 33109249
 * Modified by:
 *
 */
public class AttackBehaviour implements Behaviour {

    private final Actor target;

    /**
     * Constructor.
     *
     * @param subject the Actor to follow
     */
    public AttackBehaviour(Actor subject) {
        this.target = subject;
    }

    @Override
    public Action getAction(Actor actor, GameMap map) {
        if(!map.contains(target) || !map.contains(actor))
            return null;

        Location here = map.locationOf(actor);

        for (Exit exit : here.getExits()) {                 // here it checks all the possible exits of current actor
            Location destination = exit.getDestination();
            if (destination.getActor() == target) {         // if exits contain target actor, it returns a new attack action. Direction is null as it is a behaviour.
                if (actor.getWeaponInventory().size() > 0) {
                    return new AttackAction(target, null, actor.getWeaponInventory().get(0)); // grabs top most weapon in inventory to attack with
                }
                return new AttackAction(target, null);                                  // otherwise attacks with intrinsic weapon
            }
        }

        return null;
    }

}

