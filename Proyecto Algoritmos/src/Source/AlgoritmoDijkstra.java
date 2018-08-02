package Source;

import static Interface.Main.repaint;
import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class AlgoritmoDijkstra {

    private Grafo grafo;
    private int subTope;
    private Nodo auxi = null;
    private int auxAumulado;
    private int subAcomulado;
    private Nodo nodo[];
    private int tope;
    private int permanente;
    private int nodoFin;

    public AlgoritmoDijkstra(Grafo arboles, int tope, int permanente, int nodoFin) {
        this.grafo = arboles;
        this.tope = tope;
        this.nodo = new Nodo[tope];
        this.permanente = permanente;
        this.nodoFin = nodoFin;
    }

    public int getAcumulado() {
        return nodo[nodoFin].getAcumulado();
    }

    public void dijkstra(JPanel jPanel1) {
        for (int i = 0; i < tope; i++) {
            nodo[i] = new Nodo();
        }

        if (permanente != nodoFin) {
            jPanel1.paint(jPanel1.getGraphics());
            repaint(tope, grafo);
            Pintar.clickSobreNodo(jPanel1.getGraphics(), grafo.getCordeX(permanente), grafo.getCordeY(permanente), null, Color.GREEN); // pinta de color GREEN los nodos

            nodo[permanente].setVisitado(true);
            nodo[permanente].setNombre(permanente);

            do {
                subAcomulado = 0;
                auxAumulado = 2000000000;
                nodo[permanente].setEtiqueta(true);
                for (int j = 0; j < tope; j++) {
                    if (grafo.getmAdyacencia(j, permanente) == 1) {
                        subAcomulado = nodo[permanente].getAcumulado() + grafo.getmCoeficiente(j, permanente);
                        if (subAcomulado <= nodo[j].getAcumulado() && nodo[j].isVisitado() == true && nodo[j].isEtiqueta() == false) {
                            nodo[j].setAcumulado(subAcomulado);
                            nodo[j].setVisitado(true);
                            nodo[j].setNombre(j);
                            nodo[j].setPredecesor(nodo[permanente]);
                        } else if (nodo[j].isVisitado() == false) {
                            nodo[j].setAcumulado(subAcomulado);
                            nodo[j].setVisitado(true);
                            nodo[j].setNombre(j);
                            nodo[j].setPredecesor(nodo[permanente]);
                        }
                    }
                }
                for (int i = 0; i < tope; i++) {
                    if (nodo[i].isVisitado() == true && nodo[i].isEtiqueta() == false) {
                        if (nodo[i].getAcumulado() <= auxAumulado) {
                            permanente = nodo[i].getNombre();
                            auxAumulado = nodo[i].getAcumulado();
                        }
                    }
                }
                subTope++;
            } while (subTope < tope + 1);
            auxi = nodo[nodoFin];
            if (auxi.getPredecesor() == null) {
                JOptionPane.showMessageDialog(null, "No se Pudo LLegar Al Nodo " + nodoFin);
            }
            while (auxi.getPredecesor() != null) {
                Pintar.pintarCamino(jPanel1.getGraphics(), grafo.getCordeX(auxi.getNombre()), grafo.getCordeY(auxi.getNombre()), grafo.getCordeX(auxi.getPredecesor().getNombre()), grafo.getCordeY(auxi.getPredecesor().getNombre()), Color.GREEN);
                Pintar.clickSobreNodo(jPanel1.getGraphics(), grafo.getCordeX(auxi.getNombre()), grafo.getCordeY(auxi.getNombre()), null, Color.GREEN);
                auxi = auxi.getPredecesor();
            }
            Pintar.clickSobreNodo(jPanel1.getGraphics(), grafo.getCordeX(nodoFin), grafo.getCordeY(nodoFin), null, Color.GREEN);
        } else {
            Pintar.clickSobreNodo(jPanel1.getGraphics(), grafo.getCordeX(nodoFin), grafo.getCordeY(nodoFin), null, Color.GREEN);
        }
    }

}
