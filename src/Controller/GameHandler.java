package Controller;

import Model.*;
import Model.CardsAndTiles.BonusCard;
import Model.CardsAndTiles.CardsAndTiles;
import Model.CardsAndTiles.FavorTile;
import Model.FactionSubclasses.DariusTheGreat;
import Model.FactionSubclasses.VladTheImpaler;
import View.*;

import java.util.ArrayList;

public class GameHandler {

    Player[] playerList;

    CardsAndTiles cardsAndTiles;

    public GameHandler( int playerSize)
    {
        cardsAndTiles = new CardsAndTiles(playerSize);
        playerList = new Player[playerSize];
    }


    /**
     * playerChoseBonusCard(cardsAndTiles.bonusCards.get(cardIndex), player, playerID);
     * notTakenCard is not used.
     * TODO
     * @param bonusCard which is chosen by the player.
     * @param player who select bonusCard.
     */
    public void playerChoseBonusCard(BonusCard bonusCard, Player player) {
        if (bonusCard.isPlayerOcupied()) {
            System.err.println(" Please choose different Bonus Card \n" +
                    "This card was chosen by another player");

        }
        else {
            int playerId = player.getPlayerId();
            int previousCardId = cardsAndTiles.findPlayerCard(playerId);
            if (previousCardId != -1) {
                cardsAndTiles.bonusCards.get(previousCardId).setPlayerOcupied(false);
                cardsAndTiles.bonusCards.get(previousCardId).setPlayerId(-1);
                player.setGoldIncome(player.getGoldIncome() - cardsAndTiles.bonusCards.get(previousCardId).getGoldBonus());
                player.setWorkerIncome(player.getWorkerIncome() - cardsAndTiles.bonusCards.get(previousCardId).getWorkerBonus());
                player.setPowerIncome(player.getPowerIncome() - cardsAndTiles.bonusCards.get(previousCardId).getPowerBonus());
                player.setShipLevel(player.getShipLevel() - cardsAndTiles.bonusCards.get(previousCardId).getShippingRange());
                player.setPriestIncome(player.getPriestIncome() - cardsAndTiles.bonusCards.get(previousCardId).getPriestBonus());
                player.setPerBuildingIncome(player.getPerBuildingIncome() - cardsAndTiles.bonusCards.get(previousCardId).getPerBuildingBonus());
                player.setCultBonusIncome(player.getCultBonusIncome() - cardsAndTiles.bonusCards.get(previousCardId).getCultBonus());
                player.getSpecialActionToken().isCultTack = false;
                player.getSpecialActionToken().isSpade = false;
                player.setHavingDwellingBonus(false);
                player.setHavingSanctuary(false);
                player.setHavingTradeHouse(false);
            }
            bonusCard.setPlayerOcupied(true);
            bonusCard.setPlayerId(playerId);
            player.setGoldIncome(player.getGoldIncome() + bonusCard.getGoldBonus());
            player.setWorkerIncome(player.getWorkerIncome() + bonusCard.getWorkerBonus());
            player.setPowerIncome(player.getPowerIncome() + bonusCard.getPowerBonus());
            player.setShipLevel(player.getShipLevel() + bonusCard.getShippingRange());
            player.setPriestIncome(player.getPriestIncome() + bonusCard.getPriestBonus());
            player.setPerBuildingIncome(player.getPerBuildingIncome() + bonusCard.getPerBuildingBonus());
            player.setCultBonusIncome(player.getCultBonusIncome() + bonusCard.getCultBonus());
            player.getSpecialActionToken().isCultTack = bonusCard.isSpecialCult();
            player.getSpecialActionToken().isSpade = bonusCard.isSpacialSpade();
            player.setHavingDwellingBonus(bonusCard.isDwelling());
            player.setHavingSanctuary(bonusCard.isSanctuary());
            player.setHavingTradeHouse(bonusCard.isTradeHouse());
        }
    }

  //  private int islamBonus; //water
   // private int christianityBonus; //Air
   // private int hinduismBonus; //Fire
    //private int jewBonus; //earth


    //From now on, when passing (see Action #8, page 14), get 2/3/3/4
    //Victory points for 1/2/3/4 of your Trading houses on the Game board.
    boolean isPassingBonusForTradingHouse;
    public void playerChooseFavorTile(FavorTile favorTile,Player player, Religion religion){
        if(favorTile.getPlayerIds().size() >= favorTile.getNumberOfPlayer() ){
            System.err.println("You cannot choose this Favor");
        }
        else
        {
            favorTile.getPlayerIds().add((Integer) player.getPlayerId() );
            //TODO religion part is missing.
            player.setNeededCombinedPowerTown(6);
            player.setPowerIncome(player.getPowerIncome() + favorTile.getPowerBonus());
            player.setWorkerIncome(player.getWorkerIncome() + favorTile.getWorkerBonus());
            player.setGoldIncome(player.getGoldIncome() + favorTile.getGoldBonus());
            player.setVictoryPointNum(player.getVictoryPointNum() + favorTile.getVictoryPoint());
            player.setTradingEveryRound(favorTile.isTradingHouse());
            player.setDwellingToTradingEveryRound(favorTile.isPassingBonusForTradingHouse());
            player.setDwellingEveryRound(favorTile.isDwellingBonus());
            if(favorTile.isSpecialCult())
            player.getSpecialActionToken().isCultTack = favorTile.isSpecialCult();

        }
    }

    public void buildTradingPost( Player player)
    {
        //player.buildTradingPost();
        if(player.getFaction() instanceof DariusTheGreat )
        {
            nomadAbility( player);
        }
    }

    public void chooseScoringTile( Player player)
    {
        if( player.getFaction() instanceof VladTheImpaler)
            selectScoringTile(player, 2);
        else
            selectScoringTile(player, 1);
    }
    public void selectScoringTile( Player player, int count)
    {
        if( count == 0)
        {
            return;
        }
        count --;
        ScoringTile.displayScoringTileChoice();
    }

    public void nomadAbility( Player player)
    {
        // TO DO
    }
}
