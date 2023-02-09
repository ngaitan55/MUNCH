package munch;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Network {
    private List<String> metabolites = new ArrayList<>();
    private List<Reaction> reactions = new ArrayList<>();
    private Map<String, List<Edge>> networkGraph = new HashMap<>();

    public void constructNetworkGraph() throws Exception {
        if(reactions.isEmpty()) throw new Exception("Reactions list is empty");
        for(Reaction rxn:reactions){
            List<String> substrates = rxn.getSubstrates();
            List<String> products = rxn.getProducts();
            for(String substrate : substrates) {
                List<Edge> subsEdges = networkGraph.computeIfAbsent(substrate, v -> new ArrayList<>());
                for(String product:products){
                    Edge e = new Edge(rxn.getId(), substrate, product);
                    subsEdges.add(e);
                }
            }
        }
    }

    public void generateGraphTextFile() throws IOException {
        try(PrintWriter writer = new PrintWriter("MetNetworkGraph.tsv")){
            writer.println("source" + NetworkReader.INPUT_DELIMITER + "target" + NetworkReader.INPUT_DELIMITER
                    + "rxnId");
            for(Map.Entry<String, List<Edge>> entry : networkGraph.entrySet()){
                String source = entry.getKey();
                for(Edge e : entry.getValue()){
                    String target = e.successor;
                    String rxnId = e.getReactionId();
                    writer.println(source + NetworkReader.INPUT_DELIMITER + target + NetworkReader.INPUT_DELIMITER
                            + rxnId);
                }
            }
        }
    }
    public void addMetabolite(String metabolite){
        metabolites.add(metabolite);
    }

    public void addReaction(String reactionId){
        reactions.add(new Reaction(reactionId));
    }

    public void addEdge(String reactionId, String substrate, String product){
        Edge e = new Edge(reactionId, substrate, product);
        networkGraph.computeIfAbsent(substrate, v -> new ArrayList<>()).add(e);
    }

    public String getMetabolite(int i) {
        return metabolites.get(i);
    }

    public Reaction getReaction(int i) {
        return reactions.get(i);
    }

    public List<String> getMetabolites() {
        return metabolites;
    }

    public List<Reaction> getReactions() {
        return reactions;
    }

    public Map<String, List<Edge>> getNetworkGraph() {
        return networkGraph;
    }

    class Edge{
        String id;
        String predecessor;
        String successor;

        public Edge(String reactionId, String predecessor, String successor) {
            this.id = reactionId;
            this.predecessor = predecessor;
            this.successor = successor;
        }


        public String getReactionId() {
            return id;
        }

        public String getPredecessor() {
            return predecessor;
        }

        public String getSuccessor() {
            return successor;
        }

        public String getEdgeRecord(){
            return predecessor + NetworkReader.INPUT_DELIMITER + successor + NetworkReader.INPUT_DELIMITER +
                    id;
        }
    }
}
