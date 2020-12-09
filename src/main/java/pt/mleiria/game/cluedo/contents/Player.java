package pt.mleiria.game.cluedo.contents;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Player {

    private final Card[] cards;
    private final String playerName;
    private final List<Guess> guessList;


    public Player(final Card[] cards, final String playerName){
        this.cards = new Card[cards.length];
        System.arraycopy(cards, 0, this.cards, 0, cards.length);
        this.playerName = playerName;
        guessList = new ArrayList<>();
    }


    public void guess(final Guess guess){
        guessList.add(guess);

    }

    public void receiveFeedBack(final Card[] card){

    }

    public String getPlayerName() {
        return playerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return getPlayerName().equals(player.getPlayerName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPlayerName());
    }
}
