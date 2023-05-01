package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AreaAttackAction;
import game.actions.AttackAction;
import game.actions.DespawnAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.Behaviour;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;
import game.managers.ResetManager;
import game.managers.Resettable;

import java.util.HashMap;
import java.util.Map;

public abstract class EnemyResettable extends Actor implements Resettable {
    private final Map<Integer, Behaviour> behaviours = new HashMap<>();
    private IntrinsicWeapon intrinsicWeapon;    //UNSURE WHAT TO PUT HERE private/protected/final etc.

    protected int attackPriority = 0;
    protected int followPriority = 500;
    protected int wanderPriority = 999;

    private boolean resetFlag = false;



    public EnemyResettable(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
    }

    public EnemyResettable(String name, char displayChar, int hitPoints, EnemyType enemyType, IntrinsicWeapon intrinsicWeapon) {
        super(name, displayChar, hitPoints);
        this.addCapability(enemyType);
        this.behaviours.put(wanderPriority, new WanderBehaviour());
        this.behaviours.put(attackPriority, new AttackBehaviour());
        this.intrinsicWeapon = intrinsicWeapon;
        ResetManager.getInstance().registerResettable(this);
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

        if (resetFlag) {
            return new DespawnAction();
        }


        if (!behaviours.containsKey(followPriority)) {
            if(Math.random() <= 0.1){
                return new DespawnAction();
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
     * @return ActionList of allowable actions from other actor to current actor
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();

        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {        // if exit contains actor with HOSTILE_TO_ENEMY status, follows the actor
            behaviours.put(followPriority, new FollowBehaviour(otherActor));       // follow player forever
        }

        if(!otherActor.capabilitiesList().equals(this.capabilitiesList())) {      // if the other actor and current actor has different capabilities, allow attack
            actions.add(new AttackAction(this, direction));
        }

        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {        // if other actor is player, add option for player to attack with any weapon in inventory
            for (WeaponItem weapon : otherActor.getWeaponInventory()) {
                actions.add(new AttackAction(this, direction, weapon));


               if (weapon.hasCapability(Status.AOE_CAPABLE_WEAPON)) {
                   actions.add(new AreaAttackAction(weapon));
                }
            }
        }

        if (otherActor.hasCapability(Status.AOE_CAPABLE)) {
            actions.add(new AreaAttackAction());
        }

        return actions;
    }


    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {

        return new IntrinsicWeapon(intrinsicWeapon.damage(), intrinsicWeapon.verb(), intrinsicWeapon.chanceToHit());
    }

    public void resetHp() {
        this.resetMaxHp(this.getMaxHp());
    }


    @Override
    public void reset() {
        resetFlag = true;
    }

}

