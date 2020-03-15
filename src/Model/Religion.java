package com.company;

public class Religion {

    final int MAX_LENGTH = 10;
    int orderOfCult_3;      //Holds id of player who took this area
    int orderOfCult_2_1;    //Holds id of player who took this area
    int orderOfCult_2_2;    //Holds id of player who took this area
    int orderOfCult_2_3;    //Holds id of player who took this are
    int[] playerPositions;
    int[] powerAwardPositions; //placed /wrt the higher value of track number

    Religion(int playerCount, int[] initial_religion_points){
        orderOfCult_3 = 0;
        orderOfCult_2_1 = 0;
        orderOfCult_2_2 = 0;
        orderOfCult_2_3 = 0;
        playerPositions = new int[playerCount];
        powerAwardPositions = new int[4];
        powerAwardPositions[0] = 3;
        powerAwardPositions[1] = 5;
        powerAwardPositions[2] = 7;
        powerAwardPositions[3] = 10;
        setupReligion(playerCount,initial_religion_points);
    }


    private void setupReligion(int playerCount, int[] initial_religion_points){
        for(int i = 0; i< playerCount; i++){
            updateReligion(initial_religion_points[i], i, false);
        }

    }
    /**
     * Updates the place of given player and returns
     * the amount of power obtained by end of progress.
     * @param count  the number of advance in religion track
     * @param player_id the id of player whose round
     * @return powerAward the amount of power player gained
     *
     */
    private  int updateReligion(int count, int player_id, boolean key){
        int powerAward = 0;
        int currentPos = playerPositions[player_id];
        int endPos = currentPos + count;
        int awardSearchLength = powerAwardPositions.length;
        if (endPos >= 10){
            if (!key){
               System.out.println("Since there is no key end pos is stuck on 9"); // Can be replaced with an GUI message
               awardSearchLength -= 1;
               endPos = 9;
            }
        }
        for (int i = 0; i< awardSearchLength; i++ ){
            if (currentPos < powerAwardPositions[i] && endPos >= powerAwardPositions[i]){
                powerAward += powerAwardPositions[i];
            }
        }
        playerPositions[player_id] = endPos;
        return powerAward;
    }
    private int placePriest(int player_id,boolean key){
        if(orderOfCult_3 != -1) {
            orderOfCult_3 = player_id;
            return this.updateReligion(3, player_id, key);
        }else if (orderOfCult_2_1 != -1){
            orderOfCult_2_1 = player_id;
            return this.updateReligion(2, player_id, key);
        }else if (orderOfCult_2_2 != -1){
            orderOfCult_2_2 = player_id;
            return this.updateReligion(2, player_id, key);
        }else if (orderOfCult_2_3 != -1){
            orderOfCult_2_1 = player_id;
            return this.updateReligion(2, player_id, key);
        }else
            return -1; // Error value which indicates there is no empty place
    }
    private  int addOrderOfReligion(int player_id, boolean key){
        return updateReligion(1,player_id, key);
    }
}
