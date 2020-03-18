package Model;

public class Player {

    String nickName;
    Faction faction;
    private int playerId;
    private int levelOnePower;
    private int levelTwoPower;
    private int workerNum;
    private int priestNum;
    private int goldNum;
    private int powerIncome;
    private int victoryPointNum;
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


    public Player(String nickName, int playerId) {
        this.nickName = nickName;
        this.playerId = playerId;
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



    public Faction getFaction() {
        return faction;
    }

    public void setFaction(Faction faction) {
        this.faction = faction;
        faction.setInitialResources(workerNum, priestNum, goldNum,);
    }
}
