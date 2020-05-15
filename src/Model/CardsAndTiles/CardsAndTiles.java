package Model.CardsAndTiles;

import Model.Player;
import Model.Religion;

import java.util.ArrayList;
import java.io.Serializable;
public class CardsAndTiles implements Serializable {
    public ArrayList<TownTile> townTiles;
    public ArrayList<BonusCard> bonusCards;
    public ArrayList<FavorTile> favorTiles;
    public ArrayList<ScoringTile> scoringTiles;
    public ArrayList<BonusCard> selectedBonusCards;
    public ArrayList<ScoringTile> selectedScoringTiles;
    public Player[] players;
    public CardsAndTiles(int playerNumber, Player[] players) {
        townTiles = new ArrayList<>();
        bonusCards = new ArrayList<>();
        favorTiles = new ArrayList<>();
        scoringTiles = new ArrayList<>();
        this.players = players;
        createTownTile(townTiles);
        createBonusCard(bonusCards);
        createFavorTile(favorTiles);
        createScoringTile(scoringTiles);
        selectedBonusCards = selectedBonusCard(bonusCards,playerNumber);
        //The line below gives nullpointerexception since other parts of the code are not yet completed. - Tolga
        selectedScoringTiles = selectedScoringTiles(scoringTiles);
    }


    /**
     * Town tiles were created and added into arraylist.
     * Keycount will be used religion part. If you have not town key you cannot place 10th place in any cult track part.
     * (for going last place (10th) of each religion part, you need at least one key).
    **/
    public static void createTownTile(ArrayList<TownTile> townTiles){
        townTiles.add(new TownTile(0,0,8,1,1,1,1,0,0));
        townTiles.add(new TownTile(0,0,8,1,1,1,1,0,0));
        townTiles.add(new TownTile(0,1,9,0,0,0,0,0,0));
        townTiles.add(new TownTile(0,1,9,0,0,0,0,0,0));
        townTiles.add(new TownTile(0,0,7,0,0,0,0,2,0));
        townTiles.add(new TownTile(0,0,7,0,0,0,0,2,0));
        townTiles.add(new TownTile(8,0,6,0,0,0,0,0,0));
        townTiles.add(new TownTile(8,0,6,0,0,0,0,0,0));
        townTiles.add(new TownTile(0,0,5,0,0,0,0,0,6));
        townTiles.add(new TownTile(0,0,5,0,0,0,0,0,6));
    }

    /**
     * Bonus cards were created and added  into arrayList.
     * The attributes of bonus cards were arranged by looking cards and its requirements, incomes and bonuses.
     * @param bonusCards is array list.
     */
    public static void createBonusCard(ArrayList<BonusCard> bonusCards){
        bonusCards.add(new BonusCard(0,false,0,0,0,0,1,false,false,false,false,false));
        bonusCards.add(new BonusCard(0,false,0,3,0,1,0,false,false,false,false,false));
        bonusCards.add(new BonusCard(0,false,6,0,0,0,0,false,false,false,false,false));
        bonusCards.add(new BonusCard(0,false,0,3,1,0,0,false,false,false,false,false));
        bonusCards.add(new BonusCard(0,false,2,0,0,0,0,true,false,false,false,false));
        bonusCards.add(new BonusCard(0,false,4,0,0,0,0,false,true,false,false,false));
        bonusCards.add(new BonusCard(0,false,2,0,0,0,0,false,false,true,false,false));
        bonusCards.add(new BonusCard(0,false,0,0,0,1,0,false,false,false,true,false));
        bonusCards.add(new BonusCard(0,false,0,0,0,2,0,false,false,false,false,true));

    }

    /**
     * Favor Tiles were created and added into arraylist.
     * The attributes of favor tiles were arranged by looking tiles and its requirements, incomes and bonuses.
     * @param favorTiles is favor tiles arraylist.
     */
    public static void createFavorTile(ArrayList<FavorTile> favorTiles){
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
     * @param scoringTiles is array list.
     */
    public static void createScoringTile(ArrayList<ScoringTile> scoringTiles){
        scoringTiles.add(new ScoringTile(4,0,0,0,1,0,0,2,0,0,
                true,false,false,true, false,false,false,false,false));
        scoringTiles.add(new ScoringTile(0,4,0,0,0,4,0,2,0,0,
                true,false,false,false, true,false,false,false,false));
        scoringTiles.add(new ScoringTile(0,0,4,0,0,0,0,3,1,0,
                false,true,false,false, false,true,false,false,false));
        scoringTiles.add(new ScoringTile(0,0,0,4,0,0,0,3,1,0,
                false,true,false,false, false,false,true,false,false));
        scoringTiles.add(new ScoringTile(0,0,2,0,0,0,1,5,0,0,
                false,false,true,false, false,true,false,false,false));
        scoringTiles.add(new ScoringTile(4,2,0,0,0,0,1,5,0,0,
                false,false,true,false, true,false,false,false,false));
        scoringTiles.add(new ScoringTile(0,0,0,1,0,0,0,2,0,1,
                false,false,false,false, false,false,true,true,false));
        scoringTiles.add(new ScoringTile(4,0,0,4,0,0,0,5,1,0,
                false,false,false,false, false,false,true,false,true));

    }

    public int findPlayerCard(int playerId){
        int playerCardId = -1;
        for(int i = 0; i < selectedBonusCards.size();++i){
            if(playerId == selectedBonusCards.get(i).getPlayerId()){
                playerCardId =i;
                return playerCardId;
            }
        }
        return playerCardId;
    }

    private ArrayList<ScoringTile> selectedScoringTiles(ArrayList<ScoringTile> scoringTiles) {
        ArrayList<ScoringTile> temp;
        temp = new ArrayList<>();
        int max = 8;
        int min = 0;
        int range = max - min;
        for(int i = 0; i < 6;++i){
            int rand = (int)(Math.random() * range) + min;
            temp.add(scoringTiles.get(rand));
            scoringTiles.remove(rand);
            range--;
        }
        return temp;
    }

    private ArrayList<BonusCard> selectedBonusCard(ArrayList<BonusCard> bonusCards,int playerNumber) {
        ArrayList<BonusCard> temp;
        temp = new ArrayList<>();
        int max = playerNumber + 3;
        int min = 0;
        int range = max - min;
        for(int i = 0; i < max;++i){
            int rand = (int)(Math.random() * range) + min;
            temp.add(bonusCards.get(rand));
            bonusCards.remove(rand);
            range--;
        }
        return temp;
    }

    public void returnScoringTileBonus(Player player, int round){

    }
    public void returnBonusCards(int playerId,int choiceIndex){

    }
    public void playerChoseBonusCard(BonusCard bonusCard, Player player) {
        if (bonusCard.isPlayerOcupied()) {
            System.err.println(" Please choose different Bonus Card \n" +
                    "This card was chosen by another player");

        }
        else {
            int playerId = player.getPlayerId();
            int previousCardId = this.findPlayerCard(playerId);
            if (previousCardId != -1) {
                selectedBonusCards.get(previousCardId).setPlayerOcupied(false);
                selectedBonusCards.get(previousCardId).setPlayerId(-1);
                player.setShipLevel(player.getShipLevel() - selectedBonusCards.get(previousCardId).getShippingRange());
                player.getSpecialActionToken().isCultTack = false;
                player.getSpecialActionToken().isSpade = false;
            }
            bonusCard.setPlayerOcupied(true);
            bonusCard.setPlayerId(playerId);
            player.setGoldNum(player.getGoldNum() + bonusCard.getGoldBonus());
            player.setWorkerNum(player.getWorkerNum() + bonusCard.getWorkerBonus());
            player.addPowerToBowl(bonusCard.getPowerBonus());
            player.setShipLevel(player.getShipLevel() + bonusCard.getShippingRange());
            player.setPriestNum(player.getPriestNum() + bonusCard.getPriestBonus());
            player.getSpecialActionToken().isCultTack = bonusCard.isSpecialCult();
            player.getSpecialActionToken().isSpade = bonusCard.isSpacialSpade();
            player.setHavingDwellingBonus(bonusCard.isDwelling());
            player.setHavingSanctuary(bonusCard.isSanctuary());
            player.setHavingTradeHouse(bonusCard.isTradeHouse());
        }
    }

    public void playerChooseFavorTile(FavorTile favorTile,Player player, Religion religion){
        if(favorTile.getPlayerIds().size() >= favorTile.getNumberOfPlayer() ){
            System.err.println("You cannot choose this Favor");
        }
        else
        {
            favorTile.getPlayerIds().add((Integer) player.getPlayerId() );
            player.addPowerToBowl(religion.updateReligion(favorTile.getIslamBonus(),player.getPlayerId(),player.getKey()));
            player.addPowerToBowl(religion.updateReligion(favorTile.getChristianityBonus(),player.getPlayerId(),player.getKey()));
            player.addPowerToBowl(religion.updateReligion(favorTile.getJewBonus(),player.getPlayerId(),player.getKey()));
            player.addPowerToBowl(religion.updateReligion(favorTile.getHinduismBonus(),player.getPlayerId(),player.getKey()));

            religion.updateReligion(favorTile.getChristianityBonus(),player.getPlayerId(),player.getKey());

            player.setTownPowerValue(6);
            player.addPowerToBowl( favorTile.getPowerBonus());
            player.setWorkerNum(player.getWorkerNum() + favorTile.getWorkerBonus());
            player.setGoldNum(player.getGoldNum() + favorTile.getGoldBonus());
            player.setVictoryPointNum(player.getVictoryPointNum() + favorTile.getVictoryPoint());
            player.setUpgradeToTradingPostBonus(favorTile.isTradingHouse());
            player.setPassingTradingPostBonus(favorTile.isPassingBonusForTradingHouse());
            player.setBuildingDwellingBonus(favorTile.isDwellingBonus());
            if(favorTile.isSpecialCult())
                player.getSpecialActionToken().isCultTack = favorTile.isSpecialCult();

        }
    }

    public void playerChooseTownTile(TownTile townTile, Player player, Religion religion){
        if(townTile.isOccupied()){
            System.err.println("This Tile was chosen by another player");
        }
        else
        {
            townTile.setPlayerId(player.getPlayerId());
            player.addPowerToBowl( townTile.getPowerBonus());
            player.setPriestNum(player.getPriestNum() + townTile.getPriestBonus());
            player.setVictoryPointNum(player.getVictoryPointNum() + townTile.getVictoryBonus());
            player.setWorkerNum(player.getWorkerNum() + townTile.getWorkerBonus());
            player.setGoldNum(player.getGoldNum() + townTile.getGoldBonus());
            player.addPowerToBowl(religion.updateReligion(townTile.getIslamPoint(),player.getPlayerId(),player.getKey()));
            player.addPowerToBowl(religion.updateReligion(townTile.getChristianityPoint(),player.getPlayerId(),player.getKey()));
            player.addPowerToBowl(religion.updateReligion(townTile.getJewishPoint(),player.getPlayerId(),player.getKey()));
            player.addPowerToBowl(religion.updateReligion(townTile.getHinduismPoint(),player.getPlayerId(),player.getKey()));
        }
    }




   public ArrayList<TownTile> getTownTiles() {
      return townTiles;
   }

   public ArrayList<BonusCard> getBonusCards() {
      return bonusCards;
   }

   public ArrayList<FavorTile> getFavorTiles() {
      return favorTiles;
   }

   public ArrayList<ScoringTile> getScoringTiles() {
      return scoringTiles;
   }

   public ArrayList<BonusCard> getSelectedBonusCards() {
      return selectedBonusCards;
   }

   public ArrayList<ScoringTile> getSelectedScoringTiles() {
      return selectedScoringTiles;
   }
}
