package Model.CardsAndTiles;

public class BonusCard {
    private int notTakenBonus;
    private int playerId;
    private int goldBonus;
    private int powerBonus;
    private int shippingRange;
    private int workerBonus;
    private int priestBonus;
    private boolean spacialSpade;
    private boolean specialCult;
    private boolean isDwelling;
    private boolean isTradeHouse;
    private boolean isSanctuary;
    private boolean playerOcupied;

    /**
     *
     * @param notTakenBonus If card ware not taken previous game, it has more probability than other.
     * @param playerOccupied determines card is taken or not.
     * @param goldBonus Gold bonus income
     * @param powerBonus power bonus income
     * @param shippingRange extra shipping range bonus
     * @param workerBonus worker bonus income
     * @param priestBonus priest bonus income.
     * @param spacialSpade determines card has special spade action or not.
     * @param specialCult determines card has special cult action or not.
     * @param isDwelling determine there is dwelling bonus or not.
     * @param isTradeHouse determine there is trading house bonus or not.
     * @param isSanctuary determine there is sanctuary or stronghold bonus or not.
     */
    public BonusCard(int notTakenBonus, boolean playerOccupied,int goldBonus, int powerBonus, int shippingRange, int workerBonus, int priestBonus, boolean spacialSpade, boolean specialCult, boolean isDwelling, boolean isTradeHouse, boolean isSanctuary) {
        this.notTakenBonus = notTakenBonus;
        this.playerOcupied = playerOccupied;
        this.goldBonus = goldBonus;
        this.powerBonus = powerBonus;
        this.shippingRange = shippingRange;
        this.workerBonus = workerBonus;
        this.priestBonus = priestBonus;
        this.spacialSpade = spacialSpade;
        this.specialCult = specialCult;
        this.isDwelling = isDwelling;
        this.isTradeHouse = isTradeHouse;
        this.isSanctuary = isSanctuary;
        playerId = -1;
    }

    public int getNotTakenBonus() {
        return notTakenBonus;
    }

    public int getPlayerId() {
        return playerId;
    }

    public int getGoldBonus() {
        return goldBonus;
    }

    public int getPowerBonus() {
        return powerBonus;
    }

    public int getShippingRange() {
        return shippingRange;
    }

    public int getWorkerBonus() {
        return workerBonus;
    }

    public int getPriestBonus() {
        return priestBonus;
    }

    public boolean isSpacialSpade() {
        return spacialSpade;
    }

    public boolean isSpecialCult() {
        return specialCult;
    }

    public boolean isDwelling() {
        return isDwelling;
    }

    public boolean isTradeHouse() {
        return isTradeHouse;
    }

    public boolean isSanctuary() {
        return isSanctuary;
    }

    public boolean isPlayerOcupied() {
        return playerOcupied;
    }

    public void setNotTakenBonus(int notTakenBonus) {
        this.notTakenBonus = notTakenBonus;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public void setPlayerOcupied(boolean playerOcupied) {
        this.playerOcupied = playerOcupied;
    }
}
