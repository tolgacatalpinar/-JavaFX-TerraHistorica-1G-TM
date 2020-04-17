package Model.CardsAndTiles;

import Model.Player;

import java.util.ArrayList;

public class ScoringTile {

    //Every player can take this tile there is no current number of player.
    private ArrayList<Integer> playerId;
    private int requiredIslam; //water
    private int requiredBudism; //fire
    private int requiredChrist; //Air
    private int requiredJudaism; //earth
    private int priestBonus;
    private int powerBonus;
    private int workerBonus;
    private int victoryBonus;
    private int spadeBonus;
    private int goldBonus;
    private boolean isDwellingBonus;
    private boolean isTradingHouseBonus;
    private boolean isStrongHoldBonus;
    private boolean isRequiredIslam;
    private boolean isRequiredHinduism;
    private boolean isRequiredChrist;
    private boolean isRequiredJudaism;
    private boolean isRequiredSpade;
    private boolean isRequiredTown;

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
     * @param isRequiredHinduism determines tile require budism progress to get bonus or not.
     * @param isRequiredChrist determines tile require Christ progress to get bonus or not.
     * @param isRequiredJudaism determines tile require Judaism progress to get bonus or not.
     * @param isRequiredSpade determines tile require Spade progress to get bonus or not.
     * @param isRequiredTown determines tile require building a town to get bonus or not.
     */
    public ScoringTile( int requiredIslam, int requiredBudism, int requiredChrist, int requiredJudaism,
                        int priestBonus, int powerBonus, int workerBonus, int victoryBonus,
                        int spadeBonus,int goldBonus, boolean isDwellingBonus, boolean isTradingHouseBonus,
                        boolean isStrongHoldBonus, boolean isRequiredIslam, boolean isRequiredHinduism,
                        boolean isRequiredChrist, boolean isRequiredJudaism, boolean isRequiredSpade,
                        boolean isRequiredTown) {
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
        this.isRequiredHinduism = isRequiredHinduism;
        this.isRequiredChrist = isRequiredChrist;
        this.isRequiredJudaism = isRequiredJudaism;
        this.isRequiredSpade = isRequiredSpade;
        this.isRequiredTown = isRequiredTown;
        playerId = new ArrayList<>();
    }

    public ArrayList<Integer> getPlayerId() {
        return playerId;
    }

    public int getRequiredIslam() {
        return requiredIslam;
    }

    public int getRequiredBudism() {
        return requiredBudism;
    }

    public int getRequiredChrist() {
        return requiredChrist;
    }

    public int getRequiredJudaism() {
        return requiredJudaism;
    }

    public int getPriestBonus() {
        return priestBonus;
    }

    public int getPowerBonus() {
        return powerBonus;
    }

    public int getWorkerBonus() {
        return workerBonus;
    }

    public int getVictoryBonus() {
        return victoryBonus;
    }

    public int getSpadeBonus() {
        return spadeBonus;
    }

    public int getGoldBonus() {
        return goldBonus;
    }

    public boolean isDwellingBonus() {
        return isDwellingBonus;
    }

    public boolean isTradingHouseBonus() {
        return isTradingHouseBonus;
    }

    public boolean isStrongHoldBonus() {
        return isStrongHoldBonus;
    }

    public boolean isRequiredIslam() {
        return isRequiredIslam;
    }

    public boolean isRequiredBudism() {
        return isRequiredHinduism;
    }

    public boolean isRequiredChrist() {
        return isRequiredChrist;
    }

    public boolean isRequiredJudaism() {
        return isRequiredJudaism;
    }

    public boolean isRequiredSpade() {
        return isRequiredSpade;
    }

    public boolean isRequiredTown() {
        return isRequiredTown;
    }

    public void setPlayerId(ArrayList<Integer> playerId) {
        this.playerId = playerId;
    }
}
