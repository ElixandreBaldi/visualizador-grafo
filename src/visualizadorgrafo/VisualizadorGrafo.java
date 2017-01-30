package visualizadorgrafo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import static java.lang.Character.getNumericValue;
import javax.swing.SwingUtilities;

public class VisualizadorGrafo extends javax.swing.JFrame {

    /**
     * Creates new form VisualizadorGrafo
     */
    public VisualizadorGrafo() {
        initComponents();
        painelGrafo.addMouseListener(new MouseListener() {
            // À cada clique, a saída é o X/Y daquela posição
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                System.out.println(x + "," + y);
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        //função de sistema que desenha elementos na tela, fizemos um Override para desenhar as arestas que precisamos
        super.paint(g);
        if (redraw) {
            g = painelGrafo.getGraphics();
            for (int i = 0; i < nVertices; i++) { //desenha arestas
                for (int j = 0; j < nVertices; j++) {
                    if (adjacencia[i][j].getCusto() != -1) {
                        int x1, x2, y1, y2;
                        x1 = vertices[i].getCoordX();
                        y1 = vertices[i].getCoordY();
                        x2 = vertices[j].getCoordX();
                        y2 = vertices[j].getCoordY();
                        g.drawLine(x1, y1, x2, y2);
                    }
                }
            }
            redraw = false;
        }
    }

    public void redrawVertices() {
        //limpa o painel do grafo e reinsere todos os elementos, em seguida chama repaint() para reinserir arestas
        painelGrafo.removeAll();
        //desenha o vértice na tela:
        for (int i = 0; i < nVertices; i++) {
            javax.swing.JLabel novoLabel = new javax.swing.JLabel(vertices[i].getRotulo(), javax.swing.JLabel.CENTER);
            novoLabel.setBounds(vertices[i].getCoordX(), vertices[i].getCoordY(), novoLabel.getPreferredSize().width, novoLabel.getPreferredSize().height);
            novoLabel.setForeground(Color.blue);
            novoLabel.setToolTipText(i + "");
            painelGrafo.add(novoLabel);
        }
        SwingUtilities.updateComponentTreeUI(this);
        redraw = true;
        repaint();
    }

    protected void reset() {
        // reinicia tudo para criar/abrir novos arquivos
        fileLocation = "";
        vertices = new Vertice[0];
        adjacencia = new Aresta[0][0];
        nVertices = 0;
        this.setTitle("Visualizador Grafo");
        painelGrafo.removeAll();
        painelGrafo.setEnabled(true);
        itemEditar.setEnabled(true);
        itemBusca.setEnabled(true);
        subItemArquivoSalvar.setEnabled(true);
        redraw = true;
        repaint();
    }

    protected void analisaLinha(String text, boolean isAresta) throws Exception {
        if (!isAresta) { //vertice  
            int dado, i = 0, id = 0, coordX = 0, coordY = 0, flag = 1;
            String rotulo = "", textoRotulo = "";
            // flags: 1 = rótulo, 2 = x, 3 = y
            while (i < text.length()) {
                if (flag == 1) {
                    while (text.charAt(i) != ' ') {
                        textoRotulo += text.charAt(i);
                        i++;
                    }
                    rotulo = textoRotulo;

                } else if (flag < 4) {
                    dado = 0;
                    while (text.charAt(i) != ' ') {
                        int soma = getNumericValue(text.charAt(i));
                        dado = (dado * 10) + soma;
                        i++;
                    }
                    switch (flag) {
                        case 2:
                            coordX = dado;
                            break;
                        case 3:
                            coordY = dado;
                            break;
                    }
                }
                if (text.charAt(i) == ' ') {
                    i++;
                }
                flag++;
            }
            Vertice[] tmp = vertices; // copia o array vértice atual para um temporário
            nVertices++; // incrementa o número de vértices
            vertices = new Vertice[nVertices]; // recria o array de vértices vazio, com o novo tamanho
            System.arraycopy(tmp, 0, vertices, 0, tmp.length); // copia o array temporário para o novo
            Vertice novoVertice = new Vertice(coordX, coordY, rotulo); // cria um novo vértice
            vertices[nVertices - 1] = novoVertice; // insere o novo vértice na listagem
        } else {//aresta 
            int i = 0, flag = 0, origem = 0, destino = 0, custo = 0, dado;
            String textoRotulo = "", rotulo = "";
            while (i < text.length()) {
                if (flag == 3) {
                    while (text.charAt(i) != ' ') {
                        textoRotulo += text.charAt(i);
                        i++;
                    }
                    rotulo = textoRotulo;
                } else if (flag < 4) {
                    dado = 0;
                    while (text.charAt(i) != ' ') {
                        int soma = getNumericValue(text.charAt(i));
                        dado = (dado * 10) + soma;
                        i++;
                    }
                    switch (flag) {
                        case 0:
                            origem = dado;
                            break;
                        case 1:
                            destino = dado;
                            break;
                        case 2:
                            custo = dado;
                            break;
                    }
                }
                if (text.charAt(i) == ' ') {
                    i++;
                }
                flag++;
            }
            Aresta a = new Aresta(rotulo, custo);
            adjacencia[origem-1][destino-1] = a;
            adjacencia[destino-1][origem-1] = a;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        selectArquivo = new javax.swing.JFileChooser();
        lblEuleriano = new javax.swing.JLabel();
        lblConexos = new javax.swing.JLabel();
        painelGrafo = new javax.swing.JPanel();
        menuPrincipal = new javax.swing.JMenuBar();
        itemArquivo = new javax.swing.JMenu();
        subItemArquivoNovo = new javax.swing.JMenuItem();
        subItemArquivoAbrir = new javax.swing.JMenuItem();
        subItemArquivoSalvar = new javax.swing.JMenuItem();
        subItemArquivoSair = new javax.swing.JMenuItem();
        itemEditar = new javax.swing.JMenu();
        subItemEditarInserirVertice = new javax.swing.JMenuItem();
        subItemEditarInserirAresta = new javax.swing.JMenuItem();
        subItemEditarRemoverVertice = new javax.swing.JMenuItem();
        subItemEditarRemoverAresta = new javax.swing.JMenuItem();
        itemBusca = new javax.swing.JMenu();
        subItemBuscaLargura = new javax.swing.JMenuItem();
        subItemBuscaProfundidade = new javax.swing.JMenuItem();
        subItemBuscaFleury = new javax.swing.JMenuItem();
        subItemBuscaDijkstra = new javax.swing.JMenuItem();
        subItemAlgoritmosGoodman = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Visualizador Grafo");
        setMaximumSize(new java.awt.Dimension(800, 600));
        setMinimumSize(new java.awt.Dimension(800, 600));
        setName("mainFrame"); // NOI18N
        setPreferredSize(new java.awt.Dimension(800, 600));

        lblEuleriano.setText("Não euleriano");
        lblEuleriano.setEnabled(false);
        lblEuleriano.setFocusable(false);

        lblConexos.setText("Componentes conexos: 0");
        lblConexos.setEnabled(false);
        lblConexos.setFocusable(false);

        painelGrafo.setBackground(new java.awt.Color(254, 254, 254));
        painelGrafo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 0));
        painelGrafo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        painelGrafo.setEnabled(false);

        javax.swing.GroupLayout painelGrafoLayout = new javax.swing.GroupLayout(painelGrafo);
        painelGrafo.setLayout(painelGrafoLayout);
        painelGrafoLayout.setHorizontalGroup(
            painelGrafoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        painelGrafoLayout.setVerticalGroup(
            painelGrafoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 532, Short.MAX_VALUE)
        );

        itemArquivo.setText("Arquivo");

        subItemArquivoNovo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        subItemArquivoNovo.setText("Novo");
        subItemArquivoNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subItemArquivoNovoActionPerformed(evt);
            }
        });
        itemArquivo.add(subItemArquivoNovo);

        subItemArquivoAbrir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        subItemArquivoAbrir.setText("Abrir");
        subItemArquivoAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subItemArquivoAbrirActionPerformed(evt);
            }
        });
        itemArquivo.add(subItemArquivoAbrir);

        subItemArquivoSalvar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        subItemArquivoSalvar.setText("Salvar");
        subItemArquivoSalvar.setEnabled(false);
        subItemArquivoSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subItemArquivoSalvarActionPerformed(evt);
            }
        });
        itemArquivo.add(subItemArquivoSalvar);

        subItemArquivoSair.setText("Sair");
        subItemArquivoSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subItemArquivoSairActionPerformed(evt);
            }
        });
        itemArquivo.add(subItemArquivoSair);

        menuPrincipal.add(itemArquivo);

        itemEditar.setText("Editar");
        itemEditar.setEnabled(false);

        subItemEditarInserirVertice.setText("Inserir vértice");
        subItemEditarInserirVertice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subItemEditarInserirVerticeActionPerformed(evt);
            }
        });
        itemEditar.add(subItemEditarInserirVertice);

        subItemEditarInserirAresta.setText("Inserir aresta");
        subItemEditarInserirAresta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subItemEditarInserirArestaActionPerformed(evt);
            }
        });
        itemEditar.add(subItemEditarInserirAresta);

        subItemEditarRemoverVertice.setText("Remover vértice");
        subItemEditarRemoverVertice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subItemEditarRemoverVerticeActionPerformed(evt);
            }
        });
        itemEditar.add(subItemEditarRemoverVertice);

        subItemEditarRemoverAresta.setText("Remover aresta");
        subItemEditarRemoverAresta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subItemEditarRemoverArestaActionPerformed(evt);
            }
        });
        itemEditar.add(subItemEditarRemoverAresta);

        menuPrincipal.add(itemEditar);

        itemBusca.setText("Algoritmos");
        itemBusca.setEnabled(false);

        subItemBuscaLargura.setText("Busca em largura");
        subItemBuscaLargura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subItemBuscaLarguraActionPerformed(evt);
            }
        });
        itemBusca.add(subItemBuscaLargura);

        subItemBuscaProfundidade.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_MASK));
        subItemBuscaProfundidade.setText("Busca em profundidade");
        subItemBuscaProfundidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subItemBuscaProfundidadeActionPerformed(evt);
            }
        });
        itemBusca.add(subItemBuscaProfundidade);

        subItemBuscaFleury.setText("Ciclo Euleriano (Fleury)");
        itemBusca.add(subItemBuscaFleury);

        subItemBuscaDijkstra.setText("Custo Mínimo (Dijkstra)");
        itemBusca.add(subItemBuscaDijkstra);

        subItemAlgoritmosGoodman.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
        subItemAlgoritmosGoodman.setText("Goodman");
        subItemAlgoritmosGoodman.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subItemAlgoritmosGoodmanActionPerformed(evt);
            }
        });
        itemBusca.add(subItemAlgoritmosGoodman);

        menuPrincipal.add(itemBusca);

        setJMenuBar(menuPrincipal);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(painelGrafo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblEuleriano)
                        .addGap(18, 18, 18)
                        .addComponent(lblConexos)
                        .addGap(0, 485, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(painelGrafo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEuleriano)
                    .addComponent(lblConexos))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void subItemArquivoSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subItemArquivoSairActionPerformed
        System.exit(0);
    }//GEN-LAST:event_subItemArquivoSairActionPerformed
    
    private boolean isEuleriano(){
        int cont;
        if(QtdComponentesConexo == 1){
            for(int i = 0; i<nVertices; i++){
                cont = 0;
                for(int j = 0; j<nVertices; j++){
                    if(adjacencia[i][j].getCusto() != -1)
                        cont++;
                }
                if(cont%2 != 0)
                    return false;
            }
        }
        else
            return false;
        
        return true;
    }
    
    private void subItemBuscaProfundidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subItemBuscaProfundidadeActionPerformed
        // TODO add your handling code here:
        int noInformado;
        
        String xString = JOptionPane.showInputDialog(painelGrafo,
                "Informe o nó de início", null);
        try {
            noInformado = Integer.parseInt(xString);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Valor inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
            System.err.println(e);
            return;
        }
        Busca B = new Busca(nVertices, adjacencia);
        int[] vetor = B.profundidade(noInformado);
        for(int i = 0; i<nVertices; i++)
            System.out.println(vetor[i]);
    }//GEN-LAST:event_subItemBuscaProfundidadeActionPerformed
    
    private void subItemArquivoAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subItemArquivoAbrirActionPerformed
        String conteudo = "", line;
        int returnVal = selectArquivo.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            reset();
            Charset inputCharset = Charset.forName("ISO-8859-1");
            try {
                File file = selectArquivo.getSelectedFile();
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), inputCharset));
                fileLocation = file.getAbsolutePath();
                this.setTitle("Visualizador Grafo - " + file.getName());
                boolean isAresta = false;
                while ((line = reader.readLine()) != null) {
                    if (line.length() > 2) {
                        if (line.charAt(0) == '/') {
                            if (line.charAt(1) == '/') {
                                continue;
                            }
                        }
                    }
                    if (line.isEmpty()) {
                        redrawVertices();
                        isAresta = true;
                        adjacencia = new Aresta[nVertices][nVertices];
                        for (int i = 0; i < nVertices; i++) {
                            for (int j = 0; j < nVertices; j++) {
                                adjacencia[i][j] = new Aresta("NULL", -1);
                            }
                        }
                        continue;
                    }
                    analisaLinha(line, isAresta);
                }
                reader.close();
                System.out.println("Leitura bem-sucedida.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao carregar arquivo.", "Erro", JOptionPane.ERROR_MESSAGE);
                System.err.println(e);
            }
            redraw = true;
            repaint();
        }
    }//GEN-LAST:event_subItemArquivoAbrirActionPerformed

    private void subItemArquivoSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subItemArquivoSalvarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_subItemArquivoSalvarActionPerformed
    
    boolean isGrafo(Aresta[][] grafo, int n){
        for(int i = 0; i<n; i++)
            for(int j = 0; j<n; j++)
                if(grafo[i][j].getCusto() != -1)
                    return true;
        return false;
    }
    
    private void subItemAlgoritmosGoodmanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subItemAlgoritmosGoodmanActionPerformed
        Aresta[][] adjacenciaGoodman = adjacencia;
        int nComponentesConexo = 0, visita = 0, primeiro = 0;
        boolean[] visitados = new boolean[nVertices];
        boolean flag = false;
        
        while(isGrafo(adjacenciaGoodman, nVertices)){            
            Busca B = new Busca(nVertices, adjacenciaGoodman);
        
            for(int i = 0; i<nVertices; i++){//escolhe qualquer um que não foi visitado                
                if(!visitados[i]){
                    visita = i;
                    i = nVertices;
                }
                
                int conexos[] = B.profundidade(visita);                
                primeiro = conexos[0];
                flag = true;
                for(int j = 0; j<nVertices; j++){
                    if(conexos[j]!=-1)
                        visitados[conexos[j]] = true;
                }
                for(int j = 1; j <nVertices; j++){
                    if(conexos[j] != -1){
                        for(int w = 0; w < nVertices; w++){
                            if(adjacenciaGoodman[primeiro][w].getCusto() < adjacenciaGoodman[conexos[j]][w].getCusto()){
                                adjacenciaGoodman[primeiro][w] = adjacenciaGoodman[conexos[j]][w];
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
        
        for(int i = 0; i<nVertices; i++){
            if(!visitados[i])
                nComponentesConexo++;
        }
        QtdComponentesConexo = nComponentesConexo;    
        System.out.println("Componentes Conexos: "+nComponentesConexo);//ALERT              
        System.out.println("Euleriano: "+isEuleriano());//ALERT
        
    }//GEN-LAST:event_subItemAlgoritmosGoodmanActionPerformed
                                                       
    private void subItemEditarInserirVerticeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subItemEditarInserirVerticeActionPerformed
        int x, y;
        String rotulo = JOptionPane.showInputDialog(painelGrafo,
                "Rótulo", null);
        String xString = JOptionPane.showInputDialog(painelGrafo,
                "Coordenada X", null);
        try {
            x = Integer.parseInt(xString);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Valor inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
            System.err.println(e);
            return;
        }
        String yString = JOptionPane.showInputDialog(painelGrafo,
                "Coordenada Y", null);
        try {
            y = Integer.parseInt(yString);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Valor inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
            System.err.println(e);
            return;
        }
        nVertices++; // incrementa o número de vértices
        Aresta[][] tmp = adjacencia;
        adjacencia = new Aresta[nVertices][nVertices];
        for (int i = 0; i < nVertices - 1; i++) {
            System.arraycopy(tmp[i], 0, adjacencia[i], 0, nVertices - 1);
        }
        for (int i = 0; i < nVertices; i++) {
            adjacencia[i][nVertices - 1] = new Aresta("NULL", -1);
        }
        for (int i = 0; i < nVertices; i++) {
            adjacencia[nVertices - 1][i] = new Aresta("NULL", -1);
        }
        Vertice[] tmp2 = vertices; // copia o array vértice atual para um temporário
        vertices = new Vertice[nVertices]; // recria o array de vértices vazio, com o novo tamanho
        System.arraycopy(tmp2, 0, vertices, 0, tmp2.length); // copia o array temporário para o novo
        Vertice novoVertice = new Vertice(x, y, rotulo); // cria um novo vértice
        vertices[nVertices - 1] = novoVertice; // insere o novo vértice na listagem
        
        redrawVertices();
    }//GEN-LAST:event_subItemEditarInserirVerticeActionPerformed

    private void subItemArquivoNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subItemArquivoNovoActionPerformed
        reset();
    }//GEN-LAST:event_subItemArquivoNovoActionPerformed

    private void subItemBuscaLarguraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subItemBuscaLarguraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_subItemBuscaLarguraActionPerformed

    private void subItemEditarInserirArestaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subItemEditarInserirArestaActionPerformed
        int custo, origem, destino;
        String rotulo = JOptionPane.showInputDialog(painelGrafo,
                "Rótulo", null);
        String custoString = JOptionPane.showInputDialog(painelGrafo,
                "Custo", null);
        try {
            custo = Integer.parseInt(custoString);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Valor inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
            System.err.println(e);
            return;
        }
        String origemString = JOptionPane.showInputDialog(painelGrafo,
                "ID de Origem", null);
        try {
            origem = Integer.parseInt(origemString);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Valor inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
            System.err.println(e);
            return;
        }
        String destinoString = JOptionPane.showInputDialog(painelGrafo,
                "ID de Destino", null);
        try {
            destino = Integer.parseInt(destinoString);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Valor inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
            System.err.println(e);
            return;
        }
        try {
            Aresta a = new Aresta(rotulo, custo);
            adjacencia[origem][destino] = a;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ID inexistente.", "Erro", JOptionPane.ERROR_MESSAGE);
            System.err.println(e);
        }
        redraw = true;
        repaint();
    }//GEN-LAST:event_subItemEditarInserirArestaActionPerformed

    private void subItemEditarRemoverArestaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subItemEditarRemoverArestaActionPerformed
        int origem, destino;
        String origemString = JOptionPane.showInputDialog(painelGrafo,
                "ID de Origem", null);
        try {
            origem = Integer.parseInt(origemString);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Valor inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
            System.err.println(e);
            return;
        }
        String destinoString = JOptionPane.showInputDialog(painelGrafo,
                "ID de Destino", null);
        try {
            destino = Integer.parseInt(destinoString);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Valor inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
            System.err.println(e);
            return;
        }
        try {
            adjacencia[origem][destino] = new Aresta("NULL", -1);
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ID inexistente.", "Erro", JOptionPane.ERROR_MESSAGE);
            System.err.println(e);
        }
        redraw = true;
        repaint();
    }//GEN-LAST:event_subItemEditarRemoverArestaActionPerformed

    private void subItemEditarRemoverVerticeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subItemEditarRemoverVerticeActionPerformed
        int id;
        String idString = JOptionPane.showInputDialog(painelGrafo,
                "ID", null);
        try {
            id = Integer.parseInt(idString);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Valor inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
            System.err.println(e);
            return;
        }
        if (id < 0 || id >= nVertices){
            JOptionPane.showMessageDialog(null, "ID inexistente.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        nVertices--;
        Vertice[] tmp = vertices; // copia o array vértice atual para um temporário
        vertices = new Vertice[nVertices];
        for (int i=0;i<id;i++)
            vertices[i] = tmp[i]; //copia a primeira parte do array, antes do elemento excluido
        if (id < nVertices)
            for (int i=id+1;i<nVertices+1;i++)
                vertices[i-1] = tmp[i]; //copia a segunda parte do array, depois do elemento excluido
        //Apaga arestas do nodo excluído
        Aresta[][] tmp2 = adjacencia;
        adjacencia = new Aresta[nVertices][nVertices];
        for (int i=0; i<id;i++) // primeiro quadrante
            for (int j=0; j<id; j++)
                adjacencia[i][j] = tmp2[i][j];
        for (int i=id+1; i<nVertices+1;i++) // segundo quadrante
            for (int j=0; j<id; j++)
                adjacencia[i-1][j] = tmp2[i][j];
        for (int i=0; i<id;i++) // terceiro quadrante
            for (int j=id+1; j<nVertices+1; j++)
                adjacencia[i][j-1] = tmp2[i][j];
        for (int i=id+1; i<nVertices+1;i++) // quarto quadrante
            for (int j=id+1; j<nVertices+1; j++)
                adjacencia[i-1][j-1] = tmp2[i][j];
        redrawVertices();
    }//GEN-LAST:event_subItemEditarRemoverVerticeActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VisualizadorGrafo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VisualizadorGrafo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VisualizadorGrafo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VisualizadorGrafo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VisualizadorGrafo().setVisible(true);
            }
        });
    }

    private String fileLocation;
    private Vertice[] vertices;
    private Aresta[][] adjacencia;
    private int nVertices;
    private boolean redraw = false;
    private int QtdComponentesConexo = 0;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu itemArquivo;
    private javax.swing.JMenu itemBusca;
    private javax.swing.JMenu itemEditar;
    private javax.swing.JLabel lblConexos;
    private javax.swing.JLabel lblEuleriano;
    private javax.swing.JMenuBar menuPrincipal;
    private javax.swing.JPanel painelGrafo;
    private javax.swing.JFileChooser selectArquivo;
    private javax.swing.JMenuItem subItemAlgoritmosGoodman;
    private javax.swing.JMenuItem subItemArquivoAbrir;
    private javax.swing.JMenuItem subItemArquivoNovo;
    private javax.swing.JMenuItem subItemArquivoSair;
    private javax.swing.JMenuItem subItemArquivoSalvar;
    private javax.swing.JMenuItem subItemBuscaDijkstra;
    private javax.swing.JMenuItem subItemBuscaFleury;
    private javax.swing.JMenuItem subItemBuscaLargura;
    private javax.swing.JMenuItem subItemBuscaProfundidade;
    private javax.swing.JMenuItem subItemEditarInserirAresta;
    private javax.swing.JMenuItem subItemEditarInserirVertice;
    private javax.swing.JMenuItem subItemEditarRemoverAresta;
    private javax.swing.JMenuItem subItemEditarRemoverVertice;
    // End of variables declaration//GEN-END:variables
}
