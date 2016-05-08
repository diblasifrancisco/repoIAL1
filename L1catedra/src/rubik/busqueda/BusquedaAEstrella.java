package rubik.busqueda;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Vector;

public class BusquedaAEstrella extends BusquedaHeuristica implements Busqueda {

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
                /*  asigno heuriristica  */
		//traza = new TrazaGenerica(nodoActual);
    listaAbierta.add(nodoActual);
    while(!solucionEncontrada) {
      if(listaAbierta.size() == 0) {
        break;
      }
      else {
	//traza.imprimirInicioIteracion(listaAbierta);
        /*  estrategia Aestrella en grafo con heurisitica  cambia la forma en que ordena la lista  */
      }
    }
    /*  reportes de rendimiento  */
    if(nodoSolucion == null) {
      return new Vector<Operador>();
    }
    else {
      return encontrarCamino(nodoSolucion);
    }
	}


    private void ordenarListaFnEvaluacion() {
            LinkedList<NodoBusqueda> lista = listaAbierta;
	    int i, j;
	    int N = lista.size();
                 /*ordenacion burbuja segun funcon de evaluacion igual que costo uniforme pero segun f */
            listaAbierta = lista;

    }

  private NodoBusqueda getNodoMenorFnEvaluacionListaAbierta() {
    if(listaAbierta.size() == 1) {
			return listaAbierta.pollFirst();
		}
		else {
			int menorEvaluacion = listaAbierta.element().getCosto() +  listaAbierta.element().getHeuristica();
			NodoBusqueda menorNodo = listaAbierta.element();
			for(NodoBusqueda n : listaAbierta) {
				if((n.getCosto() + n.getHeuristica()) < menorEvaluacion) {
					menorNodo = n;
					menorEvaluacion = n.getCosto() + n.getHeuristica();
				}
			}
			listaAbierta.remove(menorNodo);
			return menorNodo;
		}
	}

}
