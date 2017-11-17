package my.graph.hello;

import java.util.Stack;

/**
 * check whether digraph is DAG(directed acyclic graph)
 */
public class MyDirectedCycle {
    
    private boolean[] marked;
    
    private int[] edgeTo;
    
    private Stack<Integer> cycle;   // for storing all vertices in loop, null if none
    
    private boolean[] onStack;
    
    public MyDirectedCycle(MyDigraph g) {
        marked = new boolean[g.V()];
        edgeTo = new int[g.V()];
        
        onStack = new boolean[g.V()];
        for (int i = 0; i < g.V(); i++) {
            if (!marked[i])     dfs(g, i);
        }
    }

    private void dfs(MyDigraph g, int v) {
        onStack[v] = true;
        marked[v] = true;
        for (int w : g.adj(v)) {
            if (this.hasCycle())    return;
            else if (!marked[w]) {   
                edgeTo[w] = v;      // w's father is v
                dfs(g, w);
            } else if (onStack[w]) {
                cycle = new Stack<>();
                for (int x = w; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
            }
        }
        onStack[v] = false;
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }
}
