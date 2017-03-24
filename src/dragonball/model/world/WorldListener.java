package dragonball.model.world;


import java.io.Serializable;
import java.util.EventListener;

import dragonball.model.cell.Collectible;
import dragonball.model.character.fighter.NonPlayableFighter;

public interface WorldListener extends EventListener,Serializable{
	public void onFoeEncountered(NonPlayableFighter foe);
    public void onCollectibleFound(Collectible collectible);
}
