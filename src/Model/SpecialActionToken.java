package Model;
import java.io.Serializable;
public class SpecialActionToken implements Serializable {
    public boolean isSpade;
    public boolean isCultTack;
    public boolean isStrongholdAbility;
    public boolean isFactionAbility;
    public SpecialActionToken() {
        this.isSpade = false;
        this.isCultTack = false;
        this.isStrongholdAbility = false;
        this.isFactionAbility = true;
    }
    public boolean isSpade() {
        return isSpade;
    }
    public void setSpade(boolean spade) {
        isSpade = spade;
    }
    public void setStrongholdAbility(boolean strongholdAbility) {
        isStrongholdAbility = strongholdAbility;
    }

}
