package visualizadorgrafo;

/**
 * @author Elixandre M. Baldi <https://github.com/ElixandreBaldi>
 * @author Luiz Guilherme F. Rosa <https://github.com/luizguilhermefr>
 */
public class Edge {
    
    /**
     * The label of the edge.
     */
    private String label;
    
    /**
     * The cost to walk the edge.
     */
    private double cost;
    
    /**
     * Constructor.
     * @param lbl The label of the new edge.
     * @param cst The cost to walk the new edge.
     */
    Edge(String lbl, double cst){
        this.label = lbl;
        this.cost = cst;
    }
    
    /**
     * @return The label of the edge.
     */
    public String getLabel() {
        return this.label;
    }

    /**
     * @return The cost to walk the edge. 
     */
    public double getCost() {
        return this.cost;
    }

    /**
     * Sets the edge a new label.
     * @param lbl The new label. 
     */
    public void setLabel(String lbl) {
        this.label = lbl;
    }

    /**
     * Sets the edge's cost.
     * @param cst 
     */
    public void setCost(int cst) {
        this.cost = cst;
    }  
    
    /**
     * Print the edge on the screen.
     */
    public void print(){
        System.out.printf(" |%s %f ", this.label, this.cost);
    }
}
