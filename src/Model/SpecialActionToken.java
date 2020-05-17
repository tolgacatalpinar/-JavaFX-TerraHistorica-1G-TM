package Model;
import java.io.Serializable;
public class SpecialActionToken implements Serializable {
    public boolean isSpade;
    public boolean isCultTack;
    public boolean isStrongholdAbility;
    public boolean isFactionAbility;

//Bunlar değiştirilip tekrardan false olarak set edilecek ama şimdilik böyle kalması lazım
    public SpecialActionToken() {
        this.isSpade = false;
        this.isCultTack = false;
        this.isStrongholdAbility = false;
        this.isFactionAbility = false;
    }

    public boolean isSpade() {
        return isSpade;
    }

    public void setSpade(boolean spade) {
        isSpade = spade;
    }

    public boolean isCultTack() {
        return isCultTack;
    }

    public void setCultTack(boolean cultTack) {
        isCultTack = cultTack;
    }

    public boolean isStrongholdAbility() {
        return isStrongholdAbility;
    }

    public void setStrongholdAbility(boolean strongholdAbility) {
        isStrongholdAbility = strongholdAbility;
    }

    public boolean isFactionAbility() {
        return isFactionAbility;
    }

    public void setFactionAbility(boolean factionAbility) {
        isFactionAbility = factionAbility;
    }
}
