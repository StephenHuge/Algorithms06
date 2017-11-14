import java.util.LinkedList;
import java.util.TreeMap;

import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;

public class WordNet {


    /*//    private static final String SPACE = " ";

    private ST<Integer, String> syns;   // noun --> index

    private String[] keys;

    private Digraph nouns;*/

    private static final String COMMA = ",";
    
    private static final String SPACE = " ";
    
    private TreeMap<String, Integer> words;
    
    private LinkedList<String> nouns;       // for storing nouns
    
    private Digraph nets;
    
    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms)
    {
        validate(synsets);
        validate(hypernyms);
        
        words = new TreeMap<>();
        nouns = new LinkedList<>();
        
        In in = new In(synsets);
        while (in.hasNextLine()) {
            String[] s = in.readLine().split(COMMA);
            int id = Integer.parseInt(s[0]);
            String synset = s[1];
            addWords(nouns, synset);    // store nouns
            words.put(synset, id);      // add nouns with id to TreeMap
        }
        
        in = new In(hypernyms);
        while (in.hasNextLine()) {
            String[] s = in.readLine().split(COMMA);
            int from = Integer.parseInt(s[0]);
            for (int i = 1; i < s.length; i++) {
                nets.addEdge(from, Integer.parseInt(s[i])); // add edges to digraph
            }
        }
    }

    private void addWords(LinkedList<String> mNouns, String mSynset) {
        if (mNouns == null || mSynset == null) throw new java.lang.IllegalArgumentException();
        String[] s = mSynset.split(SPACE);
        
        for (String m : s)
            mNouns.add(m);
    }

    // returns all WordNet nouns
    public Iterable<String> nouns() 
    {
        return nouns;
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) 
    {
        validate(word);
        return nouns.contains(word);
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB)     
    {
        validate(nounA, this);
        validate(nounB, this);
        int a = words.get(nounA);
        int b = words.get(nounB);
        
        BreadthFirstDirectedPaths bfda = new BreadthFirstDirectedPaths(nets, a);
        BreadthFirstDirectedPaths bfdb = new BreadthFirstDirectedPaths(nets, b);
        int dis = -1; 
        
        for (int adj : nets.adj(a)) {
            if (bfdb.hasPathTo(adj)) {
                dis = bfdb.distTo(adj);
            }
        }
        for (int adj : nets.adj(b)) {
            if (bfda.hasPathTo(adj)) {
                dis = bfda.distTo(adj);
            }
        }
        return dis;
    }
    private int distance(int a, int  b, 
                         BreadthFirstDirectedPaths bfda, 
                         BreadthFirstDirectedPaths bfdb, int min) {
        for (int adj : nets.adj(a)) {
            if (bfdb.hasPathTo(adj)) {
                int dis = bfdb.distTo(adj);
                min =  dis < min ? dis : min;
            }
        }
        return -1;
    }
    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) 
    {
        validate(nounA, this);
        validate(nounB, this);
        return nounB;
    }
    private void validate(String s) {
        this.validate(s, null);
    } 
    private void validate(String s, WordNet d) 
    {
        if (s == null)           throw new java.lang.IllegalArgumentException();
        if (d == null)           return;
        //        if (!d.syns.contains(s)) throw new java.lang.IllegalArgumentException();
    }
    // do unit testing of this class
    public static void main(String[] args) 
    {
        /*  String synset = "synsets15.txt";
        //        String synset = "synsets50000-subgraph.txt";
        String hyper = "hypernyms15Path.txt";
        synset = "src/" + synset;
        hyper = "src/" + hyper;

        WordNet wn = new WordNet(synset, hyper);

        for (String s : wn.nouns())
//            System.out.println(s + " " + wn.syns.get(s));
        System.out.println("------------------------");

        for (int j = 0; j < wn.nouns.V(); j++) {
            Iterable<Integer> adj = wn.nouns.adj(j);
            System.out.println(j + ": ");
            for (int i : adj)
                System.out.println(" " + i);
        }*/
    }
}

/*        syns = new ST<>();

In in = new In(synsets);

int count = 0;  // count for lines
while(in.hasNextLine()) {
    count++;
    String[] s = in.readLine().split(COMMA);

    int id = Integer.parseInt(s[0]);
    String synset = s[1];
    syns.put(id, synset);     // add nouns with id to ST
}
keys = new String[count];
for (int id : syns.keys()) {
    keys[id] = syns.get(id);
}

nouns = new Digraph(count);
in = new In(hypernyms);
while (in.hasNextLine()) {
    String[] s = in.readLine().split(COMMA);
    int from = Integer.parseInt(s[0]);

    for (int i = 1; i < s.length; i++) 
        nouns.addEdge(from, Integer.parseInt(s[i]));
} */