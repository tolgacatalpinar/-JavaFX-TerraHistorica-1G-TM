package Model;
import java.io.Serializable;
public class Structure implements Serializable{
    private int buildingScore;
    public Structure(){
        setBuildingScore(0);
    }

    protected void setBuildingScore(int buildingScore) {
        this.buildingScore = buildingScore;
    }

    public int getBuildingScore() {
        return buildingScore;
    }
}
