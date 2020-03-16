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
        if(isDariusTheGreat( player))
        {
            nomadAbility( player);
        }
    }

    public void chooseScoringTile( Player player)
    {
        if( isVladTheImpaler( player))
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




    public boolean isDariusTheGreat( Player player)
    {
        if( player.getFaction() instanceof DariusTheGreat)
            return true;
        return false;
    }
    public boolean isVladTheImpaler( Player player)
    {
        if( player.getFaction() instanceof VladTheImpaler)
            return true;
        return false;
    }
    public void nomadAbility( Player player)
    {
        // TO DO
    }
}
