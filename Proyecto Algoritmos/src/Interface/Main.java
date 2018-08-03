package Interface;

import java.awt.Color;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import Source.AlgoritmoDijkstra;
import Source.Grafo;
import Source.Pintar;

public class Main extends javax.swing.JFrame {

    private int tope = 0;
    private int nodoFin;
    private int permanente;
    int n = 0, nn = 0, id, id2;

    public Main() {
        initComponents();
        cargarMapa();
        this.setTitle("Camino mas Corto - Dijkstra");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    Pintar pintar = new Pintar();
    Grafo grafo = new Grafo();

    private void cargarMapa() {
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/Resources/map.png"));
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(800, 600, java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        jLabel1.setIcon(imageIcon);
    }

    public static void repaint(int tope, Grafo arboles) {
        for (int j = 0; j < tope; j++) {
            for (int k = 0; k < tope; k++) {
                if (arboles.getmAdyacencia(j, k) == 1) {
                    Pintar.pintarLinea(jPanel1.getGraphics(), arboles.getCordeX(j), arboles.getCordeY(j), arboles.getCordeX(k), arboles.getCordeY(k), arboles.getmCoeficiente(j, k));
                }
            }
        }
        for (int j = 0; j < tope; j++) {
            Pintar.pintarCirculo(jPanel1.getGraphics(), arboles.getCordeX(j), arboles.getCordeY(j), String.valueOf(arboles.getNombre(j)));
        }
    }
    
    public void cargar() {
        String infoNodos = abrirArchivo("nodosAux.txt");
        iniciarMapa(infoNodos, 28, true);

        String infoArcos = abrirArchivo("arcosAux.txt");
        iniciarMapa(infoArcos, 31, false);
    }

    public void clicIzqSobreNodo(int xxx, int yyy) {
        for (int j = 0; j < tope; j++) {
            if ((xxx + 2) > grafo.getCordeX(j) && xxx < (grafo.getCordeX(j) + 13) && (yyy + 2) > grafo.getCordeY(j) && yyy < (grafo.getCordeY(j) + 13)) {
                if (nn == 0) {
                    permanente = j;
                    repaint(tope, grafo);
                } else {
                    nodoFin = j;
                }
                nn++;
                n = 0;
                id = -1;
                Pintar.clickSobreNodo(jPanel1.getGraphics(), grafo.getCordeX(j), grafo.getCordeY(j), null, Color.GREEN);
                break;
            }
        }
    }

    private void cargarNodos(int x, int y) {
        grafo.setCordeX(tope, x);
        grafo.setCordeY(tope, y);
        grafo.setNombre(tope, tope);
        Pintar.pintarCirculo(jPanel1.getGraphics(), grafo.getCordeX(tope), grafo.getCordeY(tope), String.valueOf(grafo.getNombre(tope)));
        tope++;
    }

    private void cargarArcos(int id, int id2, int tamano) {
        grafo.setmAdyacencia(id2, id, 1);
        grafo.setmAdyacencia(id, id2, 1);
        grafo.setmCoeficiente(id2, id, tamano);
        grafo.setmCoeficiente(id, id2, tamano);
        Pintar.pintarLinea(jPanel1.getGraphics(), grafo.getCordeX(id), grafo.getCordeY(id), grafo.getCordeX(id2), grafo.getCordeY(id2), tamano);
        Pintar.pintarCirculo(jPanel1.getGraphics(), grafo.getCordeX(id), grafo.getCordeY(id), String.valueOf(grafo.getNombre(id)));
        Pintar.pintarCirculo(jPanel1.getGraphics(), grafo.getCordeX(id2), grafo.getCordeY(id2), String.valueOf(grafo.getNombre(id2)));
    }

    private void iniciarMapa(String info, int tamano, boolean cambiar) {
        StringTokenizer tokens = new StringTokenizer(info, "\n");

        for (int i = 0; i < tamano; i++) {
            StringTokenizer tokens1 = new StringTokenizer(tokens.nextElement().toString(), " ");
            if (cambiar) {
                cargarNodos(Integer.parseInt(tokens1.nextElement().toString()), Integer.parseInt(tokens1.nextElement().toString()));
            } else {
                cargarArcos(Integer.parseInt(tokens1.nextElement().toString()), Integer.parseInt(tokens1.nextElement().toString()), Integer.parseInt(tokens1.nextElement().toString()));
            }
        }
    }

    private String abrirArchivo(String nombre) {
        String infoNodos = "";
        String aux;
        try {
            FileReader lector = new FileReader(nombre);
            BufferedReader contenido = new BufferedReader(lector);

            while ((aux = contenido.readLine()) != null) {
                infoNodos += aux + "\n";
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return infoNodos;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("The Big Bang Theory - http://www.jc-mouse.net/");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(141, 141, 141));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setMaximumSize(new java.awt.Dimension(600, 800));
        jPanel1.setPreferredSize(new java.awt.Dimension(600, 800));
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel1MousePressed(evt);
            }
        });

        jLabel1.setMaximumSize(new java.awt.Dimension(600, 800));
        jLabel1.setPreferredSize(new java.awt.Dimension(600, 800));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 798, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
                .addGap(48, 48, 48))
        );

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Teatro", "Administración", "Civil", "Ciencias", "Geología y Petróleos", "ICB", "Mecánica", "Eléctrica", "Química", "Sistemas", "Esfot", "CEC" }));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Teatro", "Administración", "Civil", "Ciencias", "Geología y Petróleos", "ICB", "Mecánica", "Eléctrica", "Química", "Sistemas", "Esfot", "CEC" }));

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MousePressed
        int xxx, yyy;
        xxx = evt.getX();
        yyy = evt.getY();
        if (!evt.isMetaDown()) {
            clicIzqSobreNodo(xxx, yyy);
            if (nn == 2) {
                nn = 0;
                AlgoritmoDijkstra Dijkstra = new AlgoritmoDijkstra(grafo, tope, permanente, nodoFin);
                Dijkstra.dijkstra(jPanel1);
                double acumulado = Dijkstra.getAcumulado();
                JOptionPane.showMessageDialog(this, "Su recorrido total es:\n"
                        + (int) acumulado+ "m");
            }
        }
    }//GEN-LAST:event_jPanel1MousePressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        switch(jComboBox2.getSelectedItem().toString()){
            case "Teatro":
                clicIzqSobreNodo(285,432);
            case "Administración":
                clicIzqSobreNodo(257,416);
            case "Civil":
                clicIzqSobreNodo(184,466);
            case "Ciencias":
                clicIzqSobreNodo(322,346);
            case "Geología y Petróleos":
                clicIzqSobreNodo(373,313);
            case "ICB":
                clicIzqSobreNodo(295,243);
            case "Mecánica":
                clicIzqSobreNodo(312,207);
            case "Eléctrica":
                clicIzqSobreNodo(362,147);
            case "Química":
                clicIzqSobreNodo(383,166);
            case "Sistemas":
                clicIzqSobreNodo(423,271);
            case "Esfot":
                clicIzqSobreNodo(504,288);
            case "CEC":
                clicIzqSobreNodo(643,72);
        }
        
        switch(jComboBox3.getSelectedItem().toString()){
            case "Teatro":
                clicIzqSobreNodo(285,432);
            case "Administración":
                clicIzqSobreNodo(257,416);
            case "Civil":
                clicIzqSobreNodo(184,466);
            case "Ciencias":
                clicIzqSobreNodo(322,346);
            case "Geología y Petróleos":
                clicIzqSobreNodo(373,313);
            case "ICB":
                clicIzqSobreNodo(295,243);
            case "Mecánica":
                clicIzqSobreNodo(312,207);
            case "Eléctrica":
                clicIzqSobreNodo(362,147);
            case "Química":
                clicIzqSobreNodo(383,166);
            case "Sistemas":
                clicIzqSobreNodo(423,271);
            case "Esfot":
                clicIzqSobreNodo(504,288);
            case "CEC":
                clicIzqSobreNodo(643,72);
        }
        
        if (nn == 2) {
                nn = 0;
                AlgoritmoDijkstra Dijkstra = new AlgoritmoDijkstra(grafo, tope, permanente, nodoFin);
                Dijkstra.dijkstra(jPanel1);
                double acumulado = Dijkstra.getAcumulado();
                JOptionPane.showMessageDialog(this, "Su recorrido total es:\n"
                        + (int) acumulado+ "m");
            }
    }//GEN-LAST:event_jButton1ActionPerformed

    public static void main(String args[]) {
        final Main main = new Main();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                main.setVisible(true);
            }
        });
        main.cargar();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private static javax.swing.JLabel jLabel1;
    public static javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

}
