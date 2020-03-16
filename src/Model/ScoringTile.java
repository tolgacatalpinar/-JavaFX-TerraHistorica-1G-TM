package Model;

public class ScoringTile {
    int cardCount;
    int structureToBuild;
    int victoryPoint;
    int religion;
    int religionCount;
    int id;
    int goldBonus;
    boolean isTownBonus;
    boolean isSpadeBonus;
    boolean isPowerBonus;
    boolean isGoldBonus;
    boolean isShippingBonus;

    public ScoringTile(int cardCount, int structureToBuild, int victoryPoint, int religion, int religionCount, int id, int goldBonus, boolean isTownBonus, boolean isSpadeBonus, boolean isPowerBonus, boolean isGoldBonus, boolean isShippingBonus) {
        this.cardCount = cardCount;
        this.structureToBuild = structureToBuild;
        this.victoryPoint = victoryPoint;
        this.religion = religion;
        this.religionCount = religionCount;
        this.id = id;
        this.goldBonus = goldBonus;
        this.isTownBonus = isTownBonus;
        this.isSpadeBonus = isSpadeBonus;
        this.isPowerBonus = isPowerBonus;
        this.isGoldBonus = isGoldBonus;
        this.isShippingBonus = isShippingBonus;
    }
    public void initializeBonuses(int id){

    }
}
