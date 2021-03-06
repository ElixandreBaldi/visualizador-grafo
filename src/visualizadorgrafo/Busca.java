/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visualizadorgrafo;

import java.util.LinkedList;

/**
 *
 * @author Elixandre
 * @author Luiz Guilherme F. Rosa
 */

public class Busca {    
    int[] vetorBusca;
    int[][] vetorCicloEuleriano;
    int iVetor;
    int nVertices;
    int qtdArestas;
    Aresta[][] adjacenciaBusca;
    boolean[] visitados;
    
    Busca(int n, Aresta[][] adja){
        vetorBusca = new int[n];
        visitados = new boolean[n];
        iVetor = 0;
        nVertices = n;   
        adjacenciaBusca = new Aresta[nVertices][nVertices];
        for(int i = 0; i<nVertices; i++){
            visitados[i] = false;
            vetorBusca[i] = -1;
            for(int j = 0; j<nVertices; j++){
                adjacenciaBusca[i][j] = new Aresta(adja[i][j].getRotulo(), adja[i][j].getCusto());
            }
        }
    }

    Busca(int n, Aresta[][] adja, int qArestas){        
        vetorCicloEuleriano = new int[qArestas][2];
        visitados = new boolean[n];
        iVetor = 0;
        nVertices = n;   
        qtdArestas = qArestas;
        adjacenciaBusca = new Aresta[nVertices][nVertices];
        for(int i = 0; i<nVertices; i++){
            visitados[i] = false;            
            for(int j = 0; j<nVertices; j++){
                adjacenciaBusca[i][j] = new Aresta(adja[i][j].getRotulo(), adja[i][j].getCusto());
            }
        }
    }

    Aresta[][] getAdjacencia(){
        return adjacenciaBusca;
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
            if(adjacenciaBusca[visita][i].getCusto() != -1 && i != visita && !visitados[i]){                                                        
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

    int[] largura(int raiz){
        iVetor = 0;
        LinkedList<Integer> filaNos = new LinkedList<>(); // fila dos nós a serem impressos/explorados
        filaNos.add(raiz);
        visitados[raiz] = true;
        while (!filaNos.isEmpty())
        {
            // Tira um nó da fila e o põe na lista de retorno
            raiz = filaNos.poll(); // poll = retira o próximo elemento da fila
            vetorBusca[iVetor] = raiz;
            iVetor++;
            for (int j=0;j<nVertices;j++)
                if(adjacenciaBusca[raiz][j].getCusto() != -1)
                    if (!visitados[j])
                    {
                        visitados[j] = true;
                        filaNos.add(j);
                    }
        }
        return vetorBusca;
    }

    protected boolean isGrafo(Aresta[][] grafo, int n){
        for(int i = 0; i<n; i++)
            for(int j = 0; j<n; j++)
                if(grafo[i][j].getCusto() != -1)
                    return true;
        return false;
    }
    
    boolean isConexo(int linhaVisita){        
        Aresta[][] adjacenciaGoodman = new Aresta[nVertices][nVertices];
        int qtdLinhasVazias = 0;
        for(int i = 0; i<nVertices; i++){
            if(linhaVazia(i) && i!=linhaVisita)
                qtdLinhasVazias++;            
            for(int j = 0; j<nVertices; j++){
                adjacenciaGoodman[i][j] = new Aresta(adjacenciaBusca[i][j].getRotulo(), adjacenciaBusca[i][j].getCusto());
            }        
        }
        int nComponentesConexo = 0, visita = 0, primeiro = 0;
        boolean[] visitados = new boolean[nVertices];
        boolean flag = false;
        while(isGrafo(adjacenciaGoodman, nVertices)){            
            Busca B = new Busca(nVertices, adjacenciaGoodman);
            for(int i = 0; i < nVertices; i++){//escolhe qualquer um que não foi visitado                
                if(!visitados[i]){
                    visita = i;
                    i = nVertices;
                }
                int conexos[] = B.profundidade(visita);                
                primeiro = conexos[0];
                flag = true;
                for(int j = 0; j < nVertices; j++){
                    if(conexos[j] != -1)
                        visitados[conexos[j]] = true;
                }
                for(int j = 1; j < nVertices; j++){
                    if(conexos[j] != -1){
                        for(int w = 0; w < nVertices; w++){
                            if(adjacenciaGoodman[primeiro][w].getCusto() < adjacenciaGoodman[conexos[j]][w].getCusto()){
                                adjacenciaGoodman[primeiro][w] = new Aresta(adjacenciaGoodman[conexos[j]][w].getRotulo(),adjacenciaGoodman[conexos[j]][w].getCusto());
                            }
                            adjacenciaGoodman[conexos[j]][w] = new Aresta("NULL", -1);
                        }
                    }
                }
            }
            nComponentesConexo++;
            if(flag){
                for(int i = 0; i<nVertices; i++)
                    adjacenciaGoodman[primeiro][i] = new Aresta("NULL", -1);
            }            
            flag = false;
        }
        for(int i = 0; i< nVertices; i++){
            if(!visitados[i])
                nComponentesConexo++;
        }        
        nComponentesConexo -=qtdLinhasVazias;
        
        if(nComponentesConexo == 1 || nComponentesConexo == 0)            
            return true;
        
        return false;
    }

    boolean linhaVazia(int linha){
        int cont = 0;
        for(int j = 0; j<nVertices; j++){
            if(adjacenciaBusca[linha][j].getCusto()==-1)
                cont++;
        }
        if(cont == nVertices)    
            return true;
        cont = 0;
        
        return false;
    }

    int[][] cicloEuleriano(int no){
        int visita = no;
        for(int i = 0; i<nVertices; i++){
            Aresta tmp;            
            if(adjacenciaBusca[visita][i].getCusto() != -1 && i != visita){ 
                tmp = new Aresta(adjacenciaBusca[visita][i].getRotulo(),adjacenciaBusca[visita][i].getCusto());
                adjacenciaBusca[visita][i] = new Aresta("NULL",-1);
                adjacenciaBusca[i][visita] = new Aresta("NULL",-1);                                
                if(isConexo(i)){                    
                    vetorCicloEuleriano[iVetor][0] = visita;
                    vetorCicloEuleriano[iVetor][1] = i;
                    iVetor++;                    
                    cicloEuleriano(i);
                }else{                    
                    adjacenciaBusca[visita][i] = new Aresta(tmp.getRotulo(),tmp.getCusto());
                    adjacenciaBusca[i][visita] = new Aresta(tmp.getRotulo(),tmp.getCusto());
                }
            }                        
        }
        
        return vetorCicloEuleriano;
    }
}