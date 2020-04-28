package Model.FactionSubclasses;

import Model.Faction;

public class AmerigoVespucci extends Faction { // mermaids
  
  public AmerigoVespucci()
  {
      TERRAIN_TILE = "Lakes";
        INITIAL_ISLAM = 2;  //water
        INITIAL_VICTORY_POINT = 19;
        INITIAL_BOWL_ONE_POWER = 3;
        INITIAL_BOWL_TWO_POWER = 9;
        INITIAL_SHIPPING_LEVEL = 1;
        INITIAL_WORKER_INCOME = 2;
        MAX_SHIPPING = 5;
        SHIPPING_UPGRADE_VICTORY_POINTS = new int[]{0, 2, 3, 4, 5};
        DWELLING_GOLD_COST = 2;
        DWELLING_WORKER_COST = 1;
        DWELLING_WORKER_INCOME = 1;
        SANCTUARY_GOLD_COST = 8;
      spadeNeededToTerraformPlains = 2;
      spadeNeededToTerraformSwamp = 1;
      spadeNeededToTerraformForest = 1;
      spadeNeededToTerraformMountains = 2;
      spadeNeededToTerraformWasteland = 3;
      spadeNeededToTerraformDesert = 3;

  }

}

