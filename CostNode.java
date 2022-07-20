public class CostNode {
    private Vertex current;
    private CostNode parent;
    private int cost;
    CostNode(Vertex current, CostNode parent, int cost){
        this.current = current;
        this.parent = parent;
        this.cost = cost;
    }

    public Vertex getCurrent() {
        return current;
    }

    public CostNode getParent() {
        return parent;
    }

    public int getCost() {
        return cost;
    }

}
