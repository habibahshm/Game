package dragonball.model.cell;


import java.io.Serializable;
import java.util.EventListener;

import dragonball.model.character.fighter.NonPlayableFighter;

public interface CellListener extends EventListener,Serializable{
 public  void onFoeEncountered(NonPlayableFighter foe) ;
 public void onCollectibleFound(Collectible collectible);
}
