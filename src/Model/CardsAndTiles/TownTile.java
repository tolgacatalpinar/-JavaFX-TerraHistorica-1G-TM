package Model.CardsAndTiles;

public class TownTile {
    int powerBonus;
    int priestBonus;
    int  keyCount;
    int victoryBonus;
    int christianityPoint;
    int hinduismPoint;
    int islamPoint;
    int jewishPoint;
    boolean isShippingBonus;
    int workerBonus;
    int goldBonus;

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
    }
}
