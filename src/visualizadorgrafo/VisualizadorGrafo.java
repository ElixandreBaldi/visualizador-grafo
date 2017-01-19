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
        painelGrafo.addMouseListener(new MouseListener (){
            @Override
            public void mouseClicked(MouseEvent e) {
                int x=e.getX();
                int y=e.getY();
                System.out.println(x+","+y);
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });
    }
    
    @Override
    public void paint(Graphics g){
        super.paint(g);
        if(redraw){
            g = painelGrafo.getGraphics();
            for(int i = 0; i<nVertices;i++){
                for(int j = 0; j<nVertices; j++){
                    if(adjacencia[i][j].getCusto() != -1){
                        int x1, x2, y1, y2;
                        x1 = vertices[i].getCoordX();
                        y1 = vertices[i].getCoordY();
                        x2 = vertices[j].getCoordX();
                        y2 = vertices[j].getCoordY();
                        g.drawLine(x1,y1,x2,y2);                        
                    }
                }
            }            
            redraw = false;
        }
    }
    
    protected void analisaLinha(String text, boolean isAresta) throws Exception {   
        if(!isAresta){ //vertice  
            int  dado, i = 0, id = 0, coordX = 0, coordY = 0, flag = 0;
            String rotulo = "", textoRotulo = "";
            // flags: 0 = id, 1 = rótulo, 2 = x, 3 = y
            while(i < text.length())
            {              
                if(flag == 1){
                    while(text.charAt(i) != ' '){                        
                        textoRotulo +=text.charAt(i);
                        i++;
                    }                    
                    rotulo = textoRotulo;
                    
                }else if(flag < 4)
                {
                    dado = 0;
                    while(text.charAt(i) != ' '){                        
                        int soma = getNumericValue(text.charAt(i));
                        dado = (dado*10) + soma;
                        i++;
                    }
                    switch(flag){
                        case 0:
                            id = dado;
                            break;
                        case 2:
                            coordX = dado;
                            break;
                        case 3:
                            coordY = dado;
                            break;
                    }
                }                
                if(text.charAt(i)==' '){
                    i++;                    
                }
                flag++;
            }
            Vertice[] tmp = vertices; // copia o array vértice atual para um temporário
            nVertices++; // incrementa o número de vértices
            vertices = new Vertice[nVertices]; // recria o array de vértices vazio, com o novo tamanho
            System.arraycopy(tmp, 0, vertices, 0, tmp.length); // copia o array temporário para o novo
            Vertice novoVertice = new Vertice(id, coordX, coordY, rotulo); // cria um novo vértice
            vertices[nVertices-1] = novoVertice; // insere o novo vértice na listagem
            //desenha o vértice na tela:
            javax.swing.JLabel novoLabel = new javax.swing.JLabel(rotulo, javax.swing.JLabel.CENTER);
            novoLabel.setBounds(coordX, coordY, novoLabel.getPreferredSize().width, novoLabel.getPreferredSize().height);
            novoLabel.setForeground(Color.blue);
            painelGrafo.add(novoLabel);
            SwingUtilities.updateComponentTreeUI(this);
        }
        else{//aresta 
           int i = 0, flag = 0, origem = 0, destino = 0, custo = 0, dado;
           String textoRotulo = "", rotulo = "";
           
           while(i < text.length())
           {
               if(flag == 3){
                    while(text.charAt(i) != ' '){                        
                        textoRotulo +=text.charAt(i);
                        i++;
                    }                    
                    rotulo = textoRotulo;                    
               }
               else if(flag < 4){
                    dado = 0;
                    while(text.charAt(i) != ' '){                        
                        int soma = getNumericValue(text.charAt(i));
                        dado = (dado*10) + soma;
                        i++;
                    }                    
                    switch(flag){
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
               if(text.charAt(i)==' '){
                    i++;                    
               }
               flag++;
           }       
           Aresta a = new Aresta(rotulo, custo);
           adjacencia[origem-1][destino-1]=a;
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

        subItemEditarInserirVertice.setText("Inserir vértice");
        itemEditar.add(subItemEditarInserirVertice);

        subItemEditarInserirAresta.setText("Inserir aresta");
        itemEditar.add(subItemEditarInserirAresta);

        subItemEditarRemoverVertice.setText("Remover vértice");
        itemEditar.add(subItemEditarRemoverVertice);

        subItemEditarRemoverAresta.setText("Remover aresta");
        itemEditar.add(subItemEditarRemoverAresta);

        menuPrincipal.add(itemEditar);

        itemBusca.setText("Busca");

        subItemBuscaLargura.setText("Busca em largura");
        itemBusca.add(subItemBuscaLargura);

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

    private void subItemBuscaProfundidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subItemBuscaProfundidadeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_subItemBuscaProfundidadeActionPerformed

    private void subItemArquivoAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subItemArquivoAbrirActionPerformed
        String conteudo = "", line;
        int returnVal = selectArquivo.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION)
        {
            Charset inputCharset = Charset.forName("ISO-8859-1");
            try
            {
                File file = selectArquivo.getSelectedFile();
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), inputCharset));
                fileLocation = file.getAbsolutePath();
                this.setTitle("Visualizador Grafo - " + file.getName());
                boolean isAresta = false;
                while ((line = reader.readLine()) != null){
                    if (line.length()>2)
                        if  (line.charAt(0)=='/')
                            if  (line.charAt(1)=='/')
                                continue;
                    if (line.isEmpty()){
                        isAresta = true;
                        adjacencia = new Aresta[nVertices][nVertices];                        
                        for(int i=0;i<nVertices;i++){                            
                            for(int j = 0; j<nVertices; j++){
                                adjacencia[i][j]= new Aresta("NULL",-1);
                            }
                        }
                        continue;
                    }
                    analisaLinha(line, isAresta);
                }
                reader.close();
                System.out.println("Leitura bem-sucedida.");
            }
            catch (Exception e)
            {
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
    private Vertice[] vertices = new Vertice[0];
    private Aresta[][] adjacencia;
    private int nVertices = 0;
    private boolean redraw = false;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu itemArquivo;
    private javax.swing.JMenu itemBusca;
    private javax.swing.JMenu itemEditar;
    private javax.swing.JLabel lblConexos;
    private javax.swing.JLabel lblEuleriano;
    private javax.swing.JMenuBar menuPrincipal;
    private javax.swing.JPanel painelGrafo;
    private javax.swing.JFileChooser selectArquivo;
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
