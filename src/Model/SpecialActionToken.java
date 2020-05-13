package Model;
import java.io.Serializable;
public class SpecialActionToken implements Serializable {
    public boolean isSpade;
    public boolean isCultTack;
    public boolean isStrongholdAbility;
    public boolean isFactionAbility;

//Bunlar değiştirilip tekrardan false olarak set edilecek ama şimdilik böyle kalması lazım
    public SpecialActionToken() {
        this.isSpade = true;
        this.isCultTack = true;
        this.isStrongholdAbility = true;
        this.isFactionAbility = true;
    }
}
