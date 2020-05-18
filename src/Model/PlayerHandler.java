package Model;
import java.io.Serializable;
import java.util.ArrayList;

public class PlayerHandler implements Serializable{


   public  void buildInitialDwelling(Player player)
   {
      player.setDwellingNum(player.getDwellingNum() + 1);

      if (player.getDwellingNum() < player.getFaction().MAX_DWELLING) {
         player.setWorkerIncome(player.getFaction().DWELLING_WORKER_INCOME + player.getWorkerIncome());
      }
   }

    public  int buildStructure(Player player, String structure, boolean isThereAdjacentOpponent) {

        if (structure.equals("Dwelling")) {
            if (player.getDwellingNum() < player.getFaction().MAX_DWELLING) {
                if (player.spendFromResources(player.getFaction().DWELLING_WORKER_COST, player.getFaction().DWELLING_GOLD_COST, 0)) {
                   System.out.println("dwelling is built");
                    player.setDwellingNum(player.getDwellingNum() + 1);

                    if (player.getDwellingNum() < player.getFaction().MAX_DWELLING) {
                        player.setWorkerIncome(player.getFaction().DWELLING_WORKER_INCOME + player.getWorkerIncome());
                    }
                    if (player.isDwellingFavorTile()) {
                        player.setVictoryPointNum(player.getVictoryPointNum() + 2);
                    }
                    if (player.isDwellingScoringTile()) {
                        player.setVictoryPointNum(player.getVictoryPointNum() + 2);
                    }
                    return 1; //As successful
                }
                else {
                    return -1; //Not enough resources
                }
            }
            else {
                return 0; //Cannot build more
            }

        }

        if (structure.equals("TradingPost")) {
            if (player.getTradingPostNum() < player.getFaction().MAX_TRADING_POST) {
                int goldCost = player.getFaction().TRADING_POST_GOLD_COST;
                if (isThereAdjacentOpponent) {
                    goldCost /= 2;
                }
                if (player.spendFromResources(player.getFaction().TRADING_POST_WORKER_COST, goldCost, 0)) {

                    player.setDwellingNum(player.getDwellingNum() - 1);
                    player.setTradingPostNum(player.getTradingPostNum() + 1);

                    if (player.isTradingPostFavorTile()) {
                        player.setVictoryPointNum(player.getVictoryPointNum() + 3);
                    }
                    if (player.isTradingPostScoringTile()) {
                        player.setVictoryPointNum(player.getVictoryPointNum() + 3);
                    }
                    player.setGoldIncome(player.getFaction().tradingPostGoldIncome[player.getTradingPostNum() - 1] + 1);
                    player.setPowerIncome(player.getFaction().tradingPostPowerIncome[player.getTradingPostNum() - 1] + 1);
                    return 1; //As successful
                }
                else {
                    return -1; //Not enough resources
                }
            }
            else {
                return 0; //Cannot build more
            }
        }
        if (structure.equals("Stronghold")) {
            if (player.getStrongholdNum() < player.getFaction().MAX_STRONGHOLD) {
                if (player.spendFromResources(player.getFaction().STRONGHOLD_WORKER_COST, player.getFaction().STRONGHOLD_GOLD_COST, 0)) {
                    player.setTradingPostNum(player.getTradingPostNum() - 1);
                    player.setStrongholdNum(player.getStrongholdNum() + 1);
                    player.getFaction().afterStronghold(); //Not implemented yet
                    player.addPowerToBowl(player.getFaction().getPowerAfterStronghold);
                    if( player.isSanctuaryStrongholdScoringTile()) {
                        player.setVictoryPointNum(player.getVictoryPointNum() + 5);
                    }
                    return 1;
                }
                else {
                    return -1; //Not enough resources
                }
            }
            else {
                return 0; //Cannot build more
            }
        }
        if (structure.equals("Sanctuary")) {
            if (player.getSanctuaryNum() < player.getFaction().MAX_SANCTUARY) {
                if (player.spendFromResources(player.getFaction().SANCTUARY_WORKER_COST, player.getFaction().SANCTUARY_GOLD_COST, 0)) {
                    player.setTempleNum(player.getTempleNum() - 1);
                    player.setSanctuaryNum(player.getSanctuaryNum() + 1);
                    player.setPriestIncome(player.getPriestIncome() + player.getFaction().SANCTUARY_PRIEST_INCOME);
                    if(player.isSanctuaryStrongholdScoringTile()) {
                        player.setVictoryPointNum(player.getVictoryPointNum() + 5);
                    }
                    return player.getFaction().favorTilesAfterBuildingTemple;
                }
                else {
                    return -1; //Not enough resources
                }
            }
            else {
                return 0; //Cannot build more
            }
        }
        if (structure.equals("Temple")) {
            if (player.getTempleNum() < player.getFaction().MAX_TEMPLE) {
                if (player.spendFromResources(player.getFaction().TEMPLE_WORKER_COST, player.getFaction().TEMPLE_GOLD_COST, 0)) {
                    player.setTradingPostNum(player.getTradingPostNum()-1);
                    player.setTempleNum(player.getTempleNum()+1);
                    player.setPriestIncome(player.getPriestIncome() + player.getFaction().TEMPLE_PRIEST_INCOME);
                    return player.getFaction().favorTilesAfterBuildingTemple;
                }
                else {
                    return -1; //Not enough resources
                }
            }
            else {
                return 0; //Cannot build more
            }
        }
        return -2; //Wrong string
    }

    public void acceptPowerFromAdjacentOpponent(int powerVal, Player player) {
        player.setVictoryPointNum(player.getVictoryPointNum() - (powerVal - 1));
        player.addPowerToBowl(powerVal);
    }

    public boolean usePowerAction(int actionId, Player player) {

        if(actionId == 0) {//Build bridge
            if(player.getBridgeNum() < player.getFaction().MAX_BRIDGE) {
                if(player.spendPowerFromBowl(3)) {
                    player.setBridgeNum(player.getBridgeNum()+1);
                    player.addVictoryPoints(player.getFaction().victoryPointForEachConnectingBridges);
                    return true;
                }
            }
        }

        else if(actionId == 1) {// 3 power for 1 priest
            if(player.spendPowerFromBowl(3)) {
                player.gainPriest(1);
                return true;
            }
        }

        else if(actionId == 2) {//4 power for 2 workers
            if(player.spendPowerFromBowl(4)) {
                player.setWorkerNum(player.getWorkerNum()+2);
                return true;
            }
        }

        else if (actionId == 3) {//4 power for 7 gold
            if(player.spendPowerFromBowl(4)) {
                player.setGoldNum(player.getGoldNum()+7);
                return true;
            }
        }
        //After action 5 or 6, player can terraform and build dwelling immedieately
        else if (actionId == 4) {//4 power for 1 free spade
            if(player.spendPowerFromBowl(4)) {
                player.setFreeSpade(player.getFreeSpade()+1);
                return true;
            }
        }

        else if (actionId == 5) {//6 power for 2 free spade
            if (player.spendPowerFromBowl(6)) {
                player.setFreeSpade(player.getFreeSpade() + 2);
                return true;
            }
        }
        return false;
    }

    public int upgradeSpadeLevel(Player player) {
        if(player.getSpadeLevel() < player.getFaction().MAX_SPADE_LEVEL) {
            if(player.spendFromResources(player.getFaction().SPADE_WORKER_COST,player.getFaction().SPADE_GOLD_COST,player.getFaction().SPADE_PRIEST_COST)) {
                player.setSpadeLevel(player.getSpadeLevel()+1);
                player.setTerraformWorkerCost(player.getTerraformWorkerCost()-1);
                if(player.getSpadeLevel() == 2)
                    player.addVictoryPoints(player.getFaction().SPADE_FIRST_UPGRADE_VICTORY);
                else if (player.getSpadeLevel() == 3) {
                    player.addVictoryPoints(player.getFaction().SPADE_SECOND_UPGRADE_VICTORY);
                }
                return 1;
            }
            else {
                return -1; //Not enough resources
            }
        }
        else {
            System.out.println("Max spade level");
            return 0; //Max spade
        }

    }
    public int upgradeShippingLevel(Player player) {
       if(player.getFaction().hasShipping) {
           if(player.getShipLevel()< player.getFaction().MAX_SHIPPING) {
               if(player.spendFromResources(0, player.getFaction().SHIPPING_GOLD_COST, player.getFaction().SHIPPING_PRIEST_COST)) {
                   player.setShipLevel(player.getShipLevel()+1);
                   player.addVictoryPoints(player.getFaction().SHIPPING_UPGRADE_VICTORY_POINTS[player.getShipLevel()-1]);
                   return 1; //Successful
               }
               else {
                   System.out.println("Not enough resources");
                   return -1; //Not enough resources
               }

           }
           else {
               System.out.println("Maximum level");
               return 0; //Max level
           }
       }
       else {
           return -2; //Faction has no shipping
       }

    }
    public boolean exchangeResources(Player player, int exchangeId) {

       if(exchangeId == 0) { //5 power for 1 priest
           if(player.spendPowerFromBowl(5)) {
               player.gainPriest(1);
               return true;
           }
       }
       else if(exchangeId ==1) { //1 priest for 1 worker
           if (player.getPriestNum() >= 1) {
               player.spendPriest(1);
               player.setWorkerNum(player.getWorkerNum()+1);
               return true;
           }
       }
       else if (exchangeId == 2) { //3 power for 1 worker
           if(player.spendPowerFromBowl(3)) {
               player.setWorkerNum(player.getWorkerNum()+1);
               return true;
           }

       }
       else if (exchangeId == 3) { //1 worker for 1 coin
           if(player.getWorkerNum() >= 1) {
               player.setWorkerNum(player.getWorkerNum()-1);
               player.setGoldNum(player.getGoldNum()+1);
           }
       }
       else if (exchangeId == 4) { //1 power for 1 coin
           if(player.spendPowerFromBowl(1)) {
               player.setGoldNum(player.getGoldNum()+1);
               return true;
           }
       }
       else if(exchangeId == 5 ) { //sacrifice power
           if (2 < player.getBowlTwoPower()) {
               player.setBowlTwoPower(player.getBowlTwoPower()-2);
               player.setBowlThreePower(player.getBowlThreePower()+1);
               return true;
           }
       }
        return false;
    }

    /**
     * Updates resources of player according to income, end of round
     * @param player
     */
    public void updateResources(Player player) {

        player.setGoldNum(player.getGoldNum() + player.getGoldIncome());
        player.setWorkerNum(player.getWorkerNum() + player.getWorkerIncome());
        player.gainPriest(player.getPriestIncome());
        player.addPowerToBowl(player.getPowerIncome());

    }

    public void passRound(Player player) {
        if(player.isPassingFavorTile() && player.getTradingPostNum() > 0) {
            int arr[] = {2,3,3,4};
            player.addVictoryPoints(arr[player.getTradingPostNum()-1]);
        }
        player.setRoundPassed(true);
    }

    public boolean terraform(Player player, String typeToChange) {
            if(player.spendFreeSpade(typeToChange)) {
                return true;
            }
            return false;
        }

    public void townFound(Player player) {
        player.setWorkerNum(player.getWorkerNum() + player.getFaction().foundingTownWorkerBonus);
        player.addVictoryPoints(player.getFaction().getAdditionalVictoryPointsAfterTown);
        if(player.isTownScoringTile()) {
            player.addVictoryPoints(5);
        }
    }

    public int getWinner(Player[] playerList, ArrayList<ArrayList<Integer>>[] religionScores, ArrayList<Integer>[] pathScores) {
        int[] playerVictoryPoints = new int[playerList.length];
        for(int i = 0; i < playerList.length; i++) {
            playerVictoryPoints[i] = playerList[i].getVictoryPointNum();
        }
        for (int i = 0; i< 4;i++){
            int countReligion = 8;
            for (int j = 0; j < 3; j++){

                for(int shaped = 1; shaped < religionScores[i].get(j).size();shaped++) {
                }
                for(int k = 0; k < religionScores[i].get(j).size(); k++){
                    int player_id = religionScores[i].get(j).get(k);
                    playerVictoryPoints[player_id] += (int)(countReligion/(j+1));
                    countReligion /= 2;
                }
            }

        }
        int count = 18;
        for (int j = 0; j < 3; j++){
            for(int k = 0; k < pathScores[j].size(); k++){
                int player_id = pathScores[j].get(k);
                playerVictoryPoints[player_id] += (int)(count*(j+1)/pathScores[j].size());
                count = count/3;
            }
        }
        //Calculate victory points
        int winnerId = 0;
        for(int i = 0; i < playerList.length;i++) {
            if(playerVictoryPoints[i] > winnerId)
                winnerId = i;
        }
        return winnerId;
    }

}
