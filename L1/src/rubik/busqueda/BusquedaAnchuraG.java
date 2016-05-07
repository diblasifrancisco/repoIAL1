package rubik.busqueda;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Vector;

/** Estrategia de Busqueda en grafo en Anchura,
 * Implementacion de la interfaz Busqueda
 * Hereda de la clase abstracta BusquedaGrafo (generica independiente de la estrategia)
 * Implementa el método buscarSolucion() y devuelve un vector de operadores (solución)
 */

public class BusquedaAnchuraG extends BusquedaGrafo implements Busqueda {

    @Override
  public Vector<Operador> buscarSolucion(Estado inicial){
    reporteInicioBusqueda();    /**Antes de comenzar la búsqueda se contabiliza tiempo*/
    /** Usa lista ABIERTOS (LinkedList) y lista CERRADOS (Hastable usando Estado como clave)*/
    listaCerrada = new HashMap<Estado, NodoBusqueda>();    /**creo la lista cerrada*/
    listaAbierta = new LinkedList<NodoBusqueda>();         /** creo la lista abierta*/
    Boolean solucionEncontrada = false;                    /** flag para determinar si se encontro la solucion*/
    NodoBusqueda nodoSolucion = null;                      /** creo el nodo solucion*/
    NodoBusqueda nodoActual = new NodoBusqueda(inicial,null,null);  /** reo nodo raiz, apartir del estado inicial*/
		nodoActual.setProfundidad(0);   /** asigno la profundidad 0 del nodo raiz al nodo actuall*/
		nodoActual.setCosto(0);         /** asigno el costo 0, de camino al nodo actuall*/
		//traza = new TrazaGenerica(nodoActual); /** creo una Traza con el nodo raizl*/
    listaAbierta.add(nodoActual);   /** coloco el nodo raiz en la lista abiertal*/
    /** Miestras no encuentre una solución, buscol*/
    while(!solucionEncontrada) {
      if(listaAbierta.size() == 0) {
        break;  
      }
      else {
	//traza.imprimirInicioIteracion(listaAbierta);  /** muestro  estado de lista abirta al coienzo de cada interaciónl*/
        nodoActual = listaAbierta.pollFirst();      /** tomo y elimino el primer elemento de la listal*/
        reporteNodosExplorados();  /** Antes de evaluar si el nodo es solución contabilizo nodos exploradosl*/

        /** Evaluo si es solución
        * Controlando repeticion de estados.
        * Lista CERRADOS (Hastable usando Estado como clave)l */
        if(!listaCerrada.containsKey(nodoActual.getEstado())) {
          if(nodoActual.getEstado().esFinal()) {
            solucionEncontrada = true;
            nodoSolucion = nodoActual;
          }
          /** si el estado actual no es objetivo lo expandol */
          else {
            listaCerrada.put(nodoActual.getEstado(), nodoActual);
            listaAbierta.addAll(expandirNodo(nodoActual));
          }
        }
      }
    }
    reporteNodosSobrantes(listaAbierta.size()); /** al terminar contabilizo nodos sobrantes l*/
    reporteFinBusqueda(); /** Contabilizo tiempo al finalizar busqueda l*/
    if(nodoSolucion == null) {
      return new Vector<Operador>();
    }
    else {
      return encontrarCamino(nodoSolucion); /** encuentro el camino y retorno la solución l*/
    }
  }

  

}
