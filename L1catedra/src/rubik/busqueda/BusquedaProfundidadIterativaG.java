package rubik.busqueda;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Vector;

/* Estrategia de Busqueda en grafo en Profundidad Interactiva, 
 * Implementacion de la interfaz Busqueda
 * Hereda de la clase abstracta BusquedaGrafo (generica independiente de la estrategia)
 * Implementa el método buscarSolucion() y devuelve un vector de operadores (solución)
 */

public class BusquedaProfundidadIterativaG extends BusquedaGrafo implements Busqueda {

  BusquedaProfundidadLimitadaG busquedapl = new BusquedaProfundidadLimitadaG();
  int profundidadActual = 0;

    @Override
public Vector<Operador> buscarSolucion(Estado inicial){
    reporteInicioBusqueda();  //Antes de comenzar la búsqueda se contabiliza tiempo
    Vector<Operador> caminoSolucion = null;
    boolean solucionEncontrada = false;
    while(!solucionEncontrada) {
      busquedapl.setProfundidadLimite(profundidadActual);
      caminoSolucion = busquedapl.buscarSolucion(inicial);
      reporteNodosExplorados(busquedapl.getNroNodosExplorados()); //Antes de evaluar si el nodo es solución contabilizo nodos explorados
      if(caminoSolucion.size() > 0) {
        solucionEncontrada = true;
      }
      else {
        profundidadActual++;
      }
      reporteNodosSobrantes(busquedapl.getNroNodosSobrantes()); // al terminar contabilizo nodos sobrantes
    }
    
    reporteFinBusqueda(); // Contabilizo tiempo al finalizar busqueda
    return caminoSolucion;
	}

}
