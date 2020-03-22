package Model.FactionSubclasses;

import Model.Faction;
import Model.Player;

public class VladTheImpaler extends Faction { // chaos magician
    public VladTheImpaler() {
        INITIAL_WORKER = 4;
        INITIAL_HINDUISM = 2; //fire
        startingDwellingNum = 1;
        DWELLING_GOLD_COST = 2;
        DWELLING_WORKER_COST = 1;
        DWELLING_WORKER_INCOME = 1;
        SANCTUARY_GOLD_COST = 8;
        STRONGHOLD_GOLD_COST = 4;
        favorTilesAfterBuildingTemple = 2;
        spadeNeededToTerraformPlains = 2;
        spadeNeededToTerraformSwamp = 3;
        spadeNeededToTerraformLakes = 3;
        spadeNeededToTerraformForest = 2;
        spadeNeededToTerraformMountains = 1;
        spadeNeededToTerraformDesert = 1;
    }


}
