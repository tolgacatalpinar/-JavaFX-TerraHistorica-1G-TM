package Model;

public class Faction {

    int INITIAL_WORKER = 0;
    int INITIAL_POWER = 0;
    int INITIAL_ISLAM = 0;
    int INITIAL_CHRISTIANITY = 0;
    int INITIAL_JEWISH = 0;
    int INITIAL_BUDISM = 0;
    int INITIAL_SHIPPING = 0;
    int INITIAL_PRIEST = 0;
    int INITIAL_GOLD = 0;
    int INITIAL_SPADE = 0;
    final int MAX_DWELLING = 8;
    int SPADE_PRIEST_COST = 0;
    int SPADE_GOLD_COST = 0;
    int SPADE_WORKER_COST = 0;
    int SPADE_FIRST_UPGRADE_VICTORY = 0;
    int SPADE_SECOND_UPGRADE_VICTORY = 0;
    int SHIPPING_PRIEST_COST = 0;
    int SHIPPING_GOLD_COST = 0;
    int SHIPPING_FIRST_UPGRADE_VICTORY = 0;
    int SHIPPING_SECOND_UPGRADE_VICTORY = 0;
    int SHIPPING_THIRD_UPGRADE_VICTORY = 0;
    int SHIPPING_FOURTH_UPGRADE_VICTORY = 0;
    int STRONGHOLD_WORKER_COST = 0;
    int STRONGHOLD_GOLD_COST = 0;
    int STRONGHOLD_POWER_INCOME = 0;
    int SANCTUARY_WORKER_COST = 0;
    int SANCTUARY_GOLD_COST = 0;
    int SANCTUARY_PRIEST_INCOME = 0;
    int TRADING_POST_WORKER_COST = 0;
    int TRADING_POST_GOLD_COST = 0;
    int TEMPLE_WORKER_COST = 0;
    int TEMPLE_GOLD_COST = 0;
    int TEMPLE_PRIEST_INCOME = 0;
    int DWELLING_WORKER_COST = 0;
    int DWELLING_GOLD_COST = 0;
    int DWELLING_WORKER_INCOME = 0;
    int rangeOfSkipTile = 0;
    int additionalWorkerOnTunneling = 0;
    int remainingSkipRiverChanceOnTown = 0;
    int startingDwellings = 0;
    int favorTilesAfterBuildingTemple = 0;
    boolean canPlayDoubleTurn;
    int spadeNeededToTerraformPlains;
    int spadeNeededToTerraformSwamp;
    int spadeNeededToTerraformLakes;
    int spadeNeededToTerraformForest;
    int spadeNeededToTerraformMountains;
    int spadeNeededToTerraformWasteland;
    int spadeNeededToTerraformDesert;
    int freeSpadesToTerraformIntoHome;
    int priestNeededToSkipTile;
    boolean freeTerraFormOnSpecialAction;
    boolean gainFavorTileAfterStronghold;
    boolean gainActionTokenAfterStronghold;
    int advanceOnCultTrackValue;
    int additionalVictoryPoints;
    boolean payNoCostForDwelling;
    boolean tradeVictoryPointForCoin;
    boolean tradeCoinsForVictoryPoint;
    int getPowerAfterStronghold;
    int gainPowerForEachSpade;
    int payPriestGetVictoryPoints;
    boolean payPriestWhenTransform;
    boolean giveWorkersForPriest;
    int additionalVictoryPointForEachSpace;
    int getVictoryPointsAfterStronghold;
    int workerCostOfBridge;
    int victoryPointForEachConnectingBridges;

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
