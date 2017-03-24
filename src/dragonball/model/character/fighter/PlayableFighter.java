package dragonball.model.character.fighter;

import java.io.Serializable;
import java.util.*;

import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.UltimateAttack;
import dragonball.model.character.PlayableCharacter;

abstract public class PlayableFighter extends Fighter implements PlayableCharacter,Serializable
{
	private int xp;
	private int targetXp;
	private int abilityPoints;

	//calls the constructor that creates for the first time.
	public PlayableFighter(String name, int maxHealthPoints, int blastDamage, int physicalDamage, int maxKi,
			int maxStamina) {
		this(name, maxHealthPoints, blastDamage, physicalDamage, maxKi, maxStamina, new ArrayList<SuperAttack>(),
				new ArrayList<UltimateAttack>());
	}
	// saved state
	public PlayableFighter(String name, int level, int xp, int targetXp, int maxHealthPoints, int blastDamage,
			int physicalDamage, int abilityPoints, int maxKi, int maxStamina, ArrayList<SuperAttack> superAttacks,
			ArrayList<UltimateAttack> ultimateAttacks) {
		super(name, level, maxHealthPoints, blastDamage, physicalDamage, maxKi, maxStamina, superAttacks,
				ultimateAttacks);
		this.xp = xp;
		this.targetXp = targetXp;
		this.abilityPoints = abilityPoints;

	}

	// create for the first time and calls the one the saved state.
	public PlayableFighter(String name, int maxHealthPoints, int blastDamage, int physicalDamage, int maxKi,
			int maxStamina, ArrayList<SuperAttack> superAttacks, ArrayList<UltimateAttack> ultimateAttacks) {
		this(name, 1, 0, 10, maxHealthPoints, blastDamage, physicalDamage, 0, maxKi, maxStamina, superAttacks,
				ultimateAttacks);
	}

	public int getXp() {
		return xp;
	}

	public void setXp(int xp) { // keep updating
		this.xp = xp;
		while (this.xp >= targetXp) {
			this.setLevel(this.getLevel() + 1);
			this.setAbilityPoints(this.getAbilityPoints() + 2);
			this.xp -= targetXp;
			targetXp += 20;
		}
	}

	public int getTargetXp() {
		return targetXp;
	}

	public void setTargetXp(int targetXp) {
		this.targetXp = targetXp;
	}

	public int getAbilityPoints() {
		return abilityPoints;
	}

	public void setAbilityPoints(int abilityPoints) {
		this.abilityPoints = abilityPoints;
	}
}
