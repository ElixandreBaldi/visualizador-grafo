package visualizadorgrafo;

public class Aresta {
    private String rotulo;
    private Integer custo;
    
    Aresta(String R, int C){
        rotulo = R;
        custo = C;
    }
    
    public String getRotulo() {
        return rotulo;
    }

    public void setRotulo(String rotulo) {
        this.rotulo = rotulo;
    }

    public int getCusto() {
        return custo;
    }

    public void setCusto(int custo) {
        this.custo = custo;
    }    
    public void print(){
        System.out.printf(" |%s %d ",rotulo,custo);
    }
}
