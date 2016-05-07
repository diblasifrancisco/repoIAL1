package rubik.busqueda;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Vector;

public class BusquedaAEstrellaLimitada extends BusquedaHeuristica implements Busqueda {

  int fLimite = 0;
  int menorF = 0;

  public void setValorFuncionEvaluacionLimite(int fLimite) {
    this.fLimite = fLimite;
  }
  public int getValorFuncionEvaluacionLimite() {
    return fLimite;
  }
  public int getMenorValorFuncionEvaluacion() {
    return menorF;
  }

    @Override
  public Vector<Operador> buscarSolucion(Estado inicial){
    reporteInicioBusqueda();
		listaCerrada = new HashMap<Estado, NodoBusqueda>();
    listaAbierta = new LinkedList<NodoBusqueda>();
    Boolean solucionEncontrada = false;
    NodoBusqueda nodoSolucion = null;
    NodoBusqueda nodoActual = new NodoBusqueda(inicial,null,null);
		nodoActual.setProfundidad(0);
		nodoActual.setCosto(0);
    nodoActual.setHeuristica(heuristica1.obtenerHeuristica(nodoActual));
		//traza = new TrazaGenerica(nodoActual);
    listaAbierta.add(nodoActual);
    while(!solucionEncontrada) {
      if(listaAbierta.size() == 0) {
        break;
      }
      else {
	//traza.imprimirInicioIteracion(listaAbierta);
        nodoActual = listaAbierta.pollFirst();
        reporteNodosExplorados();
        if(!listaCerrada.containsKey(nodoActual.getEstado())) {
          if(nodoActual.getEstado().esFinal()) {
            solucionEncontrada = true;
            nodoSolucion = nodoActual;
          }
          else {
            listaCerrada.put(nodoActual.getEstado(), nodoActual);
            listaAbierta.addAll(expandirNodoInferiorAF(nodoActual));
            ordenarListaFnEvaluacion();
          }
        }
      }
    }
    reporteNodosSobrantes(listaAbierta.size());
    reporteFinBusqueda();
    if(nodoSolucion == null) {
      return new Vector<Operador>();
    }
    else {
      return encontrarCamino(nodoSolucion);
    }
	}

  protected LinkedList<NodoBusqueda> expandirNodoInferiorAF(NodoBusqueda nodoPadre) {
    LinkedList<NodoBusqueda> expandidos = new LinkedList<NodoBusqueda>();
    for(Operador oper : nodoPadre.getEstado().operadoresAplicables()) {
      NodoBusqueda n = new NodoBusqueda(nodoPadre.getEstado().aplicarOperador(oper),nodoPadre,oper);
			n.setProfundidad(nodoPadre.getProfundidad() + 1);
			n.setCosto(nodoPadre.getCosto() + 1);
      n.setHeuristica(heuristica1.obtenerHeuristica(n));
      if((n.getCosto() + n.getHeuristica()) <= fLimite) {
        if(!listaCerrada.containsKey(n.getEstado())) {
          expandidos.add(n);
        }
      }
      if(listaCerrada.size() == 1) {
        menorF = n.getCosto() + n.getHeuristica();
      }
      else {
        if((n.getCosto() + n.getHeuristica()) < menorF) {
          menorF = n.getCosto() + n.getHeuristica();
        }
      }
    }
		//traza.imprimirFinalIteracion(nodoPadre, expandidos);
    return expandidos;
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
    private void ordenarListaFnEvaluacion() {
            LinkedList<NodoBusqueda> lista = listaAbierta;
	    int i, j;
	    int N = lista.size();
            for(i=1; i<N; i++)	{
                NodoBusqueda tmp = lista.get(i);
                for(j=i; (j>0 && ( tmp.getFuncionEv() < lista.get(j-1).getFuncionEv())); j--){
	  		lista.set(j, lista.get(j-1));
                }
	  	lista.set(j, tmp);
	  	}
            listaAbierta = lista;

    }
}