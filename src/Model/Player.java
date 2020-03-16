package Model;

public class Player {

    Faction faction;

    public void buildTradingPost()
    {
        faction.addWorker(2);
        faction.addCoinIncome(1);
    }

    public Faction getFactionBoard() {
        return faction;
    }

    public void setFactionBoard(Faction factionBoard) {
        this.faction = factionBoard;
    }

    public Faction getFaction() {
        return faction;
    }

    public void setFaction(Faction faction) {
        this.faction = faction;
    }
}
