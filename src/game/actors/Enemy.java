package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Status;
import game.actions.AttackAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.Behaviour;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;

import java.util.HashMap;
import java.util.Map;

public abstract class Enemy extends Actor {
    private Map<Integer, Behaviour> behaviours = new HashMap<>();
    private IntrinsicWeapon intrinsicWeapon;    //UNSURE WHAT TO PUT HERE private/protected/final etc.

    public Enemy(String name, char displayChar, int hitPoints, EnemyType enemyType, IntrinsicWeapon intrinsicWeapon) {
        super(name, displayChar, hitPoints);
        this.addCapability(enemyType);
        this.behaviours.put(999, new WanderBehaviour());
        this.intrinsicWeapon = intrinsicWeapon;
    }

    /**
     * At each turn, select a valid action to perform.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the valid action that can be performed in that iteration or null if no valid action is found
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        Location here = map.locationOf(this);
        for (Exit exit : here.getExits()) {                     // here it checks all the possible exits of current actor
            Location destination = exit.getDestination();

            if (destination.containsAnActor()) {
                Actor otherActor = destination.getActor();

                if (this.capabilitiesList() != otherActor.capabilitiesList()) {     // if exits contain target actor, it checks that the other actor has different capabilities
                    behaviours.put(0, new AttackBehaviour(otherActor));
                }
                if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {        // if exit contains actor with HOSTILE_TO_ENEMY status, follows the actor
                    behaviours.put(500, new FollowBehaviour(otherActor));       // follow player forever
                }
            }
        }

        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if(action != null) {
                return action;
            }
        }
        return new DoNothingAction();
    }

    /**
     * The enemy can be attacked by any actor that has the HOSTILE_TO_ENEMY capability (in this case player)
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this, direction));
            // HINT 1: The AttackAction above allows you to attack the enemy with your intrinsic weapon.
            // HINT 1: How would you attack the enemy with a weapon?
            // add weapon attack action??

        }

        return actions;
    }


    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {

        return new IntrinsicWeapon(intrinsicWeapon.damage(), intrinsicWeapon.verb(), intrinsicWeapon.chanceToHit());
    }
}

