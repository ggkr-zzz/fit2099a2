package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.actions.AreaAttackAction;
import game.actors.*;
import game.grounds.*;
import game.utils.FancyMessage;

/**
 * The main class to start the game.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 *
 */
public class Application {

	public static void main(String[] args) {

		World world = new World(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor());

		List<String> map = Arrays.asList(
				"...........................................................................",
				"......................#####....######......................................",
				"......................#..___....____#......................................",
				"..................................__#......................................",
				"......................._____........#......................................",
				"......................#............_#......................................",
				"......................#...........###......................................",
				"...........................................................................",
				"...........................................................................",
				"..................................###___###................................",
				"..................................________#................................",
				"..................................#________................................",
				"..................................#_______#................................",
				"..................................###___###................................",
				"....................................#___#..................................",
				"...........................................................................",
				"...........................................................................",
				"...........................................................................",
				"..####__##....................................................######..##...",
				"..#.....__....................................................#....____....",
				"..#___..........................................................__.....#...",
				"..####__###..................................................._.....__.#...",
				"..............................................................###..__###...",
				"...........................................................................");
		GameMap gameMap = new GameMap(groundFactory, map);
		world.addGameMap(gameMap);

		// BEHOLD, ELDEN RING
		for (String line : FancyMessage.ELDEN_RING.split("\n")) {
			new Display().println(line);
			try {
				Thread.sleep(200);
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}
		//gameMap.at(50,11).setGround(new Graveyard());
		//gameMap.at(23, 17).addActor(new LoneWolf());
		//gameMap.at(23, 17).addActor(new GiantCrab());


		gameMap.at(44, 12).addActor(new HeavySkeletalSwordsman());
		gameMap.at(44, 10).addActor(new PileOfBones(new HeavySkeletalSwordsman()));
		gameMap.at(45, 11).addActor(new PileOfBones(new HeavySkeletalSwordsman()));
		gameMap.at(45, 18).addActor(new PileOfBones(new HeavySkeletalSwordsman()));
		gameMap.at(44, 11).addActor(new GiantCrab());
		gameMap.at(38, 11).addActor(new Trader());
		
		// HINT: what does it mean to prefer composition to inheritance?
		Player player = new Player("Tarnished", '@', 10000);
		world.addPlayer(player, gameMap.at(40, 11));

		world.run();

	}
}
