package Model.FactionSubclasses;

import Model.Faction;

public class AliesterCrowley extends Faction {
    
    public AliesterCrowley() {
        INITIAL_WORKER = 1;
        INITIAL_GOLD = 15;
        INITIAL_PRIEST = 1;
        INITIAL_POWER = 0;
        INITIAL_BUDISM = 0; // fire
        INITIAL_ISLAM = 1;  //water
        INITIAL_JUDAISM = 1;  //earth     
        INITIAL_CHRISTIANITY = 0; //air
        INITIAL_VICTORY_POINT = 15;
        INITIAL_DWELLING_NUMBER = 2;
        DWELLING_GOLD_COST = 2;
        DWELLING_WORKER_COST = 1;
        DWELLING_WORKER_INCOME = 1;

    }
}
