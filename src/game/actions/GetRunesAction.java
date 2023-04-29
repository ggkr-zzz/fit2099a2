package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.managers.RuneManager;

/**
 * An action responsible for taking runes from enemies
 * Created by:
 * @author Abdus Sami
 * Modified by:
 *
 */
public class GetRunesAction extends Action {
    /**
     * An Actor object that holds the attacker
     */
    private Actor attacker;

    /**
     * A constructor for the GetRunesAction class
     * @param actor An Actor input that represents the attacker
     */
    public GetRunesAction(Actor actor) {
        this.attacker = actor;
    }

    /**
     * A method to retrieve the target's runes and add to the player's rune amount
     *
     * @param target The actor that drops runes.
     * @param map The map the actor is on.
     * @return result of the action to be displayed on the UI
     */
    @Override
    public String execute(Actor target, GameMap map) {
        String result = "";
        int targetRunes = RuneManager.getRunes(target);
        int playerRunes = RuneManager.getRunes(attacker);
        RuneManager.setRunes(attacker, playerRunes + targetRunes);
        result += System.lineSeparator() + target + " drops " + RuneManager.getRunes(target) + " runes";
        RuneManager.removeActor(target);
        return result;
    }

    /**
     * A method to print the action to the UI
     * @param actor The actor performing the action.
     * @return null (Is not a menu option)
     */
    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
