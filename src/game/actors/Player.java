package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import game.actions.ResetGameAction;
import game.items.FlaskOfCrimsonTears;
import game.managers.ResetManager;
import game.weapons.Club;
import game.managers.Resettable;
import game.managers.RuneManager;

/**
 * Class representing the Player. It implements the Resettable interface.
 * It carries around a club to attack a hostile creature in the Lands Between.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 *
 */
public class Player extends Actor implements Resettable {

	private final Menu menu = new Menu();
	private final FlaskOfCrimsonTears flask = new FlaskOfCrimsonTears();

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
		this.addCapability(Status.HOSTILE_TO_ENEMY);
		this.addWeaponToInventory(new Club());
		this.addItemToInventory(flask);
		RuneManager.setRunes(this, 1000);
		ResetManager.getInstance().registerResettable(this);
	}

	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();

		// return/print the console menu
		display.println("Tarnished(" + hitPoints + "/" + maxHitPoints +") runes: " + RuneManager.getRunes(this));

		if (map.locationOf(this).getGround().hasCapability(Status.RESET_GAME)){
			actions.add(new ResetGameAction());
		}
		return menu.showMenu(this, actions, display);
	}

	@Override
	public void reset() {
		this.resetMaxHp(maxHitPoints);
		this.flask.reset();
	}
}
