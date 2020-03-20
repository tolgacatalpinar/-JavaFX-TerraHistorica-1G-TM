package Model.FactionSubclasses;

import Model.Faction;

public class Buddha extends Faction {  // cultist
  
  public Buddha()
  {
        INITIAL_HINDUISM = 1; // fire
        INITIAL_JUDAISM = 1;  //earth
        INITIAL_VICTORY_POINT = 16;
        INITIAL_DWELLING_NUMBER = 2;
        DWELLING_GOLD_COST = 2;
        DWELLING_WORKER_COST = 1;
        DWELLING_WORKER_INCOME = 1;
  }
}
