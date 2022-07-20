import java.util.*;

public class Graph {
    private HashMap<String, Vertex> vertices;
    private HashMap<String, Edge> edges;

    public Graph() {
        this.vertices = new HashMap<>();
        this.edges = new HashMap<>();
    }

    public void addEdge(String source, String destination) {

        if (edges.get(source + "-" + destination) == null && edges.get(destination + "-" + source) == null) {
            Vertex source_v, destination_v;

            if (vertices.get(source) == null) {
                source_v = new Vertex(source);
                vertices.put(source, source_v);
            } else
                source_v = vertices.get(source);

            if (vertices.get(destination) == null) {
                destination_v = new Vertex(destination);
                vertices.put(destination, destination_v);
            } else
                destination_v = vertices.get(destination);

            Edge edge = new Edge(source_v, destination_v);
            source_v.addEdge(edge);
            destination_v.addEdge(edge);
            edges.put(source + "-" + destination, edge);
        }
        else {
            System.out.println("This edge has already added!");
        }
    }

    public void calculateMetrics() {
        boolean[] checked = new boolean[vertices.size() + 1]; // keeping the nodes which already processed to find different pairs
        for (Vertex v : vertices()) {
            checked[Integer.valueOf(v.getName())] = true;
            Queue<CostNode> costNodes = new LinkedList<>();
            costNodes.add(new CostNode(v, null, 0));
            boolean[] foundPaths = addAll(checked);
            boolean[] controlled = new boolean[vertices.size() + 1]; // this boolean exist to not add the nodes which already added to queue again
            boolean done = false;
            while (!costNodes.isEmpty() && !done) {
                CostNode currentC = costNodes.poll();
                Vertex currentV = currentC.getCurrent();
                for (Edge e : currentV.getEdges()) {
                    Vertex differentOne= e.getOther(currentV.getName());
                    CostNode diffOne = new CostNode(differentOne, currentC, currentC.getCost() + 1);
                    if (!controlled[Integer.valueOf(differentOne.getName())]) {
                        costNodes.add(diffOne);
                        controlled[Integer.valueOf(differentOne.getName())] = true;
                    }

                    if (!foundPaths[Integer.valueOf(differentOne.getName())]) {
                        foundPaths[Integer.valueOf(differentOne.getName())] = true;

                        differentOne.updateCloseness(diffOne.getCost());
                        v.updateCloseness(diffOne.getCost());
                        while(diffOne.getParent().getParent() != null){
                            diffOne = diffOne.getParent();
                            diffOne.getCurrent().updateBetweenness();
                        }
                    }
                }
            }
            if(size() != size(foundPaths)){
                v.updateCloseness(9999 * ( size() - size(foundPaths) ));
                for (int i = 1; i < foundPaths.length; i++) {
                    if(!foundPaths[i]){
                        Vertex toAddInfinity = vertices.get(String.valueOf(i));
                        toAddInfinity.updateCloseness(9999);
                    }
                }
            }
            v.finalizeCloseness();
        }
    }
    private boolean[] addAll(boolean[] booleans){
        boolean[] checked = new boolean[vertices.size() + 1];
        for (int i = 0; i < booleans.length; i++) {
            if(booleans[i]){
                checked[i] = true;
            }
        }
        return checked;
    }
    private int size(boolean[] booleans){
        int count = 0;
        for (int i = 0; i < booleans.length; i++) {
            if(booleans[i]){
                count++;
            }
        }
        return count;
    }
    public Iterable<Vertex> vertices() {
        return vertices.values();
    }

    public Iterable<Edge> edges() {
        return edges.values();
    }

    public int size() {
        return vertices.size();
    }

}