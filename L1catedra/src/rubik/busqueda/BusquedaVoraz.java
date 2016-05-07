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
   /*
    * .....
    */
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
