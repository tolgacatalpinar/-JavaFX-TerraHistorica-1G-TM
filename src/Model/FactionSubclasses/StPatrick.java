package Model.FactionSubclasses;

import Model.Faction;

public class StPatrick extends Faction { //dwarwes
  
  public StPatrick ()
  {
    setInitials();
  }
  public void setInitials() {
      TERRAIN_TILE = "Mountains";
      INITIAL_JUDAISM = 2;  //earth
      INITIAL_VICTORY_POINT = 20;
      DWELLING_GOLD_COST = 2;
      DWELLING_WORKER_COST = 1;
      DWELLING_WORKER_INCOME = 1;
      tradingPostGoldIncome = new int[]{3, 2, 2, 3};
      spadeNeededToTerraformPlains = 0;
      spadeNeededToTerraformPlains = 3;
      spadeNeededToTerraformSwamp = 3;
      spadeNeededToTerraformLakes = 2;
      spadeNeededToTerraformForest = 1;
      spadeNeededToTerraformWasteland = 1;
      spadeNeededToTerraformDesert = 2;
      hasShipping = false;
      rangeOfSkipTile = 1;
      additionalWorkerOnTunneling = 2;
      additionalVictoryPointForEachSkipTile = 4;
      skipTileWhenCalculatingLongestPath = true;
  }

    public void afterStronghold() {
        additionalWorkerOnTunneling = 1;
    }
}
