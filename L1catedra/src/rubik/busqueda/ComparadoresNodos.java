/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rubik.busqueda;

import java.util.Comparator;

/**
 *
 * @author gasmu_000
 */
public class ComparadoresNodos {
    public static Comparator<NodoBusqueda> HEURISTICA = new Comparator<NodoBusqueda>() {
        @Override
        public int compare(NodoBusqueda o1, NodoBusqueda o2) {
            Integer h1 = new Integer(o1.getHeuristica());
            Integer h2 = new Integer(o2.getHeuristica());
            return h1.compareTo(h2);
        }
    };
    
    public static Comparator<NodoBusqueda> FC_EVALUACION = new Comparator<NodoBusqueda>() {
        @Override
        public int compare(NodoBusqueda o1, NodoBusqueda o2) {
            Integer h1 = new Integer(o1.getFuncionEv());
            Integer h2 = new Integer(o2.getFuncionEv());
            return h1.compareTo(h2);
        }
    };
    
    public static Comparator<NodoBusqueda> COSTO = new Comparator<NodoBusqueda>() {
        @Override
        public int compare(NodoBusqueda o1, NodoBusqueda o2) {
            Integer h1 = new Integer(o1.getCosto());
            Integer h2 = new Integer(o2.getCosto());
            return h1.compareTo(h2);
        }
    };
}
