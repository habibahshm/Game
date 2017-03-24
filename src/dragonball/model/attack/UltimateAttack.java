package dragonball.model.attack;

import java.io.Serializable;

import dragonball.model.battle.BattleOpponent;
import dragonball.model.character.fighter.Fighter;
import dragonball.model.character.fighter.Saiyan;
import dragonball.model.exceptions.NotEnoughKiException;

public class UltimateAttack extends Attack implements Serializable{
	public UltimateAttack(String name, int damage) {
		super(name, damage);
	}

	public int getAppliedDamage(BattleOpponent attacker) {
		return this.getDamage() + ((Fighter) attacker).getBlastDamage();
	}

	public void onUse(BattleOpponent attacker, BattleOpponent defender,
			boolean defenderBlocking) throws NotEnoughKiException{
		if (attacker instanceof Saiyan && ((Saiyan) attacker).isTransformed())
			super.onUse(attacker, defender, defenderBlocking);
		else if (((Fighter) attacker).getKi() >= 3) {
			super.onUse(attacker, defender, defenderBlocking);
			((Fighter) attacker).setKi(((Fighter) attacker).getKi() - 3);
		}else
			throw new NotEnoughKiException(3, ((Fighter)attacker).getKi());
	}
}
