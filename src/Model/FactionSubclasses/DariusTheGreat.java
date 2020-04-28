package Model.FactionSubclasses;

import Model.Faction;

public class DariusTheGreat extends Faction { //nomad
         
        public DariusTheGreat ()
       {
           TERRAIN_TILE = "Desert";
        INITIAL_WORKER = 2;
        INITIAL_HINDUISM = 1; // fire
        INITIAL_JUDAISM = 1;  //earth
        startingDwellingNum = 3;
        DWELLING_GOLD_COST = 2;
        DWELLING_WORKER_COST = 1;
        DWELLING_WORKER_INCOME = 1;
        STRONGHOLD_GOLD_COST = 8;
        tradingPostGoldIncome = new int[]{2, 2, 3, 4};
        tradingPostPowerIncome = new int[]{1, 1, 1, 1};
           spadeNeededToTerraformPlains = 1;
           spadeNeededToTerraformSwamp = 2;
           spadeNeededToTerraformLakes = 3;
           spadeNeededToTerraformForest = 3;
           spadeNeededToTerraformMountains = 2;
           spadeNeededToTerraformWasteland = 1;

       }
}
