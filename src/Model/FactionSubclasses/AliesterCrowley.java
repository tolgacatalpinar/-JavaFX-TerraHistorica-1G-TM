package Model.FactionSubclasses;

import Model.Faction;
import Model.Player;

public class AliesterCrowley extends Faction {
    //Darkling
    public AliesterCrowley() {
        setInitials();
    }
    public void setInitials() {
        TERRAIN_TILE = "Swamp";
        INITIAL_WORKER = 1;
        INITIAL_PRIEST = 1;
        INITIAL_ISLAM = 1;  //water
        INITIAL_JUDAISM = 1;  //earth
        INITIAL_VICTORY_POINT = 15;
        DWELLING_GOLD_COST = 2;
        DWELLING_WORKER_COST = 1;
        DWELLING_WORKER_INCOME = 1;
        SANCTUARY_GOLD_COST = 10;
        SANCTUARY_PRIEST_INCOME = 2;
        SPADE_FIRST_UPGRADE_VICTORY = 2;
        SPADE_GOLD_COST = 0;
        SPADE_WORKER_COST = 0;
        SPADE_SECOND_UPGRADE_VICTORY = 0;
        spadeNeededToTerraformPlains = 1;
        spadeNeededToTerraformLakes = 1;
        spadeNeededToTerraformForest = 2;
        spadeNeededToTerraformMountains = 3;
        spadeNeededToTerraformWasteland = 3;
        spadeNeededToTerraformDesert = 2;
        payPriestWhenTransform = true;
    }

    public void afterStronghold() {
        giveWorkersForPriest = true;
        increaseShippingAfterStronghold = true;
    }
}
