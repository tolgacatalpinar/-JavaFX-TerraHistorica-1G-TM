package Model;
//to do : Yeni bir list yapalım size ı 1 olsun. Bu listede bridge ile bağlantı kurulan öteki space'i tutarız. Kurarken buildBridge(space) diye
// diye bir method olur en son döndürürken de getBridgeList()
public class Space{
    private boolean isOccupied;
    private String type;
    Space(){
        isOccupied = false;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        if (type.compareTo("River") == 0
                || type.compareTo("Wasteland") == 0 ||
                type.compareTo("Mountains") == 0
                || type.compareTo("Lakes") == 0 ||
                type.compareTo("Forest") == 0
                || type.compareTo("Plains") == 0 || type.compareTo("Swamp") == 0)  {
            this.type = type;
        }else
            System.out.println("ERROR------False keyword used for space type");

    }
}
