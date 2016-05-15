package rubik.busqueda;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

/** Muestra el estado de abierta, el estado extraido a evaluar y los sucesores si no es meta
 * IA 2011
 */
public class TrazaGenerica {
     //al crear una traza se imprime estado inicial que se le pasa al constructor  
     public TrazaGenerica (NodoBusqueda nodo){
           //System.out.println("\n/**** Accion: <estado,(costo g),(heuristica h), profundidad> ****/");
           //System.out.println("\n    Nodo raiz: " + toStringNodo(nodo));
     }
    //Despues de expander nodo evaluado
    public void imprimirFinalIteracion(NodoBusqueda actual, LinkedList<NodoBusqueda> sucesores){
       // System.out.println("Nodo expanido: " + toStringNodo(actual) + " \n" + 
          //                 "        Hijos: " + toStringListaNodos(sucesores));
    }
    //Antes de sacar primer elemento de frontera
    public void imprimirInicioIteracion(LinkedList<NodoBusqueda> frontera){
       // System.out.println("\n" + 
            //               "     Frontera: " + toStringListaNodos(frontera));
    }
    //muestro información de un nodo dado
    //Accion: <estado,(costo g),(heuristica h), profundidad>    
    private String toStringNodo(NodoBusqueda nodo){
            int estado = java.lang.Math.abs(nodo.getEstado().hashCode()/5000000);
            int g = nodo.getCosto();
            int p = nodo.getProfundidad();
            int h = nodo.getHeuristica();
            return (nodo.getOperador()!=null?nodo.getOperador().getEtiqueta():"") + ": <" + estado + ",(" + g + ")," + "(" + h + ")," + p +">";
    }
    // muestro información de una lista de nodo
    private String toStringListaNodos(LinkedList<NodoBusqueda> a){
        String out = "";
        for(NodoBusqueda nodo : a){
            out += toStringNodo(nodo) + " ";
        }
        return out;
    }
}
    
    
    
