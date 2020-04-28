package Model.CardsAndTiles;
import java.io.Serializable;
public class TownTile implements Serializable {

    private int playerId;
    private int powerBonus;
    private int priestBonus;
    private int  keyCount;
    private int victoryBonus;
    private int christianityPoint;
    private int hinduismPoint;
    private int islamPoint;
    private int jewishPoint;
    private int workerBonus;
    private int goldBonus;
    private boolean occupied;
    /**
     *
     * @param powerBonus power bonus income.
     * @param priestBonus priest bonus income.
     * @param victoryBonus victory bonus income.
     * @param christianityPoint christianity bonus income.
     * @param hinduismPoint hinduism bonus income.
     * @param islamPoint  islam bonus income.
     * @param jewishPoint jewish bonus income.
     * @param workerBonus worker bonus income.
     * @param goldBonus gold bonus income.
     */
    public TownTile(int powerBonus, int priestBonus, int victoryBonus, int christianityPoint,
                    int hinduismPoint, int islamPoint, int jewishPoint,  int workerBonus, int goldBonus) {
        this.powerBonus = powerBonus;
        this.priestBonus = priestBonus;
        keyCount = 1;
        this.victoryBonus = victoryBonus;
        this.christianityPoint = christianityPoint;
        this.hinduismPoint = hinduismPoint;
        this.islamPoint = islamPoint;
        this.jewishPoint = jewishPoint;
        this.workerBonus = workerBonus;
        this.goldBonus = goldBonus;
        this.playerId = -1;
        this.occupied = false;
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

    public int getWorkerBonus() {
        return workerBonus;
    }

    public int getGoldBonus() {
        return goldBonus;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }
}
