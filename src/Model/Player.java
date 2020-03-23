package Model;

import Model.StructureSubclasses.Stronghold;

import java.security.PublicKey;

public class Player {

    String nickName;
    Faction faction;
    SpecialActionToken specialActionToken;
    private int playerId;
    private int bowlOnePower;
    private int bowlTwoPower;
    private int bowlThreePower;
    private int workerNum;
    private int priestNum;
    private int goldNum;
    private int victoryPointNum;
    private int powerIncome;
    private int workerIncome;
    private int priestIncome;
    private int startingDwellingNum;
    private int priestOnBank = 7; // Not exactly know what it is
    private boolean key;
    private int bridgeNum;
    private int dwellingNum;
    private int tradingPostNum;
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
    private boolean havingTradingPostBonus;
    private boolean havingSanctuaryBonus;
    private int terraformWorkerCost;
    private boolean roundPassed;

    private boolean upgradeToTradingPostBonus; //When upgrading a Dwelling to a Trading house, immediately get 3 additional Victory points.
    private boolean isPassingTradingPostBonus; //From now on, when passing (see Action #8, page 14), get 2/3/3/4 Victory points for 1/2/3/4 of your Trading houses on the Game board.
    private boolean buildingDwellingBonus; //When building a Dwelling, immediately get 2 additional Victory points.
    private int townPowerValue;

    public Player(String nickName, int playerId) {
        this.nickName = nickName;
        this.playerId = playerId;
        specialActionToken = new SpecialActionToken();

    }

    public void setFaction(Faction faction) {
        this.faction = faction;

    }

    public void initializeGame() {
        workerNum = this.getFaction().INITIAL_WORKER;
        goldNum = this.getFaction().INITIAL_GOLD;
        priestNum = this.getFaction().INITIAL_PRIEST;
        bowlOnePower = faction.INITIAL_BOWL_ONE_POWER;
        bowlTwoPower = faction.INITIAL_BOWL_TWO_POWER;
        bowlThreePower = faction.INITIAL_BOWL_THREE_POWER;
        startingDwellingNum = this.getFaction().startingDwellingNum;
        workerIncome = faction.INITIAL_WORKER_INCOME;
        terraformWorkerCost = faction.TERRAFORM_WORKER_COST;
        startingDwellingNum = faction.startingDwellingNum;
        townPowerValue = 7;
        powerIncome = 0;
        priestIncome = 0;
        goldIncome = 0;
        spadeLevel = 1;
        dwellingNum = 0;
        tradingPostNum = 0;
        templeNum = 0;
        sanctuaryNum = 0;
        strongholdNum = 0;
        key = false;
        upgradeToTradingPostBonus = false;
        isPassingTradingPostBonus = false;
        buildingDwellingBonus = false;
        roundPassed = false;
    }

    public Faction getFaction() {
        return faction;
    }


    public void addPowerToBowl(int powerGain) {
        for(int i = 0; i < powerGain; i++) {
            if (bowlOnePower>0) {
                bowlOnePower--;
                bowlTwoPower++;
            }
            else if ( bowlTwoPower>0) {
                bowlTwoPower--;
                bowlThreePower++;
            }
            else {
                System.out.println("Cannot gain further power");
            }
        }
    }

    /**
     *
     * @param powerSpent number value of power to be spent
     * @return true if power from bowl 3 is spent correctly
     *         false if not enough power in bowl three ( a bool to ask player for sacrificing power )
     */
    public boolean spendPowerFromBowl(int powerSpent) {
            if (bowlThreePower - powerSpent > 0) {
                bowlThreePower -= powerSpent;
                bowlOnePower += powerSpent;
            }
            else {
                System.out.println("Not enough power in bowl 3");
                return false;
            }
        return true;
    }

        /**
         * Sacrifice power from bowl two
         */
        public void sacrificePower(int powerToAddBowlThree) {
            if(powerToAddBowlThree*2 < bowlTwoPower) {
                bowlTwoPower -= powerToAddBowlThree*2;
                bowlThreePower += powerToAddBowlThree;
            }
            else {
                System.out.println("You don't have enough powers to sacrifice");
        }


    }


    public void buildDwelling() {
        if (dwellingNum < this.faction.MAX_DWELLING ) {
            dwellingNum++;
            workerNum -= faction.DWELLING_WORKER_COST;
            if ( dwellingNum < faction.MAX_DWELLING) {
                workerIncome += faction.DWELLING_WORKER_INCOME;
            }
            if(buildingDwellingBonus) {
                victoryPointNum += 2;
            }
        }
        else {
            System.out.println("Cannot build more");
        }
    }

    public void townFound() {
        key = true;
        //TODO
    }

    public void upgradeToSanctuary() {
        if (sanctuaryNum < this.faction.MAX_SANCTUARY ) {
            templeNum--;
            sanctuaryNum++;
            workerNum -= faction.SANCTUARY_WORKER_COST;
            goldNum -= faction.SANCTUARY_GOLD_COST;
            priestIncome = faction.SANCTUARY_PRIEST_INCOME;
        }
        else {
            System.out.println("Cannot build more");
        }
    }

    public int upgradeToTemple() {
        if (templeNum < this.faction.MAX_TEMPLE ) {
            tradingPostNum--;
            templeNum++;
            workerNum -= faction.TEMPLE_WORKER_COST;
            goldNum -= faction.TEMPLE_GOLD_COST;
            priestIncome = faction.TEMPLE_PRIEST_INCOME;
            return faction.favorTilesAfterBuildingTemple;
        }
        else {
            System.out.println("Cannot build more");
            return -1;
        }


    }

    public void upgradeToTradingPost(boolean isThereAdjacentOpponent) {

            if (tradingPostNum < faction.MAX_TRADING_POST) {

                dwellingNum--;
                tradingPostNum++;

                if (isThereAdjacentOpponent) {
                    workerNum -= faction.TRADING_POST_WORKER_COST;
                    goldNum -= faction.TRADING_POST_GOLD_COST/2;
                }
                else {
                    workerNum -= faction.TRADING_POST_WORKER_COST;
                    goldNum -= faction.TRADING_POST_GOLD_COST;
                }
                if (upgradeToTradingPostBonus) {
                    victoryPointNum += 3;
                }

                goldIncome += faction.tradingPostGoldIncome[tradingPostNum-1];
                powerIncome += faction.tradingPostPowerIncome[tradingPostNum-1];
            }
            else {
                System.out.println("Cannot build more");
            }
    }

    public void upgradeToStronghold() {
        if (strongholdNum < this.faction.MAX_STRONGHOLD ) {
            tradingPostNum--;
            strongholdNum++;
            workerNum -= faction.STRONGHOLD_WORKER_COST;
            goldNum -= faction.STRONGHOLD_GOLD_COST;
            faction.afterStronghold();
        }
        else {
            System.out.println("Cannot build more");
        }
    }

    public void progressInReligion(Religion religion) {
        addPowerToBowl(religion.addOrderOfReligion(playerId,key));
    }

    public void sendPriest(Religion religion) {
            if (priestNum > 0) {
                addPowerToBowl(religion.placePriest(playerId,key));
                priestOnBank--;
            }
            else {
                System.out.println("No priest available");
            }

    }

    public void usePowerAction(String action) {

        if(action == "power to priest") {
            if(spendPowerFromBowl(3)){
                priestNum++;
            }
        }

        if(action == "power to worker") {
            if(spendPowerFromBowl(4)){
                workerNum += 2;
            }
        }

        if(action == "power to bridge") {
            if(spendPowerFromBowl(3)){
                bridgeNum++;
            }
        }

        if (action == "power to coin") {
            if (spendPowerFromBowl(4)) {
                goldNum += 7;
            }
        }

        if (action == "power to a spade") {
            if (spendPowerFromBowl(4)) {
                //TODO
            }
        }

        if (action == "power to two spades") {
            if (spendPowerFromBowl(6)) {
                //TODO
            }
        }

        }


    public void exchangeResources(String exchanges) {

        if (exchanges == "priest to worker") {
            if (priestNum > 0) {
                workerNum++;
            }
        }

        if (exchanges == "worker to coin") {
            if (workerNum > 0) {
                workerNum--;
                goldNum++;
            }
        }

        if (exchanges == "sacrifice power") {
            sacrificePower(1);
        }

        if (exchanges == "power to coin") {
            if (spendPowerFromBowl(1)) {
                goldNum++;
            }
        }

        if (exchanges == "power to worker") {
            if (spendPowerFromBowl(1)) {
                workerNum++;
            }
        }

        if (exchanges == "power to priest") {
            if (spendPowerFromBowl(5)) {
                workerNum++;
            }
        }
    }

    public void acceptPowerFromAdjacentOpponent(int powerVal) {
        victoryPointNum -= powerVal - 1;
        addPowerToBowl(powerVal);

    }


    public void addVictoryPoints(int count) {
        victoryPointNum += count;

}
    public void terraform(Space space) {

    }

    public void upgradeSpadeLevel() {
            if(spadeLevel < 3) {
                spadeLevel++;
                terraformWorkerCost--;
                priestNum -= faction.SPADE_PRIEST_COST;
            }
            else {
                System.out.println("Max spade level");
            }

    }

    public void upgradeShippingLevel() {
            if(shipLevel < faction.MAX_SHIPPING) {
                shipLevel++;
                addVictoryPoints(faction.SHIPPING_UPGRADE_VICTORY_POINTS[shipLevel-1]);
                //check if resources are enough
                priestNum -= faction.SHIPPING_PRIEST_COST;
                goldNum -= faction.SHIPPING_GOLD_COST;
            }

    }

    public boolean haveResources(int requiredGold, int requiredPower, int requiredPriest, int requiredWorker ) {
            return false;
    }

    public void passRound() {
            roundPassed = true;
        if( isPassingTradingPostBonus) {
            if(tradingPostNum == 1) {
                victoryPointNum += 2;
            }
            if(tradingPostNum == 2) {
                victoryPointNum += 3;
            }
            if (tradingPostNum == 3) {
                victoryPointNum +=3;
            }
            if(tradingPostNum == 4) {
                victoryPointNum+=4;
            }
        }
        returnBonusCard();

    }

    public void useBonusFromFavorTiles() {

    }

    public void returnBonusCard() {
            //Collect bonus points
        if(roundPassed) {
            if (havingDwellingBonus) {
                victoryPointNum += dwellingNum * 1;
            }
            if (havingTradingPostBonus) {
                victoryPointNum += tradingPostNum * 2;
            }
            if (havingSanctuaryBonus) {
                victoryPointNum += strongholdNum * 4;
                victoryPointNum += sanctuaryNum * 4;
            }
            havingSanctuaryBonus = false;
            havingDwellingBonus = false;
            havingTradingPostBonus = false;
        }
    }

    public void useFavorTile() {

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

    public boolean getKey(){return key;}

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
        return tradingPostNum;
    }

    public void setTradingPostNum(int tradingPostNum) {
        this.tradingPostNum = tradingPostNum;
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


    public void setHavingTradeHouse(boolean havingTradeHouse) {
        havingTradingPostBonus = havingTradeHouse;
    }


    public void setHavingSanctuary(boolean value) {
            havingSanctuaryBonus = value;
    }

    public int getInitialIslam() {
            return faction.INITIAL_ISLAM;
    }

    public int getInitialChristianity() {
            return faction.INITIAL_CHRISTIANITY;
    }
    public int getInitialJudaism() {
        return faction.INITIAL_JUDAISM;
    }
    public int getInitialHinduism() {
        return faction.INITIAL_HINDUISM;
    }

    public boolean isUpgradeToTradingPostBonus() {
        return upgradeToTradingPostBonus;
    }

    public void setUpgradeToTradingPostBonus(boolean upgradeToTradingPostBonus) {
        this.upgradeToTradingPostBonus = upgradeToTradingPostBonus;
    }

    public boolean isPassingTradingPostBonus() {
        return isPassingTradingPostBonus;
    }

    public void setPassingTradingPostBonus(boolean passingTradingPostBonus) {
        isPassingTradingPostBonus = passingTradingPostBonus;
    }

    public boolean isBuildingDwellingBonus() {
        return buildingDwellingBonus;
    }

    public void setBuildingDwellingBonus(boolean buildingDwellingBonus) {
        this.buildingDwellingBonus = buildingDwellingBonus;
    }

    public int getTownPowerValue() {
        return townPowerValue;
    }

    public void setTownPowerValue(int townPowerValue) {
        this.townPowerValue = townPowerValue;
    }

    public void setKey(boolean key) {
        this.key = key;
    }
}
