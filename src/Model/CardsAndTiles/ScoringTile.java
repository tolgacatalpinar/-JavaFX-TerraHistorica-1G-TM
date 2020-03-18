package Model.CardsAndTiles;

public class ScoringTile {

    int cardCount;
    int id;
    int requiredIslam; //water
    int requiredBudism; //fire
    int requiredChrist; //Air
    int requiredJudaism; //earth
    int priestBonus;
    int powerBonus;
    int workerBonus;
    int victoryBonus;
    int spadeBonus;
    int goldBonus;
    boolean isDwellingBonus;
    boolean isTradingHouseBonus;
    boolean isStrongHoldBonus;
    boolean isRequiredIslam;
    boolean isRequiredBudism;
    boolean isRequiredChrist;
    boolean isRequiredJudaism;
    boolean isRequiredSpade;
    boolean isRequiredTown;

    /**
     *
     * @param requiredIslam how many fields you need to progress in Islam.
     * @param requiredBudism how many fields you need to progress in Budism.
     * @param requiredChrist how many fields you need to progress in Chirst .
     * @param requiredJudaism how many fields you need to progress in Judaism.
     * @param priestBonus priest bonus income.
     * @param powerBonus power bonus income.
     * @param workerBonus worker bonus income.
     * @param victoryBonus victory bonus income.
     * @param spadeBonus spade bonus income.
     * @param goldBonus gold bonus income.
     * @param isDwellingBonus determines tile requires dwelling to get bonus or not.
     * @param isTradingHouseBonus determines tile requires trading House to get bonus or not.
     * @param isStrongHoldBonus determines tile require stronghold or sanctuary to get bonus or not.
     * @param isRequiredIslam determines tile require islam progress to get bonus or not.
     * @param isRequiredBudism determines tile require budism progress to get bonus or not.
     * @param isRequiredChrist determines tile require Christ progress to get bonus or not.
     * @param isRequiredJudaism determines tile require Judaism progress to get bonus or not.
     * @param isRequiredSpade determines tile require Spade progress to get bonus or not.
     * @param isRequiredTown determines tile require building a town to get bonus or not.
     */
    public ScoringTile( int requiredIslam, int requiredBudism, int requiredChrist, int requiredJudaism, int priestBonus, int powerBonus, int workerBonus, int victoryBonus, int spadeBonus,int goldBonus, boolean isDwellingBonus, boolean isTradingHouseBonus, boolean isStrongHoldBonus, boolean isRequiredIslam, boolean isRequiredBudism, boolean isRequiredChrist, boolean isRequiredJudaism, boolean isRequiredSpade, boolean isRequiredTown) {
        this.requiredIslam = requiredIslam;
        this.requiredBudism = requiredBudism;
        this.requiredChrist = requiredChrist;
        this.requiredJudaism = requiredJudaism;
        this.priestBonus = priestBonus;
        this.powerBonus = powerBonus;
        this.workerBonus = workerBonus;
        this.victoryBonus = victoryBonus;
        this.spadeBonus = spadeBonus;
        this.goldBonus = goldBonus;
        this.isDwellingBonus = isDwellingBonus;
        this.isTradingHouseBonus = isTradingHouseBonus;
        this.isStrongHoldBonus = isStrongHoldBonus;
        this.isRequiredIslam = isRequiredIslam;
        this.isRequiredBudism = isRequiredBudism;
        this.isRequiredChrist = isRequiredChrist;
        this.isRequiredJudaism = isRequiredJudaism;
        this.isRequiredSpade = isRequiredSpade;
        this.isRequiredTown = isRequiredTown;
    }

}
