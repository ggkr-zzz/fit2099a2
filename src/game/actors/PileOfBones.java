package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.actors.Status;
import game.actions.AttackAction;
import game.actions.DeathAction;
import game.actions.ReviveAction;
import game.behaviours.FollowBehaviour;

public class PileOfBones extends Actor {

    private int tick;
    protected Actor previousActor;
    public PileOfBones(Actor previousActor) {
        super("Pile Of Bones", 'X', 1);
        this.previousActor = previousActor;
        this.addCapability(EnemyType.SKELETAL_TYPE);
        this.addCapability(Status.PILE_OF_BONES);
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (tick == 3) {
            return new ReviveAction((Enemy) previousActor);
        }
        tick++;                         //tick counter every playTurn
        return new DoNothingAction();
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();

        if(!otherActor.capabilitiesList().equals(this.capabilitiesList())) {      // if the other actor and current actor has different capabilities, allow attack
            actions.add(new AttackAction(this, direction));
        }

        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {        // if other actor is player, add option for player to attack with any weapon in inventory
            for (Weapon weapon : otherActor.getWeaponInventory()) {
                actions.add(new AttackAction(this, direction, weapon));
            }
        }

        return actions;
    }
}
