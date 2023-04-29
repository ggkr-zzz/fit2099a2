package game.behaviours;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.Status;
import game.actions.AreaAttackAction;
import game.actions.AttackAction;

import java.util.Random;

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


    /**
     * Random number generator
     */
    private Random rand = new Random();

    /**
     *
     */


    @Override
    public Action getAction(Actor actor, GameMap map) {

        Location here = map.locationOf(actor);

        for (Exit exit : here.getExits()) {                     // here it checks all the possible exits of current actor
            Location destination = exit.getDestination();

            if (destination.containsAnActor()) {
                Actor otherActor = destination.getActor();


                if ( (actor.hasCapability(Status.AOE_CAPABLE)) && (!(rand.nextInt(100) <= 50)) ) {  // 50% chance to area attack if capable
                    if (actor.getWeaponInventory().size() > 0) {
                        return new AreaAttackAction(actor.getWeaponInventory().get(0)); // grabs top most weapon in inventory to attack with
                    }
                    return new AreaAttackAction();  // otherwise attack with intrinsic weapon
                }



                if (!actor.capabilitiesList().equals(otherActor.capabilitiesList())) {     // if exits contain target actor, it checks that the other actor has different capabilities
                    if (actor.getWeaponInventory().size() > 0) {
                        return new AttackAction(otherActor, null, actor.getWeaponInventory().get(0)); // grabs top most weapon in inventory to attack with
                    }
                    return new AttackAction(otherActor, null);  // otherwise attack with intrinsic weapon
                }
            }
        }

        return null;
    }

}



