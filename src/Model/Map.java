//package Model;
//
//import Model.TerrainSubclasses.*;
//
//import java.util.ArrayList;
//
//public class Map{
//    public Space[][] spaces;
//    public Map(){
//        spaces = new Space[9][13];
//        for(int i = 0; i < 117; i++) {
//            Space space;
//            if( i == 4 || i == 13 || i == 20 || i == 24 | i == 41 || i == 59 || i == 69 ||i == 87 ||i == 90 ||i == 91 ||i == 110)
//                space = new Desert();
//            else if( i == 0 || i == 6 || i == 16 || i == 50 || i == 53 || i == 57 || i == 74 || i == 76 || i == 93 || i == 101)
//                space = new Plains();
//            else if( i == 1 ||i == 30 || i == 36 || i == 58 || i == 65 || i == 75 || i == 81  || i == 100 || i == 102 || i == 106 || i == 112)
//                space = new Mountains();
//            else if(i == 2 || i == 9 || i == 32 || i == 34 || i == 39 || i == 62 || i == 66 || i == 70 ||  i == 85 || i == 109  || i == 115)
//                space = new Forest();
//            else if( i == 3 || i == 10 || i == 40 || i == 45 || i == 55 || i == 65 || i == 89  || i == 92 || i == 97 || i == 107 || i == 114)
//                space = new Lakes();
//            else if( i == 5 || i == 8 || i == 11 || i == 44 || i == 47 || i == 49 || i == 54 || i == 83 || i == 104 || i == 108 || i == 111 || i == 116)
//                space = new Wasteland();
//            else if(i == 7 || i == 12 || i == 17 || i == 21 || i == 28 || i == 52 || i == 56 || i == 63 || i == 88 || i == 98 || i == 105)
//                space = new Swamp();
//            else{
//                 space = new River();
//            }
//            spaces[i/13][i%13] = space;
//        }
//    }
//
//    public int getRow(Space space1){
//        boolean found = false;
//        for(int i = 0; i < 117 || !found ; i++) {
//            int row = i / 13;
//            int column = i % 13;
//
//            if (spaces[row][column].equals(space1)){
//                found = true;
//            }
//        }
//        return row;
//    }
//
//     public int getColumn(Space space1){
//        boolean found = false;
//        for(int i = 0; i < 117 || !found ; i++) {
//            int row = i / 13;
//            int column = i % 13;
//
//            if (spaces[row][column].equals(space1)){
//                found = true;
//            }
//        }
//        return column;
//    }
//
//
//    public int[] buildDwelling(Space space1) {
//        int[] location;
//        if ( space1 instanceOf river){ /// burası sakat değiştireceğim
//            location = new int[2];
//            int row = getRow(space1);
//            int column = getColumn(space1);
//            location[0] = row;
//            location[1] = column;
//        }
//        return location;
//    }
//
//    public boolean isDirectAdjacent(Space space1, Space space2) {
//       // We did not control if one of the spaces is river, because even one of them is river this info cn be useful.
//       int row1 = getRow(space1);
//       int row2 = getRow(space2);
//       int col1 = getColumn(space1);
//       int col2 = getColumn(space2);
//       if (row1 % 2 == 0){
//           if ( ((row2 == row1 + 1) || (row2 == row - 1)) && ((col2 ==  col1 - 1) || (col2 == col1))){
//            return true;
//           }
//       }
//
//       if (row1 % 2 == 1){
//            if ( ((row2 == row1 + 1) || (row2 == row - 1)) && ((col2 ==  col1 + 1) || (col2 == col1))){
//            return true;
//            }
//       }
//
//       if((row1 = row2) && ((col1 = col2 + 1) || (col1 = col2 - 1))){
//           return true;
//       }
//
//       return false;
//    }
//
//    public boolean isIndirectAdjacent(Space space1, Space space2) {
//       int row1 = getRow(space1);
//       int row2 = getRow(space2);
//       int col1 = getColumn(space1);
//       int col2 = getColumn(space2);
//       return false;
//    }
//
//    public void upgradeBuilding(Space space) {
//
//    }
//
//    public boolean canBuildBridge( Space space1, Space space2 ) {
//       return false;
//    }
//
//    public boolean isTown(Space newBuilt) {
//       return false;
//    }
//
//    public void transformTerrain(Space space) {
//
//    }
//
//    public ArrayList<Integer> adjacentPlayer(Space space) {
//      return null;
//    }
//
//
//
//}
