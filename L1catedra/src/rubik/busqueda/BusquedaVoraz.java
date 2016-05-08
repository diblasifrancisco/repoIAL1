package rubik.busqueda;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Vector;

public class BusquedaVoraz extends BusquedaHeuristica implements Busqueda {

  public Vector<Operador> buscarSolucion(Estado inicial){
    /*inicio reporte de busqueda */
    listaCerrada = new HashMap<Estado, NodoBusqueda>();
    listaAbierta = new LinkedList<NodoBusqueda>();
    Boolean solucionEncontrada = false;
    NodoBusqueda nodoSolucion = null;
    NodoBusqueda nodoActual = new NodoBusqueda(inicial,null,null);
		nodoActual.setProfundidad(0);
		nodoActual.setCosto(0);
    nodoActual.setHeuristica(h.obtenerHeuristica(nodoActual));
		TrazaGenerica traza = new TrazaGenerica(nodoActual);
    listaAbierta.add(nodoActual);
    while(!solucionEncontrada) {
      if(listaAbierta.size() == 0) {
        break;
      }
      else {
	traza.imprimirInicioIteracion(listaAbierta);  //muestro  estado de lista abirta al coienzo de la primer interación
        nodoActual = listaAbierta.pollFirst();      /** tomo y elimino el primer elemento de la listal*/
        reporteNodosExplorados();  //Antes de evaluar si el nodo es solución contabilizo nodos explorados
        if(!listaCerrada.containsKey(nodoActual.getEstado())) {
          if(nodoActual.getEstado().esFinal()) {
            solucionEncontrada = true;
            nodoSolucion = nodoActual;
          }
          else {
            listaCerrada.put(nodoActual.getEstado(), nodoActual);
            listaAbierta.addAll(expandirNodo(nodoActual));
            ordenarListaMenorHeuristica();
          }
        }
      }
    }
    /*reportes de rendimiento */
    if(nodoSolucion == null) {
      return new Vector<Operador>();
    }
    else {
      return encontrarCamino(nodoSolucion);
    }
	}

    private void ordenarListaMenorHeuristica() {
        Collections.sort(listaAbierta, ComparadoresNodos.HEURISTICA);
    }
 
}
