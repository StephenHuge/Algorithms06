package my.graph.hello;

import edu.princeton.cs.algs4.Queue;

public class MyBreadthFirstSearch {
    private boolean[] marked;

    private Queue<Integer> queue;
    //  private int count;

    public MyBreadthFirstSearch(MyGraph g, int v) {
        marked = new boolean[g.V()];
        queue = new Queue<>();
        bfs(g, v);
    }

    public void bfs(MyGraph g, int v) {
        queue.enqueue(v);
        while (queue.peek() != null) {
            int w = queue.dequeue();
            for (int adj : g.adj(w)) {
                marked[adj] = true;
                queue.enqueue(adj);
            }
        }
    }

    public boolean marked(int v) {
        return false;
    }
    //  public int count() {
    //      return count;
    //  }
}
