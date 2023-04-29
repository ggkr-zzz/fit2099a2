package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.managers.RuneManager;

/**
 * An Action to buy from the trader
 * @author Abdus Sami
 * Modified by:
 *
 */

public class BuyAction extends Action{
    /**
     * An Actor object that holds the player
     */
    private Actor player;
    /**
     * An Actor object that holds the trader
     */
    private Actor trader;
    /**
     * A WeaponItem object that holds the weapon
     */
    private WeaponItem weapon;
    /**
     * A display object
     */
    protected Display display = new Display();

    /**
     * A constructor for the BuyAction class
     * @param player An Actor input that represents the player
     * @param trader An Actor input that represents the trader
     * @param weapon A WeaponItem that represents the weapon to be bought
     */
    public BuyAction(Actor player, Actor trader, WeaponItem weapon) {
        this.player = player;
        this.trader = trader;
        this.weapon = weapon;
    }

    /**
     * A method to execute the buy interaction
     * @param player The actor performing the action.
     * @param map The map the actor is on.
     * @return result of the action to be displayed on the UI
     */
    public String execute(Actor player, GameMap map){
        int cost = RuneManager.getBuyPrice(weapon);
        int runes  = RuneManager.getRunes(player);
        if (cost <= runes) {
            player.addWeaponToInventory(weapon);
            RuneManager.setRunes(player, runes - cost);
            return weapon + " has been purchased";
        }
        else {
            return "Insufficient runes to purchase " + weapon;
        }

    }

    /**
     * A method to print the action to the UI
     * @param actor The actor performing the action.
     * @return A string input representing the string to be displayed
     */
    public String menuDescription(Actor actor){
        return actor + " buys " + weapon.toString() + " from Merchant Kale for " + RuneManager.getBuyPrice(weapon);
    }
}
