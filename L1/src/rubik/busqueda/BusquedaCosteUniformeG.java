package rubik.busqueda;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Vector;

/* Estrategia de Busqueda en grafo Costo Uniforme, 
 * Implementacion de la interfaz Busqueda
 * Hereda de la clase abstracta BusquedaGrafo (generica independiente de la estrategia)
 * Implementa el método buscarSolucion() y devuelve un vector de operadores (solución)
 */

public class BusquedaCosteUniformeG extends BusquedaGrafo implements Busqueda {

    @Override
	public Vector<Operador> buscarSolucion(Estado inicial){
    reporteInicioBusqueda();     //Antes de comenzar la búsqueda se contabiliza tiempo
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
	//traza.imprimirInicioIteracion(listaAbierta);  //muestro  estado de lista abirta al coienzo de la primer interación
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
            ordenarListaMenorCosto();
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
/**
 * Este método es muy parecido al ordenamiento burbuja,
 * solo que un poco mejor, ya que
 * reduce las comparaciones entre los elementos.
 * Un elemento es comparado con el anterior,
 * hasta que se encuentra un elemento más pequeño.
 * Si un elemento contiene un valor menor que
 * todos los elementos anteriores,
 * compara ese elemento con
 * todos los elementos anteriores
 * antes de continuar con la siguiente comparación
 */
    private void ordenarListaMenorCosto() {
            LinkedList<NodoBusqueda> lista = listaAbierta;
	    int i, j;
	    int N = lista.size();
            for(i=1; i<N; i++)	{
                NodoBusqueda tmp = lista.get(i);
                for(j=i; (j>0 && ( tmp.getCosto() < lista.get(j-1).getCosto() ) ); j--){
	  		lista.set(j, lista.get(j-1));
                }
	  	lista.set(j, tmp);
	  	}
            listaAbierta = lista;
        
    }

}
