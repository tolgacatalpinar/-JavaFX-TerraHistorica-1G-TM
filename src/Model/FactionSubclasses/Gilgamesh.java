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
        spadeNeededToTerraformPlains = 0;
        spadeNeededToTerraformPlains = 2;
        spadeNeededToTerraformSwamp = 3;
        spadeNeededToTerraformLakes = 3;
        spadeNeededToTerraformForest = 2;
        spadeNeededToTerraformMountains = 1;
        spadeNeededToTerraformDesert = 1;

      }

}
