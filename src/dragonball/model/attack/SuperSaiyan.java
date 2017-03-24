package dragonball.model.attack;

import dragonball.model.battle.BattleOpponent;
import dragonball.model.character.fighter.Fighter;
import dragonball.model.character.fighter.Saiyan;
import dragonball.model.exceptions.NotEnoughKiException;

public class SuperSaiyan extends UltimateAttack {
	public SuperSaiyan() {
		super("Super Saiyan", 0);
	}

	public int getAppliedDamage(BattleOpponent attacker) {
		return 0;
	}

	public void onUse(BattleOpponent attacker, BattleOpponent defender, boolean defenderBlocking) throws NotEnoughKiException{
		if (attacker instanceof Saiyan) {
			if (((Fighter) attacker).getKi() >= 3) {
				((Saiyan) attacker).setTransformed(true);   
			}else
				throw new NotEnoughKiException(3, ((Fighter)attacker).getKi());
		
		}
	}
}
