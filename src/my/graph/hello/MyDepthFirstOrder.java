package my.graph.hello;


import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

public class MyDepthFirstOrder {

    private boolean[] marked;

    //    private int[] edgeTo;

    private Queue<Integer> pre;

    private Queue<Integer> post;

    private Stack<Integer> reversePost;

    public MyDepthFirstOrder(MyDigraph g) {
        marked = new boolean[g.V()];
        //        edgeTo = new int[g.V()];  // no need to use that

        pre = new Queue<>();
        post = new Queue<>();
        reversePost = new Stack<>();

        for (int i = 0; i < g.V(); i++) {
            if (!marked[i]) {
                dfs(g, i);
            }
        }
    }

    private void dfs(MyDigraph g, int v) {
        pre.enqueue(v);
        marked[v] = true;
        for (int w : g.adj(v)) {
            if (!marked[w]) {
                //                edgeTo[w] = v;
                dfs(g, w);
            }
        }
        post.enqueue(v);
        reversePost.push(v);
    }

    public Iterable<Integer> pre() {        // add vertex to queue before recursion
        return pre;
    }
    public Iterable<Integer> post() {       // add vertex to queue after recursion
        return post;
    }
    public Iterable<Integer> reversePost() {    // add vertex to stack after recursion
        return reversePost;
    }
}
