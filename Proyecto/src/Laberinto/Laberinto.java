package Laberinto;

import AlgoritmosBusqueda.BusquedaAmplitud;
import AlgoritmosBusqueda.BusquedaCosto;
import AlgoritmosBusqueda.BusquedaProfundida;
import Modulos.Matriz;
import Modulos.Nodos;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Timer;

/**
 * @author Wilson Estiven Rueda Bastidas - David Joan Mosquera Perea
 */

public class Laberinto {

    Matriz matriz;
    BusquedaAmplitud busqAmplitud;
    BusquedaCosto busqCosto;
    BusquedaProfundida busqProfundida;
    ArrayList<Nodos> listaMovimientosAmplitud;
    ArrayList<Nodos> listaMovimientosAmplitud2;

    ArrayList<Nodos> listaMovimientosCostoUniforme;
    ArrayList<Nodos> listaMovimientosCostoUniforme2;

    ArrayList<Nodos> listaMovimientosProfundidad;

    public Laberinto() {

        matriz = new Matriz();
        matriz.CargarLaberinto();
        busqAmplitud = new BusquedaAmplitud(matriz.getMatriz());
        busqCosto = new BusquedaCosto(matriz.getMatriz());
        busqProfundida = new BusquedaProfundida(matriz.getMatriz());

        listaMovimientosAmplitud = new ArrayList<>();
        listaMovimientosAmplitud2 = new ArrayList<>();

        listaMovimientosCostoUniforme = new ArrayList<>();
        listaMovimientosCostoUniforme2 =  new ArrayList<>();

        listaMovimientosProfundidad = new ArrayList<>();
    }


    public void agenteAmplitud(int[][]lab) {
        ArrayList<Nodos> cola = new ArrayList<>();
        Nodos padre = new Nodos(lab, null, '0', null, 0, 0);
        ArrayList<Nodos> encontrado = new ArrayList<>();
        int conteo = 0;
        busqAmplitud.verificarAmplitud(cola, padre);
        System.err.println("" + busqAmplitud.getNodosExpandidos().size());

        for (int i = 1; i < busqAmplitud.getNodosExpandidos().size(); i++) {
            if (busqAmplitud.getNodosExpandidos().get(i).getMeta() == '5') {

                encontrado.add(conteo, busqAmplitud.getNodosExpandidos().get(i));
                conteo++;
            }

        }
        //System.out.println("metas encontradas: "+cont);
        //movimientosAmplitud(encontrado.get(0));
        movimientosAmplitud(encontrado.get(0));
    }

    public void movimientosAmplitud(Nodos meta) {
        if (meta.getOperador() != null) {
            listaMovimientosAmplitud.add(meta);
            movimientosAmplitud(meta.getPadre());
        }

    }

    public void recorridoAmplitud(int matriz1[][], int fila1, int columna1, int conteo, int posicion) {

        int fila = 0;
        int columna = 0;

        if (conteo < listaMovimientosAmplitud.size()) {
            posicion--;
            if (conteo == 0) {
                fila = fila1;
                columna = columna1;
            } else {
                fila = matriz.EncontrarAgente(matriz1)[0];
                columna = matriz.EncontrarAgente(matriz1)[1];
            }

            int[][] estadoMovimiento = busqAmplitud.moverAgente(listaMovimientosAmplitud.get(posicion).getOperador(), fila, columna);
            matriz.mostrarLaberinto(estadoMovimiento);
            System.out.println();
            conteo++;

            recorridoAmplitud(estadoMovimiento, fila, columna, conteo, posicion);
        }

    }

    public void eliminarRecorrido(){
        for (int i = 0; i < listaMovimientosAmplitud.size(); i++) {
            listaMovimientosAmplitud.removeAll(listaMovimientosAmplitud);
        }
    }

    public void AgenteCostoUniforme(int [][] c){

        ArrayList<Nodos> cola = new ArrayList<>();
        Nodos padre = new Nodos(c, null, '0', null, 0, 0);
        ArrayList<Nodos> encontrado = new ArrayList<>();
        int conteo = 0;
        busqCosto.verificacionCostoUniforme(cola, padre);

        for (int i = 1; i < busqCosto.getNodosExpandidos().size(); i++) {
            if (busqCosto.getNodosExpandidos().get(i).getMeta() == '5') {

                encontrado.add(conteo, busqCosto.getNodosExpandidos().get(i));
                conteo++;
            }
        }
        //System.out.println("metas encontradas: "+cont);
        //movimientosAmplitud(encontrado.get(0));
        //System.out.println("operador: "+encontrado.get(1).getOperador());
        movimientosCostoUniforme(encontrado.get(0));
    }

    public void movimientosCostoUniforme(Nodos meta){
        if (meta.getOperador() != null) {
            listaMovimientosCostoUniforme.add(meta);
            movimientosCostoUniforme(meta.getPadre());
        }
    }

    public void recorridoCostoUniforme(int matriz1[][], int fila1, int columna1, int conteo, int posicion){
        int fila = 0;
        int columna = 0;

        if (conteo < listaMovimientosCostoUniforme.size()) {
            posicion--;

            if (conteo == 0) {
                fila = fila1;
                columna = columna1;
            } else {
                fila = matriz.EncontrarAgente(matriz1)[0];
                columna = matriz.EncontrarAgente(matriz1)[1];
            }
            int[][] estadoMovimiento = busqCosto.moverAgente(listaMovimientosCostoUniforme.get(posicion).getOperador(), fila, columna);
            matriz.mostrarLaberinto(estadoMovimiento);
            System.out.println();
            conteo++;

            recorridoCostoUniforme(estadoMovimiento, fila, columna, conteo, posicion);
        }
    }

    public void eliminarRecorridoCosto(){
        for (int i = 0; i < listaMovimientosCostoUniforme.size(); i++) {
            listaMovimientosCostoUniforme.removeAll(listaMovimientosCostoUniforme);
        }
    }


    public void AgenteProfundidad(int[][] l) {
        ArrayList<Nodos> pila = new ArrayList<>();
        Nodos padre = new Nodos(l, null, '0', null, 0, 0);
        ArrayList<Nodos> encontrado = new ArrayList<>();
        int conteo = 0;
        busqProfundida.verificarProfundida(pila, padre);
        //System.err.println("" + bsqP.getNodosExpandidos().size());

        for (int i = 1; i < busqProfundida.getNodosExpandidos().size(); i++) {
            if (busqProfundida.getNodosExpandidos().get(i).getMeta() == '5') {

                encontrado.add(conteo, busqProfundida.getNodosExpandidos().get(i));
                conteo++;
            }

        }
        //System.out.println("metas encontradas: "+cont);
        //movimientosAmplitud(encontrado.get(0));
        movimientosProfundidad(encontrado.get(1));
    }

    public void movimientosProfundidad(Nodos meta) {
        if (meta.getOperador() != null) {
            listaMovimientosProfundidad.add(meta);
            movimientosProfundidad(meta.getPadre());
        }

    }

    public void recorridoProfundidad(int matriz1[][], int fila1, int columna1, int conteo, int posicion) {

        int fila = 0;
        int columna = 0;

        if (conteo < listaMovimientosProfundidad.size()) {
            posicion--;

            if (conteo == 0) {
                fila = fila1;
                columna = columna1;
            } else {
                fila = matriz.EncontrarAgente(matriz1)[0];
                columna = matriz.EncontrarAgente(matriz1)[1];
            }

            int[][] estadoMovimiento = busqProfundida.moverAgente(listaMovimientosProfundidad.get(posicion).getOperador(), fila, columna);
            matriz.mostrarLaberinto(estadoMovimiento);
            System.out.println();
            conteo++;

            recorridoProfundidad(estadoMovimiento, fila, columna, conteo, posicion);
        }

    }

    public void eliminarRecorridoProfundidad(){
        for (int i = 0; i < listaMovimientosProfundidad.size(); i++) {
            listaMovimientosProfundidad.removeAll(listaMovimientosProfundidad);
        }
    }

    public static void main(String[] args) {

        Laberinto game = new Laberinto();
        int[][] lab = game.matriz.getMatriz();
        int fila = game.matriz.EncontrarAgente(lab)[0];
        int columna = game.matriz.EncontrarAgente(lab)[1];

        // Secuencia para resolver la busqueda por amplitud
        /*
        game.agenteAmplitud(lab);
        game.matriz.mostrarLaberinto(lab);
        System.out.println();
        game.matriz.CargarLaberinto();
        int [][] l2 = game.matriz.getMatriz();
        game.recorridoAmplitud(l2, fila, columna, 0, game.listaMovimientosAmplitud.size());
        long tiempoFinalAmplitud = System.currentTimeMillis();
        tiempoFinalAmplitud = (tiempoFinalAmplitud - game.busqAmplitud.getTiempoInicial());
        System.err.println("Los Nodos expandidos son : "+ game.busqAmplitud.getNodosExpandidos().size() +
                " El Nivel de Profundidad es : "+game.listaMovimientosAmplitud.get(0).getProfundida() +
                " El Tiempo es : "+tiempoFinalAmplitud+" Milisegundos");
        game.eliminarRecorrido();
        */
        // Secuencia para resolver la busqueda costo uniforme

        game.AgenteCostoUniforme(lab);
        game.matriz.mostrarLaberinto(lab);
        System.out.println();
        game.matriz.CargarLaberinto();
        int [][] lab3 = game.matriz.getMatriz();
        game.recorridoCostoUniforme(lab3, fila, columna, 0, game.listaMovimientosCostoUniforme.size());
        long tiempoFinalCosto = System.currentTimeMillis();
        tiempoFinalCosto = (tiempoFinalCosto - game.busqAmplitud.getTiempoInicial());
        System.err.println("Los Nodos expandidos son: "+ game.busqCosto.getNodosExpandidos().size() +
                " Nivel de Profundidad: "+game.listaMovimientosCostoUniforme.get(0).getProfundida()+
                " El Costo: "+game.listaMovimientosCostoUniforme.get(0).getCosto() +
                " El Tiempo: "+tiempoFinalCosto+" Milisegundos");

        game.eliminarRecorridoCosto();


        // Secuencia para resolver por algoritmo de profundida
        /*
        game.AgenteProfundidad(lab);
        game.matriz.mostrarLaberinto(lab);
        System.out.println();
        game.matriz.CargarLaberinto();
        int [][] lab2 = game.matriz.getMatriz();
        game.recorridoProfundidad(lab2, fila, columna, 0, game.listaMovimientosProfundidad.size());
        long tiempoFinalAmplitud = System.currentTimeMillis();
        tiempoFinalAmplitud = (tiempoFinalAmplitud - game.busqProfundida.getTiempoInicial());
        System.err.println("Los Nodos expandidos son: "+ game.busqProfundida.getNodosExpandidos().size() +
                " La Profundidad es : "+game.listaMovimientosProfundidad.get(0).getProfundida() +
                " El Tiempo es : "+tiempoFinalAmplitud+" Milisegundos");
        game.eliminarRecorridoProfundidad();
         */

    }

}
