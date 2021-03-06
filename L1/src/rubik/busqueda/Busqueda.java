package rubik.busqueda;

import java.util.Vector;
  
/**
 * Interfaz gen�rico para algorimtos de b�squeda
 * Inteligencia artificial 2011
 */
public interface Busqueda {
    /**
     * Interfaz gen�rico para algorimtos de b�squeda
     * @param   inicial Estado inicial
     * @return  null o Vector con la lista de Operadores
     */
    
    public Vector<Operador> buscarSolucion(Estado inicial);
}
