package Model;
import java.io.Serializable;
import java.util.Arrays;

public class Religion implements Serializable{

    private final int MAX_LENGTH = 11;
    private int orderOfCult_3;      //Holds id of player who took this area
    private int orderOfCult_2_1;    //Holds id of player who took this area
    private int orderOfCult_2_2;    //Holds id of player who took this area
    private int orderOfCult_2_3;    //Holds id of player who took this are
    private  boolean keyPlaced;
    private int[] playerPositions;
    private int[] roundBasedPosition;
    private int[] powerAwardPositions; //placed /wrt the higher value of track number

    public Religion(int playerCount, int[] initial_religion_points){
        orderOfCult_3 = -1;
        orderOfCult_2_1 = -1;
        orderOfCult_2_2 = -1;
        orderOfCult_2_3 = -1;
        keyPlaced = false;
        playerPositions = new int[playerCount];
        setupReligion(playerCount,initial_religion_points);
        roundBasedPosition = new int[playerCount];
    }
    private void setupReligion(int playerCount, int[] initial_religion_points){
        powerAwardPositions = new int[MAX_LENGTH+1];
        for (int i = 0; i < playerPositions.length ; i++){
            powerAwardPositions[i] = 0;
        }
        powerAwardPositions[3] = 1;
        powerAwardPositions[5] = 2;
        powerAwardPositions[7] = 2;
        powerAwardPositions[10] = 3;
        for(int i = 0; i< playerCount; i++){
            updateReligion(initial_religion_points[i], i, 0);
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
    public int[] updateReligion(int count, int player_id, int key){
        int[] returnInfo = {0,0,0};
        //powerGain, case, kaç ilerledi
        int powerAward = 0;
        int currentPos = playerPositions[player_id];
        int endPos = currentPos + count;
        int awardSearchLength = powerAwardPositions.length;
        if(currentPos >= MAX_LENGTH){
            returnInfo[1] = 4;
        }
        else if (endPos >= MAX_LENGTH-1){
            if(keyPlaced){
                //System.out.println("Since someone used key, you can't reach end"); // Can be replaced with an GUI message
                awardSearchLength -= 1;
                endPos = MAX_LENGTH-2;
                returnInfo[1] = 1;
            }if (key == 0){
                //System.out.println("Since there is no key end pos is stuck on 9"); // Can be replaced with an GUI message
                awardSearchLength -= 1;
                endPos = MAX_LENGTH-2;
                returnInfo[1] = 2;
            }else{
                keyPlaced = true;
                endPos = MAX_LENGTH-1;
                returnInfo[1] = 3;
            }
        }
        for (int i = 0; i< awardSearchLength; i++ ){
            if (currentPos < i && endPos >= i){
                powerAward += powerAwardPositions[i];
            }
        }
        playerPositions[player_id] = endPos;
        returnInfo[0] = powerAward;
        returnInfo[2] = endPos - currentPos;
        return returnInfo;
    }
    public boolean isOccupied(int index) {

        switch (index) {
            case 0:
                return (orderOfCult_3 != -1);
            case 1:
                return (orderOfCult_2_1 != -1);
            case 2:
                return (orderOfCult_2_2 != -1);
            case 3:
                return (orderOfCult_2_3 != -1);
        }
        return false;
    }
    public int[] placePriest(int player_id,int key) {
        return updateReligion(1, player_id, key);
    }
    public  int[] addOrderOfReligion(int player_id, int key){
        int[] returnInfo = {-1,-1,-1};

        if(orderOfCult_3 == -1) {
            returnInfo = this.updateReligion(3, player_id, key);
            if (returnInfo[1] == 0)
                orderOfCult_3 = player_id;
        }else if (orderOfCult_2_1 == -1){
            returnInfo = this.updateReligion(2, player_id, key);
            if (returnInfo[1] == 0)
                orderOfCult_2_1 = player_id;
        }else if (orderOfCult_2_2 == -1){
            returnInfo = this.updateReligion(2, player_id, key);
            if (returnInfo[1] == 0)
                orderOfCult_2_2 = player_id;
        }else if (orderOfCult_2_3 == -1){
            returnInfo = this.updateReligion(2, player_id, key);
            if (returnInfo[1] == 0)
                orderOfCult_2_3 = player_id;
        }else
            System.out.println("ORDER IS FULL");
        return returnInfo; // Error value which indicates there is no empty place
    }
    public int[] getPlayerPositions()
    {
       return playerPositions;
    }
    public void updateRoundBasedPositions(int amount, int playerIndex){
        roundBasedPosition[playerIndex] += amount;
    }
    public void resetRoundBasedPosition(){
        Arrays.fill(roundBasedPosition, 0);
    }
    public int[] getRoundBasedPosition() {
        return roundBasedPosition;
    }
    public void setRoundBasedPosition(int[] roundBasedPosition) {
        this.roundBasedPosition = roundBasedPosition;
    }
}