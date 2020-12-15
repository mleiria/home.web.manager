package pt.mleiria.web.bean.game;


import pt.mleiria.ConfigFileReader;
import pt.mleiria.game.cluedo.contents.Card;
import pt.mleiria.game.cluedo.contents.GameType;
import pt.mleiria.game.cluedo.core.CardsEngine;
import pt.mleiria.graph.undirected.Graph;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@ManagedBean(name = "playersSettingsBean")
@SessionScoped
public class PlayersSettingsBean implements Serializable {

    private static final Logger LOG = Logger.getLogger(PlayersSettingsBean.class.getName());


    private CardsEngine cardsEngine;

    private GameType gameType;
    private List<SelectItem> gameTypeItems;
    private List<String> selectedCards;
    private int numPlayers;
    private final int[] numPlayersList;
    private static final int NUM_MAX_PLAYERS = 6;


    /**
     *
     */
    public PlayersSettingsBean() {
        gameTypeItems = new ArrayList<>();
        selectedCards = new ArrayList<>();
        numPlayersList = new int[NUM_MAX_PLAYERS];
        for (int i = 0; i < NUM_MAX_PLAYERS; i++) {
            numPlayersList[i] = i + 1;
        }
        for (final GameType gameType : GameType.values()) {
            gameTypeItems.add(new SelectItem(gameType.name(), gameType.getValue()));
        }
    }

    public void continueBtn() {
        cardsEngine = new CardsEngine(gameType);
        LOG.info(cardsEngine.getWhoCards().toString());
    }

    public void selectCharacterBtn(String selectedCard) {
        if (selectedCards.contains(selectedCard)) {
            LOG.info("Removing: " + selectedCard);
            selectedCards.remove(selectedCard);
        } else {
            LOG.info("Adding: " + selectedCard);
            selectedCards.add(selectedCard);
        }
    }

    public String initGameBtn() {
        for (final String card : selectedCards) {
            final Card selectedCard = cardsEngine.getCards().stream().filter(elem -> elem.getValue().equals(card)).findAny().get();
            Graph graph = cardsEngine.newEpoch(selectedCard);
            LOG.info("Current Epoch:\n " + graph.toString(cardsEngine.getCardsDescLst()));
        }
        return "initGame";
    }

    public GameType getGameType() {
        return gameType;
    }

    public void setGameType(GameType gameType) {
        this.gameType = gameType;
    }

    public List<SelectItem> getGameTypeItems() {
        return gameTypeItems;
    }

    public void setGameTypeItems(List<SelectItem> gameTypeItems) {
        this.gameTypeItems = gameTypeItems;
    }

    public CardsEngine getCardsEngine() {
        return cardsEngine;
    }

    public List<String> getSelectedCards() {
        return selectedCards;
    }

    public void setSelectedCards(List<String> selectedCards) {
        this.selectedCards = selectedCards;
    }

    public int getNumPlayers() {
        return numPlayers;
    }

    public void setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
    }

    public int[] getNumPlayersList() {
        return numPlayersList;
    }
}
