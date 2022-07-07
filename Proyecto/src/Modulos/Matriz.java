package Modulos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Wilson Estiven Rueda Bastidas - David Joan Mosquera Perea
 */

public class Matriz {
    private int matriz[][];

    public Matriz() {
        this.matriz = new int[10][10];
    }

    public void CargarLaberinto() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("Prueba.txt"));
            //Primera linea nos indica cual es la longitud de la matriz
            String linea = br.readLine();

            int fila = 0; //Para recorrer las filas de la matriz
            while (linea != null) {
                /*
                 * Tenemos todos los enteros JUNTOS en el String linea.
                 * Con split() los SEPARAMOS en un array donde cada entero
                 * es un String individual. Con un bucle, los parseamos a Integer
                 * para guardarlos en la matriz
                 */
                String[] enteros = linea.split(" ");
                for (int i = 0; i < enteros.length; i++) {
                    matriz[fila][i] = Integer.parseInt(enteros[i]);
                }

                fila++; //Incrementamos fila para la próxima línea de enteros
                linea = br.readLine(); //Leemos siguiente línea
            }
            br.close(); //Cerramos el lector de ficheros

            //Mostramos la matriz leída
        } catch (FileNotFoundException e) {
            System.out.println("Error! No se encontró el archivo");
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Error! No se pudo convertir a entero");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error! No se pudo acceder acceder al archivo.");
            e.printStackTrace();
        }

    }
    // Utilizamos el getmatriz.
    public int[][] getMatriz() {
        return this.matriz;
    }

    public int[] EncontrarAgente(int[][] a) {
        int[] agente = new int[2];
        int fila = 0;
        while (fila < 10) {
            for (int i = 0; i < 10; i++) {
                if (a[fila][i] == 2) {
                    agente[0] = fila;
                    agente[1] = i;
                }
            }
            fila++;
        }
        return agente;
    }

    public int[] EncontrarMovimientoAgente(int[][] a, int movimiento) {
        int[] agente = new int[2];
        int fila = 0;
        while (fila < 10) {
            for (int i = 0; i < 10; i++) {
                if (a[fila][i] == movimiento) {
                    agente[0] = fila;
                    agente[1] = i;
                }
            }
            fila++;
        }
        return agente;
    }

    public void mostrarLaberinto(int laberinto[][]) {
        int fila = 0;

        while (fila < 10) {
            for (int i = 0; i < 10; i++) {
                System.out.print(laberinto[fila][i]+" ");
            }
            System.out.println();

            fila++;
        }
    }
}
