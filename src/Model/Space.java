package Model;
//to do : Yeni bir list yapalım size ı 1 olsun. Bu listede bridge ile bağlantı kurulan öteki space'i tutarız.
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
