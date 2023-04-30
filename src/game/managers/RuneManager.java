package game.managers;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;

import java.util.HashMap;
import java.util.Map;

/**
 * A Rune Manager class that keeps track of the runes held by all enemies on
 * the map, and the buy and sell prices of items
 * @author Abdus Sami
 * Modified by:
 *
 */
public class RuneManager{
    /**
     * A Hash map that stores that an Actor as a key and holds the number
     * of runes representing runes held as the value
     */
    private static Map<Actor, Integer> runeTracker = new HashMap<>();
    /**
     * A Hash map that stores that an Item as a key and holds the number
     * of runes representing buy price as the value
     */
    private static Map<Item, Integer> itemBuy = new HashMap<>();
    /**
     * A Hash map that stores that an Item as a key and holds the number
     * of runes representing sell price as the value
     */
    private static Map<Item, Integer> itemSell = new HashMap<>();

    /**
     * A method to get the amount of runes an Actor has
     * @param actor The Actor to use as a key to retrieve rune value
     * @return An integer representing runes held
     */
    public static int getRunes(Actor actor) {
        return runeTracker.get(actor);
    }

    /**
     * A method to get the amount of runes an Item can be bought for
     * @param item The Item to use as a key to retrieve rune value
     * @return An integer representing buy price
     */
    public static int getBuyPrice(Item item) {
        if (itemBuy.containsKey(item)) {return itemBuy.get(item);}
        return -1;
    }

    /**
     * A method to get the amount of runes an Item can be sold for
     * @param item The Item to use as a key to retrieve rune value
     * @return An integer representing sell price
     */
    public static int getSellPrice(Item item) {
        if (itemSell.containsKey(item)) {return itemSell.get(item);}
        return -1;
    }

    /**
     * A method to set the amount of runes an Actor has
     * @param actor The Actor to use as a key to set rune value
     * @param runes An integer representing the rune value to set
     */
    public static void setRunes(Actor actor, int runes) {
        runeTracker.remove(actor);
        runeTracker.put(actor, runes);
    }

    /**
     * A method to set the amount of runes an Item can be bought for
     * @param item The Item to use as a key to set rune value
     * @param runes An integer representing the rune value to set
     */
    public static void setBuyPrice(Item item, int runes) {
        itemBuy.remove(item);
        itemBuy.put(item, runes);
    }

    /**
     * A method to set the amount of runes an Item can be sold for
     * @param item The Item to use as a key to set rune value
     * @param runes An integer representing the rune value to set
     */
    public static void setSellPrice(Item item, int runes) {
        itemSell.remove(item);
        itemSell.put(item, runes);
    }

    /**
     * A method to remove an Actor from the rune tracking hash map
     * @param actor The Actor to use as a key for removing
     */
    public static void removeActor(Actor actor) {
        runeTracker.remove(actor);
    }

}
