package pt.mleiria.game.cluedo.core;

import org.junit.Assert;
import org.junit.Test;
import pt.mleiria.game.cluedo.contents.*;
import pt.mleiria.graph.undirected.Graph;


public class CardsEngineTest {

    /**
     * A Fortaleza Vermelha
     * 6 Cards of Who
     * 11 Cards of Where
     * 6 Cards of What
     */
    @Test
    public void test_correct_card_count(){
        final CardsEngine cardsEngine = new CardsEngine(GameType.A_FORTALEZA_VERMELHA);
        final int numOfWho = 6;
        final int numOfWhere = 11;
        final int numOfWhat = 6;
        final int numOfCards = numOfWho + numOfWhere + numOfWhat;
        Assert.assertEquals(numOfCards, cardsEngine.getCards().size());
        Assert.assertEquals(numOfWho, cardsEngine.getWhoCards().size());
        System.out.println(cardsEngine.getWhoCards());
    }

    @Test
    public void test_get_ordered_cards(){
        final CardsEngine cardsEngine = new CardsEngine(GameType.A_FORTALEZA_VERMELHA);
        cardsEngine.getCards().stream().forEach(elem -> System.out.println(elem.getValue()));
        Assert.assertEquals("Cercei Lannister", cardsEngine.getCards().get(0).getValue());
    }

    @Test
    public void test_init_graph(){
        final CardsEngine cardsEngine = new CardsEngine(GameType.A_FORTALEZA_VERMELHA);
        Graph graph = cardsEngine.initGameGraph();
        Assert.assertEquals(23, graph.getVertices());
        Assert.assertEquals(102, graph.getEdges());
        System.out.println(graph.toString());
    }
    @Test
    public void test_new_epoch(){
        final CardsEngine cardsEngine = new CardsEngine(GameType.A_FORTALEZA_VERMELHA);
        Graph graph = cardsEngine.initGameGraph();
        Assert.assertEquals(23, graph.getVertices());
        System.out.println(graph.toString());

        Card knownCard = Who.CERSEI_LANNISTER;
        cardsEngine.newEpoch(knownCard);
        System.out.println(graph.toString());
        Assert.assertEquals(23, graph.getVertices());

        knownCard = What.BESTA;
        graph = cardsEngine.newEpoch(knownCard);
        System.out.println(graph.toString());
        Assert.assertEquals(23, graph.getVertices());

        knownCard = Where.CELAS_DA_PRISAO;
        graph = cardsEngine.newEpoch(knownCard);
        System.out.println(graph.toString());
        Assert.assertEquals(23, graph.getVertices());

        knownCard = Who.SANSA_STARK;
        graph = cardsEngine.newEpoch(knownCard);
        System.out.println(graph.toString());
        Assert.assertEquals(23, graph.getVertices());






    }

}
