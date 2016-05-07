package rubik.busqueda;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Vector;

/**clase abastracta que será implementada por todas aquellas búsquedas que implementen heuristicas para llegar al objetivo*/
public abstract class BusquedaHeuristica extends RendimientoBusqueda {

  HashMap<Estado, NodoBusqueda> listaCerrada;
  LinkedList<NodoBusqueda> listaAbierta;
  //TrazaGenerica traza;
  /** nuevo objeto de tipo heuristica*/
  Heuristica h = new Heuristica1();

  /**setea la heuristica a utilizar*/
  public void setHeuristica(Heuristica heuristica) {
    this.h = heuristica;
  }
  
/**genera la sucesores de un nodo y dependiendo la heuristica la seta en el nodo*/
 protected LinkedList<NodoBusqueda> expandirNodo(NodoBusqueda nodoPadre) {
    LinkedList<NodoBusqueda> expandidos = new LinkedList<NodoBusqueda>();
    for(Operador oper : nodoPadre.getEstado().operadoresAplicables()) {
      NodoBusqueda n = new NodoBusqueda(nodoPadre.getEstado().aplicarOperador(oper),nodoPadre,oper);
			n.setProfundidad(nodoPadre.getProfundidad() + 1);
			n.setCosto(nodoPadre.getCosto() + 1);
                        /*
                         * agregar h (heurisitica)
                         y f (funcion de evaluacion)que es igual al costo de camino + h
                         */

      if(!listaCerrada.containsKey(n.getEstado())) {
        expandidos.add(n);
      }
    }
    //traza.imprimirFinalIteracion(nodoPadre, expandidos);
    return expandidos;
  }

 /**genera el camino solucion mediante la búsqueda sucesiva desde el nodo objetivo pasando por su padre, y del padre de este, hasta llegar al inicial*/
  protected Vector<Operador> encontrarCamino(NodoBusqueda nodoFinal) {
    Vector<Operador> camino = new Vector<Operador>();
    NodoBusqueda nodoPaso = nodoFinal;
    while(nodoPaso.getPadre() != null) {
      camino.insertElementAt(nodoPaso.getOperador(), 0);
      nodoPaso = (NodoBusqueda)nodoPaso.getPadre();
    }
    //Imprimo Reporte de busqueda antes de retornar el camino para llegar a la solución
    System.out.println("\n REPORTE DE BUSQUEDA");
    System.out.println("---------------------------------------");
    System.out.println(getReporteCompleto());
    
    return camino;
  }

}
