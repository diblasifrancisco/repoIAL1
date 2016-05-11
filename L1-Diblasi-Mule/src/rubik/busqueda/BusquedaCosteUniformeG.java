package rubik.busqueda;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Vector;

/* Estrategia de Busqueda en grafo Costo Uniforme, 
 * Implementacion de la interfaz Busqueda
 * Hereda de la clase abstracta BusquedaGrafo (generica independiente de la estrategia)
 * Implementa el método buscarSolucion() y devuelve un vector de operadores (solución)
 */
public class BusquedaCosteUniformeG extends BusquedaPrimeroMejor {

    @Override
    protected void ordenarLista() {
        Collections.sort(listaAbierta, ComparadoresNodos.COSTO);
    }

    @Override
    protected NodoBusqueda getNodoMenor() {
        if (listaAbierta.size() == 1) {
            return listaAbierta.pollFirst();
        } else {
            int menorEvaluacion = listaAbierta.element().getCosto();
            NodoBusqueda menorNodo = listaAbierta.element();
            for (NodoBusqueda n : listaAbierta) {
                if ((n.getCosto()) < menorEvaluacion) {
                    menorNodo = n;
                    menorEvaluacion = n.getCosto();
                }
            }
            listaAbierta.remove(menorNodo);
            return menorNodo;
        }
    }
}
