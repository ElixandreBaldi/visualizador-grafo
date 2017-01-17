/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visualizadorgrafo;

/**
 *
 * @author porlibras
 */
public class Aresta {
    private String rotulo;
    private int custo;
    
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
