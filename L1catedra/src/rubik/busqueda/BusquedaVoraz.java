package rubik.busqueda;

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
		//traza = new TrazaGenerica(nodoActual);
    listaAbierta.add(nodoActual);
    while(!solucionEncontrada) {
      if(listaAbierta.size() == 0) {
        break;
      }
      else {
	//traza.imprimirInicioIteracion(listaAbierta);
        /*  estrategia voraz en grafo con heurisitica  cambia la forma en que ordena la lista  */
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
            /*completar ordenacion segun h */

    }
 
}
