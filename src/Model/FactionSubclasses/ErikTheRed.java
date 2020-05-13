package Model.FactionSubclasses;

import Model.Faction;
import Model.Player;

public class ErikTheRed extends Faction {

      public ErikTheRed() {
            setInitials();
      }

      public void setInitials() {
            TERRAIN_TILE = "Lakes";
            INITIAL_WORKER = 8;
            INITIAL_GOLD = 20;
            INITIAL_HINDUISM = 1; // fire
            INITIAL_ISLAM = 1;  //water
            INITIAL_JUDAISM = 1;  //earth
            INITIAL_CHRISTIANITY = 1; //air
            INITIAL_BOWL_ONE_POWER = 3;
            INITIAL_BOWL_TWO_POWER = 9;
            INITIAL_VICTORY_POINT = 22;
            DWELLING_GOLD_COST = 3;
            DWELLING_WORKER_COST = 2;
            DWELLING_WORKER_INCOME = 2;
            TRADING_POST_GOLD_COST = 8;
            SANCTUARY_WORKER_COST = 5;
            STRONGHOLD_WORKER_COST = 5;
            SANCTUARY_GOLD_COST = 8;
            SANCTUARY_WORKER_COST = 8;
            TEMPLE_GOLD_COST = 3;
            TEMPLE_WORKER_COST = 6;
            tradingPostGoldIncome = new int[]{2, 2, 2, 3};
            tradingPostPowerIncome = new int[]{2, 2, 2, 2};
            spadeNeededToTerraformPlains = 2;
            spadeNeededToTerraformSwamp = 1;
            spadeNeededToTerraformForest = 1;
            spadeNeededToTerraformMountains = 2;
            spadeNeededToTerraformWasteland = 3;
            spadeNeededToTerraformDesert = 3;
            foundingTownWorkerBonus = 3;
      }

      public void afterStronghold() {
            actionToken_dwelling_to_trading = true;
      }
}