package Model;

import Model.StructureSubclasses.*;

public class PlayerHandler {



    public int buildStructure(Player player, Structure structure, Boolean isThereAdjacentOpponent) {

        if (structure instanceof Dwelling) {
            if (player.getDwellingNum() < player.MAX_DWELLING) {
                if (player.spendFromResources(player.getFaction().DWELLING_WORKER_COST, player.getFaction().DWELLING_GOLD_COST, 0)) {
                    player.setDwellingNum(player.getDwellingNum() + 1);

                    if (player.getDwellingNum() < player.MAX_DWELLING) {
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

        if (structure instanceof TradingHouse) {
            if (player.getTradingPostNum() < player.MAX_TRADING_POST) {
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
        if (structure instanceof Stronghold) {
            if (player.getStrongholdNum() < player.MAX_STRONGHOLD) {
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
        if (structure instanceof Sanctuary) {
            if (player.getSanctuaryNum() < player.MAX_SANCTUARY) {
                if (player.spendFromResources(player.getFaction().SANCTUARY_WORKER_COST, player.getFaction().SANCTUARY_GOLD_COST, 0)) {
                    player.setTempleNum(player.getTempleNum() - 1);
                    player.setSanctuaryNum(player.getSanctuaryNum() + 1);
                    player.gainPriest(player.getFaction().SANCTUARY_PRIEST_INCOME);
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
        if (structure instanceof Temple) {
            if (player.getTempleNum() < player.MAX_TEMPLE) {
                if (player.spendFromResources(player.getFaction().TEMPLE_WORKER_COST, player.getFaction().TEMPLE_GOLD_COST, 0)) {
                    player.setTradingPostNum(player.getTradingPostNum()-1);
                    player.setTempleNum(player.getTempleNum()+1);
                    player.gainPriest(player.getFaction().TEMPLE_PRIEST_INCOME);
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
        return 0; //Can be changed, represents structure parameter is buggy
    }


    public void usePowerAction(String action, Player player) {

        if(action == "power to priest") {
            if(player.spendPowerFromBowl(3)){
                player.gainPriest(1);
            }
        }

        if(action == "power to worker") {
            if(player.spendPowerFromBowl(4)){
                player.setWorkerNum(player.getWorkerNum()+2);
            }
        }

        if(action == "power to bridge") {
            if(player.spendPowerFromBowl(3)){
                player.setBridgeNum(player.getBridgeNum()+1);
            }
        }

        if (action == "power to gold") {
            if (player.spendPowerFromBowl(4)) {
                player.setGoldNum(player.getGoldNum()+7);
            }
        }

        if (action == "power to a spade") {
            if (player.spendPowerFromBowl(4)) {
                //TODO
            }
        }

        if (action == "power to two spades") {
            if (player.spendPowerFromBowl(6)) {
                //TODO
            }
        }

    }
    public void upgradeSpadeLevel(Player player) {
        if(player.getSpadeLevel() < 3) {
            player.setSpadeLevel(player.getSpadeLevel()+1);
            player.setTerraformWorkerCost(player.getTerraformWorkerCost()-1);
            player.spendPriest(player.getFaction().SPADE_PRIEST_COST);
        }
        else {
            System.out.println("Max spade level");
        }

    }

    public boolean upgradeShippingLevel(Player player) {
        if(player.getShipLevel()< player.getFaction().MAX_SHIPPING) {
            if(player.spendFromResources(0, player.getFaction().SHIPPING_GOLD_COST, player.getFaction().SHIPPING_PRIEST_COST)) {
                player.setShipLevel(player.getShipLevel()+1);
                player.addVictoryPoints(player.getFaction().SHIPPING_UPGRADE_VICTORY_POINTS[player.getShipLevel()-1]);
                return true;
            }
            else {
                System.out.println("Not enough resources");
            }

        }
        else {
            System.out.println("Maximum level");
        }
        return false;

    }



}
