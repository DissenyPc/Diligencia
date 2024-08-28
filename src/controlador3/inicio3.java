package controlador3;

import java.util.*;

class Nodo {
    int id;
    int tiempo;
    Nodo anterior;

    Nodo(int id, int tiempo, Nodo anterior) {
        this.id = id;
        this.tiempo = tiempo;
        this.anterior = anterior;
    }
}

public class inicio3 {
    private static final int NUM_PUNTOS = 10;
    private static List<List<Nodo>> grafo = new ArrayList<>();

    public static void main(String[] args) {
        // Inicializar el grafo
        for (int i = 0; i < NUM_PUNTOS; i++) {
            grafo.add(new ArrayList<>());
        }

        // Añadir las conexiones entre los puntos con sus respectivos tiempos
        grafo.get(0).add(new Nodo(1, 10, null));
        grafo.get(0).add(new Nodo(2, 15, null));
        grafo.get(0).add(new Nodo(3, 20, null));
        grafo.get(1).add(new Nodo(4, 25, null));
        grafo.get(1).add(new Nodo(5, 30, null));
        grafo.get(1).add(new Nodo(6, 35, null));
        grafo.get(2).add(new Nodo(4, 20, null));
        grafo.get(2).add(new Nodo(5, 25, null));
        grafo.get(2).add(new Nodo(6, 30, null));
        grafo.get(3).add(new Nodo(4, 15, null));
        grafo.get(3).add(new Nodo(5, 20, null));
        grafo.get(3).add(new Nodo(6, 25, null));
        grafo.get(4).add(new Nodo(7, 10, null));
        grafo.get(4).add(new Nodo(8, 15, null));
        grafo.get(5).add(new Nodo(7, 20, null));
        grafo.get(5).add(new Nodo(8, 25, null));
        grafo.get(6).add(new Nodo(7, 30, null));
        grafo.get(6).add(new Nodo(8, 35, null));
        grafo.get(7).add(new Nodo(9, 10, null));
        grafo.get(8).add(new Nodo(9, 15, null));

        // Encontrar el camino con el menor coste de tiempo
        Nodo[] resultado = dijkstra(0);

        // Imprimir el tiempo mínimo para llegar al punto 10
        System.out.println("El tiempo mínimo para llegar al punto 10 es: " + resultado[9].tiempo);

        // Imprimir la secuencia de puntos geográficos
        List<Integer> camino = new ArrayList<>();
        for (Nodo nodo = resultado[9]; nodo != null; nodo = nodo.anterior) {
            camino.add(nodo.id + 1); // +1 para convertir de índice a punto geográfico
        }
        Collections.reverse(camino);
        System.out.println("La secuencia de puntos geográficos es: " + camino);
    }

    private static Nodo[] dijkstra(int inicio) {
        PriorityQueue<Nodo> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.tiempo));
        Nodo[] resultado = new Nodo[NUM_PUNTOS];
        int[] tiempos = new int[NUM_PUNTOS];
        Arrays.fill(tiempos, Integer.MAX_VALUE);
        tiempos[inicio] = 0;
        pq.add(new Nodo(inicio, 0, null));

        while (!pq.isEmpty()) {
            Nodo actual = pq.poll();

            for (Nodo vecino : grafo.get(actual.id)) {
                int nuevoTiempo = tiempos[actual.id] + vecino.tiempo;
                if (nuevoTiempo < tiempos[vecino.id]) {
                    tiempos[vecino.id] = nuevoTiempo;
                    resultado[vecino.id] = new Nodo(vecino.id, nuevoTiempo, actual);
                    pq.add(new Nodo(vecino.id, nuevoTiempo, actual));
                }
            }
        }

        return resultado;
    }
}
