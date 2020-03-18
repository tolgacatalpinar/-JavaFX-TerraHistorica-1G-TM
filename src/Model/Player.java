package Model;

public class Player {

    String nickName;
    Faction faction;
    SpecialActionToken specialActionToken;
    private int playerId;
    private int levelOnePower;
    private int levelTwoPower;
    private int workerNum;
    private int priestNum;
    private int goldNum;
    private int victoryPointNum;
    private int powerIncome;
    private int workerIncome;
    private int priestIncome;
    private int keyNum;
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



    public Player(String nickName, int playerId) {
        this.nickName = nickName;
        this.playerId = playerId;
        specialActionToken = new SpecialActionToken();
    }

    public void setFaction(Faction faction) {
        this.faction = faction;
        faction.setInitialResources(this);
    }

    public Faction getFaction() {
        return faction;
    }



    public void buildBridge() {

    }

    public void buildDwelling() {

    }

    public void upgradeToSanctuary() {

    }

    public void upgradeToTemple() {

    }

    public void upgradeToTradingPost() {
        faction.addWorker(2);
        faction.addCoinIncome(1);
    }

    public void progressInReligion(Religion religion) {

    }

    public void exchangeResource(String exchanges) {

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

    public int getKeyNum() {
        return keyNum;
    }

    public void setKeyNum(int keyNum) {
        this.keyNum = keyNum;
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




}
