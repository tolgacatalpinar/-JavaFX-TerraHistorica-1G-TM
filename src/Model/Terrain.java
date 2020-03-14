package Model;

import javafx.scene.control.Button;
import javafx.scene.shape.Polygon;

public abstract class Terrain extends Button {
   private int terrainId;
   private int row;
   private int col;

   public Terrain()
   {
      double[] points = new double[12];
      for (int i = 0; i < 12; i += 2) {
         double angle = Math.PI * (0.5 + i / 6d);
         points[i] = Math.cos(angle);
         points[i + 1] = Math.sin(angle);
      }
      Polygon polygon = new Polygon(points);

      double fieldHeight = 70;
      double fieldWidth = Math.sqrt(0.75) * fieldHeight;

      setShape(polygon);
      setPrefSize(fieldWidth, fieldHeight);
   }
   public int getTerrainId() {
      return terrainId;
   }

   public void setTerrainId(int terrainId) {
      this.terrainId = terrainId;
      row = terrainId / 13;
      col = terrainId % 13;
   }

   public int getRow() {
      return row;
   }

   public int getCol() {
      return col;
   }
}
