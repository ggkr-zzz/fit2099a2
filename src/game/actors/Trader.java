package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Club;
import game.Status;
import game.actions.BuyAction;
import game.actions.SellAction;
import game.behaviours.Behaviour;

import java.util.HashMap;
import java.util.Map;

/**
 * Trader character
 *
 * Created by:
 * @author Abdus Sami
 * Modified by:
 *
 */
public class Trader extends Actor {
    private Map<Integer, Behaviour> behaviours = new HashMap<>();

    public Trader() {

        super("Trader", 'K', 100);
        Club club = new Club();
        addWeaponToInventory(club);
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
        return new DoNothingAction();
    }

    /**
     * Actions that the trader can do
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
            for (WeaponItem weapon : getWeaponInventory()){
                actions.add(new BuyAction(otherActor, this, weapon));
            }
            for (WeaponItem weapon : otherActor.getWeaponInventory()){
                actions.add(new SellAction(otherActor, this, weapon));
            }
        }

        return actions;
    }
}
