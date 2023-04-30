package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Status;
import game.items.ConsumableItem;

public class ConsumeAction extends Action {

    protected ConsumableItem item;

    public ConsumeAction(ConsumableItem item) {
        this.item = item;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        if (item.getUsages() > 0) {
            return item.consumeItem(actor);
        }
        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        if (item.getUsages() > 0){
            return ("Consume " + item + " for " + item.itemEffect() + ". Usages left: " + item.getUsages());
        }
        return null;

    }
}
