package my.graph.hello;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class MyBreadthFirstPaths {
    
    private static final int INFINITY = Integer.MAX_VALUE;
    
    private boolean[] marked;

    private int[] edgeTo;
    
    private Queue<Integer> queue;

    private int[] distTo;
    
    private final int s;
    
    public MyBreadthFirstPaths(MyGraph g, int v) {
        marked = new boolean[g.V()];
        edgeTo = new int[g.V()];
        distTo = new int[g.V()];
        for (int i = 0; i < distTo.length; i++) {
            distTo[i] = INFINITY;
        }
        this.s = v;
        queue = new Queue<>();
        bfs(g, v);
    }
    public MyBreadthFirstPaths(MyDigraph g, int v) {
        marked = new boolean[g.V()];
        edgeTo = new int[g.V()];
        distTo = new int[g.V()];
        for (int i = 0; i < distTo.length; i++) {
            distTo[i] = INFINITY;
        }
        this.s = v;
        queue = new Queue<>();
        bfs(g, v);
    }
    private void bfs(MyDigraph g, int v) {
        distTo[v] = 0;
        marked[v] = true;
        queue.enqueue(v);
        while (queue.size() != 0) {
            int w = queue.dequeue();
            for (int adj : g.adj(w)) {
                if (!marked[adj]) {
                    marked[adj] = true;
                    edgeTo[adj] = w;
                    distTo[adj] = distTo[w] + 1;
                    queue.enqueue(adj);
                }
            }
        }
    }
    private void bfs(MyGraph g, int v) {
        distTo[v] = 0;
        queue.enqueue(v);
        while (queue.peek() != null) {
            int w = queue.dequeue();
            for (int adj : g.adj(w)) {
                if (!marked[adj]) {
                    marked[adj] = true;
                    edgeTo[adj] = w;
                    distTo[adj] = distTo[w] + 1;
                    queue.enqueue(adj);
                }
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
    public int distTo(int w) {
        return distTo[w];
    }
    public static void main(String[] args) {
        In in = new In(args[0]);
//        Digraph G = new Digraph(in);
        MyDigraph G = new MyDigraph(in);
        // StdOut.println(G);

        int s = Integer.parseInt(args[1]);
        MyBreadthFirstPaths bfs = new MyBreadthFirstPaths(G, s);

        for (int v = 0; v < G.V(); v++) {
            if (bfs.hasPathTo(v)) {
                StdOut.printf("%d to %d (%d):  ", s, v, bfs.distTo(v));
                for (int x : bfs.pathTo(v)) {
                    if (x == s) StdOut.print(x);
                    else        StdOut.print("->" + x);
                }
                StdOut.println();
            }

            else {
                StdOut.printf("%d to %d (-):  not connected\n", s, v);
            }

        }
    }
}
