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
        favorTiles.add(new FavorTile(1,3,0,0,0,7,0,0,0,0,
                false,false,false,false,false));
        favorTiles.add(new FavorTile(1,0,3,0,0,7,0,0,0,0,
                false,false,false,false,false));
        favorTiles.add(new FavorTile(1,0,0,3,0,7,0,0,0,0,
                false,false,false,false,false));
        favorTiles.add(new FavorTile(1,0,0,0,3,7,0,0,0,0,
                false,false,false,false,false));
        favorTiles.add(new FavorTile(3,0,0,2,0,6,0,0,0,0,
                false,true,false,false,false));
        favorTiles.add(new FavorTile(3,2,0,0,0,7,0,0,0,0,
                true,false,false,false,false));
        favorTiles.add(new FavorTile(3,0,2,0,0,7,4,0,0,0,
                false,false,false,false,false));
        favorTiles.add(new FavorTile(3,0,0,0,2,7,1,1,0,0,
                false,false,false,false,false));
        favorTiles.add(new FavorTile(3,0,0,1,0,7,0,0,3,0,
                false,false,false,false,false));
        favorTiles.add(new FavorTile(3,1,0,0,0,7,0,0,0,3,
                false,false,true,false,false));
        favorTiles.add(new FavorTile(3,0,1,0,0,7,0,0,0,0,
                false,false,false,false,true));
        favorTiles.add(new FavorTile(3,0,0,0,1,7,0,0,0,2,
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

    private ArrayList<Integer> playerId;
    private int requiredIslam; //water
    private int requiredBudism; //fire
    private int requiredChrist; //Air
    private int requiredJudaism; //earth
    private int priestBonus;
    private int powerBonus;
    private int workerBonus;
    private int victoryBonus;
    private int spadeBonus;
    private int goldBonus;



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
            if(bonusCard.isDwelling()){
                player.setVictoryPointNum(player.getVictoryPointNum() + player.getDwellingNum() *1 );
            }
            if(bonusCard.isTradeHouse()){
                player.setVictoryPointNum(player.getVictoryPointNum() + player.getTradingPostNum() *2 );
            }
            if(bonusCard.isSanctuary()){
                player.setVictoryPointNum(player.getVictoryPointNum() + (player.getSanctuaryNum() + player.getTempleNum()) * 4 );
            }
        }
    }
    public void returnScoringTile(int round1, int round2,Player[] playerList,Religion[] religionArr ){
        if(round1 != round2){
            for (int i = 0; i< playerList.length; i++){
                ScoringTile currentScoringTile = selectedScoringTiles.get(round2 - 1);
                playerList[i].setDwellingScoringTile (currentScoringTile.isDwellingBonus());
                playerList[i].setTradingPostScoringTile (currentScoringTile.isTradingHouseBonus());
                playerList[i].setSanctuaryStrongholdScoringTile (currentScoringTile.isStrongHoldBonus());
                playerList[i].setSpadeScoringTile (currentScoringTile.isRequiredSpade());
                playerList[i].setTownScoringTile (currentScoringTile.isRequiredTown());
                if(currentScoringTile.isRequiredIslam() && currentScoringTile.getRequiredIslam() >= religionArr[0].getRoundBasedPosition()[i] ){
                    helperReturnScoring(playerList[i],currentScoringTile);
                }else if(currentScoringTile.isRequiredChrist() && currentScoringTile.getRequiredChrist() >= religionArr[1].getRoundBasedPosition()[i]){
                    helperReturnScoring(playerList[i],currentScoringTile);
                }else if(currentScoringTile.isRequiredBudism() && currentScoringTile.getRequiredBudism() >=religionArr[3].getRoundBasedPosition()[i] ){
                    helperReturnScoring(playerList[i],currentScoringTile);
                }
                else if(currentScoringTile.isRequiredJudaism() && currentScoringTile.getRequiredJudaism() >=religionArr[2].getRoundBasedPosition()[i] ){
                    helperReturnScoring(playerList[i],currentScoringTile);

                }
            }

        }
    }
    public void helperReturnScoring(Player player, ScoringTile scoringTile){
        player.setPriestNum(player.getPriestNum() + scoringTile.getPriestBonus());
        player.setWorkerNum(player.getWorkerNum() + scoringTile.getWorkerBonus());
        player.addPowerToBowl(scoringTile.getPowerBonus());
        player.setVictoryPointNum(player.getVictoryPointNum() + scoringTile.getVictoryBonus());
        player.setSpadeLevel(player.getSpadeLevel() + scoringTile.getSpadeBonus());
        player.setGoldNum(player.getGoldNum() + scoringTile.getGoldBonus());
    }
    public void playerChooseFavorTile(FavorTile favorTile,Player player, Religion religion){
        if(favorTile.getPlayerIds().size() >= favorTile.getNumberOfPlayer() ){
            System.err.println("You cannot choose this Favor");
        }
        else
        {
            favorTile.getPlayerIds().add((Integer) player.getPlayerId() );
            player.setTownPowerValue(favorTile.getNeededCombinedPowerTown());
            player.addPowerToBowl( favorTile.getPowerBonus());
            player.setWorkerNum(player.getWorkerNum() + favorTile.getWorkerBonus());
            player.setGoldNum(player.getGoldNum() + favorTile.getGoldBonus());
            player.setVictoryPointNum(player.getVictoryPointNum() + favorTile.getVictoryPoint());
            player.setTradingPostFavorTile(favorTile.isTradingHouse());
            player.setPassingFavorTile(favorTile.isPassingBonusForTradingHouse());
            player.setDwellingFavorTile(favorTile.isDwellingBonus());
            if(favorTile.isSpecialCult())
                player.getSpecialActionToken().isCultTack = favorTile.isSpecialCult();

        }
    }

    public void playerChooseTownTile(TownTile townTile, Player player){
        if(townTile.isOccupied()){
            System.err.println("This Tile was chosen by another player");
        }
        else
        {
            townTile.setOccupied(true);
            townTile.setPlayerId(player.getPlayerId());
            player.addPowerToBowl( townTile.getPowerBonus());
            player.setPriestNum(player.getPriestNum() + townTile.getPriestBonus());
            player.setVictoryPointNum(player.getVictoryPointNum() + townTile.getVictoryBonus());
            player.setWorkerNum(player.getWorkerNum() + townTile.getWorkerBonus());
            player.setGoldNum(player.getGoldNum() + townTile.getGoldBonus());
            player.setKey(player.getKey() + 1);

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
