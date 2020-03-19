package Controller;

import Model.*;
import Model.CardsAndTiles.BonusCard;
import Model.CardsAndTiles.CardsAndTiles;
import Model.FactionSubclasses.DariusTheGreat;
import Model.FactionSubclasses.VladTheImpaler;
import View.*;

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
    public void playerChoseBonusCard(BonusCard bonusCard, Player player){
        int playerId = player.getPlayerId();
       int previousCardId = cardsAndTiles.findPlayerCard(playerId);
        if(previousCardId != -1){
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
        player.setCultBonusIncome(player.getCultBonusIncome() +bonusCard.getCultBonus());
        player.getSpecialActionToken().isCultTack = bonusCard.isSpecialCult();
        player.getSpecialActionToken().isSpade = bonusCard.isSpacialSpade();
        player.setHavingDwellingBonus(bonusCard.isDwelling());
        player.setHavingSanctuary(bonusCard.isSanctuary());
        player.setHavingTradeHouse(bonusCard.isTradeHouse());
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
