package my.graph.hello;

public class MyDepthFirstSearch {
    
    private boolean[] marked;
    
//    private int count;
    
    public MyDepthFirstSearch(MyGraph g, int v) {
        marked = new boolean[g.V()];
        dfs(g, v);
    }
    
    public void dfs(MyGraph g, int v) {
        marked[v] = true;
        for (int adj : g.adj(v)) {
            if (!marked(adj))   dfs(g, adj); // get unvisited vertex
        }
    }
    
    public boolean marked(int v) {
        return marked[v];
    }
//    public int count() {
//        return count;
//    }
}
