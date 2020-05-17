package Model.FactionSubclasses;

import Model.Faction;

public class MarieCurie extends Faction { //alchemist
  
  public MarieCurie()
  {
      setInitials();
  }
  public void setInitials() {
      TERRAIN_TILE = "Swamp";
      INITIAL_HINDUISM = 1; // fire
      INITIAL_ISLAM = 1;  //water
      INITIAL_VICTORY_POINT = 27;
      DWELLING_GOLD_COST = 2;
      DWELLING_WORKER_COST = 1;
      DWELLING_WORKER_INCOME = 1;
      tradingPostGoldIncome = new int[]{2, 2, 3, 4};
      spadeNeededToTerraformPlains = 1;
      spadeNeededToTerraformLakes = 1;
      spadeNeededToTerraformForest = 2;
      spadeNeededToTerraformMountains = 3;
      spadeNeededToTerraformWasteland = 3;
      spadeNeededToTerraformDesert = 2;
      tradeCoinsForVictoryPoint = true;
      tradeVictoryPointForCoin = true;
      sorcerersStone = true;
  }

    public void afterStronghold() {
        gainPowerForEachSpade = 2;
        getPowerAfterStronghold = 12;
    }
}
