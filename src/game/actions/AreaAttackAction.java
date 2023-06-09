package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;
import java.util.ArrayList;

public class AreaAttackAction extends Action {

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

        ArrayList<String> finalMessageArr = new ArrayList<>();    //Array with strings that will be combined later with newlines

        String finalMessage = "";               // will be the final constructed string to be returned

        if (weapon == null) {
            weapon = actor.getIntrinsicWeapon();
        }

        Location here = map.locationOf(actor);
        finalMessageArr.add(actor + " " + weapon.verb() +" the area");
        for (Exit exit : here.getExits()) {                     // here it checks all the possible exits of current actor
            Location destination = exit.getDestination();

            if (destination.containsAnActor()) {
                Actor target = destination.getActor();
                finalMessageArr.add(new AttackAction(target, null).execute(target, map)); // attacks each actor around.

            }

        }

        //iterates through each value in finalMessageArr and adds a newline except for last line.
        for (int j = 0; j < finalMessageArr.size()-1; j++) {
            finalMessage += finalMessageArr.get(j) + "\n";
        }
        finalMessage += finalMessageArr.get(finalMessageArr.size()-1);


        return finalMessage;
    }

    @Override
    public String menuDescription(Actor actor) {
        return (actor + " AOE attacks all around with " + (weapon != null ? weapon : "Intrinsic Weapon"));
    }
}
