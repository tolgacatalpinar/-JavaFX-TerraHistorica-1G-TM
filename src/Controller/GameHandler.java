package Controller;

import Model.*;
import Model.FactionSubclasses.DariusTheGreat;
import Model.FactionSubclasses.VladTheImpaler;
import View.*;

public class GameHandler {

    Player[] playerList;

    public GameHandler( int playerSize)
    {
        playerList = new Player[playerSize];
    }

    public void buildTradingPost( Player player)
    {
        player.buildTradingPost();
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
