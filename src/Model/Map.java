package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Map implements Serializable {
   public Space[][] spaces;
   public ArrayList<Space> visited = new ArrayList<Space>(); //
   final int ROW_NUMBER = 9;
   final int COLUMN_NUMBER = 13;

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
         else if (i == 3 || i == 10 || i == 40 || i == 45 || i == 55 || i == 64 || i == 89 || i == 92 || i == 97 || i == 107 || i == 114)
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


   public int getRow(Space space1) {
      int row = -1;

      for (int i = 0; i < ROW_NUMBER; i++) {
         for (int j = 0; j < COLUMN_NUMBER; j++) {
            if (spaces[i][j].equals(space1))
               row = i;
         }
      }
      return row;
   }

   public int getColumn(Space space1) {
      int col = -1;

      for (int i = 0; i < ROW_NUMBER; i++) {
         for (int j = 0; j < COLUMN_NUMBER; j++) {
            if (spaces[i][j].equals(space1))
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
      } else if (canBuild(space1, color)) {
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


   public Space[] getAdjacentSpaces(Space space1) {
      Space[] adjacents = new Space[6];
      int row, column = -1;
      int j = 0;

      for (int i = 0; i < 117; i++) {
         row = i / 13;
         column = i % 13;
         if (isDirectAdjacent(space1, spaces[row][column])) {
            if (j < 6) {
               adjacents[j] = spaces[row][column];
               j++;
            }
         }
      }

      return adjacents;
   }

   public Space[] getAdjacentRivers(Space space1) {
      Space[] riverArr = new Space[6];
      Space[] adjacents = getAdjacentSpaces(space1);

      int count = 0;
      for (Space adjacent : adjacents) {
         if (adjacent != null && adjacent.getType().equals("River")) {
            riverArr[count] = adjacent;
            count++;
         }
      }
      return riverArr;
   }

   public Space[] getAdjacentNonRivers(Space space1) {
      Space[] nonRiverArr = new Space[6];
      Space[] adjacents = getAdjacentSpaces(space1);

      int count = 0;
      for (Space adjacent : adjacents) {
         if (adjacent != null && !adjacent.getType().equals("River")) {
            nonRiverArr[count] = adjacent;
            count++;
         }
      }
      return nonRiverArr;
   }

   public boolean canBuildBridge(Space space1, Space space2) {
      int row1 = getRow(space1);
      int row2 = getRow(space2);
      int col1 = getColumn(space1);
      int col2 = getColumn(space2);

      //preliminary case
      if (space1.getType().equals("River") || space2.getType().equals("River"))
         return false;

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

      if (row1 - row2 == 1) {
         if (row1 % 2 == 0) {
            return ((col2 == col1 - 2 && spaces[row1][col1 - 1].getType().equals("River") && spaces[row1 - 1][col1 - 1].getType().equals("River")) ||
                    (col2 == col1 + 1 && spaces[row1][col1 + 1].getType().equals("River") && spaces[row1 - 1][col1].getType().equals("River")));
         } else {
            return ((col2 == col1 - 1 && spaces[row1][col1 - 1].getType().equals("River") && spaces[row1 - 1][col1].getType().equals("River")) ||
                    (col2 == col1 + 2 && spaces[row1][col1 + 1].getType().equals("River") && spaces[row1 - 1][col1 + 1].getType().equals("River")));
         }
      }

      if (row2 - row1 == 1) {
         if (row2 % 2 == 0) {
            return ((col1 == col2 - 2 && spaces[row2][col2 - 1].getType().equals("River") && spaces[row2 - 1][col2 - 1].getType().equals("River")) ||
                    (col1 == col2 + 1 && spaces[row2][col2 + 1].getType().equals("River") && spaces[row2 - 1][col2].getType().equals("River")));
         } else {
            return ((col1 == col2 - 1 && spaces[row2][col2 - 1].getType().equals("River") && spaces[row2 - 1][col2].getType().equals("River")) ||
                    (col1 == col2 + 2 && spaces[row2][col2 + 1].getType().equals("River") && spaces[row2 - 1][col2 + 1].getType().equals("River")));
         }
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

   public ArrayList<Space> bridgeables(Space space1) {

      ArrayList<Space> bridgeables = new ArrayList<Space>();
      for (int i = 0; i < ROW_NUMBER; i++) {
         for (int j = 0; j < COLUMN_NUMBER; j++) {
            if (canBuildBridge(space1, spaces[i][j])) {
               bridgeables.add(spaces[i][j]);
               System.out.println(i + " - " + j);
            }
         }
      }
      return bridgeables;
   }
   public int calculateTownScoreHelper(int x, int y, String playerColor, ArrayList<Space> traversed){

      if(x-1 < 0 || y-1 < 0 || x > ROW_NUMBER-1 || y > COLUMN_NUMBER-1||spaces[x][y].getType() == "River" || spaces[x][y].getType() == "Empty"  || spaces[x][y].isMarked() ||
              !spaces[x][y].isOccupied()|| spaces[x][y].getType() != playerColor ){
         return 0;
      }else{
         Space space1 = spaces[x][y];
         space1.setMarked(true);
         System.out.println("X is "+ x + " Y is "+ y + "Player color is" + playerColor);
         traversed.add(spaces[x][y]);
         if (x %2 == 0){
            return (
                    calculateTownScoreHelper(x-1, y-1, playerColor, traversed )
                    + calculateTownScoreHelper(x-1, y, playerColor, traversed)
                    + calculateTownScoreHelper(x+1, y, playerColor, traversed)
                    +calculateTownScoreHelper(x, y+1, playerColor,traversed )
                    + calculateTownScoreHelper(x, y-1, playerColor, traversed)
                    + calculateTownScoreHelper(x+1, y-1, playerColor,traversed )
                   );

         }else{
            return (calculateTownScoreHelper(x-1, y, playerColor, traversed)
                    + calculateTownScoreHelper(x+1, y, playerColor, traversed)
                    +calculateTownScoreHelper(x, y+1, playerColor, traversed)
                    + calculateTownScoreHelper(x, y-1, playerColor, traversed)
                    +calculateTownScoreHelper(x-1, y+1, playerColor, traversed)
                    + calculateTownScoreHelper(x+1, y, playerColor, traversed));

         }

      }
   }
   public ArrayList<Integer>[] calculatePathScores(Player[] playerList){
      int[] scoresPath = getLongestPathValues(playerList);
      ArrayList<Integer>[] playerTable = new ArrayList[3];
      playerTable[0] = (new ArrayList<Integer>());
      playerTable[2] = (new ArrayList<Integer>());
      playerTable[1] = (new ArrayList<Integer>());
      int count = 0;
      while(count < 3) {
         int maxValue = 0;
         for(int j = 0; j < scoresPath.length; j++) {
            if(scoresPath[j] > maxValue) {
               maxValue = scoresPath[j];
            }
         }
         for(int k = 0; k < scoresPath.length; k++) {
            if( scoresPath[k] == maxValue) {
               playerTable[count].add(k);
               scoresPath[k] = -1;
            }
         }
         count++;
      }
      return playerTable;
   }

   public int getLongestPathHelper(int x, int y, String playerColor, ArrayList<Space> traversed){

      if(x-1 < 0 || y-1 < 0 || x > ROW_NUMBER-1 || y > COLUMN_NUMBER-1||spaces[x][y].getType() == "River" || spaces[x][y].getType() == "Empty"  || spaces[x][y].isMarked() ||
              !spaces[x][y].isOccupied()|| spaces[x][y].getType() != playerColor ){
         return 0;
      }else{
         Space space1 = this.spaces[x][y];
         space1.setMarkedForScore(true);
         System.out.println("X is "+ x + " Y is "+ y + "Player color is" + playerColor);
         traversed.add(spaces[x][y]);
         if (x %2 == 0){
            return (
                    getLongestPathHelper(x-1, y-1, playerColor, traversed )
                            + getLongestPathHelper(x-1, y, playerColor, traversed)
                            + getLongestPathHelper(x+1, y, playerColor, traversed)
                            +getLongestPathHelper(x, y+1, playerColor,traversed )
                            + getLongestPathHelper(x, y-1, playerColor, traversed)
                            + getLongestPathHelper(x+1, y-1, playerColor,traversed )
            );

         }else{
            return (getLongestPathHelper(x-1, y, playerColor, traversed)
                    + getLongestPathHelper(x+1, y, playerColor, traversed)
                    +getLongestPathHelper(x, y+1, playerColor, traversed)
                    + getLongestPathHelper(x, y-1, playerColor, traversed)
                    +getLongestPathHelper(x-1, y+1, playerColor, traversed)
                    + getLongestPathHelper(x+1, y, playerColor, traversed));
         }
      }
   }
   public int[] getLongestPathValues(Player[] players) {
      int[] playerScores = new int[players.length];
      for (int player_index = 0; player_index < players.length; player_index++) {
         ArrayList<Space> playerTerrains = new ArrayList<Space>();
         for (int i = 0; i < 9; i++) { //row
            for (int j = 0; j < 13; j++) {
               if (spaces[i][j].getType().equals(players[player_index].getFaction().TERRAIN_TILE)) {
                  playerTerrains.add(spaces[i][j]);
               }
            }
         }
         int max = -1;
         int temp = 0;
         for (int k = 0; k < playerTerrains.size(); k++) {
            ArrayList<Space> traversed = new ArrayList<Space>();
            getLongestPathHelper(playerTerrains.get(k).getX(), playerTerrains.get(k).getY(), players[player_index].getFaction().TERRAIN_TILE, traversed);
            temp = traversed.size();
            if(max <temp)
               max = temp;
            unmarkAll(false);
         }
         playerScores[player_index] = max;
      }
      return playerScores;
   }
   public void unmarkAll(boolean isTown){
      for (int i = 0; i < 9; i++) { //row
         for (int j = 0; j < 13; j++) {
            if(!isTown)
               spaces[i][j].setMarkedForScore(false);
            else
               spaces[i][j].setMarked(false);
         }
         }
   }
   public int calculateTownScore(int x1, int y1, String playerColor , int townThreshold){
      ArrayList<Space> traversed = new ArrayList<Space>();
      calculateTownScoreHelper(x1,y1, playerColor, traversed);
      int calculated = 0;
      for (int i = 0; i< traversed.size(); i++){
         calculated += traversed.get(i).getStructure().getBuildingScore();
      }
      System.out.println("Calculated" + calculated);
      if (calculated < townThreshold){
         for (int i = 0; i< traversed.size(); i++){
            traversed.get(i).setMarked(false);
         }
         Space[] adjacentSpaces = getAdjacentSpaces(spaces[x1][y1]);
         for (int j = 0; j< adjacentSpaces.length; j++){
            if(adjacentSpaces[j] != null && adjacentSpaces[j].getType().equals(playerColor)){
               if (adjacentSpaces[j].isMarked())
                  spaces[x1][y1].setMarked(true);
            }
         }
      }else{
         System.out.println("Town founded");
      }
      return  calculated;
   }


   public ArrayList<Player> adjacentPlayers(Space space1, String playerColor) {
      Space[] adjacents = getAdjacentSpaces(space1);
      ArrayList<Player> list = new ArrayList<>();
      for (Space adjacent : adjacents) {
         if (adjacent != null && !(adjacent.getType().equals(playerColor)) && adjacent.isOccupied() && !list.contains(adjacent.getPlayer())) {
            list.add(adjacent.getPlayer());
         }
      }
      return list;
   }

   public boolean canBuild(Space space1, String playerColor) {
      if (space1.getType().equals(playerColor) && !space1.isOccupied()) {
         Space[] adjacents = getAdjacentSpaces(space1);
         for (Space adjacent : adjacents) {
            if (adjacent.getType().equals(playerColor) && !adjacent.isOccupied()) {
               return true;
            }
         }
         Space[] bridgeList = space1.getBridgeList();
         return bridgeList[0].getType().equals(playerColor) && !(bridgeList[0].isOccupied());
      }
      return false;
   }

   public boolean canBuildTurnOne(Space space1, String playerColor) {
      return space1.getType().equals(playerColor) && !space1.isOccupied();
   }

   private Space[] getOneLevelDistantRivers(Space[] rivers)
   {
      Space[] riversViaShipping = new Space[10];

      for (Space riverAroundSpace : rivers) {
         Space[] adjacentRivers = getAdjacentRivers(riverAroundSpace);
         for (int i = 0; i < adjacentRivers.length; i++) {
            boolean doesExist = false;
            for (int j = 0; j < rivers.length; j++) {
               if (adjacentRivers[i] != null && rivers[j] != null && rivers[j].equals(adjacentRivers[i]))
                  doesExist = true;
            }
            if (!doesExist) {
               int index = 0;
               while (riversViaShipping[index] != null)
                  index++;
               riversViaShipping[index] = adjacentRivers[i];
            }
         }
      }
      return riversViaShipping;
   }
   private void addEnabledTerrainsOnlyFourLevelShipping(Space space, ArrayList<Space> reachableTerrains)
   {
      Space[] riversAroundSpace = getAdjacentRivers(space);
      Space[] riversViaShipping = getOneLevelDistantRivers(getOneLevelDistantRivers(getOneLevelDistantRivers(riversAroundSpace)));
      for (Space riverViaShipping : riversViaShipping) {
         Space[] accessableTerrains = getAdjacentNonRivers(riverViaShipping);
         for (Space terrain : accessableTerrains) {
            if (terrain != null && !reachableTerrains.contains(terrain))
               reachableTerrains.add(terrain);
         }
      }
   }
   private void addEnabledTerrainsOnlyThreeLevelShipping(Space space, ArrayList<Space> reachableTerrains)
   {
      Space[] riversAroundSpace = getAdjacentRivers(space);
      Space[] riversViaShipping = getOneLevelDistantRivers(getOneLevelDistantRivers(riversAroundSpace));
      for (Space riverViaShipping : riversViaShipping) {
         Space[] accessableTerrains = getAdjacentNonRivers(riverViaShipping);
         for (Space terrain : accessableTerrains) {
            if (terrain != null && !reachableTerrains.contains(terrain))
               reachableTerrains.add(terrain);
         }
      }
   }
   private void addEnabledTerrainsOnlyTwoLevelShipping(Space space, ArrayList<Space> reachableTerrains) {
      Space[] riversAroundSpace = getAdjacentRivers(space);
      Space[] riversViaShipping = getOneLevelDistantRivers(riversAroundSpace);
      for (Space riverViaShipping : riversViaShipping) {
         Space[] accessableTerrains = getAdjacentNonRivers(riverViaShipping);
         for (Space terrain : accessableTerrains) {
            if (terrain != null && !reachableTerrains.contains(terrain))
               reachableTerrains.add(terrain);
         }
      }
   }

   private void addEnabledTerrainsOnlyOneLevelShipping(Space space, ArrayList<Space> reachableTerrains) {
      Space[] riversAroundSpace = getAdjacentRivers(space);
      for (Space riverAroundSpace : riversAroundSpace) {
         Space[] accessableTerrains = getAdjacentNonRivers(riverAroundSpace);
         for (Space terrain : accessableTerrains) {
            if (terrain != null && !reachableTerrains.contains(terrain))
               reachableTerrains.add(terrain);
         }
      }
   }

   public void getShippingEnabledTerrains(int shipping, Space space, ArrayList<Space> reachableTerrains) {
      switch (shipping)
      {
         case 1:
            addEnabledTerrainsOnlyOneLevelShipping(space, reachableTerrains);
            break;
         case 2:
            addEnabledTerrainsOnlyOneLevelShipping(space, reachableTerrains);
            addEnabledTerrainsOnlyTwoLevelShipping(space, reachableTerrains);
            break;
         case 3:
            addEnabledTerrainsOnlyOneLevelShipping(space, reachableTerrains);
            addEnabledTerrainsOnlyTwoLevelShipping(space, reachableTerrains);
            addEnabledTerrainsOnlyThreeLevelShipping(space, reachableTerrains);
            break;
         case 4:
            addEnabledTerrainsOnlyOneLevelShipping(space, reachableTerrains);
            addEnabledTerrainsOnlyTwoLevelShipping(space, reachableTerrains);
            addEnabledTerrainsOnlyThreeLevelShipping(space, reachableTerrains);
            addEnabledTerrainsOnlyFourLevelShipping(space, reachableTerrains);
            break;
      }
   }
}
