package dragonball.model.cell;

public class CollectibleCell extends Cell {
	private Collectible collectible;

	public CollectibleCell(Collectible a) {
		collectible = a;
	}

	public String toString() {
		if(collectible.equals(Collectible.SENZU_BEAN))
			return "[s]";
		else
			return "[d]";
	}

	public Collectible getCollectible() {
		return collectible;
	}
    
	public void onStep(){
		if(this.getCellListener()!=null){
			(this.getCellListener()).onCollectibleFound(collectible);
		}		
	}
}
