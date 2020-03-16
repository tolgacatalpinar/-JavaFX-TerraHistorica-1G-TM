package Model.CardsAndTiles;

public class FavorTile {
    int islamBonus;
    int christianityBonus;
    int hinduismBonus;
    int jewBonus;
    int[] playerIds;
    int powerBonus;
    int workerBonus;
    int giveCultChoice;
    int workerIncomeBonus;
    int powerIncomeBonus;
    int perStructureBonus;
    int structureToBonus;

    public int returnTownSize(){
        int townSize = 4;
        return townSize;
    }
}
