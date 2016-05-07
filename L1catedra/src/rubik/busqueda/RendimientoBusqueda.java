package rubik.busqueda;

/* Clase abstracta que guarda los métodos requeridos para
 * calular las medidas de rendimiento para evaluar los algoritmos de busqueda
 * IA 2010
 */

public abstract class RendimientoBusqueda {

  long tiempoBusquedaSeg;  //tiempo de busqueda en segundos
  long tiempoBusquedaMili;  //tiempo de busqueda en milisegundos
  long cantidadNodosExplorados;  //numeros de nodos explorados
  long cantidadNodosSobrantes;   //nuemros de nodos sin explorar pero generados

 //este metodo permite mostrar las medidas de rendimiento, 
  // debe llamarse al finalzar la busqueda antes de retornar el camino solución
  public String getReporteCompleto(){
         return ("Tiempo de busqueda: " + tiempoBusquedaMili + " milisegundos (" + tiempoBusquedaSeg + " segundos)" +
        "\n"+"Nodos Generados: " + (cantidadNodosExplorados + cantidadNodosSobrantes) +
        "\n"+"Nodos Explorados: " + cantidadNodosExplorados) ;
   }
  
  // y se toma el tiempo inicial al comienzo de la busqueda
  //contador de numeros de nodos inicializados a 0
  protected void reporteInicioBusqueda() {
    cantidadNodosExplorados = 0;
    cantidadNodosSobrantes = 0;
    tiempoBusquedaMili = System.currentTimeMillis();
    tiempoBusquedaSeg = (System.currentTimeMillis()/ 1000);
  }

//se llama cuando se evalua un nodo extraido de la lista abirta  
//contador de nodos explorados
  protected void reporteNodosExplorados() {
    cantidadNodosExplorados++;
  }
  protected void reporteNodosExplorados(long cantidad) {
    cantidadNodosExplorados += cantidad;
  }
//se llama cuando se evalua un nodo extraido de la lista abirta  
//contador de nodos que han sido generados pero no explorados o evaluados
  protected void reporteNodosSobrantes(long cantidad) {
    cantidadNodosSobrantes += cantidad;
  }
  
 // y se toma el tiempo final al finalizar la busqueda
 // se calcula diferencia de inicio y fina para determinar tiempo en seg y miliseg
  protected void reporteFinBusqueda() {
    tiempoBusquedaMili = System.currentTimeMillis() - tiempoBusquedaMili;
    tiempoBusquedaSeg = (System.currentTimeMillis()/ 1000) - tiempoBusquedaSeg;
  }
  
  public long getNroNodosGenerados() {
    return cantidadNodosExplorados + cantidadNodosSobrantes;
  }
  public long getNroNodosExplorados() {
    return cantidadNodosExplorados;
  }
  public long getNroNodosSobrantes() {
    return cantidadNodosSobrantes;
  }
  public long getTiempoBusquedaMili() {
    return tiempoBusquedaMili;
  }
  public long getTiempoBusquedaMinu() {
    return tiempoBusquedaSeg;
  }
  

}
