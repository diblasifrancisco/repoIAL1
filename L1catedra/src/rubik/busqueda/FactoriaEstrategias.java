/*
 * Esta clase es una factoria de estrategias de búsqueda que implementen la interface Busqueda.java.
 * Por medio del método estatico getEstrategia() permitirá establecer 
 * la estrategia a utilizar mediante constantes
 * Su utilizacion se ejemplifica con la opción ANCHURA_GRAFO = 3, para la estrategia primero en anchura en grafo.
 * IA 2010
 */

package rubik.busqueda;

public class FactoriaEstrategias {
    //Constantes
    public static final int ANCHURA_ARBOL = 0;
    public static final int COSTO_UNIFORME_ARBOL = 1;
    public static final int PROFUNDIDAD_ARBOL = 2;
    public static final int ANCHURA_GRAFO = 3;
    public static final int COSTO_UNIFORME_GRAFO = 4; 
    public static final int PROFUNDIDAD_GRAFO = 5; 
    public static final int PROFUNDIDAD_INTE_GRAFO = 6;
    
    public static final int VORAZ_H1 = 7;
    public static final int VORAZ_H2 = 8;
    public static final int AESTRELLA_H1 = 9;
    public static final int AESTRELLA_H2 = 10;
    public static final int IDA_H1 = 11;
    public static final int IDA_H2 = 12;
    
    private static int estrategia = 1; 

    public static Busqueda getEstrategia(int estrategia){ 
        FactoriaEstrategias.estrategia = estrategia;  
        Busqueda busq = null;       
        switch(estrategia) {
          case 0:
                System.out.println("\nBUSQUEDA ANCHURA EN ARBOL");
                busq = new BusquedaAnchura(); break; 
          case 1:
                System.out.println("\nBUSQUEDA COSTO UNIFORME EN ARBOL");
                busq =  new BusquedaCosteUniforme(); break; 
          case 2:
                System.out.println("\nBUSQUEDA PROFUNDIDAD EN ARBOL");
                busq =  new BusquedaProfundidad(); break; 
          case 3:
                System.out.println("\nBUSQUEDA ANCHURA EN GRAFO");
                busq =  new BusquedaAnchuraG();  break;     
          case 4:
                System.out.println("\nBUSQUEDA COSTO UNIFORME EN GRAFO");
                busq =  new BusquedaCosteUniformeG(); break; 
          case 5:
                System.out.println("\nBUSQUEDA PROFUNDIDAD EN GRAFO");
                busq =  new BusquedaProfundidadG(); break; 
          case 6:
                System.out.println("\nBUSQUEDA PROFUNDIDAD INTERACTIVA EN GRAFO");
                busq =  new BusquedaProfundidadIterativaG(); break; 
          
          // para cada caso se instancia la clase correspondiente a la implementacion
	  // de cada estrategia, con cada una de las dos heuristica
           case 7:
                System.out.println("\nBUSQUEDA VORAZ EN GRAFO con HEURISITCA 1");
                busq = new BusquedaVoraz();
               ((BusquedaHeuristica)busq).setHeuristica(new Heuristica1()); //asigno la H1
                break; 
           case 8:
                System.out.println("\nBUSQUEDA VORAZ EN GRAFO con HEURISITCA 2");
                busq = new BusquedaVoraz();
               ((BusquedaHeuristica)busq).setHeuristica(new Heuristica2()); //asigno la H1
                break; 
                
           case 9:
               System.out.println("\nBUSQUEDA A* EN GRAFO con HEURISITCA 1");
                busq = new BusquedaAEstrella();
               ((BusquedaHeuristica)busq).setHeuristica(new Heuristica1()); //asigno la H1
                break; 
           case 10:
               System.out.println("\nBUSQUEDA A* EN GRAFO con HEURISITCA 2");
                busq = new BusquedaAEstrella();
               ((BusquedaHeuristica)busq).setHeuristica(new Heuristica2()); //asigno la H1
                break; 

           
        }
        return busq;
    } 
}
