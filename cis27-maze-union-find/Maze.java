import java.util.ArrayList;
import java.util.Random;

public class Maze {
  private ArrayList<Integer> nodes;
  private int endNode;
  private Random random = new Random();
  private int w;
  private int l;

  private ArrayList<Pair> removedWalls;

  public Maze(int n, int m) {
    nodes = new ArrayList<>(n * m);
    for (int i = 0; i < n * m; i++) {
      nodes.add(i);
    }
    endNode = n * m - 1;
    l = n;
    w = m;
    removedWalls = new ArrayList<>();
  }

  public int find(int a) {
    return nodes.get(a);
  }

  public void union(int a, int b) {
    int rootA = find(a);
    int rootB = find(b);
    if (rootA != rootB) {
      for (int i = 0; i < nodes.size(); i++) {
        if (nodes.get(i) == rootA) {
          nodes.set(i, rootB);
        }
      }
      removedWalls.add(new Pair(a, b));
    }
  }

  private ArrayList<Integer> getNeighbors(int a) {
    ArrayList<Integer> options = new ArrayList<>();
    // up
    if ((a - w) >= 0) {
      options.add(a - w);
    }
    // down
    if ((a + w) <= endNode) {
      options.add(a + w);
    }
    // left
    if ((a % w) != 0 && (a - 1) >= 0) {
      options.add(a - 1);
    }
    // right
    if ((a + 1) % w != 0 && (a + 1) <= endNode) {
      options.add(a + 1);
    }
    return options;
  }

  private int getRandomNeighbor(ArrayList<Integer> options) {
    int r = random.nextInt(options.size());
    return options.get(r);
  }

  public ArrayList<Pair> build() {
    while (!allConnected()) {
      int rand = random.nextInt(endNode + 1);
      ArrayList<Integer> neighbors = getNeighbors(rand);
      if (!neighbors.isEmpty()) {
        int randomNeighbor = getRandomNeighbor(neighbors);
        union(rand, randomNeighbor);
      }
    }
    return removedWalls;
  }

  public boolean allConnected() {
    for (int i = 0; i < nodes.size(); i++) {
      if (find(i) != find(0)) {
        return false;
      }
    }
    return true;
  }

}

class Pair {
  int node1;
  int node2;

  public Pair(int a, int b) {
    node1 = a;
    node2 = b;
  }
}

// @formatter:off
/*
 
+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+
                    |           |               |           |
+   +   +   +---+   +   +---+---+---+   +---+---+---+---+   +
|   |   |   |       |           |       |           |       |
+   +---+---+---+   +   +---+   +   +   +   +---+---+---+   +
|   |               |   |           |           |       |   |
+---+   +---+   +   +---+---+---+---+---+---+   +---+   +   +
|       |   |   |       |       |       |   |       |       |
+---+---+   +---+---+   +---+   +   +---+   +---+   +   +---+
|           |       |   |       |   |   |   |   |       |   |
+---+   +---+   +---+   +---+   +   +   +   +   +   +---+   +
|       |                   |               |               |
+   +   +   +---+---+---+---+---+   +   +   +   +---+---+   +
|   |           |   |       |   |   |   |       |       |   |
+   +---+   +---+   +   +---+   +---+   +   +---+   +   +---+
|   |           |               |   |   |           |       |
+   +---+   +---+   +---+---+---+   +---+   +   +---+---+   +
|       |       |           |   |       |   |       |       |
+   +---+   +   +---+---+   +   +---+   +   +---+---+---+   +
|   |       |                           |               |   |
+   +---+---+   +   +---+   +---+   +   +   +---+---+   +   +
|   |       |   |       |   |       |           |       |   |
+---+   +---+   +---+---+---+   +   +---+   +   +---+---+---+
|       |       |   |   |   |   |       |   |       |   |   |
+   +   +---+   +   +   +   +---+   +   +---+   +---+   +   +
|   |   |                   |       |   |   |               |
+   +   +   +   +---+---+---+   +---+   +   +   +---+---+---+
|   |       |           |       |   |   |   |               |
+---+   +   +---+   +---+   +---+   +   +   +   +---+   +---+
|       |       |       |   |               |   |            
+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+

 */