import java.util.TreeMap;

import edu.princeton.cs.algs4.Digraph;

public class SAP {
    private static class Dis implements Comparable<Dis>{
        int a;
        int b;

        public Dis (int ma, int mb) {
            if (ma > mb) {
                int t = ma;
                ma = mb;
                mb = t;
            }
        }
        @Override
        public int compareTo(Dis d) {
            if (a < d.a)    return -1;
            if (a > d.a)    return 1;
            else 
                return b < d.b ? -1 : (b > d.b ? 1 : 0);
        }

    }
    private static class Ans {
        int shorest;
        int ancestor;
        public Ans(int s, int a) {
            shorest = s;
            ancestor = a;
        }
    }

    private Digraph g;

    private TreeMap<Dis, Ans> answer; 
    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G)
    {
        this.g = G;
        answer = new TreeMap<>();
    }
    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w)
    {
        validate(v);
        validate(w);

        int[] ans = shortest(v, w);
        return ans[0];
    }
    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w)
    {
        validate(v);
        validate(w);

        int[] ans = shortest(v, w);
        return ans[1];
    }
    private void validate(int v) {
        if (v < 0 || v > g.V()) throw new java.lang.IllegalArgumentException();
    }
    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w)
    {
        validate(v);
        validate(w);

        for (int t : v) validate(t);
        for (int t : w) validate(t);
        int[] ans = shortest(v, w);
        return ans[0];
    }
    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w)
    {
        validate(v);
        validate(w);
        for (int t : v) validate(t);
        for (int t : w) validate(t);
        int[] ans = shortest(v, w);
        return ans[1];
    }

    private void validate(Iterable<Integer> w) {
        if (w == null) throw new java.lang.IllegalArgumentException();
    }
    private int[] shortest(int v, int w){
        Dis d = new Dis(v, w);
        if (answer.containsKey(d)) {
            Ans t = answer.get(d);
            return new int[] {t.shorest, t.ancestor};   // use cache
        }

        int[] result = new int[2];  
        DeluxeBFS bfsv = new DeluxeBFS(g, v);  
        DeluxeBFS bfsw = new DeluxeBFS(g, w);  
        boolean[] markedv = bfsv.marked();  // marked array for v
        boolean[] markedw = bfsw.marked();  // marked array for w
        int shorest = Integer.MAX_VALUE;    // shorest path
        int t = Integer.MAX_VALUE;      
        int ancestor = Integer.MAX_VALUE;   // shorest ancestor

        for (int i = 0; i < markedv.length; i++){  
            if (markedv[i] && markedw[i]){  
                t = bfsv.distTo(i) + bfsw.distTo(i);  
                if (t < shorest){  
                    shorest = t;  
                    ancestor = i;  
                }  
            }  
        }  
        if (shorest == Integer.MAX_VALUE){  
            shorest = -1;
            ancestor = -1;
        } 
        result[0] = shorest;  
        result[1] = ancestor;  
        answer.put(d, new Ans(shorest, ancestor));
        return result;            
    }  
    private int[] shortest(Iterable<Integer> v, Iterable<Integer> w){  
        int ancestor = Integer.MAX_VALUE;  
        int shortest = Integer.MAX_VALUE;  
        int[] result = new int[2];  
        for (int vNode : v){  
            for (int wNode : w){  
                int[] t = shortest(vNode, wNode);  
                if (t[0] != -1 && t[0] < shortest){  
                    shortest = t[0];  
                    ancestor = t[1];  
                }  
            }  
        }  
        if (shortest == Integer.MAX_VALUE){  
            result[0] = -1;  
            result[1] = -1;  
            return result;  
        }  
        result[0] = shortest;  
        result[1] = ancestor;  
        return result;  
    }  
    // do unit testing of this class
    public static void main(String[] args)
    {}
}
