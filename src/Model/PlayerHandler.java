package Model;

import Model.StructureSubclasses.*;
import java.io.Serializable;

public class PlayerHandler implements Serializable{


   public  void buildInitialDwelling(Player player)
   {
      player.setDwellingNum(player.getDwellingNum() + 1);

      if (player.getDwellingNum() < player.getFaction().MAX_DWELLING) {
         player.setWorkerIncome(player.getFaction().DWELLING_WORKER_INCOME);
      }
      if (player.isBuildingDwellingBonus()) {
         player.setVictoryPointNum(player.getVictoryPointNum() + 2);
      }
   }

    public  int buildStructure(Player player, String structure, boolean isThereAdjacentOpponent) {

        if (structure.equals("Dwelling")) {
            if (player.getDwellingNum() < player.getFaction().MAX_DWELLING) {
                if (player.spendFromResources(player.getFaction().DWELLING_WORKER_COST, player.getFaction().DWELLING_GOLD_COST, 0)) {
                   System.out.println("dwelling is built");
                    player.setDwellingNum(player.getDwellingNum() + 1);

                    if (player.getDwellingNum() < player.getFaction().MAX_DWELLING) {
                        player.setWorkerIncome(player.getFaction().DWELLING_WORKER_INCOME);
                    }
                    if (player.isBuildingDwellingBonus()) {
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

                    if (player.isUpgradeToTradingPostBonus()) {
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

        if(actionId == 1) {//Build bridge

        }

        else if(actionId == 2) {// 3 power for 1 priest
            if(player.spendPowerFromBowl(3)) {
                player.gainPriest(1);
                return true;
            }
        }

        else if(actionId == 3) {//4 power for 2 workers
            if(player.spendPowerFromBowl(4)) {
                player.setWorkerNum(player.getWorkerNum()+2);
                return true;
            }
        }

        else if (actionId == 4) {//4 power for 7 gold
            if(player.spendPowerFromBowl(4)) {
                player.setGoldNum(player.getGoldNum()+7);
                return true;
            }
        }
        //After action 5 or 6, player can terraform and build dwelling immedieately
        else if (actionId == 5) {//4 power for 1 free spade
            if(player.spendPowerFromBowl(4)) {
                player.setFreeSpade(player.getFreeSpade()+1);
                return true;
            }
        }

        else if (actionId == 6) {//6 power for 2 free spade
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

//   public static void returnBonusCard(Player player) {
//      //Collect bonus points
//      if (player.isRoundPassed()) {
//         if (player.isHavingDwellingBonus()) {
//            player.addVictoryPoints( player.getDwellingNum());
//         }
//         if (player.isHavingTradingPostBonus()) {
//            player.addVictoryPoints(player.getTradingPostNum() * 2);
//         }
//         if (player.isHavingSanctuaryBonus()) {
//            player.addVictoryPoints(player.getStrongholdNum() * 4);
//            player.addVictoryPoints(player.getSanctuaryNum() * 4);
//         }
//         player.setHavingSanctuary( false);
//         player.setHavingDwellingBonus( false);
//         player.setHavingTradeHouse( false);
//      }
//   }

//   public void getBonusFromFavorTile(Player player) {
//      if (player.isRoundPassed() && player.isPassingTradingPostBonus()) {
//         if (player.getTradingPostNum() == 1) {
//            player.addVictoryPoints(2);
//         }
//         if (player.getTradingPostNum() == 2) {
//            player.addVictoryPoints(3);
//         }
//         if (player.getTradingPostNum() == 3) {
//            player.addVictoryPoints(3);
//         }
//         if (player.getTradingPostNum() == 4) {
//            player.addVictoryPoints(4);
//         }
//      }
//   }

    public boolean exchangeResources(Player player, int exchangeId) {

       if(exchangeId == 1) { //5 power for 1 priest
           if(player.spendPowerFromBowl(5)) {
               player.gainPriest(1);
               return true;
           }
       }
       else if(exchangeId ==2) { //1 priest for 1 worker
           if (player.getPriestNum() >= 1) {
               player.spendPriest(1);
               player.setWorkerNum(player.getWorkerNum()+1);
               return true;
           }
       }
       else if (exchangeId == 3) { //3 power for 1 worker
           if(player.spendPowerFromBowl(3)) {
               player.setWorkerNum(player.getWorkerNum()+1);
               return true;
           }

       }
       else if (exchangeId == 4) { //1 worker for 1 coin
           if(player.getWorkerNum() >= 1) {
               player.setWorkerNum(player.getWorkerNum()-1);
               player.setGoldNum(player.getGoldNum()+1);
           }
       }
       else if (exchangeId == 5) { //1 power for 1 coin
           if(player.spendPowerFromBowl(1)) {
               player.setGoldNum(player.getGoldNum()+1);
               return true;
           }
       }
       else if(exchangeId == 6 ) { //sacrifice power
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
        player.setRoundPassed(true);
    }

    public boolean terraform(Player player, String typeToChange) {
            if(player.spendFreeSpade(typeToChange)) {
                return true;
            }
            return false;
        }

    public boolean townFound(Player player) {
        //TODO
        return false;
    }

    public void progressInReligion(Player player, Religion religion) {
        //TODO
    }

}
