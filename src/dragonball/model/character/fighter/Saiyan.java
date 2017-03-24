package dragonball.model.character.fighter;

import java.util.ArrayList;

import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.UltimateAttack;

public class Saiyan extends PlayableFighter {
	private boolean transformed;

	public Saiyan(String name) {
		super(name, 1000, 150, 100, 5, 3);
	}

	public Saiyan(String name, int level, int xp, int targetXp,
			int maxHealthPoints, int blastDamage, int physicalDamage,
			int abilityPoints, int maxKi, int maxStamina,
			ArrayList<SuperAttack> superAttacks,
			ArrayList<UltimateAttack> ultimateAttacks) {
		super(name, level, xp, targetXp, maxHealthPoints, blastDamage,
				physicalDamage, abilityPoints, maxKi, maxStamina, superAttacks,
				ultimateAttacks);
	}

	public boolean isTransformed() {
		return transformed;
	}

	public void setTransformed(boolean transformed) {
		this.transformed = transformed;
	}

	public void onAttackerTurn() {
		if (transformed) {
			this.setKi(this.getKi() - 1);
			this.setStamina(this.getStamina() + 1);
			if ((this.getKi()) == 0) {
				transformed = false;
				this.setStamina(0);
			}
		} else
			this.setStamina(this.getStamina() + 1);
	}

	public void onDefenderTurn() {
		if (transformed) {
			this.setKi(this.getKi() - 1);
			this.setStamina(this.getStamina() + 1);
			if ((this.getKi()) == 0) {
				transformed = false;
				this.setStamina(0);
			}
		} else
			this.setStamina(this.getStamina() + 1);
	}

}
