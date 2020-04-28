package Model;
import java.io.Serializable;
public class Faction implements Serializable {

    public String TERRAIN_TILE;
    public int MAX_SHIPPING = 3;
    public int INITIAL_VICTORY_POINT = 19;
    public int INITIAL_WORKER  = 3;
    public int INITIAL_WORKER_INCOME = 1;
    public int INITIAL_BOWL_ONE_POWER = 5;
    public int INITIAL_BOWL_TWO_POWER = 7;
    public int INITIAL_BOWL_THREE_POWER = 0;
    public int INITIAL_ISLAM = 0;
    public int INITIAL_CHRISTIANITY = 0;
    public int INITIAL_JUDAISM = 0;
    public int INITIAL_HINDUISM = 0;
    public int INITIAL_SHIPPING_LEVEL = 0;
    public int INITIAL_PRIEST = 0;
    public int INITIAL_GOLD = 15;
    public int INITIAL_SPADE = 0;
    public int SPADE_PRIEST_COST = 1;
    public int SPADE_GOLD_COST = 5;
    public int SPADE_WORKER_COST = 2;
    public int SPADE_FIRST_UPGRADE_VICTORY = 6;
    public int SPADE_SECOND_UPGRADE_VICTORY = 6;
    public int SHIPPING_PRIEST_COST = 1;
    public int SHIPPING_GOLD_COST = 4;
    public int SHIPPING_UPGRADE_VICTORY_POINTS[] = {2,3,4,0};
    public int STRONGHOLD_WORKER_COST = 4;
    public int STRONGHOLD_GOLD_COST = 6;
    //public int STRONGHOLD_POWER_INCOME = 2;
    //public int STRONGHOLD_PRIEST_INCOME = 1;
    public int SANCTUARY_WORKER_COST = 4;
    public int SANCTUARY_GOLD_COST = 10;
    public int SANCTUARY_PRIEST_INCOME = 1;
    public int TRADING_POST_WORKER_COST = 2;
    public int TRADING_POST_GOLD_COST = 6;
    public int tradingPostGoldIncome[] = {2,2,2,2};
    public int tradingPostPowerIncome[] = {1,1,2,2};
    public int TEMPLE_WORKER_COST = 2;
    public int TEMPLE_GOLD_COST = 5;
    public int TEMPLE_PRIEST_INCOME = 0;
    public int DWELLING_WORKER_COST = 1;
    public int DWELLING_GOLD_COST = 2;
    public int DWELLING_WORKER_INCOME = 1;
    public int TERRAFORM_WORKER_COST = 3;
    public int rangeOfSkipTile = 0;
    public int additionalWorkerOnTunneling = 0;
    public int remainingSkipRiverChanceOnTown = 0;
    public int startingDwellingNum = 2;
    public int favorTilesAfterBuildingTemple = 1;
    public boolean canPlayDoubleTurn;
    public int spadeNeededToTerraformPlains;
    public int spadeNeededToTerraformSwamp = 0;
    public int spadeNeededToTerraformLakes = 0;
    public int spadeNeededToTerraformForest = 0;
    public int spadeNeededToTerraformMountains = 0;
    public int spadeNeededToTerraformWasteland = 0;
    public int spadeNeededToTerraformDesert = 0;
    public int freeSpadesToTerraformIntoHome;
    public int priestNeededToSkipTile;
    public boolean freeTerraFormOnSpecialAction;
    public boolean gainFavorTileAfterStronghold;
    public boolean gainActionTokenAfterStronghold;
    public int advanceOnCultTrackValue;
    public int additionalVictoryPoints;
    public boolean payNoCostForDwelling;
    public boolean tradeVictoryPointForCoin;
    public boolean tradeCoinsForVictoryPoint;
    public int getPowerAfterStronghold;
    public int gainPowerForEachSpade;
    public int payPriestGetVictoryPoints;
    public boolean payPriestWhenTransform;
    public boolean giveWorkersForPriest;
    public int additionalVictoryPointForEachSpace;
    public int getVictoryPointsAfterStronghold;
    public int workerCostOfBridge;
    public int victoryPointForEachConnectingBridges;
    public boolean hasShipping = true;

    public void afterStronghold() {

    }



}
