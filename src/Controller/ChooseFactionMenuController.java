package Controller;

import Model.Faction;
import Model.FactionSubclasses.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.io.Serializable;
public class ChooseFactionMenuController  implements Initializable, Serializable{

    @FXML
    Button backButton,  startButton;

    @FXML
    Text playerQueue;

    @FXML
    ImageView wastelandFaction, forestFaction, lakesFaction, desertFaction, mountainsFaction, swampFaction, plainsFaction;

    @FXML
    ImageView wastelandFactionSelected, forestFactionSelected, lakesFactionSelected, desertFactionSelected, mountainsFactionSelected, swampFactionSelected, plainsFactionSelected;

    @FXML
    Button chooseVladTheImpaler, chooseGilgamesh, chooseMorganLeFay, chooseHelenOfTroy, chooseErikTheRed, chooseAmerigoVespucci, chooseDariusTheGreat, chooseRamessesII,
           chooseLeonardoDaVinci, chooseStPatrick, chooseMarieCurie, chooseAleisterCrowley, chooseBuddha, chooseHusseinTheTeaMaker;

    Image imProfile;

    String player1Name, player2Name, player3Name, player4Name, player5Name;
    ArrayList<String> playerNames = new ArrayList<>();
    ArrayList<Faction> factions = new ArrayList<>();
    public int numbersOfPlayer;

    int counter = 0;

    public void getNumbersOfPlayer(String str){
        numbersOfPlayer = Integer.parseInt(str);
    }

    public void getNamesOfPlayer(String player1, String player2, String player3, String player4, String player5){
        player1Name = player1;
        player2Name = player2;
        player3Name = player3;
        player4Name = player4;
        player5Name = player5;

        playerNames.add(player1);
        playerNames.add(player2);
        playerNames.add(player3);
        playerNames.add(player4);
        playerNames.add(player5);
    }

    @FXML
    public void startGameButtonClicked( MouseEvent event) throws IOException {


        Stage stage;


        stage = (Stage) startButton.getScene().getWindow();


        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/GameView.fxml"));
        Parent root = loader.load();
        GameController gameController = loader.getController();
        int totalPlayerNumber = factions.size();
        gameController.loadPlayers(factions, playerNames,totalPlayerNumber);
        gameController.loadInitialMap();
        gameController.loadReligion(totalPlayerNumber);
        gameController.loadCardsAndTiles(totalPlayerNumber);



        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }


    @FXML
    public void backButtonClicked( MouseEvent event) throws IOException {


        Stage stage;


        stage = (Stage) backButton.getScene().getWindow();


        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/MenuViews/MainMenuView.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void showStartButton(){

        if(counter == numbersOfPlayer)
            startButton.setVisible(true);

    }

    public void showPlayerQueue(){
        if(counter == 0 && counter < numbersOfPlayer)
            playerQueue.setText("Choose Faction For " + player1Name);
        else if(counter == 1 && counter < numbersOfPlayer)
            playerQueue.setText("Choose Faction For " + player2Name);
        else if(counter == 2 && counter < numbersOfPlayer)
            playerQueue.setText("Choose Faction For " + player3Name);
        else if(counter == 3 && counter < numbersOfPlayer)
            playerQueue.setText("Choose Faction For " + player4Name);
        else if(counter == 4 && counter < numbersOfPlayer)
            playerQueue.setText("Choose Faction For " + player5Name);
        else
            playerQueue.setText("You can start the game");
    }

    @FXML
    public void vladTheImpalerSelected( MouseEvent event) throws IOException {

        if(counter < numbersOfPlayer) {
            Faction newFaction = new VladTheImpaler();
            factions.add(newFaction);
            wastelandFactionSelected.setVisible(true);
            chooseVladTheImpaler.setVisible(false);
            chooseGilgamesh.setVisible(false);
            counter++;
            showStartButton();
            showPlayerQueue();
        }
    }

    @FXML
    public void gilgameshSelected( MouseEvent event) throws IOException {

        if(counter < numbersOfPlayer) {
            Faction newFaction = new Gilgamesh();
            factions.add(newFaction);
            wastelandFactionSelected.setVisible(true);
            chooseVladTheImpaler.setVisible(false);
            chooseGilgamesh.setVisible(false);
            counter++;
            showStartButton();
            showPlayerQueue();
        }
    }

    @FXML
    public void morganLeFaySelected( MouseEvent event) throws IOException {
        if(counter < numbersOfPlayer) {
            Faction newFaction = new MorganLeFay();
            factions.add(newFaction);
            forestFactionSelected.setVisible(true);
            chooseMorganLeFay.setVisible(false);
            chooseHelenOfTroy.setVisible(false);
            counter++;
            showStartButton();
            showPlayerQueue();
        }
    }

    @FXML
    public void helenOfTroySelected( MouseEvent event) throws IOException {
        if(counter < numbersOfPlayer) {
           Faction newFaction = new HelenOfTroy();
           factions.add(newFaction);
            forestFactionSelected.setVisible(true);
            chooseMorganLeFay.setVisible(false);
            chooseHelenOfTroy.setVisible(false);
            counter++;
            showStartButton();
            showPlayerQueue();
        }
    }

    @FXML
    public void erikTheRedSelected( MouseEvent event) throws IOException {
        if(counter < numbersOfPlayer) {
           Faction newFaction = new ErikTheRed();
           factions.add(newFaction);
            lakesFactionSelected.setVisible(true);
            chooseErikTheRed.setVisible(false);
            chooseAmerigoVespucci.setVisible(false);
            counter++;
            showStartButton();
            showPlayerQueue();
        }
    }

    @FXML
    public void amerigoVespucciSelected( MouseEvent event) throws IOException {
        if(counter < numbersOfPlayer) {
           Faction newFaction = new AmerigoVespucci();
           factions.add(newFaction);
            lakesFactionSelected.setVisible(true);
            chooseErikTheRed.setVisible(false);
            chooseAmerigoVespucci.setVisible(false);
            counter++;
            showStartButton();
            showPlayerQueue();
        }
    }

    @FXML
    public void dariusTheGreatSelected( MouseEvent event) throws IOException {
        if(counter < numbersOfPlayer) {
           Faction newFaction = new DariusTheGreat();
           factions.add(newFaction);
            desertFactionSelected.setVisible(true);
            chooseDariusTheGreat.setVisible(false);
            chooseRamessesII.setVisible(false);
            counter++;
            showStartButton();
            showPlayerQueue();
        }
    }

    @FXML
    public void ramessesIISelected( MouseEvent event) throws IOException {
        if(counter < numbersOfPlayer) {
           Faction newFaction = new Ramesses();
           factions.add(newFaction);
            desertFactionSelected.setVisible(true);
            chooseDariusTheGreat.setVisible(false);
            chooseRamessesII.setVisible(false);
            counter++;
            showStartButton();
            showPlayerQueue();
        }
    }

    @FXML
    public void leonardoDaVinciSelected( MouseEvent event) throws IOException {
        if(counter < numbersOfPlayer) {
           Faction newFaction = new LeonardoDaVinci();
           factions.add(newFaction);
            mountainsFactionSelected.setVisible(true);
            chooseLeonardoDaVinci.setVisible(false);
            chooseStPatrick.setVisible(false);
            counter++;
            showStartButton();
            showPlayerQueue();
        }
    }

    @FXML
    public void stPatrickSelected( MouseEvent event) throws IOException {
        if(counter < numbersOfPlayer) {
           Faction newFaction = new StPatrick();
           factions.add(newFaction);
            mountainsFactionSelected.setVisible(true);
            chooseLeonardoDaVinci.setVisible(false);
            chooseStPatrick.setVisible(false);
            counter++;
            showStartButton();
            showPlayerQueue();
        }
    }

    @FXML
    public void marieCurieSelected( MouseEvent event) throws IOException {
        if(counter < numbersOfPlayer) {
           Faction newFaction = new MarieCurie();
           factions.add(newFaction);
            swampFactionSelected.setVisible(true);
            chooseMarieCurie.setVisible(false);
            chooseAleisterCrowley.setVisible(false);
            counter++;
            showStartButton();
            showPlayerQueue();
        }
    }

    @FXML
    public void aleisterCrowleySelected( MouseEvent event) throws IOException {
        if(counter < numbersOfPlayer) {
           Faction newFaction = new AliesterCrowley();
           factions.add(newFaction);
            swampFactionSelected.setVisible(true);
            chooseMarieCurie.setVisible(false);
            chooseAleisterCrowley.setVisible(false);
            counter++;
            showStartButton();
            showPlayerQueue();
        }
    }

    @FXML
    public void buddhaSelected( MouseEvent event) throws IOException {
        if(counter < numbersOfPlayer) {
           Faction newFaction = new Buddha();
           factions.add(newFaction);
            plainsFactionSelected.setVisible(true);
            chooseBuddha.setVisible(false);
            chooseHusseinTheTeaMaker.setVisible(false);
            counter++;
            showStartButton();
            showPlayerQueue();
        }
    }

    @FXML
    public void husseinTheTeaMakerSelected( MouseEvent event) throws IOException {
        if(counter < numbersOfPlayer) {
           Faction newFaction = new HusseinTheTeaMaker();
           factions.add(newFaction);
            plainsFactionSelected.setVisible(true);
            chooseBuddha.setVisible(false);
            chooseHusseinTheTeaMaker.setVisible(false);
            counter++;
            showStartButton();
            showPlayerQueue();
        }
    }

    @FXML
    public void onVladTheImpaler( MouseEvent event) throws IOException {
        chooseVladTheImpaler.setOpacity(100);
    }

    @FXML
    public void offVladTheImpaler( MouseEvent event) throws IOException {
        chooseVladTheImpaler.setOpacity(0);
    }

    @FXML
    public void onGilgamesh( MouseEvent event) throws IOException {
        chooseGilgamesh.setOpacity(100);
    }

    @FXML
    public void offGilgamesh( MouseEvent event) throws IOException {
        chooseGilgamesh.setOpacity(0);
    }

    @FXML
    public void onMorganLeFay( MouseEvent event) throws IOException {
        chooseMorganLeFay.setOpacity(100);
    }

    @FXML
    public void offMorganLeFay( MouseEvent event) throws IOException {
        chooseMorganLeFay.setOpacity(0);
    }

    @FXML
    public void onHelenOfTroy( MouseEvent event) throws IOException {
        chooseHelenOfTroy.setOpacity(100);
    }

    @FXML
    public void offHelenOfTroy( MouseEvent event) throws IOException {
        chooseHelenOfTroy.setOpacity(0);
    }

    @FXML
    public void onErikTheRed( MouseEvent event) throws IOException {
        chooseErikTheRed.setOpacity(100);
    }

    @FXML
    public void offErikTheRed( MouseEvent event) throws IOException {
        chooseErikTheRed.setOpacity(0);
    }

    @FXML
    public void onAmerigoVespucci( MouseEvent event) throws IOException {
        chooseAmerigoVespucci.setOpacity(100);
    }

    @FXML
    public void offAmerigoVespucci( MouseEvent event) throws IOException {
        chooseAmerigoVespucci.setOpacity(0);
    }

    @FXML
    public void onDariusTheGreat( MouseEvent event) throws IOException {
        chooseDariusTheGreat.setOpacity(100);
    }

    @FXML
    public void offDariusTheGreat( MouseEvent event) throws IOException {
        chooseDariusTheGreat.setOpacity(0);
    }

    @FXML
    public void onRamessesII( MouseEvent event) throws IOException {
        chooseRamessesII.setOpacity(100);
    }

    @FXML
    public void offRamessesII( MouseEvent event) throws IOException {
        chooseRamessesII.setOpacity(0);
    }

    @FXML
    public void onLeonardoDaVinci( MouseEvent event) throws IOException {
        chooseLeonardoDaVinci.setOpacity(100);
    }

    @FXML
    public void offLeonardoDaVinci( MouseEvent event) throws IOException {
        chooseLeonardoDaVinci.setOpacity(0);
    }

    @FXML
    public void onStPatrick( MouseEvent event) throws IOException {
        chooseStPatrick.setOpacity(100);
    }

    @FXML
    public void offStPatrick( MouseEvent event) throws IOException {
        chooseStPatrick.setOpacity(0);
    }

    @FXML
    public void onMarieCurie( MouseEvent event) throws IOException {
        chooseMarieCurie.setOpacity(100);
    }

    @FXML
    public void offMarieCurie( MouseEvent event) throws IOException {
        chooseMarieCurie.setOpacity(0);
    }

    @FXML
    public void onAleisterCrowley( MouseEvent event) throws IOException {
        chooseAleisterCrowley.setOpacity(100);
    }

    @FXML
    public void offAleisterCrowley( MouseEvent event) throws IOException {
        chooseAleisterCrowley.setOpacity(0);
    }

    @FXML
    public void onBuddha( MouseEvent event) throws IOException {
        chooseBuddha.setOpacity(100);
    }

    @FXML
    public void offBuddha( MouseEvent event) throws IOException {
        chooseBuddha.setOpacity(0);
    }

    @FXML
    public void onHusseinTheTeaMaker( MouseEvent event) throws IOException {
        chooseHusseinTheTeaMaker.setOpacity(100);
    }

    @FXML
    public void offHusseinTheTeaMaker( MouseEvent event) throws IOException {
        chooseHusseinTheTeaMaker.setOpacity(0);
    }





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {




        imProfile = new Image(getClass().getResourceAsStream("/Images/ChooseFactionImages/wasteland.png"));
        wastelandFaction.setImage(imProfile);

        imProfile = new Image(getClass().getResourceAsStream("/Images/ChooseFactionImages/forest.png"));
        forestFaction.setImage(imProfile);

        imProfile = new Image(getClass().getResourceAsStream("/Images/ChooseFactionImages/lakes.png"));
        lakesFaction.setImage(imProfile);

        imProfile = new Image(getClass().getResourceAsStream("/Images/ChooseFactionImages/desert.png"));
        desertFaction.setImage(imProfile);

        imProfile = new Image(getClass().getResourceAsStream("/Images/ChooseFactionImages/mountains.png"));
        mountainsFaction.setImage(imProfile);

        imProfile = new Image(getClass().getResourceAsStream("/Images/ChooseFactionImages/swamp.png"));
        swampFaction.setImage(imProfile);

        imProfile = new Image(getClass().getResourceAsStream("/Images/ChooseFactionImages/plains.png"));
        plainsFaction.setImage(imProfile);

        //Selected views

        imProfile = new Image(getClass().getResourceAsStream("/Images/ChooseFactionImages/wastelandSelected.png"));
        wastelandFactionSelected.setImage(imProfile);

        imProfile = new Image(getClass().getResourceAsStream("/Images/ChooseFactionImages/forestSelected.png"));
        forestFactionSelected.setImage(imProfile);

        imProfile = new Image(getClass().getResourceAsStream("/Images/ChooseFactionImages/lakesSelected.png"));
        lakesFactionSelected.setImage(imProfile);

        imProfile = new Image(getClass().getResourceAsStream("/Images/ChooseFactionImages/desertSelected.png"));
        desertFactionSelected.setImage(imProfile);

        imProfile = new Image(getClass().getResourceAsStream("/Images/ChooseFactionImages/mountainsSelected.png"));
        mountainsFactionSelected.setImage(imProfile);

        imProfile = new Image(getClass().getResourceAsStream("/Images/ChooseFactionImages/swampSelected.png"));
        swampFactionSelected.setImage(imProfile);

        imProfile = new Image(getClass().getResourceAsStream("/Images/ChooseFactionImages/plainsSelected.png"));
        plainsFactionSelected.setImage(imProfile);




    }




}
