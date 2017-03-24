package dragonball.model.battle;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import dragonball.model.attack.Attack;
import dragonball.model.attack.PhysicalAttack;
import dragonball.model.cell.Collectible;
import dragonball.model.character.fighter.*;
import dragonball.model.exceptions.NotEnoughKiException;
import dragonball.model.exceptions.NotEnoughSenzuBeansException;
import dragonball.model.player.Player;

public class Battle implements Serializable{
	private BattleOpponent me, foe, attacker;
	private boolean meBlocking, foeBlocking;
	private BattleListener Listener;

	public void setListener(BattleListener listener) {
		Listener = listener;
	}

	public Battle(BattleOpponent me, BattleOpponent foe) {
		this.me = me;
		this.foe = foe;
		this.attacker = me;
		// casting me
		Fighter fm = (Fighter) me;
		fm.setHealthPoints(fm.getMaxHealthPoints());
		fm.setStamina(fm.getMaxStamina());
		fm.setKi(0);
		// casting foe
		Fighter ff = (Fighter) foe;
		ff.setHealthPoints(ff.getMaxHealthPoints());
		ff.setStamina(ff.getMaxStamina());
		ff.setKi(0);
		// setting transformation state
		if (fm instanceof Saiyan && ((Saiyan) fm).isTransformed())
			((Saiyan) fm).setTransformed(false);
		if (ff instanceof Saiyan && ((Saiyan) ff).isTransformed())
			((Saiyan) ff).setTransformed(false);

	}

	public ArrayList<Attack> getAssignedAttacks() {
		ArrayList<Attack> attacks = new ArrayList<Attack>();
		attacks.add(new PhysicalAttack());
		attacks.addAll(((Fighter) attacker).getSuperAttacks());
		attacks.addAll(((Fighter) attacker).getUltimateAttacks());
		return attacks;

	}

	public void attack(Attack attack)throws NotEnoughKiException{
		if (attacker instanceof PlayableFighter) {
			attack.onUse(attacker, foe, foeBlocking);
		} else if (attacker instanceof NonPlayableFighter) {
			attack.onUse(attacker, me, meBlocking);
		}
		BattleEvent ev = new BattleEvent(this, BattleEventType.ATTACK, attack);
		if (Listener != null)
			Listener.onBattleEvent(ev);
		endTurn();
	}

	public void block() {
		if (attacker instanceof PlayableFighter) {
			meBlocking = true;
		} else if (attacker instanceof NonPlayableFighter) {
			foeBlocking = true;
		}
		BattleEvent e = new BattleEvent(this, BattleEventType.BLOCK);
		if (Listener != null)
			Listener.onBattleEvent(e);
		this.endTurn();
	}

	public void use(Player player, Collectible collectible) throws NotEnoughSenzuBeansException{
		if (collectible.equals(Collectible.SENZU_BEAN) && player.getSenzuBeans() > 0) {
			(player.getActiveFighter()).setHealthPoints((player.getActiveFighter()).getMaxHealthPoints());
			(player.getActiveFighter()).setStamina(((player.getActiveFighter()).getMaxStamina()));
			player.setSenzuBeans(player.getSenzuBeans() - 1);
			BattleEvent e = new BattleEvent(this, BattleEventType.USE, collectible);
			if (Listener != null)
				Listener.onBattleEvent(e);
			this.endTurn();
		}else
			throw new NotEnoughSenzuBeansException();
	}

	public BattleOpponent getDefender() {
		if (attacker instanceof PlayableFighter)
			return foe;
		return me;
	}

	public void play() throws NotEnoughKiException{
		int type = new Random().nextInt(4);
		if (type == 0)
			this.block();
		else if (type == 1 && (((Fighter) foe).getSuperAttacks()) != null
				&& (((Fighter) foe).getSuperAttacks()).size() > 0) {
			int idx = new Random().nextInt((((Fighter) foe).getSuperAttacks()).size());
			this.attack((((Fighter) foe).getSuperAttacks()).get(idx));
		} else if (type == 2 && (((Fighter) foe).getUltimateAttacks()) != null
				&& (((Fighter) foe).getUltimateAttacks()).size() > 0) {
			int idx = new Random().nextInt((((Fighter) foe).getUltimateAttacks()).size());
			this.attack((((Fighter) foe).getUltimateAttacks()).get(idx));
		} else
			this.attack(new PhysicalAttack());
	}

	public void start() {
		BattleEvent e = new BattleEvent(this, BattleEventType.STARTED);
		if (this.Listener != null)
			this.Listener.onBattleEvent(e);
	}

	public void endTurn (){
		if (((Fighter) this.getDefender()).getHealthPoints() == 0) {
			BattleEvent e = new BattleEvent(this, BattleEventType.ENDED, attacker);
			if (Listener != null)
				Listener.onBattleEvent(e);
		} else {
			if (attacker instanceof PlayableFighter) {
				me.onAttackerTurn();
				foe.onDefenderTurn();
				foeBlocking = false;
				switchTurn();

			} else {
				foe.onAttackerTurn();
				me.onDefenderTurn();
				meBlocking = false;
				switchTurn();
			}

			BattleEvent e = new BattleEvent(this, BattleEventType.NEW_TURN);
			if (Listener != null)
				Listener.onBattleEvent(e);
		}
	}

	public void switchTurn() {
		if (attacker instanceof PlayableFighter)
			attacker = foe;
		else if (attacker instanceof NonPlayableFighter)
			attacker = me;
	}

	public BattleOpponent getMe() {
		return me;
	}

	public BattleOpponent getFoe() {
		return foe;
	}

	public boolean isMeBlocking() {
		return meBlocking;
	}

	public BattleOpponent getAttacker() {
		return attacker;
	}

	

	public boolean isFoeBlocking() {
		return foeBlocking;
	}

}
