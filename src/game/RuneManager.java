package game;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.behaviours.Behaviour;

import java.util.HashMap;
import java.util.Map;

public class RuneManager{
    private static Map<Actor, Integer> runeTracker = new HashMap<>();
    private static Map<Item, Integer> itemBuy = new HashMap<>();
    private static Map<Item, Integer> itemSell = new HashMap<>();

    public static int getRunes(Actor actor) {
        return runeTracker.get(actor);
    }

    public static int getBuyPrice(Item item) {
        return itemBuy.get(item);
    }

    public static int getSellPrice(Item item) {
        return itemSell.get(item);
    }

    public static void setRunes(Actor actor, int runes) {
        runeTracker.remove(actor);
        runeTracker.put(actor, runes);
    }

    public static void setBuyPrice(Item item, int runes) {
        itemBuy.remove(item);
        itemBuy.put(item, runes);
    }

    public static void setSellPrice(Item item, int runes) {
        itemSell.remove(item);
        itemSell.put(item, runes);
    }

    public static void removeActor(Actor actor) {
        runeTracker.remove(actor);
    }

}
