/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rubik.busqueda;

import rubik.modelo.*;
/**
 *
 * @author sam
 */

public class Heuristica2 implements Heuristica {

    @Override
  public int obtenerHeuristica(Nodo nodo) {
    int h = 0;
    for(Cara cara : ((EstadoRubik)nodo.getEstado()).getCubo().caras) {
      for(byte casilla : cara.casillas) {
        if(casilla != cara.color) {
          h++;
        }
      }
    }
    return h;
  }

}
