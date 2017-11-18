package my.graph.mst;

import edu.princeton.cs.algs4.StdOut;

public class Edge implements Comparable<Edge> {
    
    private final int v;
    
    private final int w;
    
    private final double weight;

    public Edge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }
    
    public double weight() {
        return weight;
    }
    
    public int either() {
        return v;
    }
    
    public int other(int vertex) {
        if      (vertex == v)        return w;
        else if (vertex == w)        return v;
        else throw new RuntimeException("Inconsistent edge");
    }
    
    @Override
    public int compareTo(Edge that) {
        return (weight() < that.weight()) ? -1 : ((weight() > that.weight()) ? 1 : 0);  
    }
    
    public String toString() {
        return String.format("%d-%d %.2f", v, w, weight);
    }

    public static void main(String[] args) {
        Edge[] edges = new Edge[3];
        edges[0] = new Edge(1, 2, 0.123);
        edges[1] = new Edge(3, 2, 0.3);
        edges[2] = new Edge(2, 2, 0.3);
        for (Edge e : edges)
            StdOut.println(e);
        
        for (int i = 0; i < edges.length; i++) {
            for (int j = 0; j < edges.length; j++) {
                StdOut.println(String.format("%s -- %s : %d", 
                                                edges[i], edges[j],
                                                edges[i].compareTo(edges[j])));
            }
        }
        
    }
}
