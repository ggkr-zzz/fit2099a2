package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.managers.RuneManager;

/**
 * An Action to buy from the trader
 * Created by:
 * @author Abdus Sami
 * Modified by:
 *
 */

public class BuyAction extends Action{
    /**
     * The player object
     */
    private Actor player;
    /**
     * The trader object
     */
    private Actor trader;
    /**
     * A weapon object
     */
    private WeaponItem weapon;
    /**
     * A display object
     */
    protected Display display = new Display();

    public BuyAction(Actor player, Actor trader, WeaponItem weapon) {
        this.player = player;
        this.trader = trader;
        this.weapon = weapon;
    }

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


    public String menuDescription(Actor actor){
        return actor + " buys " + weapon.toString() + " from Merchant Kale for " + RuneManager.getBuyPrice(weapon);
    }
}
