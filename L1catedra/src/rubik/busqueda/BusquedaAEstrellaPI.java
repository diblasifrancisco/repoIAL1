package rubik.busqueda;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Vector;

public class BusquedaAEstrellaPI extends BusquedaHeuristica implements Busqueda {

  BusquedaAEstrellaLimitada busquedapl = new BusquedaAEstrellaLimitada();
  int fActual = 0;

public Vector<Operador> buscarSolucion(Estado inicial){
    reporteInicioBusqueda();
    Vector<Operador> caminoSolucion = null;
    boolean solucionEncontrada = false;
    fActual = heuristica1.obtenerHeuristica(new NodoBusqueda(inicial,null,null));
    while(!solucionEncontrada) {
      busquedapl.setValorFuncionEvaluacionLimite(fActual);
      caminoSolucion = busquedapl.buscarSolucion(inicial);
      reporteNodosExplorados(busquedapl.getNroNodosExplorados());
      reporteNodosSobrantes(busquedapl.getNroNodosSobrantes());
      if(caminoSolucion.size() > 0) {
        solucionEncontrada = true;
      }
      else {
        fActual = busquedapl.getMenorValorFuncionEvaluacion();
      }
    }
    reporteFinBusqueda();
    
    return caminoSolucion;
	}

}
