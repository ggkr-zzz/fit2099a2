package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.managers.ResetManager;

public class ResetGameAction extends Action {
    @Override
    public String execute(Actor actor, GameMap map) {
        ResetManager.getInstance().run();
        return "Resetting game...";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Rest.";
    }
}
