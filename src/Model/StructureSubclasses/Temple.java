package Model.StructureSubclasses;

import Model.Structure;
import java.io.Serializable;
public class Temple extends Structure implements Serializable {
    //image per level should be added
    public Temple(){
        super();
        setBuildingScore(2);

    }
}
