package Controller;

import javafx.scene.control.Button;

public class Terrain extends Button {
   private int terrainId;
   private int row;
   private int col;

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
