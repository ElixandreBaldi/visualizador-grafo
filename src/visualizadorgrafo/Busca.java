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
    int noInformado;
    int[] vetorBusca;
    int iVetor;
    int nVertices;
    Aresta[][] adjacencia;
    boolean[] visitados;
    
    Busca(int no, int n, Aresta[][]adja){
        vetorBusca = new int[n];
        visitados = new boolean[n];
        iVetor = 0;
        nVertices = n;
        noInformado = no;
        adjacencia = adja;
        for(int i = 0; i<nVertices; i++){
            visitados[i] = false;
            vetorBusca[i] = -1;
        }
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
            /*System.out.printf("em %d: ",visita);
            for(int j = 0; j<nVertices; j++){
                System.out.printf("%b ",visitados[j]);
            }
            System.out.println("");
            */
                
                
            if(adjacencia[visita][i].getCusto() != -1 && i != visita && !visitados[i]){                                        
                /*System.out.println("entrou? "+visitados[i]+" "+i);*/
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
}
