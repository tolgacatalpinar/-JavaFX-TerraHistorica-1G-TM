package Model.FactionSubclasses;

import Model.Faction;

public class LeonardoDaVinci extends Faction { //Engineer
  
  public LeonardoDaVinci()
  {
        INITIAL_WORKER = 2;
        INITIAL_GOLD = 10;
        INITIAL_PRIEST = 0;
        INITIAL_POWER = 0;
        INITIAL_BUDISM = 0; // fire
        INITIAL_ISLAM = 0;  //water
        INITIAL_JUDAISM = 0;  //earth     
        INITIAL_CHRISTIANITY = 0; //air
        INITIAL_VICTORY_POINT = 16;
        INITIAL_DWELLING_NUMBER = 2;
        DWELLING_GOLD_COST = 1;
        DWELLING_WORKER_COST = 1;
        DWELLING_WORKER_INCOME = 1;
}
