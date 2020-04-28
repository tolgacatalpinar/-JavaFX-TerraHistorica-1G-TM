package Model.FactionSubclasses;

import Model.Faction;

public class Buddha extends Faction {  // cultist
  
  public Buddha()
  {
      TERRAIN_TILE = "Plains";
        INITIAL_HINDUISM = 1; // fire
        INITIAL_JUDAISM = 1;  //earth
        INITIAL_VICTORY_POINT = 16;
        DWELLING_GOLD_COST = 2;
        DWELLING_WORKER_COST = 1;
        DWELLING_WORKER_INCOME = 1;
        SANCTUARY_GOLD_COST = 8;
        STRONGHOLD_GOLD_COST = 8;
      spadeNeededToTerraformSwamp = 1;
      spadeNeededToTerraformLakes = 2;
      spadeNeededToTerraformForest = 3;
      spadeNeededToTerraformMountains = 3;
      spadeNeededToTerraformWasteland = 2;
      spadeNeededToTerraformDesert = 1;

  }
}
