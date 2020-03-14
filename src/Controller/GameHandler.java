package Controller;

import Model.*;
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
        if(isNomad( player))
        {
            nomadAbility( player);
        }
    }

    public void chooseScoringTile( Player player)
    {
        if( isChaosMagician( player))
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




    public boolean isNomad( Player player)
    {
        if( player.getFaction() instanceof Nomad)
            return true;
        return false;
    }
    public boolean isChaosMagician( Player player)
    {
        if( player.getFaction() instanceof ChaosMagician)
            return true;
        return false;
    }
    public void nomadAbility( Player player)
    {
        // TO DO
    }
}
