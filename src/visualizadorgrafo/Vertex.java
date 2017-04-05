package visualizadorgrafo;

/**
 * @author Elixandre M. Baldi <https://github.com/ElixandreBaldi>
 * @author Luiz Guilherme F. Rosa <https://github.com/luizguilhermefr>
 */
public class Vertex {

    /**
     * The X coordinate of the vertex.
     */
    private int coordX;

    /**
     * The Y coordinate of the vertex.
     */
    private int coordY;

    /**
     * The vertex's label.
     */
    private String label;

    /**
     * Constructor.
     *
     * @param x The X coordinate.
     * @param x The Y coordinate.
     * @param label The label.
     */
    Vertex(int x, int y, String label) {
        this.coordX = x;
        this.coordY = y;
        this.label = label;
    }

    /**
     * @return The X coordinate of the vertex.
     */
    int getCoordX() {
        return this.coordX;
    }

    /**
     * @return The Y coordinate of the vertex.
     */
    int getCoordY() {
        return this.coordY;
    }

    /**
     * Sets the X coordinate of the vertex.
     *
     * @param x The coordinate.
     */
    void setCoordX(int x) {
        this.coordX = x;
    }

    /**
     * Sets the Y coordinate of the vertex.
     *
     * @param y The coordinate.
     */
    void setCoordY(int y) {
        this.coordY = y;
    }

    /**
     * @return The vertex's label.
     */
    String getLabel() {
        return this.label;
    }

    /**
     * Sets the label of the vertex.
     *
     * @param label The wanted label.
     */
    void setLabel(String label) {
        this.label = label;
    }

    /**
     * Prints the Vertex on the screen.
     */
    void print() {
        System.out.println(this.label + " " + this.coordX + " " + this.coordY);
    }
}
