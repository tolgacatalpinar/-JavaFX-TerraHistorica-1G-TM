package Model.FactionSubclasses;

import Model.Faction;
import Model.Player;

public class ErikTheRed extends Faction {
  
  public ErikTheRed()
  {
        INITIAL_WORKER = 8;
        INITIAL_GOLD = 20;
        INITIAL_PRIEST = 0;
        INITIAL_HINDUISM = 1; // fire
        INITIAL_ISLAM = 1;  //water
        INITIAL_JUDAISM = 1;  //earth     
        INITIAL_CHRISTIANITY = 1; //air
        INITIAL_VICTORY_POINT = 22;
        INITIAL_DWELLING_NUMBER = 2;
        DWELLING_GOLD_COST = 3;
        DWELLING_WORKER_COST = 2;
        DWELLING_WORKER_INCOME = 2;
  }




}
