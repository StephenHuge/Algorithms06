package my.graph.hello;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * use MyDirectedCycle to judge whether there is a loop, and if no
 * use MyDepthFirstOrder to get reverse order as topological order.
 */
public class MyTopological {
    
    private static final String NEWLINE = System.getProperty("line.separator");
    private Iterable<Integer> order;
    
    public MyTopological(MyDigraph g) {
        MyDirectedCycle cyclefinder = new MyDirectedCycle(g);
        if (!cyclefinder.hasCycle()) {
            MyDepthFirstOrder dfs = new MyDepthFirstOrder(g);
            order = dfs.reversePost();
        }
    }
    
    public boolean isDAG() {
        return order != null;
    }
    public Iterable<Integer> order() {
        return order;
    }
    
    public static void main(String[] args) {
        In in = new In(args[0]);
        
        MyDigraph g = new MyDigraph(in);
        StdOut.println(g);
        
        MyTopological top = new MyTopological(g);
        StdOut.print("Topological Order of g is : " + NEWLINE);
        
        for (int w : top.order) {
            StdOut.print(w + " ");
        }
        
    }
}
