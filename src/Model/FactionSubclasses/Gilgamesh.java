package Model.FactionSubclasses;

import Model.Faction;

public class Gilgamesh extends Faction {
      public Gilgamesh() {
        TERRAIN_TILE = "Wasteland";
        INITIAL_HINDUISM = 1; // fire
        INITIAL_CHRISTIANITY = 1; //air
        INITIAL_VICTORY_POINT = 25;
        DWELLING_WORKER_COST = 1;
        DWELLING_WORKER_INCOME = 1;
        spadeNeededToTerraformPlains = 2;
        spadeNeededToTerraformSwamp = 2;
        spadeNeededToTerraformLakes = 2;
        spadeNeededToTerraformForest = 2;
        spadeNeededToTerraformMountains = 2;
        spadeNeededToTerraformDesert = 2;

      }

  @Override
  public void afterStronghold() {
    super.afterStronghold();
    freeSpadesToTerraformIntoHome =2;
  }
}
