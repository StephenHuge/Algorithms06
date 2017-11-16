package my.graph.hello;

import java.util.ArrayList;
import java.util.Stack;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class MyDigraph {
    
    private static final String NEWLINE = System.getProperty("line.separator");
    
    private final int V;
    
    private int E;
    
    private ArrayList<Integer>[] adjs;
    
    @SuppressWarnings("unchecked")
    public MyDigraph(int v) {
        this.V = v;
        this.E = 0;
        adjs = (ArrayList<Integer>[]) new ArrayList[v];
        for (int i = 0; i < v; i++) {
            adjs[i] = new ArrayList<Integer>();
        }
    }
    @SuppressWarnings("unchecked")
    public MyDigraph(In in) {
        this.V = in.readInt();
        if (V < 0) throw new java.lang.IllegalArgumentException();
        int E = in.readInt();
        if (E < 0) throw new java.lang.IllegalArgumentException();
        
        adjs = (ArrayList<Integer>[]) new ArrayList[this.V()];
        for (int i = 0; i < this.V(); i++) {
            adjs[i] = new ArrayList<Integer>();
        }
       for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();
            addEdge(v, w);
        }
    }
    public MyDigraph(MyGraph g) {
        this(g.V());
        this.E = g.E();
        for (int i = 0; i < g.V(); i++) {
            // reverse to keep adj's order
            Stack<Integer> reverse = new Stack<>();
            for (int adj : g.adj(i)) {
                reverse.push(adj);
            }
            for (int adj : reverse) {
//                this.addEdge(i, adj);
                adjs[i].add(adj);
            }
        }
    }
    public void addEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        adjs[v].add(w);
        E++;
    }
    
    public int V() {
        return V;
    }
    
    public int E() {
        return E;
    }
    
    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        return adjs[v];
    }
    
    public int degree(int v) {
        validateVertex(v);
        return adjs[v].size();
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("This graph has " + V() + " vertices and "+ E() + " edges" + NEWLINE);
        for (int i = 0; i < E(); i++) {
            sb.append(i + " : ");
            for (int adj : adj(i)) {
                sb.append(adj + " ");
            }
            sb.append(NEWLINE);
        }
        return sb.toString();
        
    }
    private void validateVertex(int v) {
        if (v < 0 || v >= V) throw new java.lang.IllegalArgumentException();
    }
    public static void main(String[] args) {
        In in1 = new In(args[0]);
        In in2 = new In(args[0]);
        
        MyDigraph G1 = new MyDigraph(in1);
        Digraph G2 = new Digraph(in2);
        StdOut.println(G1);
        StdOut.println("------------------------");
        StdOut.println(G2);
    }
}
