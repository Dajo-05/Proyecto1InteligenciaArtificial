package Laberinto;

import AlgoritmosBusqueda.BusquedaAmplitud;
import AlgoritmosBusqueda.BusquedaCosto;
import AlgoritmosBusqueda.BusquedaProfundidad;
import Modulos.Matriz;
import Modulos.Nodos;


import java.util.ArrayList;
import java.util.Scanner;


/**
 * @author Wilson Estiven Rueda Bastidas - David Joan Mosquera Perea
 */

public class Laberinto {

    Matriz matriz;
    BusquedaAmplitud busqAmplitud;
    BusquedaCosto busqCosto;
    BusquedaProfundidad busqProfundidad;
    ArrayList<Nodos> listaMovimientosAmplitud;

    ArrayList<Nodos> listaMovimientosCostoUniforme;

    ArrayList<Nodos> listaMovimientosProfundidad;

    public Laberinto() {

        matriz = new Matriz();
        matriz.CargarLaberinto();
        busqAmplitud = new BusquedaAmplitud(matriz.getMatriz());
        busqCosto = new BusquedaCosto(matriz.getMatriz());
        busqProfundidad = new BusquedaProfundidad(matriz.getMatriz());

        listaMovimientosAmplitud = new ArrayList<>();

        listaMovimientosCostoUniforme = new ArrayList<>();

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
        //Solución del algoritmo amplitud.
        //Solución para que encuentre el 1 item.
        //movimientosAmplitud(encontrado.get(0));
        //Solución para que encuentre el 2 item.
        movimientosAmplitud(encontrado.get(1));

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
            System.out.println("Pasos del agente en el laberinto: ");

            recorridoAmplitud(estadoMovimiento, fila, columna, conteo, posicion);
        }
    }

    public void eliminarRecorrido(){
        for (int i = 0; i < listaMovimientosAmplitud.size(); i++) {
            listaMovimientosAmplitud.removeAll(listaMovimientosAmplitud);
        }
    }

    public void AgenteCostoUniforme(int [][] lab){

        ArrayList<Nodos> cola = new ArrayList<>();
        Nodos padre = new Nodos(lab, null, '0', null, 0, 0);
        ArrayList<Nodos> encontrado = new ArrayList<>();
        int conteo = 0;
        busqCosto.verificacionCostoUniforme(cola, padre);

        for (int i = 1; i < busqCosto.getNodosExpandidos().size(); i++) {
            if (busqCosto.getNodosExpandidos().get(i).getMeta() == '5') {

                encontrado.add(conteo, busqCosto.getNodosExpandidos().get(i));
                conteo++;
            }
        }
        //movimientosAmplitud(encontrado.get(1));
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
            System.out.println("Pasos del agente en el laberinto: ");
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


    public void AgenteProfundidad(int[][] lab) {
        ArrayList<Nodos> pila = new ArrayList<>();
        Nodos padre = new Nodos(lab, null, '0', null, 0, 0);
        ArrayList<Nodos> encontrado = new ArrayList<>();
        int conteo = 0;
        busqProfundidad.verificarProfundidad(pila, padre);
        //System.err.println("" + bsqP.getNodosExpandidos().size());

        for (int i = 1; i < busqProfundidad.getNodosExpandidos().size(); i++) {
            if (busqProfundidad.getNodosExpandidos().get(i).getMeta() == '5') {

                encontrado.add(conteo, busqProfundidad.getNodosExpandidos().get(i));
                conteo++;
            }

        }

        //Solución del algoritmo por profundidad.
        //Solución para que encuentre el 1 item.
        //movimientosProfundidad(encontrado.get(0));
        //Solución para que encuentre el 2 item.
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

            int[][] estadoMovimiento = busqProfundidad.moverAgente(listaMovimientosProfundidad.get(posicion).getOperador(), fila, columna);
            matriz.mostrarLaberinto(estadoMovimiento);
            System.out.println();
            conteo++;
            System.out.println("Pasos del agente en el laberinto: ");

            recorridoProfundidad(estadoMovimiento, fila, columna, conteo, posicion);
        }
    }

    public void eliminarRecorridoProfundidad(){
        for (int i = 0; i < listaMovimientosProfundidad.size(); i++) {
            listaMovimientosProfundidad.removeAll(listaMovimientosProfundidad);
        }
    }

    public static void main(String[] args) {

        int opcion = 0;

        do {
            Scanner scaner = new Scanner(System.in);
            System.out.println("==================================");
            System.out.println("Por favor Seleccione un algoritmo");
            System.out.println("==================================");
            System.out.println("1. Algoritmo por profundidad");
            System.out.println("2. Algoritmo por Amplitud");
            System.out.println("3. Algoritmo Costo Uniforme");
            System.out.println("4. Salir");
            System.out.println("Ingrese un numero de las opciones");

             opcion = scaner.nextInt();

             switch (opcion){
                 case 1:
                     // El algoritmo de busqueda que utiliza es por profundidad.
                     Laberinto game = new Laberinto();
                     int[][] lab = game.matriz.getMatriz();
                     int fila = game.matriz.EncontrarAgente(lab)[0];
                     int columna = game.matriz.EncontrarAgente(lab)[1];

                     game.AgenteProfundidad(lab);
                     game.matriz.mostrarLaberinto(lab);
                     System.out.println();
                     game.matriz.CargarLaberinto();
                     int [][] lab2 = game.matriz.getMatriz();
                     game.recorridoProfundidad(lab2, fila, columna, 0, game.listaMovimientosProfundidad.size());
                     long tiempoFinal = System.currentTimeMillis();
                     tiempoFinal = (tiempoFinal - game.busqProfundidad.getTiempoInicial());
                     System.err.println("Los Nodos expandidos son: "+ game.busqProfundidad.getNodosExpandidos().size() +
                             " La Profundidad es : "+game.listaMovimientosProfundidad.get(0).getProfundidad() +
                             " El Tiempo es : "+tiempoFinal+" Milisegundos");
                     game.eliminarRecorridoProfundidad();

                     break;
                 case 2:
                     // El algoritmo de busqueda que utiliza es por amplitud.
                     Laberinto game2 = new Laberinto();
                     int[][] laba2 = game2.matriz.getMatriz();
                     int fila2 = game2.matriz.EncontrarAgente(laba2)[0];
                     int columna2 = game2.matriz.EncontrarAgente(laba2)[1];


                     game2.agenteAmplitud(laba2);
                     game2.matriz.mostrarLaberinto(laba2);
                     System.out.println();
                     game2.matriz.CargarLaberinto();
                     int [][] lab3 = game2.matriz.getMatriz();
                     game2.recorridoAmplitud(lab3, fila2, columna2, 0, game2.listaMovimientosAmplitud.size());
                     long tiempoFinalAmplitud = System.currentTimeMillis();
                     tiempoFinalAmplitud = (tiempoFinalAmplitud - game2.busqAmplitud.getTiempoInicial());
                     System.err.println("Los Nodos expandidos son : "+ game2.busqAmplitud.getNodosExpandidos().size() +
                             " El Nivel de Profundidad es : "+game2.listaMovimientosAmplitud.get(0).getProfundidad() +
                             " El Tiempo es : "+tiempoFinalAmplitud+" Milisegundos");
                     game2.eliminarRecorrido();

                     break;
                 case 3:
                     // El algoritmo de busqueda que utiliza es por costo uniforme.
                     Laberinto game3 = new Laberinto();
                     int[][] laba3 = game3.matriz.getMatriz();
                     int fila3 = game3.matriz.EncontrarAgente(laba3)[0];
                     int columna3 = game3.matriz.EncontrarAgente(laba3)[1];


                     game3.AgenteCostoUniforme(laba3);
                     game3.matriz.mostrarLaberinto(laba3);
                     System.out.println();
                     game3.matriz.CargarLaberinto();
                     int [][] lab4 = game3.matriz.getMatriz();
                     game3.recorridoCostoUniforme(lab4, fila3, columna3, 0, game3.listaMovimientosCostoUniforme.size());
                     long tiempoFinalCosto = System.currentTimeMillis();
                     tiempoFinalCosto = (tiempoFinalCosto - game3.busqAmplitud.getTiempoInicial());
                     System.err.println("Los Nodos expandidos son: "+ game3.busqCosto.getNodosExpandidos().size() +
                             " Nivel de Profundidad: "+game3.listaMovimientosCostoUniforme.get(0).getProfundidad()+
                             " El Costo: "+game3.listaMovimientosCostoUniforme.get(0).getCosto() +
                             " El Tiempo: "+tiempoFinalCosto+" Milisegundos");

                     game3.eliminarRecorridoCosto();

                     break;
                 default:
                     System.out.println("Digite una opción del menú");
                     break;

             }

        }while (opcion != 4) ;

    }

}
