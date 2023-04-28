package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;

import java.util.Random;

public class AreaAttackAction extends Action {

    /**
     * Random number generator
     */
    private Random rand = new Random();

    /**
     * Weapon used for the attack
     */
    private Weapon weapon;


    /**
     * Constructor.
     *
     * @param weapon the weapon Actor uses to attack
     */
    public AreaAttackAction(Weapon weapon) {
        this.weapon = weapon;
    }

    public AreaAttackAction() {
        this.weapon = null;
    }

    @Override
    public String execute(Actor actor, GameMap map) {

        String finalMessage = "";

        if (weapon == null) {
            weapon = actor.getIntrinsicWeapon();
        }

        Location here = map.locationOf(actor);

        for (Exit exit : here.getExits()) {                     // here it checks all the possible exits of current actor
            Location destination = exit.getDestination();

            if (destination.containsAnActor()) {
                Actor target = destination.getActor();

                if (!(rand.nextInt(100) <= weapon.chanceToHit())) {
                    finalMessage += (actor + " misses " + target + ".") + "\n";
                }

                int damage = weapon.damage();
                finalMessage += (actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.") + "\n";
                target.hurt(damage);
                if (!target.isConscious()) {
                    finalMessage += new DeathAction(actor).execute(target, map);
                }

            }

        }
        return finalMessage;
    }

    @Override
    public String menuDescription(Actor actor) {
        return (actor + " AOE attacks all around with " + (weapon != null ? weapon : "Intrinsic Weapon"));
    }
}
