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
        this.buildingType = building;
        if(buildingType.equals("Dwelling"))
            setBuildingScore(1);
        else if(buildingType.equals("Trading Post"))
            setBuildingScore(2);
        else if(buildingType.equals("Stronghold"))
            setBuildingScore(3);
        else if(buildingType.equals("Temple"))
            setBuildingScore(3);
        else if(buildingType.equals("Sanctuary"))
            setBuildingScore(4);

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
