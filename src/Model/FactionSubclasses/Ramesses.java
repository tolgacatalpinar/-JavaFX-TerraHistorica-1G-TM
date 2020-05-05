package Model.FactionSubclasses;

import Model.Faction;

public class Ramesses extends Faction { // FAKİR
  
  public Ramesses()
  {
      TERRAIN_TILE = "Desert";
        INITIAL_HINDUISM = 1; // fire
        INITIAL_CHRISTIANITY = 1; //air
        INITIAL_VICTORY_POINT = 33;
        DWELLING_GOLD_COST = 2;
        DWELLING_WORKER_COST = 1;
        DWELLING_WORKER_INCOME = 1;
        STRONGHOLD_GOLD_COST = 10;
        SPADE_SECOND_UPGRADE_VICTORY = 0;
        INITIAL_BOWL_ONE_POWER = 7;
        INITIAL_BOWL_TWO_POWER = 5;
      SHIPPING_UPGRADE_VICTORY_POINTS = new int[]{};
      spadeNeededToTerraformPlains = 1;
      spadeNeededToTerraformSwamp = 2;
      spadeNeededToTerraformLakes = 3;
      spadeNeededToTerraformForest = 3;
      spadeNeededToTerraformMountains = 2;
      spadeNeededToTerraformWasteland = 1;
      hasShipping = false;
      rangeOfSkipTile = 1;
      priestNeededToSkipTile = 1;
      additionalVictoryPointForEachSkipTile = 4;
      skipTileWhenCalculatingLongestPath = true;
  }

    @Override
    public void afterStronghold() {
        super.afterStronghold();
        rangeOfSkipTile = 2;
    }
}
