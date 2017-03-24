package dragonball.model.exceptions;

@SuppressWarnings("serial")
public class NotEnoughKiException extends NotEnoughResourcesException {
	private int availableKi;
	private  int requiredKi;
	
	 public NotEnoughKiException(int requiredKi, int availableKi){
		 super("no enough Ki, you required "+ requiredKi +" Ki but you only have "+availableKi +"Ki");
		 this.requiredKi = requiredKi;
		 this.availableKi = availableKi;
	 }
	 
	public int getAvailableKi() {
		return availableKi;
	}
	public int getRequiredKi() {
		return requiredKi;
	}
}
