package AlgoritmosBusqueda;

import Modulos.Matriz;
import Modulos.Nodos;
import java.util.ArrayList;

/**
 * @author Wilson Estiven Rueda Bastidas - David Joan Mosquera Perea
 */

public class BusquedaAmplitud {
    Matriz matriz = new Matriz();
    int[][] Estado = new int[10][10];
    ArrayList<Nodos> nodosExpandidos = new ArrayList<>();
    ArrayList<Nodos> recorridoMeta1 = new ArrayList<>();
    long tiempoInicial;

    public BusquedaAmplitud(int[][] estado) {
        this.Estado = estado;
        this.tiempoInicial = System.currentTimeMillis();
    }

    //Movimiento del operador - mover izquierda.
    public int[][] moverIzquierda(int fila, int columna) {
        int movimientoColumna = columna - 1;

        if (movimientoColumna < 0) {
            movimientoColumna = 0;
        } else if (movimientoColumna > 9) {
            movimientoColumna = 9;
        }

        this.Estado[fila][columna] = 7;
        this.Estado[fila][movimientoColumna] = 2;

        return this.Estado;
    }

    //Movimiento del operador - mover derecha.
    public int[][] moverDerecha(int fila, int columna) {
        int movimientoColumna = columna + 1;

        if (movimientoColumna < 0) {
            movimientoColumna = 0;
        } else if (movimientoColumna > 9) {
            movimientoColumna = 9;
        }
        this.Estado[fila][columna] = 7;
        this.Estado[fila][movimientoColumna] = 2;

        return this.Estado;
    }

    //Movimiento del operador - mover arriba.
    public int[][] moverArriba(int fila, int columna) {
        int movimientoFila = fila - 1;

        if (movimientoFila < 0) {
            movimientoFila = 0;
        } else if (movimientoFila > 9) {
            movimientoFila= 9;
        }

        this.Estado[fila][columna] = 7;
        this.Estado[movimientoFila][columna] = 2;

        return this.Estado;
    }

    //Movimiento del operador - mover izquierda.
    public int[][] moverAbajo(int fila, int columna) {
        int movimientoFila = fila + 1;

        if (movimientoFila < 0) {
            movimientoFila = 0;
        } else if (movimientoFila > 9) {
            movimientoFila = 9;
        }

        this.Estado[fila][columna] = 7;
        this.Estado[movimientoFila][columna] = 2;

        return this.Estado;
    }

    public void verificarAmplitud(ArrayList<Nodos> cola, Nodos padre) {

        int conteo = 0;
        int conteoMeta = 0;
        cola.add(padre);
        int fila = 0;
        int columna = 0;
        String movimiento = "";
        char verificarIzquierda, verificarDerecha, verificarArriba, verificarAbajo;
        char meta = '0';
        int acumulable = 1;
        long tiempo = 0;

        try {

            while (conteoMeta < 2) {

                Nodos nodoExpandido = cola.remove(0);
                nodosExpandidos.add(nodoExpandido);

                if (nodoExpandido.getOperador() == null) {
                    fila = matriz.EncontrarAgente(nodoExpandido.getEstado())[0];
                    columna = matriz.EncontrarAgente(nodoExpandido.getEstado())[1];
                } else {
                    fila = nodoExpandido.getPosicionFila();
                    columna = nodoExpandido.getPosicionColumna();
                }

                meta = nodoExpandido.getMeta();

                if (meta == '5') {
                    conteoMeta++;

                }

                if (conteoMeta == 2) {
                    acumulable = conteoMeta;
                }

                movimiento = verificar(fila, columna);

                verificarIzquierda = movimiento.charAt(0);
                verificarDerecha = movimiento.charAt(1);
                verificarArriba = movimiento.charAt(2);
                verificarAbajo = movimiento.charAt(3);

                if (verificarIzquierda == '1' || verificarIzquierda == '5') {

                    int movimientoColumnaIzquierda = columna - 1;

                    if (movimientoColumnaIzquierda >= 0) {

                        if (movimientoColumnaIzquierda > 9) {
                            movimientoColumnaIzquierda = columna;
                        }

                        cola.add(new Nodos(moverIzquierda(fila, columna), nodoExpandido, verificarIzquierda, acumulable, "IZQUIERDA", nodoExpandido.getProfundida() + 1, fila, movimientoColumnaIzquierda));

                    }
                }

                if (verificarDerecha == '1' || verificarDerecha == '5') {
                    int movimientoColumnaDerecha = columna + 1;

                    if (movimientoColumnaDerecha >= 0) {
                        if (movimientoColumnaDerecha > 9) {
                            movimientoColumnaDerecha = columna;
                        }
                        cola.add(new Nodos(moverDerecha(fila, columna), nodoExpandido, verificarDerecha, acumulable, "DERECHA", nodoExpandido.getProfundida() + 1, fila, movimientoColumnaDerecha));
                    }
                }
                if (verificarArriba == '1' || verificarArriba == '5') {
                    int movimientoFilaArriba = fila - 1;

                    if (movimientoFilaArriba >= 0) {
                        if (movimientoFilaArriba > 9) {
                            movimientoFilaArriba = fila;
                        }
                        cola.add(new Nodos(moverArriba(fila, columna), nodoExpandido, verificarArriba, acumulable, "ARRIBA", nodoExpandido.getProfundida() + 1, movimientoFilaArriba, columna));
                    }
                }

                if (verificarAbajo == '1' || verificarAbajo == '5') {
                    int movimientoFilaAbajo = fila + 1;

                    if (movimientoFilaAbajo >= 0) {
                        if (movimientoFilaAbajo > 9) {
                            movimientoFilaAbajo = fila;
                        }
                        cola.add(new Nodos(moverAbajo(fila, columna), nodoExpandido, verificarAbajo, acumulable, "ABAJO", nodoExpandido.getProfundida() + 1, movimientoFilaAbajo, columna));
                    }
                }

            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Mensaje: " + e.getMessage());
        }

    }

    public ArrayList<Nodos> getNodosExpandidos() {

        return nodosExpandidos;
    }

    public void setNodosExpandidos(ArrayList<Nodos> nodosExpandidos) {

        this.nodosExpandidos = nodosExpandidos;
    }

    public int[][] moverAgente(String movimiento, int fila, int columna) {

        if (movimiento.equalsIgnoreCase("IZQUIERDA")) {
            return this.moverIzquierda(fila, columna);
        }
        if (movimiento.equalsIgnoreCase("DERECHA")) {
            return this.moverDerecha(fila, columna);
        }
        if (movimiento.equalsIgnoreCase("ARRIBA")) {
            return this.moverArriba(fila, columna);
        }
        if (movimiento.equalsIgnoreCase("ABAJO")) {
            return this.moverAbajo(fila, columna);
        }
        return this.Estado;
    }

    public String verificar(int fila, int columna) {

        String movimientos = "";

        int movimientoColumnaIzquierda = columna- 1;

        if (movimientoColumnaIzquierda >= 0) {
            if (movimientoColumnaIzquierda > 9) {
                movimientoColumnaIzquierda = columna;
            }
            int izquierda = this.Estado[fila][movimientoColumnaIzquierda];
            if (izquierda == 5) {
                movimientos += "5";
            } else {
                if (izquierda != 1 && izquierda != 7 && izquierda != 5 && izquierda != 2) {
                    movimientos += "1";

                } else {
                    movimientos += "0";
                }
            }

        } else {
            movimientos += "0";
        }

        int movimientoColumnaDerecha = columna + 1;

        if (movimientoColumnaDerecha >= 0) {
            if (movimientoColumnaDerecha > 9) {
                movimientoColumnaDerecha = columna;
            }

            int derecha = this.Estado[fila][movimientoColumnaDerecha];
            if (derecha == 5) {
                movimientos += "5";
            } else {
                if (derecha != 1 && derecha != 7 && derecha != 5 && derecha != 2) {
                    movimientos += "1";
                } else {
                    movimientos += "0";
                }
            }
        } else {
            movimientos += "0";
        }

        int movimientoFilaArriba = fila - 1;

        if (movimientoFilaArriba >= 0) {
            if (movimientoFilaArriba > 9) {
                movimientoFilaArriba = fila;
            }
            int arriba = this.Estado[movimientoFilaArriba][columna];
            if (arriba == 5) {
                movimientos += "5";
            } else {
                if (arriba != 1 && arriba != 7 && arriba != 5 && arriba != 2) {
                    movimientos += "1";
                } else {
                    movimientos += "0";
                }
            }
        } else {
            movimientos += "0";
        }

        int movimientoFilaAbajo = fila + 1;

        if (movimientoFilaAbajo >= 0) {
            if (movimientoFilaAbajo > 9) {
                movimientoFilaAbajo = fila;
            }

            int abajo = this.Estado[movimientoFilaAbajo][columna];
            if (abajo == 5) {
                movimientos += "5";
            } else {
                if (abajo != 1 && abajo != 7 && abajo != 5 && abajo != 2) {
                    movimientos += "1";
                } else {
                    movimientos += "0";
                }
            }
        } else {
            movimientos += "0";
        }

        return movimientos;
    }

    public long getTiempoInicial() {
        return tiempoInicial;
    }

    public void setTiempoInicial(long tiempoInicial) {
        this.tiempoInicial = tiempoInicial;
    }


}
