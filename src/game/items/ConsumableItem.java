package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.actions.ConsumeAction;

public abstract class ConsumableItem extends Item {

    private int usages;
    private ConsumeAction consumeAction;

    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */

    public ConsumableItem(String name, char displayChar, boolean portable, int usages) {
        super(name, displayChar, portable);
        this.setUsages(usages);
        this.consumeAction = new ConsumeAction(this);
        this.addAction(consumeAction);
    }

    public void setUsages(int usages) {
        this.usages = usages;
    }

    public int getUsages() {
        return usages;
    }

    public void decrementUsages() {
        usages -= 1;

        if (usages == 0) {
            this.removeAction(consumeAction);
        }
    }

    public abstract String consumeItem(Actor actor);

    public abstract String itemEffect();
}
