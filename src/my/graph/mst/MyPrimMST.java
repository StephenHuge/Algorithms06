package my.graph.mst;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

public class MyPrimMST {

    private int[] edgeTo;
    
    private int[] distTo;
    
    private MinPQ<Edge> pq;
    
    private double weight;
    
    public MyPrimMST(EdgeWeightedGraph g) {
        pq = new MinPQ<>();
        edgeTo = new int[g.V()];
        distTo = new int[g.V()];
        weight = 0;
    }

    public Iterable<Edge> edges() {
        return null;
    }

    public double weight() {
        return -1.0;
    }
    public static void main(String[] args) {
        In in = new In(args[0]);
        
        EdgeWeightedGraph g = new EdgeWeightedGraph(in);
        MyPrimMST prim = new MyPrimMST(g);
        
        for (Edge e : prim.edges())
            StdOut.println(e);
        StdOut.print(String.format("Weight : %f", prim.weight()));
    }
}
