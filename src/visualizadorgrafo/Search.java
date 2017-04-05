package visualizadorgrafo;

import java.util.LinkedList;

/**
 * @author Elixandre M. Baldi <https://github.com/ElixandreBaldi>
 * @author Luiz Guilherme F. Rosa <https://github.com/luizguilhermefr>
 */

public class Search {
    int[] vetorBusca;
    int[][] vetorCicloEuleriano;
    int iVetor;
    int nVertices;
    int qtdArestas;
    Edge[][] adjacenciaBusca;
    boolean[] visitados;
    
    Search(int n, Edge[][] adja){
        vetorBusca = new int[n];
        visitados = new boolean[n];
        iVetor = 0;
        nVertices = n;   
        adjacenciaBusca = new Edge[nVertices][nVertices];
        for(int i = 0; i<nVertices; i++){
            visitados[i] = false;
            vetorBusca[i] = -1;
            for(int j = 0; j<nVertices; j++){
                adjacenciaBusca[i][j] = new Edge(adja[i][j].getLabel(), adja[i][j].getCost());
            }
        }
    }

    Search(int n, Edge[][] adja, int qArestas){        
        vetorCicloEuleriano = new int[qArestas][2];
        visitados = new boolean[n];
        iVetor = 0;
        nVertices = n;   
        qtdArestas = qArestas;
        adjacenciaBusca = new Edge[nVertices][nVertices];
        for(int i = 0; i<nVertices; i++){
            visitados[i] = false;            
            for(int j = 0; j<nVertices; j++){
                adjacenciaBusca[i][j] = new Edge(adja[i][j].getLabel(), adja[i][j].getCost());
            }
        }
    }

    Edge[][] getAdjacencia(){
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
            if(adjacenciaBusca[visita][i].getCost() != -1 && i != visita && !visitados[i]){                                                        
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
                if(adjacenciaBusca[raiz][j].getCost() != -1)
                    if (!visitados[j])
                    {
                        visitados[j] = true;
                        filaNos.add(j);
                    }
        }
        return vetorBusca;
    }

    protected boolean isGrafo(Edge[][] grafo, int n){
        for(int i = 0; i<n; i++)
            for(int j = 0; j<n; j++)
                if(grafo[i][j].getCost() != -1)
                    return true;
        return false;
    }
    
    boolean isConexo(int linhaVisita){        
        Edge[][] adjacenciaGoodman = new Edge[nVertices][nVertices];
        int qtdLinhasVazias = 0;
        for(int i = 0; i<nVertices; i++){
            if(linhaVazia(i) && i!=linhaVisita)
                qtdLinhasVazias++;            
            for(int j = 0; j<nVertices; j++){
                adjacenciaGoodman[i][j] = new Edge(adjacenciaBusca[i][j].getLabel(), adjacenciaBusca[i][j].getCost());
            }        
        }
        int nComponentesConexo = 0, visita = 0, primeiro = 0;
        boolean[] visitados = new boolean[nVertices];
        boolean flag = false;
        while(isGrafo(adjacenciaGoodman, nVertices)){            
            Search B = new Search(nVertices, adjacenciaGoodman);
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
                            if(adjacenciaGoodman[primeiro][w].getCost() < adjacenciaGoodman[conexos[j]][w].getCost()){
                                adjacenciaGoodman[primeiro][w] = new Edge(adjacenciaGoodman[conexos[j]][w].getLabel(),adjacenciaGoodman[conexos[j]][w].getCost());
                            }
                            adjacenciaGoodman[conexos[j]][w] = new Edge("NULL", -1);
                        }
                    }
                }
            }
            nComponentesConexo++;
            if(flag){
                for(int i = 0; i<nVertices; i++)
                    adjacenciaGoodman[primeiro][i] = new Edge("NULL", -1);
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
            if(adjacenciaBusca[linha][j].getCost()==-1)
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
            Edge tmp;            
            if(adjacenciaBusca[visita][i].getCost() != -1 && i != visita){ 
                tmp = new Edge(adjacenciaBusca[visita][i].getLabel(),adjacenciaBusca[visita][i].getCost());
                adjacenciaBusca[visita][i] = new Edge("NULL",-1);
                adjacenciaBusca[i][visita] = new Edge("NULL",-1);                                
                if(isConexo(i)){                    
                    vetorCicloEuleriano[iVetor][0] = visita;
                    vetorCicloEuleriano[iVetor][1] = i;
                    iVetor++;                    
                    cicloEuleriano(i);
                }else{                    
                    adjacenciaBusca[visita][i] = new Edge(tmp.getLabel(),tmp.getCost());
                    adjacenciaBusca[i][visita] = new Edge(tmp.getLabel(),tmp.getCost());
                }
            }                        
        }
        
        return vetorCicloEuleriano;
    }
}