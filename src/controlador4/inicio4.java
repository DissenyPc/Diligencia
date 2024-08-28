package controlador4;

import java.util.*;

public class inicio4 {
   

    public static void main(String[] args) {
        final int NUM_PUNTOS = 10;
        int[][] tiempos = new int[NUM_PUNTOS-1][NUM_PUNTOS]; // MENOS 1 POR QUE NUNCA SE VA A IR MAS LEJOS DE NUM_PUNTOS
        int[] dp = new int[NUM_PUNTOS];
        int[] camino = new int[NUM_PUNTOS];


        // Inicializar los tiempos con un valor alto (infinito)
        for (int[] fila : tiempos) {
            Arrays.fill(fila, Integer.MAX_VALUE);
        }

        // Añadir las conexiones entre los puntos con sus respectivos tiempos
        tiempos[0][1] = 19;
        tiempos[0][2] = 15;
        tiempos[0][3] = 25;
        tiempos[1][4] = 25;
        tiempos[1][5] = 30;
        tiempos[1][6] = 35;
        tiempos[2][4] = 20;
        tiempos[2][5] = 25;
        tiempos[2][6] = 30;
        tiempos[3][4] = 19;
        tiempos[3][5] = 20;
        tiempos[3][6] = 35;
        tiempos[4][7] = 10;
        tiempos[4][8] = 31;
        tiempos[5][7] = 20;
        tiempos[5][8] = 2;
        tiempos[6][7] = 30;
        tiempos[6][8] = 35;
        tiempos[7][9] = 41;
        tiempos[8][9] = 1;

        // Inicializar el array dp con un valor alto (infinito)
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0; // El tiempo para llegar al punto inicial es 0

        // Calcular el tiempo mínimo para llegar a cada punto
        for (int i = 0; i < NUM_PUNTOS-1; i++) {
            for (int j = 0; j < NUM_PUNTOS; j++) {
                if (tiempos[i][j] != Integer.MAX_VALUE /*&& dp[i] != Integer.MAX_VALUE*/) { // EN LAS PRUEBAS REALIZADAS ES INECESARIO
                    if (dp[i] + tiempos[i][j] < dp[j]) {
                        dp[j] = dp[i] + tiempos[i][j];
                        camino[j] = i;
                    }
                }
            }
        }

        // Imprimir el tiempo mínimo para llegar al punto 10
        System.out.println("El tiempo mínimo para llegar al punto 10 es: " + dp[9]);

        // Imprimir la secuencia de puntos geográficos
        List<Integer> secuencia = new ArrayList<>();
        for (int i = 9; i != 0; i = camino[i]) {
            secuencia.add(i + 1); // +1 para convertir de índice a punto geográfico
        }
        secuencia.add(1); // Añadir el punto inicial
        Collections.reverse(secuencia);
        System.out.println("La secuencia de puntos geográficos es: " + secuencia);
    }
}

