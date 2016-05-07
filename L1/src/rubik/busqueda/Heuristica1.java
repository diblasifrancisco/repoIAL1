/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rubik.busqueda;

import rubik.modelo.Cara;
import rubik.modelo.EstadoRubik;

/**
 * Esta clase implementa las primera heuristica utilizada en la busqueda
 * informada. 
 * Manzano Fernando 2009
 */

class Heuristica1 implements Heuristica {

    /**
     * Esta heuristica, devuelve la cantidad minima de movimientos necesarios para resolver
     * el cubo, suponiendo que cada movimiento acomoda la totalidad de los recuadros
     * que afecta. Como cada giro afecta a 3 cuadros de cada una de las 4 caras que
     * afecta, entonces la cantidad maxima de recuadros que se pueden poner en su posicion
     * correcta con un solo movimiento es 12. Por ende, la heuristica es admisible.
     *
     * El calculo se realiza contando cuantas casillas no estan en su cara correspondiente.
     * Si todas sus casillas estan en su lugar correspondiente, devuelve 0 por definicion.
     * Si la cantidad de casillas es multiplo de 12, devuelve la cantidad de casillas sobre 12
     * ya que en el mejor caso, con cada movimiento acomodamos 12 casillas distintas en su lugar.
     * Si la cantidad de casillas no es multiplo de 12, entonces devuelve el techo de la
     * cantidad de casillas sobre 12.
     *
     * @param estado - Estado sobre el cual se calcula la heuristica
     * @return - El valor numerico de la heuristica aplicada al estado pasado como parametro
     */
    @Override
    public int obtenerHeuristica(Nodo nodo){

        // Contador de las casillas fuera de lugar
        int casillasNoUbicadas = 0;
        
        // Necesitamos el estado del cubo
        Estado estado = nodo.getEstado();
        EstadoRubik estadoR = (EstadoRubik) estado;

        // Obtenemos las caras del cubo
        Cara caras[] = estadoR.getCubo().caras;

        // Para cada cara del cubo, contamos cuantas casillas estan fuera de posicion
        for(Cara cara : caras) {
            for(byte colorCasilla : cara.casillas) {
                if(colorCasilla!=cara.color) {
                    casillasNoUbicadas++;
                }
            }
        }

        // Si la cantidad de casillas es 0 un multiplo de 12, devuelo esa cantidad sobre 12
        if(casillasNoUbicadas % 12 == 0){
            return casillasNoUbicadas / 12;
        } else { // Sino, le sumo uno para realizar el ultimo movimiento
            return casillasNoUbicadas / 12 + 1;
        }

    }


}
