package rubik.busqueda;

import java.util.LinkedList;
import java.util.Vector;

/* Clase abstracta que guarda las implementaciones genericas para una Busqueda en Arbol
 * Hereda de la clase RendimientoBusqueda, 
 * para calcular medidas de evaluación de las estrategias de busqueda
 * Usa lista ABIERTOS (LinkedList)
 * implementa el método expandirNodo()
 * implementa el método encontrarCamino()
 */

public abstract class BusquedaArbol extends RendimientoBusqueda{
	LinkedList<NodoBusqueda> abierta;
	//TrazaGenerica traza;

//implementacion del metodo para expandir un nodo padre
//devuelve la lista de sucesores
  protected LinkedList<NodoBusqueda> expandirNodo(NodoBusqueda nodoPadre) {
            LinkedList<NodoBusqueda> expandidos = new LinkedList<NodoBusqueda>();
            
            for(Operador oper : nodoPadre.getEstado().operadoresAplicables()) {
              NodoBusqueda n = new NodoBusqueda(nodoPadre.getEstado().aplicarOperador(oper),nodoPadre,oper);
              n.setProfundidad(nodoPadre.getProfundidad() + 1);
              n.setCosto(nodoPadre.getCosto() + 1);
              expandidos.add(n);
            }
            
            //traza.imprimirFinalIteracion(nodoPadre, expandidos); //muestro la lista de hijos del nodo expandido
            return expandidos;
  }

  // implementa el metodo para encontrar un camino a partir de un nodo solución
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
    
    return camino; //devuelvo el camino
    
  }

}
