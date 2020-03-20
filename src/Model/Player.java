package Model;

public class Player {

    String nickName;
    Faction faction;
    SpecialActionToken specialActionToken;
    private int playerId;
    private int levelOnePower;
    private int levelTwoPower;
    private int levelThreePower;
    private int workerNum;
    private int priestNum;
    private int goldNum;
    private int victoryPointNum;
    private int islamProgress;
    private int christianityProgress;
    private int hinduismProgress;
    private int judaismProgress;
    private int powerIncome;
    private int workerIncome;
    private int priestIncome;
    private int startingDwellingNum;
    private boolean key;
    private int bridgeNum;
    private int dwellingNum;
    private int tradingHouseNum;
    private int templeNum;
    private int sanctuaryNum;
    private int strongholdNum;
    private int spadeInventory;
    private int spadeLevel;
    private int shipLevel;
    private int religionTrackInventory;
    private int goldIncome;
    private int perBuildingIncome;
    private int cultBonusIncome;
    private boolean havingDwellingBonus;
    private boolean havingTradeHouse;
    private boolean havingSanctuary;


    public Player(String nickName, int playerId) {
        this.nickName = nickName;
        this.playerId = playerId;
        specialActionToken = new SpecialActionToken();

    }

    public void chooseFaction(Faction faction) {
        this.faction = faction;
        workerNum = this.getFaction().INITIAL_WORKER;
        goldNum = this.getFaction().INITIAL_GOLD;
        priestNum = this.getFaction().INITIAL_PRIEST;
        //Initial power which level?
        //addPowerToBowl(this.getFaction().INITIAL_POWER);
        startingDwellingNum = this.getFaction().INITIAL_DWELLING_NUMBER;
        hinduismProgress = this.getFaction().INITIAL_HINDUISM;
        islamProgress = this.getFaction().INITIAL_ISLAM;
        christianityProgress = this.getFaction().INITIAL_CHRISTIANITY;
        judaismProgress = this.getFaction().INITIAL_JUDAISM;
        dwellingNum = 0;
        tradingHouseNum = 0;
        templeNum = 0;
        sanctuaryNum = 0;
        strongholdNum = 0;
        key = false;
    }

    public Faction getFaction() {
        return faction;
    }


    public void buildBridge() {

    }

    /**
     * later
     * @param count
     */
    public void addPowerToBowl(int count) {

    }

    public void buildDwelling() {
        if (dwellingNum < this.faction.MAX_DWELLING ) {
            dwellingNum++;
            workerNum -= faction.DWELLING_WORKER_COST;
            workerIncome = faction.DWELLING_WORKER_INCOME;
        }
        else {
            System.out.println("Max dwelling reached");
        }
    }

    public void townFound() {
        key = true;
        //TODO
    }

    public void upgradeToSanctuary() {

        templeNum--;
        sanctuaryNum++;
        workerNum -= faction.SANCTUARY_WORKER_COST;
        goldNum -= faction.SANCTUARY_GOLD_COST;
        priestIncome = faction.SANCTUARY_PRIEST_INCOME;
    }

    public int upgradeToTemple() {
        tradingHouseNum--;
        templeNum++;
        workerNum -= faction.TEMPLE_WORKER_COST;
        goldNum -= faction.TEMPLE_GOLD_COST;
        priestIncome = faction.TEMPLE_PRIEST_INCOME;
        return faction.favorTilesAfterBuildingTemple;


    }

    public void upgradeToTradingHouse(boolean isThereAdjacentOpponent) {
        dwellingNum--;
        tradingHouseNum++;

        if(isThereAdjacentOpponent) {
            workerNum -= faction.TRADING_POST_WORKER_COST;
            goldNum  -= faction.TRADING_POST_GOLD_COST-3;
        }
        else {
            workerNum -= faction.TRADING_POST_WORKER_COST;
            goldNum  -= faction.TRADING_POST_GOLD_COST;
        }

    }

    public void upgradeToStronghold() {
        tradingHouseNum--;
        strongholdNum++;
        workerNum -= faction.STRONGHOLD_WORKER_COST;
        goldNum -= faction.STRONGHOLD_GOLD_COST;
        powerIncome = faction.STRONGHOLD_POWER_INCOME;
    }

    public void progressInReligion(Religion religion) {
        addPowerToBowl(religion.addOrderOfReligion(playerId,key));
    }

    public void sendPriest(Religion religion) {
        addPowerToBowl(religion.placePriest(playerId,key));
        priestNum--;
    }

    public void exchangeResource(String exchanges) {

        if(exchanges == "power for priest") {

        }
        if(exchanges == "power for worker") {

        }
        if(exchanges == "power for coin") {

        }
        if(exchanges == "priest to worker") {

        }
        if(exchanges == "worker to coin") {

        }
        if(exchanges == "") {

        }
        if(exchanges == "") {

        }
        if(exchanges == "") {

        }

    }



public void addVictoryPoints(int count) {
        victoryPointNum += count;

}
    public void terraform(Space space) {

    }

    public void upgradeSpadeLevel() {

    }

    public void upgradeShippingLevel() {

    }


    public int getLevelOnePower() {
        return levelOnePower;
    }

    public void setLevelOnePower(int levelOnePower) {
        this.levelOnePower = levelOnePower;
    }

    public int getLevelTwoPower() {
        return levelTwoPower;
    }

    public void setLevelTwoPower(int levelTwoPower) {
        this.levelTwoPower = levelTwoPower;
    }

    public int getLevelThreePower() {
        return levelThreePower;
    }

    public void setLevelThreePower(int levelThreePower) {
        this.levelThreePower = levelThreePower;
    }

    public int getWorkerNum() {
        return workerNum;
    }

    public void setWorkerNum(int workerNum) {
        this.workerNum = workerNum;
    }

    public int getPriestNum() {
        return priestNum;
    }

    public void setPriestNum(int priestNum) {
        this.priestNum = priestNum;
    }

    public int getGoldNum() {
        return goldNum;
    }

    public void setGoldNum(int goldNum) {
        this.goldNum = goldNum;
    }

    public int getPowerIncome() {
        return powerIncome;
    }

    public void setPowerIncome(int powerIncome) {
        this.powerIncome = powerIncome;
    }

    public int getVictoryPointNum() {
        return victoryPointNum;
    }

    public void setVictoryPointNum(int victoryPointNum) {
        this.victoryPointNum = victoryPointNum;
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

    public int getBridgeNum() {
        return bridgeNum;
    }

    public void setBridgeNum(int bridgeNum) {
        this.bridgeNum = bridgeNum;
    }

    public int getDwellingNum() {
        return dwellingNum;
    }

    public void setDwellingNum(int dwellingNum) {
        this.dwellingNum = dwellingNum;
    }

    public int getTradingPostNum() {
        return tradingHouseNum;
    }

    public void setTradingPostNum(int tradingHouseNum) {
        this.tradingHouseNum = tradingHouseNum;
    }

    public int getTempleNum() {
        return templeNum;
    }

    public void setTempleNum(int templeNum) {
        this.templeNum = templeNum;
    }

    public int getSanctuaryNum() {
        return sanctuaryNum;
    }

    public void setSanctuaryNum(int sanctuaryNum) {
        this.sanctuaryNum = sanctuaryNum;
    }

    public int getStrongholdNum() {
        return strongholdNum;
    }

    public void setStrongholdNum(int strongholdNum) {
        this.strongholdNum = strongholdNum;
    }

    public int getSpadeInventory() {
        return spadeInventory;
    }

    public void setSpadeInventory(int spadeInventory) {
        this.spadeInventory = spadeInventory;
    }

    public int getSpadeLevel() {
        return spadeLevel;
    }

    public void setSpadeLevel(int spadeLevel) {
        this.spadeLevel = spadeLevel;
    }

    public int getShipLevel() {
        return shipLevel;
    }


    public int getReligionTrackInventory() {
        return religionTrackInventory;
    }

    public void setReligionTrackInventory(int religionTrackInventory) {
        this.religionTrackInventory = religionTrackInventory;
    }

    public String getNickName() {
        return nickName;
    }

    public void setShipLevel(int shipLevel) {
        this.shipLevel = shipLevel;
    }

    public int getGoldIncome() {
        return goldIncome;
    }

    public void setGoldIncome(int goldIncome) {
        this.goldIncome = goldIncome;
    }

    public SpecialActionToken getSpecialActionToken() {
        return specialActionToken;
    }

    public void setSpecialActionToken(SpecialActionToken specialActionToken) {
        this.specialActionToken = specialActionToken;
    }


    public int getPlayerId() {
        return playerId;
    }
    public int getPerBuildingIncome() {
        return perBuildingIncome;
    }

    public void setPerBuildingIncome(int perBuildingIncome) {
        this.perBuildingIncome = perBuildingIncome;
    }

    public int getCultBonusIncome() {
        return cultBonusIncome;
    }

    public void setCultBonusIncome(int cultBonusIncome) {
        this.cultBonusIncome = cultBonusIncome;
    }

    public boolean isHavingDwellingBonus() {
        return havingDwellingBonus;
    }

    public void setHavingDwellingBonus(boolean havingDwellingBonus) {
        this.havingDwellingBonus = havingDwellingBonus;
    }

    public boolean isHavingTradeHouse() {
        return havingTradeHouse;
    }

    public void setHavingTradeHouse(boolean havingTradeHouse) {
        this.havingTradeHouse = havingTradeHouse;
    }

    public boolean isHavingSanctuary() {
        return havingSanctuary;
    }

    public void setHavingSanctuary(boolean havingSanctuary) {
        this.havingSanctuary = havingSanctuary;
    }



}
