package dragonball.model.exceptions;

import dragonball.model.attack.Attack;

@SuppressWarnings("serial")
public class DuplicateAttackException extends InvalidAssignAttackException{
    private Attack attack;

    
    public DuplicateAttackException(Attack attack){
    	super("you already have the " +attack.getName()+" attack");
    	this.attack = attack;
    }
	public Attack getAttack() {
		return attack;
	}
}
