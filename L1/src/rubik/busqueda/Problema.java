package rubik.busqueda;

import java.util.Vector;

/**
 * Clase genérica (indepeniente de estados y algoritmos concretos) que representa 
 * un problema de búsqueda en espacio de estados.
 * Está caracterizado por un Estado inicial y un método de Busqueda
 * IA 2011
 */
public class Problema {
    /** Estado inicial del problema*/
    private Estado inicial;
    /** Método de busqueda e utilizar en la resolución del problema */
    private Busqueda buscador;

    public Problema(Estado inicial, Busqueda buscador) {
        this.inicial = inicial;
        this.buscador = buscador;
    }

    public void setBuscador(Busqueda buscador) {
        this.buscador = buscador;
    }

    public void setInicial(Estado inicial) {
        this.inicial = inicial;
    }
    
    public Busqueda getBuscador (){
        return buscador;
    }
    
    /**
     * Aplica el método de Busqueda de este Problema concreto para resolverlo. 
     * Devuelve la lista de Operadores que permiten alcanzar un Estado final desde
     * el Estado inicial del Problema
     * @return null o Vector de Operadores
     */
    public Vector<Operador> obtenerSolucion() {
        return(buscador.buscarSolucion(inicial));
    }
    
    
    
}
