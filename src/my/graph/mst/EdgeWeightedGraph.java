package my.graph.mst;

import java.util.ArrayList;

import edu.princeton.cs.algs4.In;

public class EdgeWeightedGraph {
    
    private final int V;
    
    private int E;
    
    private ArrayList<Edge>[] adj;
    
    @SuppressWarnings("unchecked")
    public EdgeWeightedGraph(int V) {
        this.V = V;
        this.E = 0;
        adj = (ArrayList<Edge>[]) new ArrayList[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new ArrayList<Edge>();
        }
    }
    @SuppressWarnings("unchecked")
    public EdgeWeightedGraph(In in) {
        this.V = in.readInt();
        this.E = in.readInt();
        adj = (ArrayList<Edge>[]) new ArrayList[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new ArrayList<Edge>();
        }
        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();
            double weight = in.readDouble();
            Edge e = new Edge(v, w, weight);
            adj[v].add(e);      // there is only one edge, which has two references
            adj[w].add(e);
        }
    }
    
    public int V() {
        return V;
    }
    public int E() {
        return E;
    }
    public void addEdge(Edge e) {
        int v = e.either(), w = e.other(v);
        adj[v].add(e);
        adj[w].add(e);
        E++;
    }
    
    public Iterable<Edge> adj(int v) {
        return adj[v];
    }
    public Iterable<Edge> edges() {
        ArrayList<Edge> edges = new ArrayList<>();
        for (int i = 0; i < E(); i++) {
            for (Edge e : this.adj(i))
                if (e.other(i) > i)     edges.add(e);   // ignore self loop 
        }
        return edges;
    }
    
    public String toString() {
        // TODO
        return null;
    }
    
}
