package Model.CardsAndTiles;

public class BonusCard {
    int notTakenBonus;
    int playerOccupied;
    int goldBonus;
    int powerBonus;
    int shippingRange;
    int perBuildingBonus;
    int workerBonus;
    int cultBonus;
    int priestBonus;
    boolean spacialSpade;
    boolean specialCult;
    boolean isDwelling;
    boolean isTradeHouse;
    boolean isSanctuary;

    /**
     *
     * @param notTakenBonus If card ware not taken previous game, it has more probability than other.
     * @param playerOccupied Which player take it.
     * @param goldBonus Gold bonus income
     * @param powerBonus power bonus income
     * @param shippingRange extra shipping range bonus
     * @param perBuildingBonus If card give bonus incomes when building structure It determines bonus.
     * @param workerBonus worker bonus income
     * @param cultBonus If specialCult is true, It determines cultBonus.
     * @param priestBonus priest bonus income.
     * @param spacialSpade determines card has special spade action or not.
     * @param specialCult determines card has special cult action or not.
     * @param isDwelling determine there is dwelling bonus or not.
     * @param isTradeHouse determine there is trading house bonus or not.
     * @param isSanctuary determine there is sanctuary or stronghold bonus or not.
     */
    public BonusCard(int notTakenBonus, int playerOccupied, int goldBonus, int powerBonus, int shippingRange, int perBuildingBonus, int workerBonus, int cultBonus, int priestBonus, boolean spacialSpade, boolean specialCult, boolean isDwelling, boolean isTradeHouse, boolean isSanctuary) {
        this.notTakenBonus = notTakenBonus;
        this.playerOccupied = playerOccupied;
        this.goldBonus = goldBonus;
        this.powerBonus = powerBonus;
        this.shippingRange = shippingRange;
        this.perBuildingBonus = perBuildingBonus;
        this.workerBonus = workerBonus;
        this.cultBonus = cultBonus;
        this.priestBonus = priestBonus;
        this.spacialSpade = spacialSpade;
        this.specialCult = specialCult;
        this.isDwelling = isDwelling;
        this.isTradeHouse = isTradeHouse;
        this.isSanctuary = isSanctuary;
    }
}
