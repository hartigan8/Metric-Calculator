import java.util.ArrayList;

public class Vertex {
    private String name;
    private ArrayList<Edge> edges;
    private Vertex parent;
    private int betweenness;
    private double closeness;

    public Vertex(String name) {
        this.name = name;
        edges = new ArrayList<Edge>();
        parent = null;
        closeness = 0;
        betweenness = 0;
    }
    public void finalizeCloseness(){
        closeness = 1 / closeness;
    }
    public void updateBetweenness(){
        betweenness++;
    }

    public void updateCloseness(int value){
        closeness += value;
    }

    public int getBetweenness() {
        return betweenness;
    }

    public double getCloseness() {
        return closeness;
    }

    public void addEdge(Edge e) {
        edges.add(e);
    }

    public ArrayList<Edge> getEdges() {
        return this.edges;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Vertex getParent() {
        return parent;
    }

    public void setParent(Vertex parent) {
        this.parent = parent;
    }

}
