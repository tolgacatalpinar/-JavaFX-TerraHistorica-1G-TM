package Model;
import java.io.Serializable;
public class Structure implements Serializable{

    private int buildingScore;
    private String building;

    public Structure(){
        setBuildingScore(0);
        building = "";
    }

    public Structure(String building){
        setBuildingScore(0);
        this.building = building;
    }

    public void setBuilding(String building){
        this.building = building;
    }

    public String getBuilding(){
        return building;
    }

    protected void setBuildingScore(int buildingScore) {
        this.buildingScore = buildingScore;
    }

    public int getBuildingScore() {
        return buildingScore;
    }
}
