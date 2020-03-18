package Model.CardsAndTiles;

public class TownTile {

    private int playerId;
    private int powerBonus;
    private int priestBonus;
    private int  keyCount;
    private int victoryBonus;
    private int christianityPoint;
    private int hinduismPoint;
    private int islamPoint;
    private int jewishPoint;
    private boolean isShippingBonus;
    private int workerBonus;
    private int goldBonus;

    /**
     *
     * @param powerBonus power bonus income.
     * @param priestBonus priest bonus income.
     * @param victoryBonus victory bonus income.
     * @param christianityPoint christianity bonus income.
     * @param hinduismPoint hinduism bonus income.
     * @param islamPoint  islam bonus income.
     * @param jewishPoint jewish bonus income.
     * @param isShippingBonus determine tiles's bonus is shipping bonus or not.
     * @param workerBonus worker bonus income.
     * @param goldBonus gold bonus income.
     */
    public TownTile(int powerBonus, int priestBonus, int victoryBonus, int christianityPoint, int hinduismPoint, int islamPoint, int jewishPoint, boolean isShippingBonus, int workerBonus, int goldBonus) {
        this.powerBonus = powerBonus;
        this.priestBonus = priestBonus;
        keyCount = 1;
        this.victoryBonus = victoryBonus;
        this.christianityPoint = christianityPoint;
        this.hinduismPoint = hinduismPoint;
        this.islamPoint = islamPoint;
        this.jewishPoint = jewishPoint;
        this.isShippingBonus = isShippingBonus;
        this.workerBonus = workerBonus;
        this.goldBonus = goldBonus;
        playerId = -1;
    }

    public int getPlayerId() {
        return playerId;
    }

    public int getPowerBonus() {
        return powerBonus;
    }

    public int getPriestBonus() {
        return priestBonus;
    }

    public int getKeyCount() {
        return keyCount;
    }

    public int getVictoryBonus() {
        return victoryBonus;
    }

    public int getChristianityPoint() {
        return christianityPoint;
    }

    public int getHinduismPoint() {
        return hinduismPoint;
    }

    public int getIslamPoint() {
        return islamPoint;
    }

    public int getJewishPoint() {
        return jewishPoint;
    }

    public boolean isShippingBonus() {
        return isShippingBonus;
    }

    public int getWorkerBonus() {
        return workerBonus;
    }

    public int getGoldBonus() {
        return goldBonus;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }
}
