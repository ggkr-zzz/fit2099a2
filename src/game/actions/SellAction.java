package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.RuneManager;

import java.util.Scanner;

/**
 * An Action to sell to the trader
 * Created by:
 * @author Abdus Sami
 * Modified by:
 *
 */

public class SellAction extends Action{
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

    public SellAction(Actor player, Actor trader, WeaponItem weapon) {
        this.player = player;
        this.trader = trader;
        this.weapon = weapon;
    }

    public String execute(Actor player, GameMap map){
        int cost = RuneManager.getSellPrice(weapon);
        int runes  = RuneManager.getRunes(player);
        player.removeWeaponFromInventory(weapon);
        RuneManager.setRunes(player, runes + cost);
        return weapon + " has been sold";
    }


    public String menuDescription(Actor actor){
        return actor + " sells " + weapon.toString() + " to Merchant Kale";
    }
}
