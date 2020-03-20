package Model.FactionSubclasses;

import Model.Faction;
import Model.Player;

public class AliesterCrowley extends Faction {
    
    public AliesterCrowley() {
        INITIAL_WORKER = 1;
        INITIAL_GOLD = 15;
        INITIAL_PRIEST = 1;
        INITIAL_ISLAM = 1;  //water
        INITIAL_JUDAISM = 1;  //earth     
        INITIAL_VICTORY_POINT = 15;
        INITIAL_DWELLING_NUMBER = 2;
        DWELLING_GOLD_COST = 2;
        DWELLING_WORKER_COST = 1;
        DWELLING_WORKER_INCOME = 1;

    }

}
