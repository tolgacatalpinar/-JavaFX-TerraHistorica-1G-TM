package Model.CardsAndTiles;

public class FavorTile {
    int[] playerIds;
    int islamBonus; //water
    int christianityBonus; //Air
    int hinduismBonus; //Fire
    int jewBonus; //earth
    int neededCombinedPowerTown;
    int powerBonus;
    int workerBonus;
    int goldBonus;
    int victoryPoint;
    int[] roundedVictoryBonus;
    boolean isSpecialCult;
    boolean isTownBonus;
    boolean isTradingHouse;
    boolean isDwellingBonus;
    //From now on, when passing (see Action #8, page 14), get 2/3/3/4
    //Victory points for 1/2/3/4 of your Trading houses on the Game board.
    boolean isPassingBonusForTradingHouse;

    /**
     *
     * @param islamBonus number of islam bonus.
     * @param christianityBonus number of christ bonus.
     * @param hinduismBonus number of hinduism bonus.
     * @param jewBonus number of jew bonus.
     * @param neededCombinedPowerTown to build a town, you need 7 combined powers. If player has town bonus favor tile, he/she needs 6 combined powers.
     * @param powerBonus power bonus income .
     * @param workerBonus worker bonus income.
     * @param goldBonus gold bonus income .
     * @param victoryPoint victory bonus income.
     * @param isSpecialCult determines tile has special cult action or not.
     * @param isTownBonus determines tile has town bonus or not.
     * @param isTradingHouse determines tile requied trading house to get bonus or not.
     * @param isDwellingBonus determines tile requied dwelling to get bonus or not.
     * @param isPassingBonusForTradingHouse determines tile has passing bonus for trading house each round bonus or not.
     * Rule : From now on, when passing (see Action #8, page 14), get 2/3/3/4 Victory points for 1/2/3/4 of your Trading houses on the Game board.
     */
    public FavorTile(int islamBonus, int christianityBonus, int hinduismBonus, int jewBonus, int neededCombinedPowerTown, int powerBonus, int workerBonus, int goldBonus, int victoryPoint, boolean isSpecialCult, boolean isTownBonus, boolean isTradingHouse, boolean isDwellingBonus, boolean isPassingBonusForTradingHouse) {
        if(isPassingBonusForTradingHouse){
            roundedVictoryBonus = new int [] {1,2,3,4};
        }
        this.islamBonus = islamBonus;
        this.christianityBonus = christianityBonus;
        this.hinduismBonus = hinduismBonus;
        this.jewBonus = jewBonus;
        this.neededCombinedPowerTown = neededCombinedPowerTown;
        this.powerBonus = powerBonus;
        this.workerBonus = workerBonus;
        this.goldBonus = goldBonus;
        this.victoryPoint = victoryPoint;
        this.isSpecialCult = isSpecialCult;
        this.isTownBonus = isTownBonus;
        this.isTradingHouse = isTradingHouse;
        this.isDwellingBonus = isDwellingBonus;
        this.isPassingBonusForTradingHouse = isPassingBonusForTradingHouse;
    }
}
