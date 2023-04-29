package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.Enemy;

public class ReviveAction extends Action {
    Enemy reviveEnemy;

    public ReviveAction(Enemy reviveEnemy) {
        this.reviveEnemy = reviveEnemy;
    }

    public void ReviveAction(Enemy reviveActor) {
        this.reviveEnemy = reviveActor;
    }
    @Override
    public String execute(Actor actor, GameMap map) {
        Location location = map.locationOf(actor);
        map.removeActor(actor);
        reviveEnemy.resetHp();                          //revives enemy to full hp.
        map.addActor(reviveEnemy, location);
        return actor + " is revived to " + reviveEnemy + ".";
    }

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
