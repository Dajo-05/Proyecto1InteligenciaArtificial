package AlgoritmosBusqueda;

import Modulos.Matriz;
import Modulos.Nodos;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * @author Wilson Estiven Rueda Bastidas - David Joan Mosquera Perea
 */

public class BusquedaCosto {
    Matriz matriz = new Matriz();
    int[][] Estado = new int[10][10];
    ArrayList<Nodos> nodosExpandidos = new ArrayList<>();
    long tiempoInicial;

    public BusquedaCosto(int[][] estado) {
        this.Estado = estado;
        this.tiempoInicial = System.currentTimeMillis();
    }

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

    public int[][] moverArriba(int fila, int columna) {
        int movimientoFila = fila - 1;

        if (movimientoFila < 0) {
            movimientoFila = 0;
        } else if (movimientoFila > 9) {
            movimientoFila = 9;
        }

        this.Estado[fila][columna] = 7;
        this.Estado[movimientoFila][columna] = 2;
        return this.Estado;
    }

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

    public void verificacionCostoUniforme(ArrayList<Nodos> cola, Nodos padre) {

        int conteoMeta = 0;
        cola.add(padre);
        int fila = 0;
        int columna = 0;
        int[] movimiento = new int[4];
        int verificarIzquierda = 0;
        int verificarArriba = 0;
        int verificarDerecha = 0;
        int verificarAbajo = 0;
        char verificarIzquierdaMeta = '0';
        char verificarArribaMeta = '0';
        char verificarDerechaMeta = '0';
        char verificarAbajoMeta = '0';
        char meta = '0';
        boolean costoAceite = false;

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
                movimiento = verficarCostoCaminos(fila, columna);

                verificarIzquierda = movimiento[0];
                verificarArriba = movimiento[1];
                verificarDerecha = movimiento[2];
                verificarAbajo = movimiento[3];

                if (verificarIzquierda != 100) {

                    if (verificarIzquierda == 0) {
                        verificarIzquierdaMeta = '5';
                    }

                    if (verificarIzquierda == 4) {
                        costoAceite = true;
                    }

                    int movimientoColumnaIzquierda = columna - 1;

                    if (movimientoColumnaIzquierda >= 0) {

                        if (movimientoColumnaIzquierda > 9) {
                            movimientoColumnaIzquierda = columna;
                        }

                        cola.add(new Nodos(moverIzquierda(fila, columna), nodoExpandido, verificarIzquierdaMeta, "IZQUIERDA", nodoExpandido.getProfundidad() + 1, fila, movimientoColumnaIzquierda, nodoExpandido.getCosto() + verificarIzquierda));
                        verificarIzquierda = '0';
                        costoAceite = false;
                    }
                }

                if (verificarArriba != 100) {

                    if (verificarArriba == 0) {
                        verificarArribaMeta = '5';
                    }

                    if (verificarArriba == 4) {
                        costoAceite = true;
                    }

                    int movimientoFilaArriba = fila - 1;

                    if (movimientoFilaArriba >= 0) {
                        if (movimientoFilaArriba > 9) {
                            movimientoFilaArriba = fila;
                        }
                        cola.add(new Nodos(moverArriba(fila, columna), nodoExpandido, verificarArribaMeta, "ARRIBA", nodoExpandido.getProfundidad() + 1, movimientoFilaArriba, columna, nodoExpandido.getCosto() + verificarArriba));
                        verificarArribaMeta = '0';
                        costoAceite = false;
                    }
                }

                if (verificarDerecha != 100) {

                    if (verificarDerecha == 0) {
                        verificarDerechaMeta = '5';
                    }

                    if (verificarDerecha == 4) {
                        costoAceite = true;
                    }
                    int movimientoColumnaDerecha = columna + 1;

                    if (movimientoColumnaDerecha >= 0) {
                        if (movimientoColumnaDerecha > 9) {
                            movimientoColumnaDerecha = columna;
                        }
                        cola.add(new Nodos(moverDerecha(fila, columna), nodoExpandido, verificarDerechaMeta, "DERECHA", nodoExpandido.getProfundidad() + 1, fila, movimientoColumnaDerecha, nodoExpandido.getCosto() + verificarDerecha));
                        verificarDerechaMeta = '0';
                        costoAceite = false;
                    }
                }

                if (verificarAbajo != 100) {

                    if (verificarAbajo == 0) {
                        verificarAbajoMeta = '5';
                    }

                    if (verificarAbajo == 4) {
                        costoAceite = true;
                    }

                    int movimientoFilaAbajo = fila + 1;

                    if (movimientoFilaAbajo >= 0) {
                        if (movimientoFilaAbajo > 9) {
                            movimientoFilaAbajo = fila;
                        }
                        cola.add(new Nodos(moverAbajo(fila, columna), nodoExpandido, verificarAbajoMeta, "ABAJO", nodoExpandido.getProfundidad() + 1, movimientoFilaAbajo, columna, nodoExpandido.getCosto() + verificarAbajo));
                        verificarAbajoMeta = '0';
                        costoAceite = false;
                    }
                }
                Collections.sort(cola, new Comparator<Nodos>() {
                    @Override
                    public int compare(Nodos o1, Nodos o2) {
                        return new Integer(o1.getCosto()).compareTo(new Integer(o2.getCosto()));
                    }
                });
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Mensaje: " + e.getMessage());
        }
    }

    public int[] verficarCostoCaminos(int fila, int columna) {

        int[] mover = new int[4];

        int izq = 0;
        int arri = 0;
        int dere = 0;
        int abaj = 0;

        int movimientoColumnaIzquierda = columna - 1;

        if (movimientoColumnaIzquierda >= 0) {
            if (movimientoColumnaIzquierda > 9) {
                movimientoColumnaIzquierda = columna;
            }
            int izquierda = this.Estado[fila][movimientoColumnaIzquierda];

            if (izquierda == 5) {
                izq = 0;
            } else {
                if (izquierda != 1 && izquierda != 7 && izquierda != 5 && izquierda != 2) {
                    if (izquierda == 6 || izquierda == 8) {
                        izq = 4;
                    } else {
                        izq = 1;
                    }
                } else {
                    izq = 100;
                }
            }
        } else {
            izq = 100;
        }

        int movimientoFilaArriba = fila - 1;

        if (movimientoFilaArriba >= 0) {
            if (movimientoFilaArriba > 9) {
                movimientoFilaArriba = fila;
            }
            int arriba = this.Estado[movimientoFilaArriba][columna];

            if (arriba == 5) {
                arri = 0;
            } else {
                if (arriba != 1 && arriba != 7 && arriba != 5 && arriba != 2) {
                    if (arriba == 6 || arriba == 8) {
                        arri = 4;
                    } else {
                        arri = 1;
                    }

                } else {
                    arri = 100;
                }
            }

        } else {
            arri = 100;
        }

        int movimientoColumnaDerecha = columna + 1;

        if (movimientoColumnaDerecha >= 0) {
            if (movimientoColumnaDerecha > 9) {
                movimientoColumnaDerecha = columna;
            }

            int derecha = this.Estado[fila][movimientoColumnaDerecha];

            if (derecha == 5) {
                dere = 0;
            } else {

                if (derecha != 1 && derecha != 7 && derecha != 5 && derecha != 2) {
                    if (derecha == 6 || derecha == 8) {
                        dere = 4;
                    } else {
                        dere = 1;
                    }

                } else {
                    dere = 100;
                }
            }

        } else {
            dere = 100;
        }

        int movimientoFilaAbajo = fila + 1;

        if (movimientoFilaAbajo >= 0) {
            if (movimientoFilaAbajo > 9) {
                movimientoFilaAbajo = fila;
            }

            int abajo = this.Estado[movimientoFilaAbajo][columna];
            if (abajo == 5) {
                abaj = 0;
            } else {
                if (abajo != 1 && abajo != 7 && abajo != 5 && abajo != 2) {
                    if (abajo == 6 || abajo == 8) {
                        abaj = 4;
                    } else {
                        abaj = 1;
                    }
                } else {
                    abaj = 100;
                }
            }
        } else {
            abaj = 100;
        }

        mover[0] = izq;
        mover[1] = arri;
        mover[2] = dere;
        mover[3] = abaj;
        return mover;
    }

    public int[][] moverAgente(String movimiento, int fila, int columna) {

        if (movimiento.equalsIgnoreCase("IZQ")) {
            return this.moverIzquierda(fila, columna);
        }
        if (movimiento.equalsIgnoreCase("ARRI")) {
            return this.moverArriba(fila, columna);
        }
        if (movimiento.equalsIgnoreCase("DERE")) {
            return this.moverDerecha(fila, columna);
        }
        if (movimiento.equalsIgnoreCase("ABAJ")) {
            return this.moverAbajo(fila, columna);
        }

        return this.Estado;
    }

    public ArrayList<Nodos> getNodosExpandidos() {
        return nodosExpandidos;
    }

    public void setNodosExpandidos(ArrayList<Nodos> nodosExpandidos) {
        this.nodosExpandidos = nodosExpandidos;
    }


}
