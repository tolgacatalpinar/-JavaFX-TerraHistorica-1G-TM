package Controller;

import Model.Player;
import Model.PlayerHandler;

public class RoundController {

    int currentPlayerId = 0;
    int playerCount = 0;
    int currentRound = 0;
    final int MAX_ROUND = 6;
    Player[] playerList;
    PlayerHandler playerHandler;

    RoundController(Player[] playerList, PlayerHandler playerHandler){
        currentPlayerId = 0;
        this.playerCount = playerList.length;
        this.playerHandler = playerHandler;
    }

    public int getCurrentPlayerId(){
        return currentPlayerId;
    }
    
    public int getCurrentRound(){
        return currentRound;
    }

    /**
     * Will be called after ending a turn
     */
    public void endTurn() { //Skip Turn Clicked
        currentPlayerId++;
        if(currentPlayerId >= playerCount) {
            currentPlayerId = 0;
        }
    }

    /**
     * Round is over, update resources and pass to next round
     */
    private void endRound() { //Pass Round Clicked

        for(int i = 0; i < playerCount; i++){
            playerHandler.updateResources(playerList[i]);
        }
        currentRound++;
        if(currentRound > MAX_ROUND) {
            System.out.println("GAME OVER");
        }
    }

    public void passRound() {
        playerHandler.passRound(playerList[currentPlayerId]);

        if(isRoundOver()) {
            endRound();
        }
        endTurn();
    }

    private boolean isRoundOver() {

        for (int i = 0; i < playerCount; i++) {
            if (!playerList[i].isRoundPassed()) {
                return false;
            }
        }
        return true;
    }

    /**
     * This method is to check every time after a player
     * builds an initial dwelling and ends his/her turn
     *
     * @return  false if initial dwelling part continues
     *          true if it is over, now round is 1
     */
    public boolean isRoundZeroOver() {

        for(int i = 0; i < playerCount;i++) {
            if(playerList[i].getStartingDwellingNum() > playerList[i].getDwellingNum()) {
                return false;
            }
        }
        currentRound++;
        return true;
    }


}
