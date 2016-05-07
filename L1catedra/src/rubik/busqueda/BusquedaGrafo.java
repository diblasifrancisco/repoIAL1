package rubik.busqueda;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Vector;

/* Clase abstracta que guarda las implementaciones genericas para una Busqueda en Grafo
 * Inteligencia Artificial 2016
 * Hereda de la clase RendimientoBusqueda, 
 * para calcular medidas de evaluación de las estrategias de busqueda
 * Usa lista ABIERTOS (LinkedList) y lista CERRADOS (Hastable) usando Estado como clave
 * implementa el método expandirNodo()
 * implementa el método encontrarCamino()
 */
public abstract class BusquedaGrafo  extends RendimientoBusqueda{
        HashMap<Estado, NodoBusqueda> listaCerrada; 
        LinkedList<NodoBusqueda>      listaAbierta;
	//TrazaGenerica traza;
//implementacion del metodo control de estados repetidos en cerrada antes de colocar hijos en abierta
//devuelve la lista de sucesores
 protected LinkedList<NodoBusqueda> expandirNodo(NodoBusqueda nodoPadre) {
        LinkedList<NodoBusqueda> expandidos = new LinkedList<NodoBusqueda>(); //creo lista de expandidos
        // genero los estados posibles según los movimientos que se pueden hacer a partir del estado actual
        for(Operador oper : nodoPadre.getEstado().operadoresAplicables()) {
          NodoBusqueda n = new NodoBusqueda(nodoPadre.getEstado().aplicarOperador(oper),nodoPadre,oper); //genero un nodo hijo
                            n.setProfundidad(nodoPadre.getProfundidad() + 1); //asigno profundidad
                            n.setCosto(nodoPadre.getCosto() + 1);             //y costo de camino, en este problema es igual a la profundidad
          
         if(!listaCerrada.containsKey(n.getEstado())) {
            expandidos.add(n);     //agrego el nuevo nodoa la lista de hijos, siempre que no este en la lista cerado
          }
        }
	 //traza.imprimirFinalIteracion(nodoPadre, expandidos); //muestro la lista de hijos del nodo expandido
         return expandidos;
  }
  // implementa el metodo para encontrar un camino a partir de un nodo solución
  protected Vector<Operador> encontrarCamino(NodoBusqueda nodoFinal) {
    Vector<Operador> camino = new Vector<Operador>(); //lista de operadores que permiten llegar a la solución
    NodoBusqueda nodoPaso = nodoFinal;
    //recorro desde el nodo solución hasta el nodo raiz, preguntando por su padre
    while(nodoPaso.getPadre() != null) {
      camino.insertElementAt(nodoPaso.getOperador(), 0); //obtengo el padre y el operador que permite llegar al hijo
      nodoPaso = (NodoBusqueda)nodoPaso.getPadre();      //subo en la jerarquia
    }
    //Imprimo Reporte de busqueda antes de retornar el camino para llegar a la solución
    System.out.println("\n REPORTE DE BUSQUEDA");
    System.out.println("---------------------------------------");
    System.out.println(getReporteCompleto());
    
    return camino; //devuelvo el camino
  }

}
