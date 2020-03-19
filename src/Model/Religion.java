package Model;
public class Religion {

    private final int MAX_LENGTH = 11;
    private int orderOfCult_3;      //Holds id of player who took this area
    private int orderOfCult_2_1;    //Holds id of player who took this area
    private int orderOfCult_2_2;    //Holds id of player who took this area
    private int orderOfCult_2_3;    //Holds id of player who took this are
    private  boolean keyPlaced;
    private int[] playerPositions;
    private int[] powerAwardPositions; //placed /wrt the higher value of track number

    public Religion(int playerCount, int[] initial_religion_points){
        orderOfCult_3 = -1;
        orderOfCult_2_1 = -1;
        orderOfCult_2_2 = -1;
        orderOfCult_2_3 = -1;
        keyPlaced = false;
        playerPositions = new int[playerCount];
        setupReligion(playerCount,initial_religion_points);
    }


    private void setupReligion(int playerCount, int[] initial_religion_points){
        powerAwardPositions = new int[MAX_LENGTH+1];
        for (int i = 0; i < playerPositions.length ; i++){
            powerAwardPositions[i] = 0;
        }
        powerAwardPositions[4] = 1;
        powerAwardPositions[6] = 2;
        powerAwardPositions[8] = 2;
        powerAwardPositions[11] = 3;
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
    private int updateReligion(int count, int player_id, boolean key){
        int powerAward = 0;
        int currentPos = playerPositions[player_id];
        int endPos = currentPos + count;
        int awardSearchLength = powerAwardPositions.length;
        if(currentPos >= MAX_LENGTH){
            System.out.println("Cannot advance more on this religion");
            return -1; // these -1's can represent error messages or throw exceptions
        }
        if (endPos >= MAX_LENGTH){
            if(keyPlaced){
                System.out.println("Since someone used key, you can't reach end"); // Can be replaced with an GUI message
                awardSearchLength -= 1;
                endPos = MAX_LENGTH-1;
            }
            if (!key){
                System.out.println("Since there is no key end pos is stuck on 9"); // Can be replaced with an GUI message
                awardSearchLength -= 1;
                endPos = MAX_LENGTH-1;
            }else
                keyPlaced = true;
        }
        for (int i = 0; i< awardSearchLength; i++ ){
            if (currentPos < i && endPos >= i){
                powerAward += powerAwardPositions[i];
            }
        }
        playerPositions[player_id] = endPos;
        return powerAward;
    }
    public boolean isOccupied(int index){
        for(int i = 0; i< playerPositions.length; i++){
            if(playerPositions[i] == index){
                return true;
            }
        }
        return false;
    }
    public int placePriest(int player_id,boolean key) {
        return updateReligion(1, player_id, key);
    }

    public  int addOrderOfReligion(int player_id, boolean key){
        if(orderOfCult_3 == -1) {
            orderOfCult_3 = player_id;
            return this.updateReligion(3, player_id, key);
        }else if (orderOfCult_2_1 == -1){
            orderOfCult_2_1 = player_id;
            return this.updateReligion(2, player_id, key);
        }else if (orderOfCult_2_2 == -1){
            orderOfCult_2_2 = player_id;
            return this.updateReligion(2, player_id, key);
        }else if (orderOfCult_2_3 == -1){
            orderOfCult_2_3 = player_id;
            return this.updateReligion(2, player_id, key);
        }else
            System.out.println("ORDER IS FULL");
        return -1; // Error value which indicates there is no empty place
        //// these -1's can represent error messages or throw exceptions
    }
    public int[] getPlayerPositions()
    {
       return playerPositions;
    }
}