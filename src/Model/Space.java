package Model;
//to do : Yeni bir list yapalım size ı 1 olsun. Bu listede bridge ile bağlantı kurulan öteki space'i tutarız. Kurarken buildBridge(space) diye
// diye bir method olur en son döndürürken de getBridgeList()
import java.io.Serializable;
public class Space implements Serializable{
    private boolean isOccupied;
    private boolean bridgability;
    private boolean bridgeConnection;
    private String bridgeType;
    private String type;
    private Space[] bridgeList; // this array holds the other space that can be reached from this space; thanks to bridge(if exists)
    private Boolean hasBridge;

    private Structure structure;
    private Player player;
        
    public Space(String type){
        isOccupied = false;
        this.type = type;
        structure = null;
        bridgability = false;
        bridgeConnection = false;
        bridgeType = "";
    }

    public String getBridgeType(){
        return bridgeType;
    }

    public void setBridgeType(String bridgeType){
        this.bridgeType = bridgeType;
    }

    public boolean getBridgability(){
        return bridgability;
    }

    public void setBridgability(boolean bridgability){
        this.bridgability = bridgability;
    }

    public boolean getBridgeConnection(){
        return bridgeConnection;
    }

    public void setBridgeConnection(boolean bridgeConnection){
        this.bridgeConnection = bridgeConnection;
    }



    public void setPlayer(Player player) {
        this.player = player;
    }

    public Model.Player getPlayer() {
        return player;
    }

    public Space(){
        isOccupied = false;
        structure = null;
    }
    public boolean isOccupied() {
        return isOccupied;
    }

    public void setStructure(String building){
        this.structure = new Structure(building);
    }

    public Structure getStructure(){
        return structure;
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
        }else
            System.out.println("ERROR------False keyword used for space type");

    }
}
