package rubik.busqueda;

/**
 * Nodos usados por la BusquedaAnchura. 
 * Añade el Operador usado para generar el estado almacenado en este Nodo. 
 * Usado para simplificar la reconstrucción del camino solución.
 * IA 2011
 */
public class NodoBusqueda extends Nodo {
    /** Operador empleado para generar el Estado almacenado en este Nodo*/
    private Operador operador;
    private int costo;
    private int profundidad;
    private int heuristica;
    private int funcionEv;
    
    public NodoBusqueda(Estado estado, Nodo padre, Operador operador) {
        super(estado, padre);
        this.operador = operador;
    }

    public Operador getOperador() {
        return operador;
    }
    
    public void setOperador(Operador operador) {
        this.operador = operador;
    }
    
    public int getCosto(){
        return costo;
    }
    
    public void setCosto(int costo){
        this.costo = costo;
    }
    
    @Override
    public String toString(){
        return (super.toString()+"\nOPERADOR:"+operador.getEtiqueta());
    }

    public int getProfundidad() {
        return profundidad;
    }

    public void setProfundidad(int profundidad) {
        this.profundidad = profundidad;
    }

    public int getHeuristica() {
        return heuristica;
    }
    public void setHeuristica(int heuristica) {
        this.heuristica = heuristica;
    }
    public int getFuncionEv() {
        return funcionEv;
    }
    public void setFuncionEv(int g, int h) {
        this.funcionEv = g+h;
    }
}
