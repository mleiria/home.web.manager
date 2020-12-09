package pt.mleiria.web.bean.game;


import pt.mleiria.game.cluedo.contents.GameType;
import pt.mleiria.game.cluedo.core.CardsEngine;

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

    /**
     *
     */
    public PlayersSettingsBean() {
        gameTypeItems = new ArrayList<>();
        selectedCards = new ArrayList<>();
        for (final GameType gameType : GameType.values()) {
            gameTypeItems.add(new SelectItem(gameType.name(), gameType.getValue()));
        }
    }

    public void continueBtn() {
        cardsEngine = new CardsEngine(gameType);
        LOG.info(cardsEngine.getWhoCards().toString());
    }

    public void selectCharacterBtn(String selectedCard){
        LOG.info(selectedCard);
        if(selectedCards.contains(selectedCard)){
            selectedCards.remove(selectedCard);
        }else{
            selectedCards.add(selectedCard);
        }
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
}
