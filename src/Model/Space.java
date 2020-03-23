package Model;

public class Space{
    private boolean isOccupied;
    Space(){
        isOccupied = false;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }
}
