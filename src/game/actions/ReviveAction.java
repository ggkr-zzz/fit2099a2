package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.Enemy;

public class ReviveAction extends Action {
    Enemy enemyToRevive;

    public ReviveAction(Enemy enemyToRevive) {
        this.enemyToRevive = enemyToRevive;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        Location location = map.locationOf(actor);
        map.removeActor(actor);
        enemyToRevive.resetHp();                          //revives enemy to full hp.
        map.addActor(enemyToRevive, location);
        return actor + " is revived to " + enemyToRevive + ".";
    }

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
