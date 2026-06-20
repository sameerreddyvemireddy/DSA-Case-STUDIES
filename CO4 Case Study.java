class Edge {
    int src, dest, weight;

    Edge(int s, int d, int w) {
        src = s;
        dest = d;
        weight = w;
    }
}

public class BMTCBellmanFord {

    static final int V = 7; // Number of Bus Hubs
    static final int E = 11; // Number of Routes

    public static void bellmanFord(Edge[] edges, int source) {

        int[] dist = new int[V];

        for (int i = 0; i < V; i++)
            dist[i] = Integer.MAX_VALUE;

        dist[source] = 0;

        // Relax all edges V-1 times
        for (int i = 1; i < V; i++) {
            for (int j = 0; j < E; j++) {
                int u = edges[j].src;
                int v = edges[j].dest;
                int w = edges[j].weight;

                if (dist[u] != Integer.MAX_VALUE &&
                    dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                }
            }
        }

        // Check for negative cycle
        for (int j = 0; j < E; j++) {
            int u = edges[j].src;
            int v = edges[j].dest;
            int w = edges[j].weight;

            if (dist[u] != Integer.MAX_VALUE &&
                dist[u] + w < dist[v]) {
                System.out.println("Negative Weight Cycle Detected!");
                return;
            }
        }

        String[] hubs = {"MJC", "KEM", "JAY", "KOR", "WHF", "HBR", "MRT"};

        System.out.println("Shortest distance from MJC:");

        for (int i = 0; i < V; i++) {
            System.out.println(hubs[i] + " -> " + dist[i]);
        }
    }

    public static void main(String[] args) {

        Edge[] edges = new Edge[E];

        edges[0] = new Edge(0, 1, 8);   // MJC -> KEM
        edges[1] = new Edge(0, 2, 5);   // MJC -> JAY
        edges[2] = new Edge(1, 3, 1);   // KEM -> KOR
        edges[3] = new Edge(2, 3, 4);   // JAY -> KOR
        edges[4] = new Edge(2, 4, 10);  // JAY -> WHF
        edges[5] = new Edge(3, 4, 6);   // KOR -> WHF
        edges[6] = new Edge(4, 6, -3);  // WHF -> MRT (Negative Edge)
        edges[7] = new Edge(3, 5, 6);   // KOR -> HBR
        edges[8] = new Edge(5, 6, 3);   // HBR -> MRT
        edges[9] = new Edge(1, 5, 7);   // KEM -> HBR
        edges[10] = new Edge(0, 6, 20); // MJC -> MRT

        bellmanFord(edges, 0);
    }
}
