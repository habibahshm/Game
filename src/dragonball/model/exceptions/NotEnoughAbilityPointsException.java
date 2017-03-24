package dragonball.model.exceptions;

@SuppressWarnings("serial")
public class NotEnoughAbilityPointsException extends NotEnoughResourcesException {
	public NotEnoughAbilityPointsException(){
		super("Not enough ability points");
	}
}
