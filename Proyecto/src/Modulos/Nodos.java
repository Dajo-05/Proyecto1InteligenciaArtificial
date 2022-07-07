package Modulos;

/**
 * @author Wilson Estiven Rueda Bastidas - David Joan Mosquera Perea
 */
public class Nodos {
    // Estado de la matriz 10x10.
    int Estado[][] = new int[10][10];
    Nodos padre;
    // Dirección movimiento del agente.
    String operador;
    int profundida;
    int costo;
    char meta;
    int posicionFila;
    int posicionColumna;
    int metasEncontradas;


    public Nodos(int[][] estado, Nodos padre, char meta , String operador, int profundida, int costo) {
        this.Estado = estado;
        this.padre = padre;
        this.operador = operador;
        this.profundida = profundida;
        this.meta = meta;
        this.costo = costo;
    }

    public Nodos(int[][] estado, Nodos padre, char meta , int metaEncontrada ,String operador, int profundida, int posicionFila, int posicionColumna){
        this.Estado = estado;
        this.padre = padre;
        this.operador = operador;
        this.profundida = profundida;
        this.posicionFila = posicionFila;
        this.posicionColumna = posicionColumna;
        this.meta = meta;
        this.metasEncontradas = metaEncontrada;
    }

    public Nodos(int[][] estado, Nodos padre, char meta , String operador, int profundida, int posicionFila, int posicionColumna, int costo){
        this.Estado = estado;
        this.padre = padre;
        this.operador = operador;
        this.profundida = profundida;
        this.posicionFila= posicionFila;
        this.posicionColumna= posicionColumna;
        this.meta = meta;
        this.costo = costo;
    }
    // Los get y set de Estado.
    public int[][] getEstado() {
        return Estado;
    }
    public void setEstado(int[][] Estado) {
        this.Estado = Estado;
    }

    // Los get y set de padre.
    public Nodos getPadre() {
        return padre;
    }
    public void setPadre(Nodos padre) {
        this.padre = padre;
    }

    // Los get y set operador.
    public String getOperador() {
        return operador;
    }
    public void setOperador(String operador) {
        this.operador = operador;
    }

    // Los get y set profundida.
    public int getProfundida() {
        return profundida;
    }
    public void setProfundida(int profundida) {
        this.profundida = profundida;
    }

    // Los get y set costo.
    public int getCosto() {
        return costo;
    }
    public void setCosto(int costo) {
        this.costo = costo;
    }

    // Los get y set meta.
    public char getMeta() {
        return meta;
    }
    public void setMeta(char meta) {
        this.meta = meta;
    }

    // Los get y set posicionFila.
    public int getPosicionFila() {
        return posicionFila;
    }
    public void setPosicionFila(int posicionFila) {
        this.posicionFila = posicionFila;
    }

    // Los get y set posicionColumna.
    public int getPosicionColumna() {
        return posicionColumna;
    }
    public void setPosicionColumna(int posicionColumna) {
        this.posicionColumna= posicionColumna;
    }

    // Los get y set metasEncontradas.
    public int getMetasEncontradas() {
        return metasEncontradas;
    }
    public void setMetasEncontradas(int metasEncontradas) {
        this.metasEncontradas = metasEncontradas;
    }

}
