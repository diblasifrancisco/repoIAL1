/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rubik.busqueda;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Vector;

/**
 *
 * @author gasmu_000
 */
public abstract class BusquedaPrimeroMejor extends BusquedaHeuristica implements Busqueda{
    
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
                nodoActual = getNodoMenor();

                reporteNodosExplorados();  //Antes de evaluar si el nodo es solución contabilizo nodos explorados
                if (!listaCerrada.containsKey(nodoActual.getEstado())) {
                    if (nodoActual.getEstado().esFinal()) {
                        solucionEncontrada = true;
                        //medida de rendimiento agregada
                        this.setResultadoEncontrado(true);
                        nodoSolucion = nodoActual;
                    } else {
                        listaCerrada.put(nodoActual.getEstado(), nodoActual);
                        listaAbierta.addAll(expandirNodo(nodoActual));

                        traza.imprimirFinalIteracion(nodoActual, listaAbierta);

                        ordenarLista();
                    }
                }
            }
        }
        /*reportes de rendimiento */
        // al terminar contabilizo nodos sobrantes con la clase RendimientoBusqueda
        this.reporteNodosSobrantes(listaAbierta.size());
        // Contabilizo tiempo al finalizar busqueda con la clase RendimientoBusqueda
        this.reporteFinBusqueda();
        if (nodoSolucion == null) {
            return new Vector<Operador>();
        } else {
            return encontrarCamino(nodoSolucion);
        }
    }
    
    protected abstract void ordenarLista();
    
    protected abstract NodoBusqueda getNodoMenor();
}
