package Model;

import java.io.Serializable;

import java.util.ArrayList;

public class Map implements Serializable {
   public Space[][] spaces;
   public static int townScore = 0; // bu variable'ı şimdilik istown çalışsın diye ekledim. Belki durur ama oo olması icin buradan kaldırıp
   // player classına da ekleyebiliriz, is town'a da parameter olarak player objesi alırız, öyle de is görür.
   public ArrayList<Space> visited = new ArrayList<Space>(); //
   final int ROW_NUMBER = 9;
   final int COLUMN_NUMBER = 13;
   public CoordinateTuple bridgeCoordinates;

   public Map() {
      spaces = new Space[9][13];
      for (int i = 0; i < 117; i++) {
         Space space;
         if (i == 4 || i == 13 || i == 20 || i == 24 | i == 41 || i == 59 || i == 69 || i == 87 || i == 90 || i == 91 || i == 110)
            space = new Space("Desert");
         else if (i == 0 || i == 6 || i == 16 || i == 50 || i == 53 || i == 57 || i == 74 || i == 76 || i == 93 || i == 101)
            space = new Space("Plains");
         else if (i == 1 || i == 30 || i == 36 || i == 58 || i == 65 || i == 75 || i == 81 || i == 100 || i == 102 || i == 106 || i == 112)
            space = new Space("Mountains");
         else if (i == 2 || i == 9 || i == 32 || i == 34 || i == 39 || i == 62 || i == 66 || i == 70 || i == 85 || i == 109 || i == 115)
            space = new Space("Forest");
         else if (i == 3 || i == 10 || i == 40 || i == 45 || i == 55 || i == 65 || i == 89 || i == 92 || i == 97 || i == 107 || i == 114)
            space = new Space("Lakes");
         else if (i == 5 || i == 8 || i == 11 || i == 44 || i == 47 || i == 49 || i == 54 || i == 83 || i == 104 || i == 108 || i == 111 || i == 116)
            space = new Space("Wasteland");
         else if (i == 7 || i == 12 || i == 17 || i == 21 || i == 28 || i == 52 || i == 56 || i == 63 || i == 88 || i == 98 || i == 105)
            space = new Space("Swamp");
         else {
            space = new Space("River");
         }
         spaces[i / 13][i % 13] = space;
      }
   }

   public Map(Space[][] spaces) {
      this.spaces = spaces;
   }

   public void setSpaces(Space[][] spaces) {
      this.spaces = spaces;
   }

   public int getRow(Space space1) {
      int row = -1;

      for (int i = 0; i < ROW_NUMBER; i++) {
         for (int j = 0; j < COLUMN_NUMBER; j++) {
            if( spaces[i][j].equals(space1))
               row = i;
         }
      }
      return row;
   }

   public int getColumn(Space space1) {
      int col = -1;

      for (int i = 0; i < ROW_NUMBER; i++) {
         for (int j = 0; j < COLUMN_NUMBER; j++) {
            if( spaces[i][j].equals(space1))
               col = j;
         }
      }
      return col;
   }


   public void buildDwelling(Space space1, String color, boolean isInitialDwelling) {
      if (isInitialDwelling) {
         if (canBuildTurnOne(space1, color)) {
            space1.setOccupied(true);
            space1.setStructure("Dwelling");

         }
      }
      else if (canBuild(space1, color)) {
         space1.setOccupied(true);
         space1.setStructure("Dwelling");
      }

   }


   public boolean isDirectAdjacent(Space space1, Space space2) {
      // We did not control if one of the spaces is river, because even one of them is river this info can be useful.

      int row1 = getRow(space1);
      int row2 = getRow(space2);
      int col1 = getColumn(space1);
      int col2 = getColumn(space2);

      if (row1 % 2 == 0) {
         if (((row2 == row1 + 1) || (row2 == row1 - 1)) && ((col2 == col1 - 1) || (col2 == col1))) {
            return true;
         }
      }

      if (row1 % 2 == 1)
         if (((row2 == row1 + 1) || (row2 == row1 - 1)) && ((col2 == col1 + 1) || (col2 == col1))) {
            return true;
         }


      return (row1 == row2) && ((col1 == col2 + 1) || (col1 == col2 - 1));
   }


   public Space[] adjacencyList(Space space1) {
      Space[] adjacents = new Space[6];
      int row, column = -1;
      int j = 0;

      for (int i = 0; i < 117; i++) {
         row = i / 13;
         column = i % 13;
         if (isDirectAdjacent(space1, spaces[row][column])) {
            if(j < 6) {
               adjacents[j] = spaces[row][column];
               j++;
            }
         }
      }


      return adjacents;
   }

   public boolean isUndirectAdjacent(Space space1, Space space2, int shippingLevel) {
      boolean result;
      result = isReachable(space1, space2, shippingLevel, 0, false, null);
      visited.clear();
      return result;
   }

   public boolean isReachable(Space space1, Space space2, int shippingLevel, int counter, boolean found, ArrayList<Space> previous) {
      Space[] adjacents = adjacencyList(space1);
      boolean isOver = false;
      ArrayList<Space> rivers = new ArrayList<Space>();

      for (int i = 0; i < rivers.size() && isOver; i++) {
         if (visited.contains(rivers.get(i)) || counter >= shippingLevel)
            isOver = true;
         else
            isOver = false;
      }

      if (isOver || found) {
         counter--;
         return found;
      } else {
         for (int i = 0; i < adjacents.length && isOver; i++) {
            if (adjacents[i] == space2) {
               found = true;
               return found;
            }

            if (!visited.contains(adjacents[i]) && adjacents[i].getType().equals("River") && !previous.contains(adjacents[i])) {
               counter++;
               previous = rivers;
               visited.add(adjacents[i]);
               isReachable(adjacents[i], space2, shippingLevel, counter, found, previous);
            }
         }
      }
      return found;
   }

   public ArrayList<Space> adjacentRivers(Space space1) {
      ArrayList<Space> list = new ArrayList<Space>();
      Space[] adjacents = adjacencyList(space1);

      for (Space adjacent : adjacents) {
         if (adjacent.getType().equals("River"))
            list.add(adjacent);
      }
      return list;
   }

   public boolean canBuildBridge(Space space1, Space space2) {
      int row1 = getRow(space1);
      int row2 = getRow(space2);
      int col1 = getColumn(space1);
      int col2 = getColumn(space2);

      // bridge case 1
      if (Math.abs(row1 - row2) == 2 && col1 == col2) {
         if (row1 % 2 == 0) {
            if (spaces[(row1 + row2) / 2][col1].getType().equals("River") && spaces[(row1 + row2) / 2][col1 - 1].getType().equals("River"))
               return true;
         } else {
            if (spaces[(row1 + row2) / 2][col1].getType().equals("River") && spaces[(row1 + row2) / 2][col1 + 1].getType().equals("River"))
               return true;
         }
      }

      // bridge case 2
      if (Math.abs(row1 - row2) == 1) {
         if ((col2 == col1 + 1) && (spaces[row1][col1 + 1].getType().equals("River") && spaces[row1 + 1][col1].getType().equals("River")))
            return true;
         else if ((col2 == col1 - 2) && (spaces[row1][col1 - 1].getType().equals("River") && spaces[row1 - 1][col1 - 1].getType().equals("River")))
            return true;
         else if ((col2 == col1 - 2) && (spaces[row1][col1 - 1].getType().equals("River") && spaces[row1 + 1][col1 - 1].getType().equals("River")))
            return true;
         else if ((col2 == col1 + 1) && (spaces[row1][col1 + 1].getType().equals("River") && spaces[row1 - 1][col1].getType().equals("River")))
            return true;
            /////
         else if ((col2 == col1 + 2) && (spaces[row1][col1 + 1].getType().equals("River") && spaces[row1 + 1][col1 + 1].getType().equals("River")))
            return true;
         else if ((col2 == col1 - 1) && (spaces[row1][col1 - 1].getType().equals("River") && spaces[row1 - 1][col1].getType().equals("River")))
            return true;
         else if ((col2 == col1 - 1) && (spaces[row1][col1 - 1].getType().equals("River") && spaces[row1 + 1][col1].getType().equals("River")))
            return true;
         else return (col2 == col1 + 2) && (spaces[row1][col1 + 1].getType().equals("River") && spaces[row1 - 1][col1 + 1].getType().equals("River"));
      }
      return false;
   }

   public void buildBridge(Space space1, Space space2) {
      if (canBuildBridge(space1, space2)) {
         space1.setBridge(true);
         space1.buildBridge(space2);
         space2.buildBridge(space1);
         space2.setBridge(true);
         System.out.println("Yeni köprümüz hayırlı uğurlu olsun");
      }
   }

   public ArrayList<Space> bridgeables(Space space1){
      Space[] adjacents = adjacencyList(space1);
      ArrayList<Space> bridgeables = new ArrayList<Space>();
      for (Space adjacent : adjacents) {
         if (canBuildBridge(space1, adjacent)) {
            bridgeables.add(adjacent);
         }
      }
      return bridgeables;
   }
   public int calculateTownScore(Space space1, String playerColor) {
      Space[] adjacents = adjacencyList(space1);
      boolean isOver = true;

      for (int i = 0; i < adjacents.length && isOver; i++) {
         isOver = !(adjacents[i].getColor().equals(playerColor)) || visited.contains(adjacents[i]) || !adjacents[i].isOccupied();
      }

      if (isOver) {
         townScore = townScore + space1.getStructure().getBuildingScore();
         return townScore;
      }
      else {
         for (Space adjacent : adjacents)
            if (adjacent.getColor().equals(playerColor) && !(visited.contains(adjacent)) && adjacent.isOccupied()) {
               visited.add(adjacent);
               return calculateTownScore(adjacent, playerColor);
            }
      }
      return townScore;
   }

   public boolean isTown(Space space1, String playerColor) {
      if (calculateTownScore(space1, playerColor) >= 7) {
         townScore = 0;
         visited.clear();
         return true;
      } else {
         townScore = 0;
         visited.clear();
         return false;
      }
   }



   public void transformTerrain(Space original, String newType) {
      original.setType(newType);
   }

   public boolean upgradeStructure(Space space1, String playerColor, String structure) {
      if (canBuild(space1,playerColor)) {
         if (space1.getStructure().getBuilding().equals("Dwelling") && structure.equals("TradingPost")){
            space1.setStructure("TradingPost");
            return true;
          }
         if (space1.getStructure().getBuilding().equals("TradingPost") && structure.equals("Temple")) {
            space1.setStructure("Temple");
            return true;
         }
         if (space1.getStructure().getBuilding().equals("Temple") && structure.equals(("Sanctuary"))) {
            space1.setStructure("Sanctuary");
            return true;
         }
         if (space1.getStructure().getBuilding().equals("TradingPost") && structure.equals(("Stronghold"))) {
            space1.setStructure("Stronghold");
            return true;
         }
      }

      return false;
   }


//   public ArrayList<Space> adjacentPlayer(Space space1, String playerColor) {
//      Space[] adjacents = adjacencyList(space1);
//      ArrayList<Space> list = new ArrayList<>();
//      for (Space adjacent : adjacents) {
//         if (!(adjacent.getColor().equals(playerColor)) && adjacent.isOccupied()) {
//            list.add(adjacent);
//         }
//      }
//      return list;
//   }
   public ArrayList<Player> adjacentPlayers(Space space1, String playerColor) {
      Space[] adjacents = adjacencyList(space1);
      ArrayList<Player> list = new ArrayList<>();
      for (Space adjacent : adjacents) {
         if (!(adjacent.getColor().equals(playerColor)) && adjacent.isOccupied() && !list.contains(adjacent.getPlayer())) {
            list.add(adjacent.getPlayer());
         }
      }
      return list;
   }

   public boolean canBuild(Space space1, String playerColor) {
      if (space1.getColor().equals(playerColor) && !space1.isOccupied()) {
         Space[] adjacents = adjacencyList(space1);
         for (Space adjacent : adjacents) {
            if (adjacent.getColor().equals(playerColor) && !adjacent.isOccupied()) {
               return true;
            }
         }

         Space[] bridgeList = space1.getBridgeList();
         return bridgeList[0].getColor().equals(playerColor) && !(bridgeList[0].isOccupied());
      }
      return false;
   }


   public boolean canBuildTurnOne(Space space1, String playerColor) {
      return space1.getColor().equals(playerColor) && !space1.isOccupied();
   }
}
