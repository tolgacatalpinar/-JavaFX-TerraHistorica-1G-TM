package Controller;

import Model.Player;
import Model.PlayerHandler;

public class RoundController {

    int currentPlayerId = 0;
    int playerCount = 0;
    int currentRound = 0;
    final int MAX_ROUND = 6;
    PlayerHandler playerHandler;
    Player[] passRoundPlayerList;
    int lastPassedIndex = 0;

    RoundController(Player[] playerList){
        currentPlayerId = 0;
        this.playerCount = playerList.length;
    }

    public int getCurrentPlayerId(){
        return currentPlayerId;
    }

    public int getCurrentRound(){
        return currentRound;
    }

    public void setCurrentPlayerId(int currentPlayerId){
        this.currentPlayerId = currentPlayerId;
    }

    public void setPlayerCount(int playerCount){
        this.playerCount = playerCount;
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
    private void endRound(Player[] playerList) { //Pass Round Clicked
        playerList = passRoundPlayerList;
        lastPassedIndex = 0;
        System.out.println("Round Over");
        for(int i = 0; i < playerCount; i++){
            playerHandler.updateResources(playerList[i]);
        }
        currentRound++;
        if(currentRound > MAX_ROUND) {
            System.out.println("GAME OVER");
        }
    }

    public void passRound(Player[] playerList) {
        System.out.println(playerList[0]);
        System.out.println(currentPlayerId);
        for(int i = 0; i < playerList.length; i++){
            System.out.println(playerList[i].getPlayerId());
        }
        playerHandler.passRound(playerList[currentPlayerId]);
        passRoundPlayerList[lastPassedIndex] = playerList[currentPlayerId];
        lastPassedIndex++;
        if(isRoundOver()) {
            endRound(playerList);
        }
        endTurn();
    }

    private boolean isRoundOver() {
        return lastPassedIndex == playerCount-1;
    }

    /**
     * This method is to check every time after a player
     * builds an initial dwelling and ends his/her turn
     *
     * @return  false if initial dwelling part continues
     *          true if it is over, now round is 1
     */
    public boolean isRoundZeroOver(Player[] playerList) {

        for(int i = 0; i < playerCount;i++) {
            if(playerList[i].getStartingDwellingNum() > playerList[i].getDwellingNum()) {
                return false;
            }
        }
        currentRound++;
        return true;
    }


}
