package dragonball.model.exceptions;

@SuppressWarnings("serial")
public class MaximumAttacksLearnedException extends InvalidAssignAttackException{
	 public MaximumAttacksLearnedException(){
		 super("you have reached your maximum number of assigned attacks of super/ultimate attacks");
	 }
}
