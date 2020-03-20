package Model;

public class Faction {

    public int INITIAL_DWELLING_NUMBER = 2;
    public int INITIAL_VICTORY_POINT;
    public int INITIAL_WORKER  = 3;
    public int INITIAL_LEVEL_ONE_POWER = 5;
    public int INITIAL_LEVEL_TWO_POWER = 7;
    public int INITIAL_LEVEL_THREE_POWER = 0;
    public int INITIAL_ISLAM = 0;
    public int INITIAL_CHRISTIANITY = 0;
    public int INITIAL_JUDAISM = 0;
    public int INITIAL_HINDUISM = 0;
    public int INITIAL_SHIPPING = 0;
    public int INITIAL_PRIEST = 0;
    public int INITIAL_GOLD = 15;
    public int INITIAL_SPADE = 0;
    public final int MAX_DWELLING = 8;
    public int SPADE_PRIEST_COST = 0;
    public int SPADE_GOLD_COST = 0;
    public int SPADE_WORKER_COST = 0;
    public int SPADE_FIRST_UPGRADE_VICTORY = 0;
    public int SPADE_SECOND_UPGRADE_VICTORY = 0;
    public int SHIPPING_PRIEST_COST = 0;
    public int SHIPPING_GOLD_COST = 0;
    public int SHIPPING_FIRST_UPGRADE_VICTORY = 0;
    public int SHIPPING_SECOND_UPGRADE_VICTORY = 0;
    public int SHIPPING_THIRD_UPGRADE_VICTORY = 0;
    public int SHIPPING_FOURTH_UPGRADE_VICTORY = 0;
    public int STRONGHOLD_WORKER_COST = 0;
    public int STRONGHOLD_GOLD_COST = 0;
    public int STRONGHOLD_POWER_INCOME = 0;
    public int SANCTUARY_WORKER_COST = 0;
    public int SANCTUARY_GOLD_COST = 0;
    public int SANCTUARY_PRIEST_INCOME = 0;
    public int TRADING_POST_WORKER_COST = 0;
    public int TRADING_POST_GOLD_COST = 0;
    public int TEMPLE_WORKER_COST = 0;
    public int TEMPLE_GOLD_COST = 0;
    public int TEMPLE_PRIEST_INCOME = 0;
    public int DWELLING_WORKER_COST = 1;
    public int DWELLING_GOLD_COST = 2;
    public int DWELLING_WORKER_INCOME = 0;
    public int rangeOfSkipTile = 0;
    public int additionalWorkerOnTunneling = 0;
    public int remainingSkipRiverChanceOnTown = 0;
    public int startingDwellings = 0;
    public int favorTilesAfterBuildingTemple = 1;
    public boolean canPlayDoubleTurn;
    public int spadeNeededToTerraformPlains;
    public int spadeNeededToTerraformSwamp;
    public int spadeNeededToTerraformLakes;
    public int spadeNeededToTerraformForest;
    public int spadeNeededToTerraformMountains;
    public int spadeNeededToTerraformWasteland;
    public int spadeNeededToTerraformDesert;
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

    private int powerIncome;
    private int workerIncome;
    private int priestIncome;
    private int coinIncome;


    public void addCoinIncome(int value)
    {
        coinIncome += value;
    }

    public void addPower(int value)
    {
        powerIncome += value;
    }
    public void addWorker(int value)
    {
        workerIncome += value;
    }


    public int getPowerIncome() {
        return powerIncome;
    }

    public void setPowerIncome(int powerIncome) {
        this.powerIncome = powerIncome;
    }

    public int getWorkerIncome() {
        return workerIncome;
    }

    public void setWorkerIncome(int workerIncome) {
        this.workerIncome = workerIncome;
    }

    public int getPriestIncome() {
        return priestIncome;
    }

    public void setPriestIncome(int priestIncome) {
        this.priestIncome = priestIncome;
    }

    public int getCoinIncome() {
        return coinIncome;
    }

    public void setCoinIncome(int coinIncome) {
        this.coinIncome = coinIncome;
    }
}
