package Model;

public class Structure {
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
