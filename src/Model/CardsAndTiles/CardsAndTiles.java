package Model.CardsAndTiles;

import java.util.ArrayList;

public class CardsAndTiles {
    ArrayList<TownTile> townTiles;
    ArrayList<BonusCard> bonusCards;
    ArrayList<FavorTile> favorTiles;
    ArrayList<ScoringTile> scoringTiles;

    public CardsAndTiles(ArrayList<TownTile> townTiles, ArrayList<BonusCard> bonusCards, ArrayList<FavorTile> favorTiles, ArrayList<ScoringTile> scoringTiles) {
        createTownTile(townTiles);
        createBonusCard(bonusCards);
        createFavorTile(favorTiles);
        createScoringTile(scoringTiles);

        this.townTiles = townTiles;
        this.bonusCards = bonusCards;
        this.favorTiles = favorTiles;
        this.scoringTiles = scoringTiles;

    }

    /**
     * Town tiles were created and added into arraylist.
     * Keycount will be used religion part. If you have not town key you cannot place 10th place in any cult track part.
     * (for going last place (10th) of each religion part, you need at least one key).
    **/
    public static void createTownTile(ArrayList<TownTile> townTiles){
        townTiles = new ArrayList<>();
        townTiles.add(new TownTile(0,0,8,1,1,1,1,false,0,0));
        townTiles.add(new TownTile(0,0,8,1,1,1,1,false,0,0));
        townTiles.add(new TownTile(0,1,9,0,0,0,0,false,0,0));
        townTiles.add(new TownTile(0,1,9,0,0,0,0,false,0,0));
        townTiles.add(new TownTile(0,0,7,0,0,0,0,false,2,0));
        townTiles.add(new TownTile(0,0,7,0,0,0,0,false,2,0));
        townTiles.add(new TownTile(8,0,6,0,0,0,0,false,0,0));
        townTiles.add(new TownTile(8,0,6,0,0,0,0,false,0,0));
        townTiles.add(new TownTile(0,0,5,0,0,0,0,false,0,6));
        townTiles.add(new TownTile(0,0,5,0,0,0,0,false,0,6));
    }

    /**
     * Bonus cards were created and added  into arrayList.
     * The attributes of bonus cards were arranged by looking cards and its requirements, incomes and bonuses.
     * @param bonusCards
     */
    public static void createBonusCard(ArrayList<BonusCard> bonusCards){
        bonusCards = new ArrayList<>();
        bonusCards.add(new BonusCard(0,false,0,0,0,0,0,0,1,false,false,false,false,false));
        bonusCards.add(new BonusCard(0,false,0,3,0,0,1,0,0,false,false,false,false,false));
        bonusCards.add(new BonusCard(0,false,6,0,0,0,0,0,0,false,false,false,false,false));
        bonusCards.add(new BonusCard(0,false,0,3,1,0,0,0,0,false,false,false,false,false));
        bonusCards.add(new BonusCard(0,false,2,0,0,0,0,0,0,true,false,false,false,false));
        bonusCards.add(new BonusCard(0,false,4,0,0,0,0,1,0,false,true,false,false,false));
        bonusCards.add(new BonusCard(0,false,2,0,0,1,0,0,0,false,false,true,false,false));
        bonusCards.add(new BonusCard(0,false,0,0,0,2,1,0,0,false,false,false,true,false));
        bonusCards.add(new BonusCard(0,false,0,0,0,4,2,0,0,false,false,false,false,true));

    }

    /**
     * Favor Tiles were created and added into arraylist.
     * The attributes of favor tiles were arranged by looking tiles and its requirements, incomes and bonuses.
     * @param favorTiles
     */
    public static void createFavorTile(ArrayList<FavorTile> favorTiles){
        favorTiles = new ArrayList<>();
        favorTiles.add(new FavorTile(1,3,0,0,0,0,0,0,0,0,
                false,false,false,false,false));
        favorTiles.add(new FavorTile(1,0,3,0,0,0,0,0,0,0,
                false,false,false,false,false));
        favorTiles.add(new FavorTile(1,0,0,3,0,0,0,0,0,0,
                false,false,false,false,false));
        favorTiles.add(new FavorTile(1,0,0,0,3,0,0,0,0,0,
                false,false,false,false,false));
        favorTiles.add(new FavorTile(3,0,0,2,0,6,0,0,0,0,
                false,true,false,false,false));
        favorTiles.add(new FavorTile(3,2,0,0,0,0,0,0,0,0,
                true,false,false,false,false));
        favorTiles.add(new FavorTile(3,0,2,0,0,0,4,0,0,0,
                false,false,false,false,false));
        favorTiles.add(new FavorTile(3,0,0,0,2,0,1,1,0,0,
                false,false,false,false,false));
        favorTiles.add(new FavorTile(3,0,0,1,0,0,0,0,3,0,
                false,false,false,false,false));
        favorTiles.add(new FavorTile(3,1,0,0,0,0,0,0,0,3,
                false,false,true,false,false));
        favorTiles.add(new FavorTile(3,0,1,0,0,0,0,0,0,0,
                false,false,false,false,true));
        favorTiles.add(new FavorTile(3,0,0,0,1,0,0,0,0,2,
                false,false,false,true,false));
    }


    /**
     * The scoring tiles were created and added into arrayList.
     * The attributes of scoring tiles were arranged by looking tiles and its requirements, incomes and bonuses.
     * However, id and cardcount were not changed. ALso functiality of these variable is unkown by naci dalkiran.
     * @param scoringTiles
     */
    public static void createScoringTile(ArrayList<ScoringTile> scoringTiles){
        scoringTiles = new ArrayList<>();
        scoringTiles.add(new ScoringTile(4,0,0,0,1,0,0,2,0,0,
                true,false,false,true, false,false,false,false,false));
        scoringTiles.add(new ScoringTile(0,4,0,0,0,4,0,2,0,0,
                true,false,false,false, true,false,false,false,false));
        scoringTiles.add(new ScoringTile(0,0,4,0,0,0,0,3,1,0,
                false,true,false,false, false,true,false,false,false));
        scoringTiles.add(new ScoringTile(0,0,0,4,0,0,0,3,1,0,
                false,true,false,true, false,false,true,false,false));
        scoringTiles.add(new ScoringTile(0,0,2,0,0,0,1,5,0,0,
                false,false,true,false, false,true,false,false,false));
        scoringTiles.add(new ScoringTile(4,2,0,0,0,0,1,5,0,0,
                false,false,true,false, true,false,false,false,false));
        scoringTiles.add(new ScoringTile(0,0,0,1,0,0,0,2,0,1,
                false,false,false,false, false,false,true,true,false));
        scoringTiles.add(new ScoringTile(4,0,0,4,0,0,0,5,0,0,
                false,false,false,false, false,false,true,false,true));

        //Todo
    }

}
