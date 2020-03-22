package Model.FactionSubclasses;

import Model.Faction;
import Model.Player;

public class LeonardoDaVinci extends Faction { //Engineer
  
  public LeonardoDaVinci() {
     INITIAL_WORKER = 2;
     INITIAL_GOLD = 10;
     INITIAL_BOWL_ONE_POWER = 3;
     INITIAL_BOWL_TWO_POWER = 9;
     INITIAL_VICTORY_POINT = 16;
     INITIAL_WORKER_INCOME = 0;
     DWELLING_GOLD_COST = 1;
     DWELLING_WORKER_COST = 1;
     DWELLING_WORKER_INCOME = 1;
     TRADING_POST_WORKER_COST = 1;
     TRADING_POST_WORKER_COST = 4;
     SANCTUARY_WORKER_COST = 3;
     STRONGHOLD_WORKER_COST = 3;
     TEMPLE_GOLD_COST = 4;
     TEMPLE_WORKER_COST = 1;
     spadeNeededToTerraformPlains = 3;
     spadeNeededToTerraformSwamp = 3;
     spadeNeededToTerraformLakes = 2;
     spadeNeededToTerraformForest = 1;
     spadeNeededToTerraformWasteland = 1;
     spadeNeededToTerraformDesert = 2;

  }



}
