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
    private Actor attacker;

    public GetRunesAction(Actor actor) {
        this.attacker = actor;
    }

    /**
     * The target's rune is retrieved and added to the player's rune amount
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

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
