package Model.FactionSubclasses;

import Model.Faction;

public class HelenOfTroy extends Faction { // auren
 
  public HelenOfTroy()
  {
     setInitials();
  }
  public void setInitials() {
      TERRAIN_TILE = "Forest";
      INITIAL_ISLAM = 1;  //water
      INITIAL_CHRISTIANITY = 1; //air
      INITIAL_VICTORY_POINT = 27;
      DWELLING_GOLD_COST = 2;
      DWELLING_WORKER_COST = 1;
      DWELLING_WORKER_INCOME = 1;
      SANCTUARY_GOLD_COST = 8;
      spadeNeededToTerraformPlains = 0;
      spadeNeededToTerraformSwamp = 0;
      spadeNeededToTerraformLakes = 0;
      spadeNeededToTerraformForest = 0;
      spadeNeededToTerraformMountains = 0;
      spadeNeededToTerraformWasteland = 0;
      spadeNeededToTerraformDesert = 0;
  }

    public void afterStronghold() {
        advanceOnCultTrackValue = 2;
    }
}
