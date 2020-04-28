package Model.StructureSubclasses;

import Model.Structure;
import java.io.Serializable;
public class TradingHouse extends Structure implements Serializable {
    //image per level should be added
    public  TradingHouse(){
        super();
        setBuildingScore(2);
    }
}
