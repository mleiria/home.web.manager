package pt.mleiria.game.cluedo.core;

import pt.mleiria.game.cluedo.contents.*;
import pt.mleiria.graph.undirected.Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class CardsEngine {
    private static final Logger LOG = Logger.getLogger(CardsEngine.class.getName());


    private final List<Optional<Card>> currentEpoch;
    private final List<Card> cards;
    private final List<Card> whoCards;
    private final List<Card> whereCards;
    private final List<Card> whatCards;
    private final GameType gameType;
    private final int totalNumPossibilities;

    public CardsEngine(final GameType gameType) {
        currentEpoch = new ArrayList<>();
        cards = new ArrayList<>();

        whoCards = Arrays.stream(Who.values()).filter(elem -> elem.getGameType().equals(gameType)).collect(Collectors.toList());
        whoCards.stream().forEach(elem -> {
            currentEpoch.add(Optional.ofNullable(elem));
            cards.add(elem);
        });

        whatCards = Arrays.stream(What.values()).filter(elem -> elem.getGameType().equals(gameType)).collect(Collectors.toList());
        whatCards.stream().forEach(elem -> {
            currentEpoch.add(Optional.ofNullable(elem));
            cards.add(elem);
        });

        whereCards = Arrays.stream(Where.values()).filter(elem -> elem.getGameType().equals(gameType)).collect(Collectors.toList());
        whereCards.stream().forEach(elem -> {
            currentEpoch.add(Optional.ofNullable(elem));
            cards.add(elem);
        });
        this.gameType = gameType;
        totalNumPossibilities = whoCards.size() * whatCards.size() * whereCards.size();
    }

    /**
     * @return all cards of a GameType
     */
    public List<Card> getCards() {
        return cards;
    }


    public Graph newEpoch(final Card[] knownCards) {
        Graph graph = null;
        for(final Card card : knownCards){
            graph = newEpoch(card);
        }
        return graph;
    }
    /**
     * Refreshes the currenttEpoch list with the new info
     *
     * @param knownCard
     */
    public Graph newEpoch(final Card knownCard) {
        for (int i = 0; i < currentEpoch.size(); i++) {
            final Optional<Card> optCard = currentEpoch.get(i);
            if (optCard.isPresent() && optCard.get().equals(knownCard)) {
                currentEpoch.set(i, Optional.empty());
            }
        }
        final int whoLen = whoCards.size();//getCurrentWhoLen();
        final int whatLen = whatCards.size();//getCurrentWhatLen();
        final int whereLen = whereCards.size();//getCurrentWhereLen();
        final List<Integer[]> tmpLst = new ArrayList<>();
        final int numVertices = whoLen + whatLen + whereLen;
        int cnt = 0;
        for (int i = 0; i < whoLen; i++) {
            for (int j = whoLen; j < whoLen + whatLen; j++) {
                if (currentEpoch.get(i).isPresent() && currentEpoch.get(j).isPresent()) {
                    tmpLst.add(new Integer[]{i, j});
                }
            }
            cnt++;
        }
        for (int i = whoLen; i < whoLen + whatLen; i++) {
            for (int j = whoLen + whatLen; j < numVertices; j++) {
                if (currentEpoch.get(i).isPresent() && currentEpoch.get(j).isPresent()) {
                    tmpLst.add(new Integer[]{i, j});
                }
            }
            cnt++;
        }
        final List<Integer[]> graphLst = new ArrayList<>();
        graphLst.add(new Integer[]{0, numVertices});
        graphLst.add(new Integer[]{0, tmpLst.size()});
        graphLst.addAll(tmpLst);
        return new Graph(graphLst);


    }

    /**
     * @return
     */
    public int getCurrentWhoLen() {
        int cnt = 0;
        for (int i = 0; i < whoCards.size(); i++) {
            if (currentEpoch.get(i).isPresent()) {
                cnt++;
            }
        }
        return cnt;
    }

    /**
     * @return
     */
    public int getCurrentWhatLen() {
        int cnt = 0;
        for (int i = whoCards.size(); i < whatCards.size() + whoCards.size(); i++) {
            if (currentEpoch.get(i).isPresent()) {
                cnt++;
            }
        }
        return cnt;
    }

    /**
     * @return
     */
    public int getCurrentWhereLen() {
        int cnt = 0;
        for (int i = whoCards.size() + whatCards.size(); i < currentEpoch.size(); i++) {
            if (currentEpoch.get(i).isPresent()) {
                cnt++;
            }
        }
        return cnt;
    }

    /**
     * @return
     */
    public Graph initGameGraph() {
        final int whoLen = whoCards.size();
        final int whatLen = whatCards.size();
        final int whereLen = whereCards.size();
        final List<Integer[]> tmpLst = new ArrayList<>();
        final int numVertices = whoLen + whatLen + whereLen;
        for (int i = 0; i < whoLen; i++) {
            for (int j = whoLen; j < whoLen + whatLen; j++) {
                tmpLst.add(new Integer[]{i, j});
            }
        }
        for (int i = whoLen; i < whoLen + whatLen; i++) {
            for (int j = whoLen + whatLen; j < numVertices; j++) {
                tmpLst.add(new Integer[]{i, j});
            }
        }
        final List<Integer[]> graphLst = new ArrayList<>();
        graphLst.add(new Integer[]{0, numVertices});
        graphLst.add(new Integer[]{0, tmpLst.size()});
        graphLst.addAll(tmpLst);
        return new Graph(graphLst);

    }

    public List<Card> getWhoCards() {
        return whoCards;
    }

    public List<Card> getWhatCards() {
        return whatCards;
    }

    public List<Card> getWhereCards() {
        return whereCards;
    }

    public int getTotalNumPossibilities() {
        return totalNumPossibilities;
    }
}
