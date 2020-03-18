package Model.FactionSubclasses;

import Model.Faction;

public class DariusTheGreat extends Faction { //nomad
         
        public DariusTheGreat ()
       {
        INITIAL_WORKER = 2;
        INITIAL_GOLD = 15;
        INITIAL_PRIEST = 0;
        INITIAL_POWER = 0;
        INITIAL_HINDUISM = 1; // fire
        INITIAL_JUDAISM = 1;  //earth
        INITIAL_VICTORY_POINT = 19;
        INITIAL_DWELLING_NUMBER = 3;
        DWELLING_GOLD_COST = 2;
        DWELLING_WORKER_COST = 1;
        DWELLING_WORKER_INCOME = 1;
       }
}
