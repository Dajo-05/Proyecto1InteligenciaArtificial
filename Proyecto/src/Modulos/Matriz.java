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

           //Para recorrer las filas de la matriz
            int fila = 0;
            while (linea != null) {

                String[] enteros = linea.split(" ");
                for (int i = 0; i < enteros.length; i++) {
                    matriz[fila][i] = Integer.parseInt(enteros[i]);
                }

                fila++;
                linea = br.readLine();
            }
            br.close();
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

    public int[] EncontrarAgente(int[][] lab) {
        int[] agente = new int[2];
        int fila = 0;
        while (fila < 10) {
            for (int i = 0; i < 10; i++) {
                if (lab[fila][i] == 2) {
                    agente[0] = fila;
                    agente[1] = i;
                }
            }
            fila++;
        }
        return agente;
    }

    public int[] EncontrarMovimientoAgente(int[][] lab, int movimiento) {
        int[] agente = new int[2];
        int fila = 0;
        while (fila < 10) {
            for (int i = 0; i < 10; i++) {
                if (lab[fila][i] == movimiento) {
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
