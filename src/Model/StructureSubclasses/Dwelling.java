package Model.StructureSubclasses;

import Model.Structure;
import java.io.Serializable;

public class Dwelling extends Structure implements Serializable {
    //image per level should be added
    public  Dwelling(){
        super();
        setBuildingScore(1);

    }
}
