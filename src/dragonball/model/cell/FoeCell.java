package dragonball.model.cell;



import dragonball.model.character.fighter.*;

public class FoeCell extends Cell {
	private NonPlayableFighter foe;

	public FoeCell( NonPlayableFighter f) {
		foe = f;
	}

	public String toString() {
		if(foe.isStrong())
			return "[b]";
		else
			return "[w]";
	}

	public NonPlayableFighter getFoe() {
		return foe;
	}
	
	public void onStep() {
		if(this.getCellListener()!=null)
			(this.getCellListener()).onFoeEncountered(foe);
	}
}
