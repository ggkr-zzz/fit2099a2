package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.actions.ConsumeAction;
import game.actors.Status;
import game.managers.Resettable;

public class FlaskOfCrimsonTears extends ConsumableItem implements Resettable {

    private int hpIncrement;


    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */
    public FlaskOfCrimsonTears() {
        super("Flask of Crimson Tears", 'c', false, 2);
        this.hpIncrement = 250;
    }
    
    public int getHpIncrement() {
        return this.hpIncrement;
    }

    @Override
    public String consumeItem(Actor actor) {
        actor.heal(getHpIncrement());
        decrementUsages();
        return actor + " is healed for " + getHpIncrement() + " using " + this + ". Usages left: " + this.getUsages();
    }

    @Override
    public String itemEffect() {
        return "250 health";
    }

    @Override
    public void reset() {
        this.setUsages(this.getMaxUsages());
    }

}
