package rubik.busqueda;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Vector;

public class BusquedaAEstrella extends BusquedaPrimeroMejor {
    
    @Override
    protected void ordenarLista() {
        Collections.sort(listaAbierta, ComparadoresNodos.FC_EVALUACION);
    }
    
    @Override
    protected NodoBusqueda getNodoMenor() {
        if (listaAbierta.size() == 1) {
            return listaAbierta.pollFirst();
        } else {
            int menorEvaluacion = listaAbierta.element().getCosto() + listaAbierta.element().getHeuristica();
            NodoBusqueda menorNodo = listaAbierta.element();
            for (NodoBusqueda n : listaAbierta) {
                if ((n.getCosto() + n.getHeuristica()) < menorEvaluacion) {
                    menorNodo = n;
                    menorEvaluacion = n.getCosto() + n.getHeuristica();
                }
            }
            listaAbierta.remove(menorNodo);
            return menorNodo;
        }
    }

}
