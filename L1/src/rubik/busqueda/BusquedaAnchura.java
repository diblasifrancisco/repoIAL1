package rubik.busqueda;

import java.util.LinkedList;
import java.util.Vector;

/* Estrategia de Busqueda en arbol en Anchura, 
 * Implementacion de la interfaz Busqueda
 * Hereda de la clase abstracta BusquedaArbol (generica independiente de la estrategia)
 * Implementa el método buscarSolucion() y devuelve un vector de operadores (solución)
 */


public class BusquedaAnchura extends BusquedaArbol implements Busqueda {

    @Override
  public Vector<Operador> buscarSolucion(Estado inicial){
    reporteInicioBusqueda(); //Antes de comenzar la búsqueda se contabiliza tiempo
    // Usa lista ABIERTOS (LinkedList)
    abierta = new LinkedList<NodoBusqueda>(); //creo la lista abierta o frontera
    Boolean solucionEncontrada = false;
    NodoBusqueda nodoSolucion = null;       //nodo solucion
    NodoBusqueda nodoActual = new NodoBusqueda(inicial,null,null);  //nodo raiz
    nodoActual.setProfundidad(0);
    nodoActual.setCosto(0);
    //traza = new TrazaGenerica(nodoActual); //creo una Traza con el nodo raiz
    abierta.add(nodoActual);
    while(!solucionEncontrada) {
      if(abierta.size() == 0) {
        break;
      }
      else {
	//traza.imprimirInicioIteracion(abierta); //muestro  estado de lista abirta al coienzo de la primer interación
        nodoActual = abierta.pollFirst();
        reporteNodosExplorados();               //Antes de evaluar si el nodo es solución contabilizo nodos explorados
        if(nodoActual.getEstado().esFinal()) {
          solucionEncontrada = true;
          nodoSolucion = nodoActual;
        }
        else {
          abierta.addAll(expandirNodo(nodoActual)); //coloco al final de la lista
        }
      }
    }
    reporteNodosSobrantes(abierta.size()); // al terminar contabilizo nodos sobrantes
    reporteFinBusqueda();                  // Contabilizo tiempo al finalizar busqueda
    if(nodoSolucion == null) {
      return new Vector<Operador>();
    }
    else {
      return encontrarCamino(nodoSolucion); //llamo al método de la clase BusquedaArbol
    }
  }	

}
