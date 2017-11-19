package my.graph.mst;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

public class MyLazyPrimMST {
private boolean[] marked;
    
    private Queue<Edge> mst;
    
    private MinPQ<Edge> pq;
    
    private double weight;
    
    public MyLazyPrimMST(EdgeWeightedGraph g) {
        pq = new MinPQ<>();
        marked = new boolean[g.V()];
        mst = new Queue<>();
        weight = 0;
        
        visit(g, 0);
        while (!pq.isEmpty()) {
            Edge min = pq.delMin();
            
            int v = min.either(), w = min.other(v);
            if (marked[v] && marked[w]) continue;
            mst.enqueue(min);
            weight += min.weight();
            if (!marked[v])   visit(g, v);
            if (!marked[w])   visit(g, w);
        }
    }

    public Iterable<Edge> edges() {
        return mst;
    }

    public double weight() {
        return weight;
    }
    // add vertex and adjacent edges to pq
    private void visit(EdgeWeightedGraph g, int v) {    
        marked[v] = true;
        for (Edge e : g.adj(v)) {
            if (!marked[e.other(v)])    pq.insert(e);
        }
    }
    
    public static void main(String[] args) {
        In in = new In(args[0]);
        
        EdgeWeightedGraph g = new EdgeWeightedGraph(in);
        MyLazyPrimMST prim = new MyLazyPrimMST(g);
        
        for (Edge e : prim.edges())
            StdOut.println(e);
        StdOut.print(String.format("Weight : %f", prim.weight()));
    }
}
