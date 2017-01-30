/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visualizadorgrafo;

/**
 *
 * @author elixandre
 */
public class Busca {
    int[] vetorBusca;
    int iVetor;
    int nVertices;
    Aresta[][] adjacencia;
    boolean[] visitados;
    
    Busca(int n, Aresta[][]adja){
        vetorBusca = new int[n];
        visitados = new boolean[n];
        iVetor = 0;
        nVertices = n;        
        adjacencia = adja;
        for(int i = 0; i<nVertices; i++){
            visitados[i] = false;
            vetorBusca[i] = -1;
        }
    }
    Aresta[][] getAdjacencia(){
        return adjacencia;
    }
    
    boolean[] getVisitados(){
        return visitados;
    }
    void setVisitados(boolean[] v){
        visitados = v;
    }
    
    boolean presenteVetorBusca(int x){
        for(int i = 0; i<nVertices; i++){
            if(vetorBusca[i] == x)
                return true;        
        }
        return false;
       
    }
    int[] profundidade(int no){
        int visita = no;
        for(int i = 0; i<nVertices; i++){
            if(adjacencia[visita][i].getCusto() != -1 && i != visita && !visitados[i]){                                                        
                if(!presenteVetorBusca(visita))
                    vetorBusca[iVetor] = visita;
                iVetor++;
                visitados[visita] = true;                
                profundidade(i);
            }            
        }
        if(!visitados[visita])
            visitados[visita] = true;
        if(!presenteVetorBusca(visita))
            vetorBusca[iVetor] = visita;
        
        return vetorBusca;
    }
    
    void printAA(Aresta[][] adjacenciaGoodman, int nVerticesGoodman){
        for(int w = 0; w<nVerticesGoodman; w++){
            for(int y = 0; y<nVerticesGoodman; y++){
                if(adjacenciaGoodman[w][y].getCusto() != -1){
                    System.out.printf("1 ");
                }
                else
                    System.out.printf("0 ");
            }
            System.out.println("");
        }
        System.out.println("");
    }
}
