package Model.StructureSubclasses;

import Model.Structure;
import java.io.Serializable;

public class Stronghold extends Structure implements Serializable {
    //image per level should be added
    public  Stronghold(){
        super();
        setBuildingScore(3);

    }
}
