package Model.FactionSubclasses;

import Model.Faction;
import Model.Player;

public class VladTheImpaler extends Faction { // chaos magician
    public VladTheImpaler() {
        INITIAL_WORKER = 4;
        INITIAL_GOLD = 15;
        INITIAL_HINDUISM = 2; //fire
        INITIAL_DWELLING_NUMBER = 1;
        DWELLING_GOLD_COST = 2;
        DWELLING_WORKER_COST = 1;
        DWELLING_WORKER_INCOME = 1;
        favorTilesAfterBuildingTemple = 2;
    }


}
