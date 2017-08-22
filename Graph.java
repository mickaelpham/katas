import java.util.*;

public class Graph {
    private HashMap<Integer, Node> nodeLookup = new HashMap<>();

    private static class Node {
        int id;
        LinkedList<Node> adjacent = new LinkedList<>();

        public Node(int id) {
            this.id = id;
        }
    }

    // node lookup + initialization if null
    private Node getNode(int id) {
        if (!nodeLookup.containsKey(id)) {
            nodeLookup.put(id, new Node(id));
        }
        return nodeLookup.get(id);
    }

    // undirected graph -> add to both adjacent lists
    public void addEdge(int u, int v) {
        Node first = getNode(u);
        Node second = getNode(v);
        first.adjacent.add(second);
        second.adjacent.add(first);
    }

    public boolean hasPathDFS(int source, int destination) {
        Node s = getNode(source);
        Node d = getNode(destination);
        HashSet<Integer> visited = new HashSet<>();
        return hasPathDFS(s, d, visited);
    }

    private boolean hasPathDFS(Node source, Node destination, HashSet<Integer> visited) {
        // We are arrived
        if (source == destination) {
            return true;
        }

        // Node already visited check
        if (visited.contains(source.id)) {
            return false;
        }
        visited.add(source.id);

        // DFS
        for (Node x : source.adjacent) {
            if (hasPathDFS(x, destination, visited)) {
                return true;
            }
        }

        return false;
    }

    public boolean hasPathBFS(int source, int destination) {
        Node s = getNode(source);
        Node d = getNode(destination);

        Queue<Node> nodesToVisit = new LinkedList<>();
        HashSet<Integer> visited = new HashSet<>();

        nodesToVisit.offer(s);

        // BFS
        while (!nodesToVisit.isEmpty()) {
            Node current = nodesToVisit.poll();

            // at destination?
            if (current == d) {
                return true;
            }

            // visited check
            if (visited.contains(current.id)) {
                continue;
            }
            visited.add(current.id);

            // add the adjacent nodes to the queue
            for (Node x : current.adjacent) {
                nodesToVisit.add(x);
            }
        }

        return false;
    }

    // shortest path length using BFS
    public int shortestPath(int source, int destination) {
        Node s = getNode(source);
        Node d = getNode(destination);

        Queue<Node> nodesToVisit = new LinkedList<>();
        HashSet<Integer> visited = new HashSet<>();
        HashMap<Integer, Integer> distances = new HashMap<>();

        distances.put(s.id, 0);
        nodesToVisit.offer(s);

        // BFS
        while (!nodesToVisit.isEmpty()) {
            Node current = nodesToVisit.poll();
            int distance = distances.get(current.id);

            if (current == d) {
                return distance;
            }

            // visited check
            if (visited.contains(current.id)) {
                continue;
            }
            visited.add(current.id);

            // add the adjacent nodes
            for (Node x : current.adjacent) {
                nodesToVisit.add(x);
                // compute the distance if not already present
                if (!distances.containsKey(x.id)) {
                    distances.put(x.id, distance + 1);
                }
            }
        }
        // No paths found
        return -1;
    }

    // Shortest path to ALL adjacent nodes
    public HashMap<Integer, Integer> shortestPath(int sourceId) {
        Node source = getNode(sourceId);

        Queue<Node> nodesToVisit = new LinkedList<>();
        HashSet<Integer> visited = new HashSet<>();
        HashMap<Integer, Integer> distances = new HashMap<>();

        distances.put(source.id, 0);
        nodesToVisit.offer(source);

        // BFS
        while (!nodesToVisit.isEmpty()) {
            Node current = nodesToVisit.poll();
            int distance = distances.get(current.id);

            // visited check
            if (visited.contains(current.id)) {
                continue;
            }
            visited.add(current.id);

            // add the adjacent nodes
            for (Node x : current.adjacent) {
                nodesToVisit.add(x);
                // compute the distance if not already present
                if (!distances.containsKey(x.id)) {
                    distances.put(x.id, distance + 1);
                }
            }
        }

        return distances;
    }

    // MAIN
    public static void main(String[] args) {
        Graph g = new Graph();
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        g.addEdge(3, 4);
        g.addEdge(4, 5);
        g.addEdge(6, 7);
        g.addEdge(7, 8);

        System.out.println("--- Depth-First Search:");
        System.out.println("1 -> 5: " + g.hasPathDFS(1, 5));
        System.out.println("4 -> 2: " + g.hasPathDFS(4, 2));
        System.out.println("2 -> 8: " + g.hasPathDFS(2, 8));
        System.out.println("8 -> 7: " + g.hasPathDFS(8, 7));

        System.out.println("--- Breadth-First Search:");
        System.out.println("1 -> 5: " + g.hasPathBFS(1, 5));
        System.out.println("4 -> 2: " + g.hasPathBFS(4, 2));
        System.out.println("2 -> 8: " + g.hasPathBFS(2, 8));
        System.out.println("8 -> 7: " + g.hasPathBFS(8, 7));

        System.out.println("--- Shortest path:");
        System.out.println("1 -> 1: " + g.shortestPath(1, 1));
        System.out.println("1 -> 5: " + g.shortestPath(1, 5));
        System.out.println("4 -> 2: " + g.shortestPath(4, 2));
        System.out.println("2 -> 8: " + g.shortestPath(2, 8));
        System.out.println("8 -> 7: " + g.shortestPath(8, 7));

        System.out.println("--- Shortest path (memoized):");
        HashMap<Integer, Integer> distances = g.shortestPath(2);
        for (int i = 1; i <= 8; i++) {
            if (distances.containsKey(i)) {
                System.out.println("2 -> " + i + " = " + distances.get(i));
            } else {
                System.out.println("2 -> " + i + " = -1");
            }
        }
    }

}
