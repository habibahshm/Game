package dragonball.model.player;

import java.io.Serializable;
import java.util.*;

import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.SuperSaiyan;
import dragonball.model.attack.UltimateAttack;
import dragonball.model.character.fighter.*;
import dragonball.model.dragon.DragonWish;
import dragonball.model.dragon.DragonWishType;
import dragonball.model.exceptions.DuplicateAttackException;
import dragonball.model.exceptions.InvalidAssignAttackException;
import dragonball.model.exceptions.MaximumAttacksLearnedException;
import dragonball.model.exceptions.NotASaiyanException;
import dragonball.model.exceptions.NotEnoughAbilityPointsException;
import dragonball.model.exceptions.NotEnoughResourcesException;

@SuppressWarnings("unused")
public class Player implements Serializable{
	private String name;
	private ArrayList<PlayableFighter> fighters;
	private ArrayList<SuperAttack> superAttacks;
	private ArrayList<UltimateAttack> ultimateAttacks;
	private int senzuBeans;
	private int dragonBalls;
	private PlayableFighter activeFighter;
	private int exploredMaps;
	private PlayerListener playerListener;

	public Player(String name) {
		this(name, new ArrayList<PlayableFighter>(),
				new ArrayList<SuperAttack>(), new ArrayList<UltimateAttack>(),
				0, 0, null, 0);
	}

	public Player(String name, ArrayList<PlayableFighter> fighters,
			ArrayList<SuperAttack> superAttacks,
			ArrayList<UltimateAttack> ultimateAttacks, int senzuBeans,
			int dragonBalls, PlayableFighter activeFighter, int exploredMaps) {
		this.name = name;
		this.fighters = fighters;
		this.superAttacks = superAttacks;
		this.ultimateAttacks = ultimateAttacks;
		this.senzuBeans = senzuBeans;
		this.dragonBalls = dragonBalls;
		this.activeFighter = activeFighter;
		this.exploredMaps = exploredMaps;
	}

	public int getMaxFighterLevel() {
		int max = 0;
		for (int i = 0; i < fighters.size(); i++)
			max = Math.max(max, (fighters.get(i)).getLevel());
		return max;
	}

	public void callDragon() {
		if (playerListener != null)
			playerListener.onDragonCalled();
	}

	public void chooseWish(DragonWish wish) {
		switch (wish.getType()) {
		case SENZU_BEANS:
			senzuBeans += wish.getSenzuBeans();
			break;
		case ABILITY_POINTS:
			activeFighter.setAbilityPoints(wish.getAbilityPoints()
					+ activeFighter.getAbilityPoints());
			break;
		case SUPER_ATTACK:
			superAttacks.add(wish.getSuperAttack());
			break;
		case ULTIMATE_ATTACK:
			ultimateAttacks.add(wish.getUltimateAttack());
			break;
		}
		if (playerListener != null)
			playerListener.onWishChosen(wish);
	}

	public void createFighter(char race, String name) {
		PlayableFighter x = null;
		switch (race) {
		case 'E':
			x = new Earthling(name);
			break;
		case 'S':
			x = new Saiyan(name);
			break;
		case 'N':
			x = new Namekian(name);
			break;
		case 'F':
			x = new Frieza(name);
			break;
		case 'M':
			x = new Majin(name);
			break;
		}
		fighters.add(x);
		if (fighters.size() == 1)
			activeFighter = x;
	}

	public void upgradeFighter(PlayableFighter fighter, char fighterAttribute)
			throws NotEnoughAbilityPointsException {
		if ((fighter.getAbilityPoints()) == 0)
			throw new NotEnoughAbilityPointsException();
		else {
			fighter.setAbilityPoints(fighter.getAbilityPoints() - 1);
			switch (fighterAttribute) {
			case 'H':
				fighter.setMaxHealthPoints(fighter.getMaxHealthPoints() + 50);
				break;
			case 'B':
				fighter.setBlastDamage(fighter.getBlastDamage() + 50);
				break;
			case 'P':
				fighter.setPhysicalDamage(fighter.getPhysicalDamage() + 50);
				break;
			case 'K':
				fighter.setMaxKi(fighter.getMaxKi() + 1);
				break;
			case 'S':
				fighter.setMaxStamina(fighter.getMaxStamina() + 1);
				break;
			}
		}
	}

	public void assignAttack(PlayableFighter fighter, SuperAttack newAttack,
			SuperAttack oldAttack) throws MaximumAttacksLearnedException,DuplicateAttackException {
		
			if ((fighter.getSuperAttacks()).contains(newAttack))
				throw new DuplicateAttackException(newAttack);
			else if (oldAttack != null) {
				fighter.getSuperAttacks().remove(oldAttack);
				fighter.getSuperAttacks().add(newAttack);
			} else if (fighter.getSuperAttacks().size() < 4)
				fighter.getSuperAttacks().add(newAttack);
			else
				throw new MaximumAttacksLearnedException();
		
	}

	public void assignAttack(PlayableFighter fighter, UltimateAttack newAttack,
			UltimateAttack oldAttack) throws MaximumAttacksLearnedException,DuplicateAttackException ,NotASaiyanException {
		if ((fighter.getUltimateAttacks()).contains(newAttack))
			throw new DuplicateAttackException(newAttack);
		if (newAttack instanceof SuperSaiyan && !(fighter instanceof Saiyan))
			throw new NotASaiyanException();
		if (oldAttack != null) {
			fighter.getUltimateAttacks().remove(oldAttack);
			fighter.getUltimateAttacks().add(newAttack);
		} else if (fighter.getUltimateAttacks().size() < 2)
			fighter.getUltimateAttacks().add(newAttack);
		else
			throw new MaximumAttacksLearnedException();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<PlayableFighter> getFighters() {
		return fighters;
	}

	public void setFighters(ArrayList<PlayableFighter> fighters) {
		this.fighters = fighters;
	}

	public ArrayList<SuperAttack> getSuperAttacks() {
		return superAttacks;
	}

	public void setSuperAttacks(ArrayList<SuperAttack> superAttacks) {
		this.superAttacks = superAttacks;
	}

	public ArrayList<UltimateAttack> getUltimateAttacks() {
		return ultimateAttacks;
	}

	public void setUltimateAttacks(ArrayList<UltimateAttack> ultimateAttacks) {
		this.ultimateAttacks = ultimateAttacks;
	}

	public int getSenzuBeans() {
		return senzuBeans;
	}

	public void setSenzuBeans(int senzuBeans) {
		this.senzuBeans = senzuBeans;
	}

	public int getDragonBalls() {
		return dragonBalls;
	}

	public void setDragonBalls(int dragonBalls) {
		this.dragonBalls = dragonBalls;
	}

	public PlayableFighter getActiveFighter() {
		return activeFighter;
	}

	public void setActiveFighter(PlayableFighter activeFighter) {
		this.activeFighter = activeFighter;
	}

	public int getExploredMaps() {
		return exploredMaps;
	}

	public void setExploredMaps(int exploredMaps) {
		this.exploredMaps = exploredMaps;
	}

	public PlayerListener getListener() {
		return playerListener;
	}

	public void setListener(PlayerListener playerListener) {
		this.playerListener = playerListener;
	}

}