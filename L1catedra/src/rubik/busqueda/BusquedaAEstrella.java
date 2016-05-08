package rubik.busqueda;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Vector;

public class BusquedaAEstrella extends BusquedaHeuristica implements Busqueda {

    @Override
    public Vector<Operador> buscarSolucion(Estado inicial) {
        this.reporteInicioBusqueda();
        listaCerrada = new HashMap<Estado, NodoBusqueda>();
        listaAbierta = new LinkedList<NodoBusqueda>();
        Boolean solucionEncontrada = false;
        NodoBusqueda nodoSolucion = null;
        NodoBusqueda nodoActual = new NodoBusqueda(inicial, null, null);
        nodoActual.setProfundidad(0);
        nodoActual.setCosto(0);
        /*  asigno heuriristica  */
        nodoActual.setHeuristica(h.obtenerHeuristica(nodoActual));
        nodoActual.setFuncionEv(0, nodoActual.getHeuristica());

        TrazaGenerica traza = new TrazaGenerica(nodoActual);
        listaAbierta.add(nodoActual);
        while (!solucionEncontrada) {
            if (listaAbierta.size() == 0) {
                break;
            } else {
                traza.imprimirInicioIteracion(listaAbierta);  //muestro  estado de lista abirta al coienzo de la primer interación
                /**
                 * tomo y elimino el primer elemento de la listal
                 */
                nodoActual = getNodoMenorFnEvaluacionListaAbierta();

                reporteNodosExplorados();  //Antes de evaluar si el nodo es solución contabilizo nodos explorados
                if (!listaCerrada.containsKey(nodoActual.getEstado())) {
                    if (nodoActual.getEstado().esFinal()) {
                        solucionEncontrada = true;
                        nodoSolucion = nodoActual;
                    } else {
                        listaCerrada.put(nodoActual.getEstado(), nodoActual);
                        listaAbierta.addAll(expandirNodo(nodoActual));

                        traza.imprimirFinalIteracion(nodoActual, listaAbierta);

                        ordenarListaFnEvaluacion();
                    }
                }
            }
        }
        /*reportes de rendimiento */
        // al terminar contabilizo nodos sobrantes con la clase RendimientoBusqueda
        this.reporteNodosSobrantes(listaAbierta.size());
        // Contabilizo tiempo al finalizar busqueda con la clase RendimientoBusqueda
        this.reporteFinBusqueda();
        System.out.println(this.getReporteCompleto());
        if (nodoSolucion == null) {
            return new Vector<Operador>();
        } else {
            return encontrarCamino(nodoSolucion);
        }
    }

    private void ordenarListaFnEvaluacion() {
        Collections.sort(listaAbierta, ComparadoresNodos.FC_EVALUACION);
    }

    private NodoBusqueda getNodoMenorFnEvaluacionListaAbierta() {
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
