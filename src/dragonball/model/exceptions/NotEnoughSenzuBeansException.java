package dragonball.model.exceptions;

@SuppressWarnings("serial")
public class NotEnoughSenzuBeansException extends NotEnoughResourcesException {
	 public NotEnoughSenzuBeansException(){
		 super("Not enough senzuBeans");
	 }
}
