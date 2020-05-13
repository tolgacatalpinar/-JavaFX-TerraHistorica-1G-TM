package Model;
import java.io.Serializable;
public class Structure implements Serializable{

    private int buildingScore;
    private String buildingType;


    public Structure(){
        setBuildingScore(0);
        buildingType = "";
    }

    public Structure(String building){
        setBuildingScore(0);
        this.buildingType = building;
    }

    public void setBuilding(String building){
        this.buildingType = building;
    }

    public String getBuilding(){
        return buildingType;
    }

    protected void setBuildingScore(int buildingScore) {
        this.buildingScore = buildingScore;
    }

    public int getBuildingScore() {
        return buildingScore;
    }

}
