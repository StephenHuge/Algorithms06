package my.graph.hello;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/*
 * connected components
 */
public class MyCC {
    
    private boolean[] marked;
    
    private int[] id;
    
    private int count;
    
    public MyCC(MyGraph g) {
        marked = new boolean[g.V()];
        id = new int[g.V()];
        count = 0;
        for (int i = 0; i < marked.length; i++) {
            if (!marked[i]) {
                dfs(g, i);
                count++;
//                System.out.println(String.format("i : %d - count : %d", i, count));
            } 
        }
    }
    private void dfs(MyGraph g, int v) {
        marked[v] = true;
        id[v] = count;
        for (int w : g.adj(v)) {
            if (!marked[w]) {
                dfs(g, w);
            }
        }
    }
    public boolean connected(int v, int w) {
        return id[v] == id[w]; 
    }
    public int count() {
        return count;
    }
    public int id(int v) {
        return id[v];
    }
    
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        In in = new In(args[0]);
        
        MyGraph g = new MyGraph(in);
        StdOut.print(g);
        
        MyCC cc = new MyCC(g);
        int m = cc.count();
        StdOut.println(m + " components");
        
        Bag<Integer>[] components;
        components = (Bag<Integer>[]) new Bag[m];
        
        for (int i = 0; i < m; i++) 
            components[i] = new Bag<Integer>();
        for (int i = 0; i < g.V(); i++) {
            components[cc.id(i)].add(i);
        }
        
        for (int i = 0; i < m; i++) {
            for (int v : components[i])
                StdOut.print(v + " ");
            StdOut.println();
        }
        
        for (int i : cc.id) 
            StdOut.print(i + " ");
    }
}
