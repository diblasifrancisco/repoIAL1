package rubik;

import java.util.Vector;
import rubik.busqueda.FactoriaEstrategias;
import rubik.modelo.EstadoRubik;
import rubik.modelo.OperadorRubik;
import rubik.busqueda.Operador;
import rubik.busqueda.Problema;
import rubik.modelo.Cubo;
import rubik.modelo.Movimiento;


/**
 * Interfaz a travez de la consola
 * Inteligencia Artificial 2016
 */
public class Main {

     public static void main(String[] args) {
        Cubo cubo = new Cubo();
        
        long semilla = 62144;       //numero de legajo
        cubo.rnd.setSeed(semilla); //asigna semilla para generar estado aleatorio
        System.out.println("\nSEMILLA:"+semilla); //muestra la semilla por consola
             
        Vector<Movimiento> movsMezcla = cubo.mezclar(2);   //mezcla aleatoria en dos movimientos

        System.out.println("\nMOVIMIENTOS:");   //muestra los 2 movimientos aleatorios
        for (Movimiento m : movsMezcla) {
            System.out.print(m.toString() + " ");
        }
        System.out.println();

        System.out.println("CUBO INICIAL:\n" + cubo.toString());  //muestra el estado inicial del cubo mezclado.
        
        // Se crea un problema con el estado inicial y una busqueda.
        // Se elige la estrategia directamente en el llamado al metodo getEstrategia(CONSTANTE),
        // quien retornará una instancia (objeto) de una clase que implemente la Interface Busqueda
        Problema problema = new Problema(new EstadoRubik(cubo), FactoriaEstrategias.getEstrategia(3)); 
        
        Vector<Operador> opsSolucion = problema.obtenerSolucion();  //se inicia el proceso de busqueda para solucionar el cubo
       
        System.out.println("\nSOLUCION:");
        if (opsSolucion != null) {
            for (Operador o : opsSolucion) {
                System.out.println("Accion: " + o.getEtiqueta() + " "); //mostrams el camino solución (movimientos)
                OperadorRubik or = (OperadorRubik) o;
                cubo.mover(or.getMovimiento());
            }
            System.out.println();
            System.out.println("CUBO FINAL:\n" + cubo.toString());  //mostramos la estado final
        } else {
            System.out.println("no se ha encontrado solucion");
        }

    }
}
