package Model;

import Model.StructureSubclasses.Stronghold;

import java.security.PublicKey;
import java.io.Serializable;

public class Player implements Serializable {

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
   private int key;
   private int bridgeNum;
   private int dwellingNum;
   private int tradingPostNum;
   private int templeNum;
   private int sanctuaryNum;
   private int strongholdNum;
   private int freeSpade;
   private int spadeLevel;
   private int shipLevel;
   private int religionTrackInventory;
   private int goldIncome;
   private int perBuildingIncome;
   private int cultBonusIncome;
   private int numOfFavorTile;


   private boolean havingDwellingBonus;
   private boolean havingTradingPostBonus;
   private boolean havingSanctuaryBonus;
   private int terraformWorkerCost;
   private boolean roundPassed;
   private boolean upgradeToTradingPostBonus; //When upgrading a Dwelling to a Trading house, immediately get 3 additional Victory points.
   private boolean isPassingTradingPostBonus; //From now on, when passing (see Action #8, page 14), get 2/3/3/4 Victory points for 1/2/3/4 of your Trading houses on the Game board.
   private boolean buildingDwellingBonus; //When building a Dwelling, immediately get 2 additional Victory points.
   private int townPowerValue;

   public Player(Faction faction, String nickName, int playerId) {
      this.nickName = nickName;
      this.playerId = playerId;
      freeSpade = 0;
      specialActionToken = new SpecialActionToken(); //Naci
      this.faction = faction;
      setWorkerNum(getFaction().INITIAL_WORKER);
      setGoldNum(getFaction().INITIAL_GOLD);
      setPriestNum(getFaction().INITIAL_PRIEST);
      setBowlOnePower(getFaction().INITIAL_BOWL_ONE_POWER);
      setBowlTwoPower(getFaction().INITIAL_BOWL_TWO_POWER);
      setBowlThreePower(getFaction().INITIAL_BOWL_THREE_POWER);
      setStartingDwellingNum(getFaction().startingDwellingNum);
      setWorkerIncome(getFaction().INITIAL_WORKER_INCOME);
      setTerraformWorkerCost(getFaction().TERRAFORM_WORKER_COST);
      setVictoryPointNum(getFaction().INITIAL_VICTORY_POINT);
      setShipLevel(getFaction().INITIAL_SHIPPING_LEVEL);
      setStartingDwellingNum(getFaction().startingDwellingNum);
      setTownPowerValue(7);
      setPowerIncome(0);
      setPriestIncome(0);
      setGoldIncome(0);
      setSpadeLevel(1);
      setDwellingNum(0);
      setTradingPostNum(0);
      setTempleNum(0);
      setSanctuaryNum(0);
      setStrongholdNum(0);
      setNumOfFavorTile(0);
      setKey(0);
      setUpgradeToTradingPostBonus(false);
      setIsPassingTradingPostBonus(false);
      setBuildingDwellingBonus(false);
      setRoundPassed(false);
   }

   public Faction getFaction() {
      return faction;
   }

   public boolean addPowerToBowl(int powerGain) {
      for (int i = 0; i < powerGain; i++) {
         if (bowlOnePower > 0) {
            bowlOnePower--;
            bowlTwoPower++;
         } else if (bowlTwoPower > 0) {
            bowlTwoPower--;
            bowlThreePower++;
         } else {
            System.out.println("Cannot gain further power");
            return false;
         }
      }
      return true;
   }

   /**
    * @param powerSpent number value of power to be spent
    * @return true if power from bowl 3 is spent correctly
    * false if not enough power in bowl three ( a bool to ask player for sacrificing power )
    */
   public boolean spendPowerFromBowl(int powerSpent) {
      if (bowlThreePower - powerSpent > 0) {
         bowlThreePower -= powerSpent;
         bowlOnePower += powerSpent;
      } else {
         System.out.println("Not enough power in bowl 3");
         return false;
      }
      return true;
   }

   /**
    * Sacrifice power from bowl two

   public boolean sacrificePower() {

   }*/

   public void spendPriest(int priestCount) {
      priestNum -= priestCount;
      priestOnBank += priestCount;
   }

   public void gainPriest(int priestCount) {
      priestNum += priestCount;
      priestOnBank -= priestCount;
   }

   public void addVictoryPoints(int count) {
      victoryPointNum += count;

   }

   public boolean spendFreeSpade(String color) { //Spend free spade method

      int spadeNeeded = 0;
      if( color.equals("Wasteland")) {
         spadeNeeded = faction.spadeNeededToTerraformWasteland;
      }
      else if(color.equals("Swamp")) {
         spadeNeeded = faction.spadeNeededToTerraformSwamp;
      }
      else if (color.equals("Desert")) {
         spadeNeeded = faction.spadeNeededToTerraformDesert;
      }
      else if (color.equals("Plains")) {
         spadeNeeded = faction.spadeNeededToTerraformPlains;
      }
      else if (color.equals("Mountains")) {
         spadeNeeded = faction.spadeNeededToTerraformMountains;
      }
      else if (color.equals("Lakes")) {
         spadeNeeded = faction.spadeNeededToTerraformLakes;
      }
      else if (color.equals("Forest")) {
         spadeNeeded = faction.spadeNeededToTerraformForest;
      }
      else {
         System.out.println("Wrong color name");
         return false;
      }
      spadeNeeded -= freeSpade;
      if(faction.payPriestWhenTransform) {
         if(priestNum - spadeNeeded >= 0) {
            spendPriest(spadeNeeded);
            addVictoryPoints(2);
            return true;
         }
         return false;
      }
      if (workerNum - spadeNeeded*terraformWorkerCost >= 0) {
         workerNum -= spadeNeeded*terraformWorkerCost;
         return true;
      }
      System.out.println("Not enough resources");
      spadeNeeded += freeSpade;
      return false;
   }

   public boolean spendFromResources(int requiredWorker, int requiredGold, int requiredPriest) {
      if (workerNum - requiredWorker >= 0 && goldNum - requiredGold >= 0 && priestNum - requiredPriest >= 0) {
         workerNum -= requiredWorker;
         goldNum -= requiredGold;
         spendPriest(requiredPriest);
         return true;
      }
      return false;
   }


   public int getNumOfFavorTile() {
      return numOfFavorTile;
   }

   public void setNumOfFavorTile(int numOfFavorTile) {
      this.numOfFavorTile = numOfFavorTile;
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

   public int getKey() {
      return key;
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

   public void setKey(int key) {
      this.key = key;
   }

   public void setRoundPassed(boolean b) {
      roundPassed = b;
   }

   public void setIsPassingTradingPostBonus(boolean b) {
      isPassingTradingPostBonus = b;
   }

   public void setStartingDwellingNum(int startingDwellingNum) {
      this.startingDwellingNum = startingDwellingNum;
   }

   public void setTerraformWorkerCost(int terraform_worker_cost) {
      terraformWorkerCost = terraform_worker_cost;
   }

   public void setBowlOnePower(int bowlOnePower) {
      this.bowlOnePower = bowlOnePower;
   }

   public void setBowlTwoPower(int bowlTwoPower) {
      this.bowlTwoPower = bowlTwoPower;
   }

   public void setBowlThreePower(int bowlThreePower) {
      this.bowlThreePower = bowlThreePower;
   }

   public int getTerraformWorkerCost() {
      return terraformWorkerCost;
   }

   public int getBowlOnePower() {
      return bowlOnePower;
   }

   public int getBowlTwoPower() {
      return bowlTwoPower;
   }

   public int getBowlThreePower() {
      return bowlThreePower;
   }

   public int getStartingDwellingNum() {
      return startingDwellingNum;
   }

   public int getPriestOnBank() {
      return priestOnBank;
   }

   public boolean isHavingTradingPostBonus() {
      return havingTradingPostBonus;
   }

   public boolean isHavingSanctuaryBonus() {
      return havingSanctuaryBonus;
   }

   public boolean isRoundPassed() {
      return roundPassed;
   }

   public int getFreeSpade() {
      return freeSpade;
   }

   public void setFreeSpade(int freeSpade) {
      this.freeSpade = freeSpade;
   }

}
