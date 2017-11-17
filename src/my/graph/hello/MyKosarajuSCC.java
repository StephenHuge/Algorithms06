package my.graph.hello;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class MyKosarajuSCC {
    
    private boolean[] marked;
    
    private int[] id;
    
    private int count;
    
    public MyKosarajuSCC(MyDigraph g) {
        marked = new boolean[g.V()];
        id = new int[g.V()];
        count = 0;
        MyDepthFirstOrder order = new MyDepthFirstOrder(g.reverse());
        Iterable<Integer> reversePost = order.reversePost();
        
        for (int w : reversePost) {
            if (!marked[w]) {
                dfs(g, w);
                count++;
            }
        }
    }
    
    private void dfs(MyDigraph g, int v) {
        marked[v] = true;
        id[v] = count;
        for (int w : g.adj(v)) 
            if (!marked[w])   dfs(g, w);
    }

    public boolean stronglyConnected(int v, int w) {
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
        
        MyDigraph g = new MyDigraph(in);
        StdOut.print(g);
        
        MyKosarajuSCC kcc = new MyKosarajuSCC(g);
        int m = kcc.count();
        StdOut.println(m + " components");
        
        Bag<Integer>[] components;
        components = (Bag<Integer>[]) new Bag[m];
        
        for (int i = 0; i < m; i++) 
            components[i] = new Bag<Integer>();
        for (int i = 0; i < g.V(); i++) {
            components[kcc.id(i)].add(i);
        }
        
        for (int i = 0; i < m; i++) {
            for (int v : components[i])
                StdOut.print(v + " ");
            StdOut.println();
        }
        
        for (int i : kcc.id) 
            StdOut.print(i + " ");
    }
}
