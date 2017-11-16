package my.graph.hello;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class MyDepthFirstPaths {
    private boolean[] marked;
    
    private int[] edgeTo;
    
    private final int s; // root 
    
    public MyDepthFirstPaths(MyGraph g, int v) {
        marked = new boolean[g.V()];
        edgeTo = new int[g.V()];
        this.s = v;
        for (int i = 0; i < edgeTo.length; i++) {
            edgeTo[i] = i;
        }
        dfs(g, v);
    }
    public MyDepthFirstPaths(MyDigraph g, int v) {
        marked = new boolean[g.V()];
        edgeTo = new int[g.V()];
        this.s = v;
        for (int i = 0; i < edgeTo.length; i++) {
            edgeTo[i] = i;
        }
        dfs(g, v);
    }
    private void dfs(MyDigraph g, int v) {
        marked[v] = true;
        for (int adj : g.adj(v)) {
            if (!marked[adj]) {
                edgeTo[adj] = v;
                dfs(g, adj); // get unvisited vertex
            }
        }
    }
    private void dfs(MyGraph g, int v) {
        marked[v] = true;
        for (int adj : g.adj(v)) {
            if (!marked[adj]) {
                edgeTo[adj] = v;
                dfs(g, adj); // get unvisited vertex
            }
        }
    }
    public boolean hasPathTo(int w) {
        return marked[w];
    }
    
    public Iterable<Integer> pathTo(int w) {
        if (!hasPathTo(w))     return null;
        Stack<Integer> path = new Stack<>();
        for (int x = w; x != s; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(s);
        return path;
    }
    
    public static void main(String[] args) {
        In in = new In(args[0]);
//        MyGraph G = new MyGraph(in);
        MyDigraph G = new MyDigraph(in);
        // StdOut.println(G);
        int s = Integer.parseInt(args[1]);
        MyDepthFirstPaths dfs = new MyDepthFirstPaths(G, s);
        for (int i : dfs.edgeTo)
        StdOut.print(i + " ");
        StdOut.println();
        for (int v = 0; v < G.V(); v++) {
            if (dfs.hasPathTo(v)) {
                StdOut.printf("%d to %d:  ", s, v);
                for (int x : dfs.pathTo(v)) {
                    if (x == s) StdOut.print(x);
                    else        StdOut.print("-" + x);
                }
                StdOut.println();
            }

            else {
                StdOut.printf("%d to %d:  not connected\n", s, v);
            }

        }
    }
}
