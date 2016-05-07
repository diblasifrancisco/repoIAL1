package rubik.busqueda;

import java.util.Vector;
  
/**
 * Interfaz genérico para algorimtos de búsqueda
 * Inteligencia Artificial 2016
 */
public interface Busqueda {
    /**
     * Interfaz genérico para algorimtos de búsqueda
     * @param   inicial Estado inicial
     * @return  null o Vector con la lista de Operadores
     */
    
    public Vector<Operador> buscarSolucion(Estado inicial);
}
