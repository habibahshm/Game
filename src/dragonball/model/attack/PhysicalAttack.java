package dragonball.model.attack;

import dragonball.model.battle.BattleOpponent;
import dragonball.model.character.fighter.*;
import dragonball.model.exceptions.NotEnoughKiException;

public class PhysicalAttack extends Attack {
	public PhysicalAttack() {
		super("PhysicalAttack", 50);
	}

	public int getAppliedDamage(BattleOpponent attacker) {
		return 50 + ((Fighter) attacker).getPhysicalDamage();
	}

	public void onUse(BattleOpponent attacker, BattleOpponent defender,
			boolean defenderBlocking) throws NotEnoughKiException{
		super.onUse(attacker, defender, defenderBlocking);
		((Fighter) attacker).setKi(((Fighter) attacker).getKi() + 1);
	}
}
