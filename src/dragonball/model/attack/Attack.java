package dragonball.model.attack;

import java.io.Serializable;

import dragonball.model.battle.BattleOpponent;
import dragonball.model.character.fighter.Fighter;
import dragonball.model.character.fighter.Saiyan;
import dragonball.model.exceptions.NotEnoughKiException;


abstract public class Attack implements Serializable{
	private String name;
	private int damage;

	public Attack(String name, int damage) {
		this.name = name;
		this.damage = damage;
	}

	public String getName() {
		return name;
	}

	public int getDamage() {
		return damage;
	}

	abstract public int getAppliedDamage(BattleOpponent attacker);

	public void onUse(BattleOpponent attacker, BattleOpponent defender,
			boolean defenderBlocking) throws NotEnoughKiException { // here
		// blocking
		// changed
		int damage = getAppliedDamage(attacker);
		if (attacker instanceof Saiyan && ((Saiyan) attacker).isTransformed())
			damage += damage * 0.25;
		if (defenderBlocking) {
			while (((Fighter) defender).getStamina() != 0 && damage != 0) {
				if (damage >= 100) {
					((Fighter) defender).setStamina(((Fighter) defender)
							.getStamina() - 1);
					damage -= 100;
				} else if (damage != 0) {
					((Fighter) defender).setStamina(((Fighter) defender)
							.getStamina() - 1);
					damage = 0;
				}
			}
			if (damage != 0)
				((Fighter) defender).setHealthPoints(((Fighter) defender)
						.getHealthPoints() - damage);
		} else
			((Fighter) defender).setHealthPoints(((Fighter) defender)
					.getHealthPoints() - damage);
	}
}
