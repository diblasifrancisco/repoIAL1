package rubik;

import rubik.grafico.JFrameRubik;
/**
 * Interfaz gráfico basado en el Applet de Josef Jelinek (http://software.rubikscube.info/AnimCube/)
 * Inteligencia artificial 2011
 */
public class MainGrafico {

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
                 public void run() {
                      new JFrameRubik().setVisible(true); //ventana gráfica
                 }
         });
    }
}
