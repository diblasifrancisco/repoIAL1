package rubik.busqueda;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Vector;

/* Estrategia de Busqueda en grafo en Profundidad Limitada, 
 * Implementacion de la interfaz Busqueda
 * Hereda de la clase abstracta BusquedaGrafo (generica independiente de la estrategia)
 * Implementa el método buscarSolucion() y devuelve un vector de operadores (solución)
 */

public class BusquedaProfundidadLimitadaG extends BusquedaGrafo implements Busqueda {

  int profundidadLimite = 20;

  public void setProfundidadLimite(int profundidadLimite) {
    this.profundidadLimite = profundidadLimite;
  }
  public int getProfundidadLimite() {
    return profundidadLimite;
  }

    @Override
  public Vector<Operador> buscarSolucion(Estado inicial){
    reporteInicioBusqueda();    //Antes de comenzar la búsqueda se contabiliza tiempo
    int profundidadActual = 0;
    listaCerrada = new HashMap<Estado, NodoBusqueda>();
    listaAbierta = new LinkedList<NodoBusqueda>();
    Boolean solucionEncontrada = false;
    NodoBusqueda nodoSolucion = null;
    while(!solucionEncontrada) {
      NodoBusqueda nodoActual = new NodoBusqueda(inicial,null,null);
      nodoActual.setProfundidad(0);
      nodoActual.setCosto(0);
      //traza = new TrazaGenerica(nodoActual); //creo una Traza con el nodo raiz
      listaAbierta.add(nodoActual);
      listaCerrada.clear();
      while(!solucionEncontrada) {
        if(listaAbierta.size() == 0) {
          break;
        }
        else {
          //traza.imprimirInicioIteracion(listaAbierta); //muestro  estado de lista abirta al coienzo de la primer interación
          nodoActual = listaAbierta.pollFirst();
          reporteNodosExplorados(); //Antes de evaluar si el nodo es solución contabilizo nodos explorados
          if(!listaCerrada.containsKey(nodoActual.getEstado())) {
            if(nodoActual.getEstado().esFinal()) {
              solucionEncontrada = true;
              nodoSolucion = nodoActual;
            }
            else {
              listaCerrada.put(nodoActual.getEstado(), nodoActual);
              if(nodoActual.getProfundidad() < profundidadActual) {
                listaAbierta.addAll(0, expandirNodo(nodoActual));
              }
            }
          }
        }
      }
      profundidadActual++;
      if(profundidadActual > profundidadLimite) {
        break;
      }
    }
    reporteNodosSobrantes(listaAbierta.size()); // al terminar contabilizo nodos sobrantes
    reporteFinBusqueda();  // Contabilizo tiempo al finalizar busqueda
    if(nodoSolucion == null) {
      return new Vector<Operador>();
    }
    else {
      return encontrarCamino(nodoSolucion);
    }
  }

}
