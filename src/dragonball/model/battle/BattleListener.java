package dragonball.model.battle;


import java.io.Serializable;
import java.util.EventListener;

public interface BattleListener extends EventListener,Serializable{
   void onBattleEvent(BattleEvent e);
}
