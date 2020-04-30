package Model;
//to do : Yeni bir list yapalım size ı 1 olsun. Bu listede bridge ile bağlantı kurulan öteki space'i tutarız. Kurarken buildBridge(space) diye
// diye bir method olur en son döndürürken de getBridgeList()
import java.io.Serializable;
public class Space implements Serializable{
    private boolean isOccupied;
    private String type;
    private String color;
    private Space[] bridgeList; // this array holds the other space that can be reached from this space; thanks to bridge(if exists)
    private Boolean hasBridge;
        
    public Space(String type){
        isOccupied = false;
        this.type = type;
    }

    public Space(){isOccupied = false;}
    public boolean isOccupied() {
        return isOccupied;
    }
    
     public void buildBridge(Space space1){
         bridgeList = new Space[1];
         bridgeList[0] = space1;
     }
    
     public Space[] getBridgeList(){
         return bridgeList;
     }

     public void setBridge(Boolean status){
         hasBridge = status;
     }

     public String getColor(){
        return color;
     }

     public Structure getStructure(){
        return null; // todo
     }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        if (type.compareTo("River") == 0
                ||type.compareTo("Wasteland") == 0 
                ||type.compareTo("Mountains") == 0 
                ||type.compareTo("Desert") == 0 
                ||type.compareTo("Lakes") == 0 
                ||type.compareTo("Forest") == 0
                ||type.compareTo("Plains") == 0 || type.compareTo("Swamp") == 0 || type.compareTo("Empty") == 0)  {
            this.type = type;
            this.color = type;
        }else
            System.out.println("ERROR------False keyword used for space type");

    }
}
