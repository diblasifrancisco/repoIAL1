package rubik.busqueda;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Vector;

/* Estrategia de Busqueda en grafo en Profundidad, 
 * Implementacion de la interfaz Busqueda
 * Hereda de la clase abstracta BusquedaGrafo (generica independiente de la estrategia)
 * Implementa el método buscarSolucion() y devuelve un vector de operadores (solución)
 */

public class BusquedaProfundidadG extends BusquedaGrafo implements Busqueda {

  public Vector<Operador> buscarSolucion(Estado inicial){
    reporteInicioBusqueda();         //Antes de comenzar la búsqueda se contabiliza tiempo
    listaCerrada = new HashMap<Estado, NodoBusqueda>();
    listaAbierta = new LinkedList<NodoBusqueda>();
    Boolean solucionEncontrada = false;
    NodoBusqueda nodoSolucion = null;
    NodoBusqueda nodoActual = new NodoBusqueda(inicial,null,null);
		nodoActual.setProfundidad(0);
		nodoActual.setCosto(0);
		//traza = new TrazaGenerica(nodoActual);//creo una Traza con el nodo raiz
    listaAbierta.add(nodoActual);
    while(!solucionEncontrada) {
      if(listaAbierta.size() == 0) {
        break;
      }
      else {
	//traza.imprimirInicioIteracion(listaAbierta);//muestro  estado de lista abirta al coienzo de la primer interación
        nodoActual = listaAbierta.pollFirst();
        reporteNodosExplorados();  //Antes de evaluar si el nodo es solución contabilizo nodos explorados
        if(!listaCerrada.containsKey(nodoActual.getEstado())) {
          if(nodoActual.getEstado().esFinal()) {
            solucionEncontrada = true;
            nodoSolucion = nodoActual;
          }
          else {
            listaCerrada.put(nodoActual.getEstado(), nodoActual);
            listaAbierta.addAll(0, expandirNodo(nodoActual));
          }
        }
      }
    }
    reporteNodosSobrantes(listaAbierta.size()); // al terminar contabilizo nodos sobrantes
    reporteFinBusqueda(); // Contabilizo tiempo al finalizar busqueda
    if(nodoSolucion == null) {
      return new Vector<Operador>();
    }
    else {
      return encontrarCamino(nodoSolucion);
    }
  }

}
