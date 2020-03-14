package Model;

public class Player {

    FactionBoard factionBoard;
    Faction faction;


    public void buildTradingPost()
    {
        factionBoard.addWorker(2);
        factionBoard.addCoinIncome(1);
    }

    public FactionBoard getFactionBoard() {
        return factionBoard;
    }

    public void setFactionBoard(FactionBoard factionBoard) {
        this.factionBoard = factionBoard;
    }

    public Faction getFaction() {
        return faction;
    }

    public void setFaction(Faction faction) {
        this.faction = faction;
    }
}
