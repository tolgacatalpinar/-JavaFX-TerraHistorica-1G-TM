package Model;

//import javafx.scene.control.Button;
//import javafx.scene.shape.Polygon;
import java.io.Serializable;
public class Terrain extends Space implements Serializable{

    private Structure structure;

   public Terrain() {
       structure = new Structure();
   }
   public Structure getStructure(){ return structure;}
}
