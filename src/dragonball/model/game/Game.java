package dragonball.model.game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;

import dragonball.model.attack.Attack;
import dragonball.model.attack.MaximumCharge;
import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.SuperSaiyan;
import dragonball.model.attack.UltimateAttack;
import dragonball.model.battle.Battle;
import dragonball.model.battle.BattleEvent;
import dragonball.model.battle.BattleEventType;
import dragonball.model.battle.BattleListener;
import dragonball.model.battle.BattleOpponent;
import dragonball.model.cell.Collectible;
import dragonball.model.cell.EmptyCell;
import dragonball.model.character.fighter.Fighter;
import dragonball.model.character.fighter.NonPlayableFighter;
import dragonball.model.character.fighter.PlayableFighter;
import dragonball.model.dragon.Dragon;
import dragonball.model.dragon.DragonWish;
import dragonball.model.exceptions.MissingFieldException;
import dragonball.model.exceptions.UnknownAttackTypeException;
import dragonball.model.player.Player;
import dragonball.model.player.PlayerListener;
import dragonball.model.world.World;
import dragonball.model.world.WorldListener;

@SuppressWarnings({ "unused", "serial" })
public class Game implements PlayerListener, BattleListener, WorldListener, Serializable {
	private Player player;
	private World world;
	private ArrayList<NonPlayableFighter> strongFoes;
	private ArrayList<NonPlayableFighter> weakFoes;
	private ArrayList<Attack> attacks;
	private ArrayList<Dragon> dragons;
	private GameState state;
	private transient GameListener gameListener;
	private String savedGame;
	// private ArrayList<String> savedGames;

	// public ArrayList<String> getSavedGames() {
	// return savedGames;
	// }

	public GameListener getGameListener() {
		return gameListener;
	}

	public void setListener(GameListener gameListener) {
		this.gameListener = gameListener;
	}

	public Game() throws MissingFieldException, UnknownAttackTypeException {
		try {
			loadAttacks("Database-Attacks.csv");
		} catch (MissingFieldException a1) {
			loadAttacks("Database-Attacks-aux.csv");
		} catch (UnknownAttackTypeException a4) {
			loadAttacks("Database-Attacks-aux.csv");
		}

		try {
			loadFoes("Database-Foes-Range1.csv");
		} catch (MissingFieldException m) {
			loadFoes("Database-Foes-aux.csv");
		}

		try {
			loadDragons("Database-Dragons.csv");
		} catch (MissingFieldException m1) {
			loadDragons("Database-Dragons-aux.csv");
		}

		world = new World();
		world.setListener(this);
		world.generateMap(weakFoes, strongFoes);
		state = GameState.WORLD;
		player = new Player("PEPA");
		player.setListener(this);

	}

	public void save(String fileName) {
		try {
			FileOutputStream out = new FileOutputStream(fileName);
			savedGame = fileName;
			// savedGames.add(fileName);
			ObjectOutputStream save = new ObjectOutputStream(out);
			save.writeObject(this);
			out.close();
			save.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void load(String fileName) {
		try {
			FileInputStream in = new FileInputStream(fileName);
			ObjectInputStream load = new ObjectInputStream(in);
			Game loaded = (Game) load.readObject();
			this.world = loaded.world;
			this.player = loaded.player;
			this.weakFoes = loaded.weakFoes;
			this.strongFoes = loaded.strongFoes;
			this.gameListener = loaded.gameListener;
			this.state = loaded.state;
			this.attacks = loaded.attacks;
			this.dragons = loaded.dragons;
			this.savedGame = loaded.savedGame;
			world.setListener(this);
			player.setListener(this);
			for (int i = 0; i < 10; i++)
				for (int j = 0; j < 10; j++)
					getWorld().getMap()[i][j].setListener(this.world);
			in.close();
			load.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private ArrayList<String> loadCSV(String filePath) // reading database
	{
		ArrayList<String> data = new ArrayList<String>();
		String currentLine = "";
		try {
			FileReader fileReader = new FileReader(filePath);
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(fileReader);
			while ((currentLine = br.readLine()) != null) {
				data.add(currentLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}

	public void loadAttacks(String filePath) throws MissingFieldException, UnknownAttackTypeException { // load
		// attacks
		attacks = new ArrayList<Attack>();
		ArrayList<String> process = loadCSV(filePath);
		for (int i = 0; i < process.size(); i++) {
			String l = process.get(i);
			int n = new StringTokenizer(l, ",").countTokens();
			if (n < 3) {
				attacks.clear();
				String m = (3 - n) + " fields are missing in file " + filePath + " at line" + (i + 1);
				throw new MissingFieldException(m, filePath, i + 1, 3 - n);
			} else {
				String[] line = l.split(",");
				if (line[0].equals("SA"))
					attacks.add(new SuperAttack(line[1], Integer.parseInt(line[2])));
				else if (line[0].equals("UA"))
					attacks.add(new UltimateAttack(line[1], Integer.parseInt(line[2])));
				else if (line[0].equals("MC"))
					attacks.add(new MaximumCharge());
				else if (line[0].equals("SS"))
					attacks.add(new SuperSaiyan());
				else
					throw new UnknownAttackTypeException("unknown type in file " + filePath + " at line " + (i + 1),
							filePath, i + 1, line[0]);
			}
		}
	}

	private ArrayList<SuperAttack> intializeSuperAttacks( // searching for super
															// attacks in
															// attacks
			String[] foesSuperAttacks) {
		ArrayList<SuperAttack> Sattacks = new ArrayList<SuperAttack>();
		for (int j = 0; j < foesSuperAttacks.length; j++) {
			String name = foesSuperAttacks[j];
			for (int k = 0; k < attacks.size(); k++) {
				Attack a = attacks.get(k);
				if (name.equals(a.getName())) {
					if (a instanceof SuperAttack || a instanceof MaximumCharge)
						Sattacks.add((SuperAttack) a);
				}
			}
		}
		return Sattacks;
	}

	private ArrayList<UltimateAttack> intializeUltimateattacks( // searching for
																// ultimate
																// attacks in
																// attacks
			String[] foesUltimateAttacks) {
		ArrayList<UltimateAttack> Uattacks = new ArrayList<UltimateAttack>();
		for (int j = 0; j < foesUltimateAttacks.length; j++) {
			String name = foesUltimateAttacks[j];
			for (int k = 0; k < attacks.size(); k++) {
				Attack a = attacks.get(k);
				if (name.equals(a.getName())) {
					if (a instanceof UltimateAttack || a instanceof SuperSaiyan)
						Uattacks.add((UltimateAttack) a);
				}
			}
		}
		return Uattacks;

	}

	public void loadFoes(String filePath) throws MissingFieldException { // load
																			// foes

		weakFoes = new ArrayList<NonPlayableFighter>();
		strongFoes = new ArrayList<NonPlayableFighter>();
		ArrayList<SuperAttack> foeS;
		ArrayList<UltimateAttack> foeU;
		ArrayList<String> process = loadCSV(filePath);
		for (int i = 0; i < process.size(); i += 3) {
			String[] foesData = (process.get(i)).split(",");
			int n = new StringTokenizer(process.get(i), ",").countTokens();
			if (n < 8) {
				String m = (8 - n) + " fields are missing in file " + filePath + " at line" + (i + 1);
				weakFoes.clear();
				strongFoes.clear();
				throw new MissingFieldException(m, filePath, i + 1, 8 - n);
			}

			if ((process.get(i + 1)).length() != 0) {
				String[] foesSuperAttacks = (process.get(i + 1)).split(",");
				foeS = intializeSuperAttacks(foesSuperAttacks);
			} else
				foeS = new ArrayList<SuperAttack>();

			if ((process.get(i + 2)).length() != 0) {
				String[] foesUltimateAttacks = (process.get(i + 2)).split(",");
				foeU = intializeUltimateattacks(foesUltimateAttacks);
			} else
				foeU = new ArrayList<UltimateAttack>();

			NonPlayableFighter x = new NonPlayableFighter(foesData[0], Integer.parseInt(foesData[1]),
					Integer.parseInt(foesData[2]), Integer.parseInt(foesData[3]), Integer.parseInt(foesData[4]),
					Integer.parseInt(foesData[5]), Integer.parseInt(foesData[6]), Boolean.parseBoolean(foesData[7]),
					foeS, foeU);

			if (x.isStrong())
				strongFoes.add(x);
			else
				weakFoes.add(x);
		}

	}

	public void loadDragons(String filePath) throws MissingFieldException { // load
		// dragons
		dragons = new ArrayList<Dragon>();
		ArrayList<String> process = loadCSV(filePath);
		for (int i = 0; i < process.size(); i += 3) {
			String[] dragonsData = (process.get(i)).split(",");
			int n = new StringTokenizer(process.get(i), ",").countTokens();
			if (n < 3) {
				String m = (3 - n) + " fields are missing in file " + filePath + " at line" + (i + 1);
				dragons.clear();
				throw new MissingFieldException(m, filePath, i + 1, 3 - n);
			} else {
				String[] dragonSattack = (process.get(i + 1)).split(",");
				String[] dragonUattack = (process.get(i + 2)).split(",");
				ArrayList<SuperAttack> dragonS = intializeSuperAttacks(dragonSattack);
				ArrayList<UltimateAttack> dragonU = intializeUltimateattacks(dragonUattack);
				dragons.add(new Dragon(dragonsData[0], dragonS, dragonU, Integer.parseInt(dragonsData[1]),
						Integer.parseInt(dragonsData[2])));
			}
		}
	}

	public void onDragonCalled() {
		int idx = new Random().nextInt(dragons.size());
		if (gameListener != null) {
			gameListener.onDragonCalled(dragons.get(idx));
		}
		state = GameState.DRAGON;
	}

	public void onFoeEncountered(NonPlayableFighter foe) {
		Battle be = new Battle(player.getActiveFighter(), foe);
		be.setListener(this);
		be.start();
	}

	public void onCollectibleFound(Collectible collectible) {
		if (collectible.equals(Collectible.SENZU_BEAN))
			player.setSenzuBeans(player.getSenzuBeans() + 1);
		else if (collectible.equals(Collectible.DRAGON_BALL)) {
			player.setDragonBalls(player.getDragonBalls() + 1);
			if (player.getDragonBalls() == 7) {
				player.setDragonBalls(0);
				player.callDragon();
			}
			state = GameState.DRAGON;
		}
		if (gameListener != null)
			gameListener.onCollectibleFound(collectible);

	}

	public void onBattleEvent(BattleEvent e) {

		if (e.getType().equals(BattleEventType.STARTED))
			state = GameState.BATTLE;
		else if (e.getType().equals(BattleEventType.ENDED)) {
			state = GameState.WORLD;
			if (e.getWinner() instanceof PlayableFighter) {
				world.getMap()[world.getPlayerRow()][world.getPlayerColumn()] = new EmptyCell();
				NonPlayableFighter f = (NonPlayableFighter) (((Battle) e.getSource()).getFoe());
				player.getActiveFighter().setXp(player.getActiveFighter().getXp() + (5 * f.getLevel()));
				for (int i = 0; i < f.getSuperAttacks().size(); i++) {
					SuperAttack s = f.getSuperAttacks().get(i);
					if (!player.getSuperAttacks().contains(s))
						player.getSuperAttacks().add(s);
				}
				for (int i = 0; i < f.getUltimateAttacks().size(); i++) {
					UltimateAttack s = f.getUltimateAttacks().get(i);
					if (!player.getUltimateAttacks().contains(s))
						player.getUltimateAttacks().add(s);
				}
				if (f.isStrong()) {
					for (int i = 0; i < 10; i++)
						for (int j = 0; j < 10; j++)
							world.getMap()[i][j] = null;
					int foesRange = (player.getMaxFighterLevel() - 1) / 10 + 1;
					try {
						loadFoes("." + File.separator + "Database-Foes-Range" + foesRange + ".csv");
					} catch (MissingFieldException t) {
						strongFoes.clear();
						weakFoes.clear();
						try {
							loadFoes("Database-Dragons-aux.csv");
						} catch (MissingFieldException h) {
							h.getMessage();
						}

					}
					world.generateMap(weakFoes, strongFoes);
					world.resetPlayerPosition();
					player.setExploredMaps(player.getExploredMaps() + 1);
				}
			} else {
				if (savedGame != null && savedGame.length() > 0) {
					this.load(savedGame);
				} else {
					for (int i = 0; i < 10; i++)
						for (int j = 0; j < 10; j++)
							world.getMap()[i][j] = null;
					world.generateMap(weakFoes, strongFoes);
					world.resetPlayerPosition();
				}
			}

		}
		if (gameListener != null)
			gameListener.onBattleEvent(e);
	}

	public void onWishChosen(DragonWish wish) {
		state = GameState.WORLD;
	}

	public Player getPlayer() {
		return player;
	}

	public World getWorld() {
		return world;
	}

	public ArrayList<NonPlayableFighter> getStrongFoes() {
		return strongFoes;
	}

	public ArrayList<NonPlayableFighter> getWeakFoes() {
		return weakFoes;
	}

	public ArrayList<Attack> getAttacks() {
		return attacks;
	}

	public ArrayList<Dragon> getDragons() {
		return dragons;
	}

	public GameState getState() {
		return state;
	}

}
