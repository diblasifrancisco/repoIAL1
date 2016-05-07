package rubik.busqueda;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Vector;

public abstract class BusquedaHeuristica extends RendimientoBusqueda {

  HashMap<Estado, NodoBusqueda> listaCerrada;
  LinkedList<NodoBusqueda> listaAbierta;
  //TrazaGenerica traza;
  Heuristica h = new Heuristica1();

  public void setHeuristica(Heuristica heuristica) {
    this.h = heuristica;
  }

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
