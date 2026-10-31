import java.lang.reflect.Array;
import java.util.*;

public class ShortestPath {

  // DijkstraSP class, adapted to EdgeWeightedGraph
  static class DijkstraSP {
    private Edge[] edgeTo;
    private double[] distTo;
    private IndexMinPQ<Double> pq;
    private int source;

    public DijkstraSP(EdgeWeightedGraph G, int s) {
      this.source = s;
      edgeTo = new Edge[G.V()];
      distTo = new double[G.V()];
      pq = new IndexMinPQ<Double>(G.V());
      for (int v = 0; v < G.V(); v++) {
        distTo[v] = Double.POSITIVE_INFINITY;
      }
      distTo[s] = 0.0;
      pq.insert(s, 0.0);
      while (!pq.isEmpty()) {
        relax(G, pq.delMin());
      }
    }

    private void relax(EdgeWeightedGraph G, int v) {
      for (Edge e : G.adj(v)) {
        int w = e.other(v);
        if (distTo[w] > distTo[v] + e.weight()) {
          distTo[w] = distTo[v] + e.weight();
          edgeTo[w] = e;
          if (pq.contains(w)) {
            pq.change(w, distTo[w]);
          } else {
            pq.insert(w, distTo[w]);
          }
        }
      }
    }

    public double distTo(int v) {
      return distTo[v];
    }

    public boolean hasPathTo(int v) {
      return distTo[v] < Double.POSITIVE_INFINITY;
    }

    public Iterable<Edge> pathTo(int v) {
      if (!hasPathTo(v)) {
        return null;
      }
      Stack<Edge> path = new Stack<>();
      int current = v;
      while (current != source && edgeTo[current] != null) {
        Edge e = edgeTo[current];
        path.push(e);
        // Move to the other vertex of the edge, ensuring we go toward the source
        current = e.other(current);
        // Safety check to prevent infinite loops
        if (path.size() > distTo.length) {
          System.err.println("Error: Possible cycle detected in pathTo for vertex " + v);
          return null;
        }
      }
      return path;
    }
  }

  public static void main(String[] args) {
    // Initialize graph from G7 (assuming G50 was a typo)
    EdgeWeightedGraph graph = G50.graph();
    int[] fieldStations = { 7, 14, 25, 28, 40 };
    int V = graph.V();

    // Store results
    int[] assignedStation = new int[V];
    double[] minDist = new double[V];
    DijkstraSP[] dijkstra = new DijkstraSP[V];
    Arrays.fill(minDist, Double.POSITIVE_INFINITY);

    // Run Dijkstra's from each field station
    for (int station : fieldStations) {
      dijkstra[station] = new DijkstraSP(graph, station);
      for (int v = 0; v < V; v++) {
        if (Arrays.binarySearch(fieldStations, v) >= 0) {
          continue; // Skip field stations
        }
        if (dijkstra[station].hasPathTo(v) && dijkstra[station].distTo(v) < minDist[v]) {
          minDist[v] = dijkstra[station].distTo(v);
          assignedStation[v] = station;
        }
      }
    }

    System.out.println("Customer Assignments and Routes:");
    for (int v = 0; v < V; v++) {
      if (Arrays.binarySearch(fieldStations, v) >= 0) {
        continue; // Skip field stations
      }
      if (!dijkstra[assignedStation[v]].hasPathTo(v)) {
        System.out.printf("Customer %d: No path from any field station\n", v);
        continue;
      }
      System.out.printf("Customer %d: Served by Field Station %d, Response Time: %.1f\n",
          v, assignedStation[v], minDist[v]);
      System.out.print("Route: ");
      Iterable<Edge> edges = dijkstra[assignedStation[v]].pathTo(v);
      if (edges == null) {
        System.out.println("No valid path");
        continue;
      }
      Stack<Integer> path = new Stack<>();
      path.push(v);
      for (Edge e : edges) {
        int u = e.either();
        int w = e.other(u);
        int prev = path.peek();
        path.push(prev == u ? w : u);
      }
      while (!path.isEmpty()) {
        System.out.print(path.pop());
        if (!path.isEmpty()) {
          System.out.print(" -> ");
        }
      }
      System.out.println();
    }

    // Find optimal single field station from existing field stations
    double minTotalDist = Double.POSITIVE_INFINITY;
    int bestNode = -1;
    List<Integer> customerNodes = new ArrayList<>();
    for (int v = 0; v < V; v++) {
      if (Arrays.binarySearch(fieldStations, v) < 0) {
        customerNodes.add(v);
      }
    }

    for (int s : fieldStations) {
      DijkstraSP sp = new DijkstraSP(graph, s);
      double totalDist = 0.0;
      boolean reachable = true;
      for (int v : customerNodes) {
        if (Arrays.binarySearch(fieldStations, v) >= 0) {
          continue; // Skip field stations
        }
        if (!sp.hasPathTo(v)) {
          reachable = false;
          break;
        }
        totalDist += sp.distTo(v);

      }
      if (reachable && totalDist < minTotalDist) {
        minTotalDist = totalDist;
        bestNode = s;
      }
    }

    System.out.println("\nOptimal Single Field Station:");
    if (bestNode == -1) {
      System.out.println("No field station can reach all customers");
    } else {
      System.out.printf("Node %d with total cable cost (sum of distances): %.1f\n",
          bestNode, minTotalDist);
    }
  }
}

/*
 * Customer Assignments and Routes:
 * Customer 0: Served by Field Station 7, Response Time: 52.0
 * Route: 7 -> 5 -> 6 -> 0
 * Customer 1: Served by Field Station 7, Response Time: 28.0
 * Route: 7 -> 5 -> 1
 * Customer 2: Served by Field Station 14, Response Time: 30.0
 * Route: 14 -> 3 -> 2
 * Customer 3: Served by Field Station 14, Response Time: 15.0
 * Route: 14 -> 3
 * Customer 4: Served by Field Station 7, Response Time: 31.0
 * Route: 7 -> 4
 * Customer 5: Served by Field Station 7, Response Time: 15.0
 * Route: 7 -> 5
 * Customer 6: Served by Field Station 7, Response Time: 35.0
 * Route: 7 -> 5 -> 6
 * Customer 8: Served by Field Station 14, Response Time: 30.0
 * Route: 14 -> 3 -> 8
 * Customer 9: Served by Field Station 14, Response Time: 46.0
 * Route: 14 -> 3 -> 9
 * Customer 10: Served by Field Station 14, Response Time: 44.0
 * Route: 14 -> 12 -> 10
 * Customer 11: Served by Field Station 40, Response Time: 41.0
 * Route: 40 -> 45 -> 11
 * Customer 12: Served by Field Station 14, Response Time: 22.0
 * Route: 14 -> 12
 * Customer 13: Served by Field Station 14, Response Time: 64.0
 * Route: 14 -> 15 -> 13
 * Customer 15: Served by Field Station 14, Response Time: 36.0
 * Route: 14 -> 15
 * Customer 16: Served by Field Station 7, Response Time: 25.0
 * Route: 7 -> 16
 * Customer 17: Served by Field Station 7, Response Time: 20.0
 * Route: 7 -> 17
 * Customer 18: Served by Field Station 14, Response Time: 36.0
 * Route: 14 -> 21 -> 18
 * Customer 19: Served by Field Station 14, Response Time: 23.0
 * Route: 14 -> 19
 * Customer 20: Served by Field Station 14, Response Time: 35.0
 * Route: 14 -> 20
 * Customer 21: Served by Field Station 14, Response Time: 16.0
 * Route: 14 -> 21
 * Customer 22: Served by Field Station 28, Response Time: 30.0
 * Route: 28 -> 22
 * Customer 23: Served by Field Station 28, Response Time: 18.0
 * Route: 28 -> 23
 * Customer 24: Served by Field Station 25, Response Time: 25.0
 * Route: 25 -> 24
 * Customer 26: Served by Field Station 25, Response Time: 30.0
 * Route: 25 -> 26
 * Customer 27: Served by Field Station 28, Response Time: 43.0
 * Route: 28 -> 27
 * Customer 29: Served by Field Station 25, Response Time: 40.0
 * Route: 25 -> 24 -> 29
 * Customer 30: Served by Field Station 25, Response Time: 30.0
 * Route: 25 -> 30
 * Customer 31: Served by Field Station 25, Response Time: 21.0
 * Route: 25 -> 31
 * Customer 32: Served by Field Station 7, Response Time: 30.0
 * Route: 7 -> 32
 * Customer 33: Served by Field Station 40, Response Time: 58.0
 * Route: 40 -> 46 -> 33
 * Customer 34: Served by Field Station 28, Response Time: 30.0
 * Route: 28 -> 34
 * Customer 35: Served by Field Station 28, Response Time: 25.0
 * Route: 28 -> 35
 * Customer 36: Served by Field Station 40, Response Time: 30.0
 * Route: 40 -> 36
 * Customer 37: Served by Field Station 25, Response Time: 50.0
 * Route: 25 -> 37
 * Customer 38: Served by Field Station 25, Response Time: 61.0
 * Route: 25 -> 31 -> 38
 * Customer 39: Served by Field Station 40, Response Time: 40.0
 * Route: 40 -> 39
 * Customer 41: Served by Field Station 40, Response Time: 27.0
 * Route: 40 -> 41
 * Customer 42: Served by Field Station 28, Response Time: 50.0
 * Route: 28 -> 34 -> 42
 * Customer 43: Served by Field Station 40, Response Time: 41.0
 * Route: 40 -> 41 -> 43
 * Customer 44: Served by Field Station 28, Response Time: 74.0
 * Route: 28 -> 34 -> 42 -> 44
 * Customer 45: Served by Field Station 40, Response Time: 20.0
 * Route: 40 -> 45
 * Customer 46: Served by Field Station 40, Response Time: 28.0
 * Route: 40 -> 46
 * Customer 47: Served by Field Station 40, Response Time: 56.0
 * Route: 40 -> 41 -> 43 -> 47
 * Customer 48: Served by Field Station 40, Response Time: 43.0
 * Route: 40 -> 46 -> 48
 * Customer 49: Served by Field Station 40, Response Time: 63.0
 * Route: 40 -> 46 -> 49
 * 
 * Optimal Single Field Station:
 * Node 28 with total cable cost (sum of distances): 3650.0
 */