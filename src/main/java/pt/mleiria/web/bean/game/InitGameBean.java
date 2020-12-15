package pt.mleiria.web.bean.game;

import org.primefaces.model.mindmap.DefaultMindmapNode;
import org.primefaces.model.mindmap.MindmapNode;
import org.primefaces.event.SelectEvent;
import pt.mleiria.game.cluedo.contents.Card;
import pt.mleiria.game.cluedo.contents.What;
import pt.mleiria.game.cluedo.contents.Where;
import pt.mleiria.game.cluedo.contents.Who;
import pt.mleiria.game.cluedo.core.CardsEngine;
import pt.mleiria.graph.undirected.Graph;

import javax.faces.application.Application;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ManagedBean(name = "initGameBean")
@SessionScoped
public class InitGameBean implements Serializable {

    private MindmapNode root;


    private MindmapNode selectedNode;

    private Graph epochSnapshot;

    public PlayersSettingsBean getManagedBeanByEvaluateExpressionGet() {
        FacesContext context = FacesContext.getCurrentInstance();
        Application application = context.getApplication();
        return application.evaluateExpressionGet(context, "#{playersSettingsBean}", PlayersSettingsBean.class);
    }


    public InitGameBean() {
        CardsEngine cardsEngine = getManagedBeanByEvaluateExpressionGet().getCardsEngine();
        root = new DefaultMindmapNode(cardsEngine.getGameType().getValue(), cardsEngine.getGameType().name(), "FFCC00", false);

        final List<Who> whoCards = new ArrayList<>();
        final List<What> whatCards = new ArrayList<>();
        final List<Where> whereCards = new ArrayList<>();
        for (final Optional<Card> optCard : cardsEngine.getCardsInPlay()) {
            if (optCard.isPresent()) {
                final Card card = optCard.get();
                if (card instanceof Who) {
                    whoCards.add((Who) card);
                } else if (card instanceof What) {
                    whatCards.add((What) card);
                } else {
                    whereCards.add((Where) card);
                }
            }
        }
        Who[] whos = new Who[whoCards.size()];
        whos = whoCards.toArray(whos);
        What[] whats = new What[whatCards.size()];
        whats = whatCards.toArray(whats);
        Where[] wheres = new Where[whereCards.size()];
        wheres = whereCards.toArray(wheres);
        for (int i = 0; i < whos.length; i++) {
            MindmapNode nodeWho = new DefaultMindmapNode(whos[i].getValue(), whos[i].getValue(), "6e9ebf", true);
            root.addNode(nodeWho);
            for (int j = 0;j < whats.length; j++ ){
                MindmapNode nodeWhat = new DefaultMindmapNode(whats[j].getValue(), whats[j].getValue(), "6e9ebf", true);
                nodeWho.addNode(nodeWhat);
                for(int k = 0; k < wheres.length; k++){
                    MindmapNode nodeWhere = new DefaultMindmapNode(wheres[k].getValue(), wheres[k].getValue(), "6e9ebf", true);
                    nodeWhat.addNode(nodeWhere);
                }
            }
        }

        /*
        MindmapNode ips = new DefaultMindmapNode("IPs", "IP Numbers", "6e9ebf", true);
        MindmapNode ns = new DefaultMindmapNode("NS(s)", "Namespaces", "6e9ebf", true);
        MindmapNode malware = new DefaultMindmapNode("Malware", "Malicious Software", "6e9ebf", true);


        root.addNode(ips);
        root.addNode(ns);
        root.addNode(malware);
         */
    }

    public MindmapNode getRoot() {
        return root;
    }

    public MindmapNode getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(MindmapNode selectedNode) {
        this.selectedNode = selectedNode;
    }

    public void onNodeSelect(SelectEvent event) {
        MindmapNode node = (MindmapNode) event.getObject();

        //populate if not already loaded
        if (node.getChildren().isEmpty()) {
            Object label = node.getLabel();

            if (label.equals("NS(s)")) {
                for (int i = 0; i < 25; i++) {
                    node.addNode(new DefaultMindmapNode("ns" + i + ".google.com", "Namespace " + i + " of Google", "82c542", false));
                }
            } else if (label.equals("IPs")) {
                for (int i = 0; i < 18; i++) {
                    node.addNode(new DefaultMindmapNode("1.1.1." + i, "IP Number: 1.1.1." + i, "fce24f", false));
                }
            } else if (label.equals("Malware")) {
                for (int i = 0; i < 18; i++) {
                    String random = UUID.randomUUID().toString();
                    node.addNode(new DefaultMindmapNode("Malware-" + random, "Malicious Software: " + random, "3399ff", false));
                }
            }
        }
    }

    public void onNodeDblselect(SelectEvent event) {
        this.selectedNode = (MindmapNode) event.getObject();
    }
}
