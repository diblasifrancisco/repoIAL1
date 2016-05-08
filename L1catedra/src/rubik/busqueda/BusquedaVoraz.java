/**package rubik.busqueda;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Vector;

public class BusquedaVoraz extends BusquedaPrimeroMejor {

  @Override
    protected void ordenarLista() {
        Collections.sort(listaAbierta, ComparadoresNodos.HEURISTICA);
    }
    
    @Override
    protected NodoBusqueda getNodoMenor() {
        if (listaAbierta.size() == 1) {
            return listaAbierta.pollFirst();
        } else {
            int menorEvaluacion = listaAbierta.element().getHeuristica();
            NodoBusqueda menorNodo = listaAbierta.element();
            for (NodoBusqueda n : listaAbierta) {
                if ((n.getHeuristica()) < menorEvaluacion) {
                    menorNodo = n;
                    menorEvaluacion = n.getHeuristica();
                }
            }
            listaAbierta.remove(menorNodo);
            return menorNodo;
        }
    }
}
*/