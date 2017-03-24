package dragonball.model.dragon;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.UltimateAttack;

public class Dragon implements Serializable{
	private String name;
	private ArrayList<SuperAttack> superAttacks;
	private ArrayList<UltimateAttack> ultimateAttacks;
	private int senzuBeans;
	private int abilityPoints;

	public Dragon(String name, ArrayList<SuperAttack> superAttacks,
			ArrayList<UltimateAttack> ultimateAttacks, int senzuBeans,
			int abilityPoints) {
		this.name =name;
		this.superAttacks=superAttacks;
		this.ultimateAttacks = ultimateAttacks;
        this.senzuBeans = senzuBeans;
        this.abilityPoints = abilityPoints;
	}

	public String getName() {
		return name;
	}

	public DragonWish[] getWishes(){
		DragonWish [] wishList = new DragonWish[4];
		wishList[0] = senzuBeans != 0 ? new DragonWish(this, DragonWishType.SENZU_BEANS, senzuBeans) : null;
		wishList[1] = abilityPoints != 0 ? new DragonWish(this, DragonWishType.ABILITY_POINTS,abilityPoints) : null;
		int s = new Random().nextInt(superAttacks.size());
		wishList[2] = superAttacks != null ? new DragonWish(this, DragonWishType.SUPER_ATTACK, superAttacks.get(s)) : null;
		s = new Random().nextInt(superAttacks.size());
		wishList[3] = ultimateAttacks != null ? new DragonWish(this, DragonWishType.ULTIMATE_ATTACK, ultimateAttacks.get(s)) : null;
		return wishList;
		
	}
	public ArrayList<SuperAttack> getSuperAttacks() {
		return superAttacks;
	}

	public ArrayList<UltimateAttack> getUltimateAttacks() {
		return ultimateAttacks;
	}

	public int getSenzuBeans() {
		return senzuBeans;
	}

	public int getAbilityPoints() {
		return abilityPoints;
	}

}
