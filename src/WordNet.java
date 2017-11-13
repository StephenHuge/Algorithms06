import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.TreeMap;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;

public class WordNet {
    private static final String COMMA = ",";
    
    private static final String SPACE = " ";
    
    private TreeMap<Integer, String[]> synsetss;
    
    private Digraph graphs;
    
    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms)
    {
        validate(synsets);
        validate(hypernyms);
        synsetss = new TreeMap<>();
        
        In in = new In(synsets);
        while(in.hasNextLine()) {
            String[] s = in.readLine().split(COMMA);
            
            int id = Integer.parseInt(s[0]);
            String[] nouns = s[1].split(SPACE);
            
            synsetss.put(id, nouns);
        }
        
        in = new In(hypernyms);
        graphs = new Digraph(synsetss.size());
        
        while(in.hasNextLine()) {
            String[] s = in.readLine().split(COMMA);
            
            int son = Integer.parseInt(s[0]);
            String[] fathers = Arrays.copyOfRange(s, 1, s.length);
            
            for (String t : fathers)
                graphs.addEdge(son, Integer.parseInt(t));
        }
    }

    // returns all WordNet nouns
    public Iterable<String> nouns() 
    {
        Collection<String[]> strs = synsetss.values();
        return null;
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) 
    {
        validate(word);
        return false;
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) 
    {
        validate(nounA);
        validate(nounB);
        return 0;
    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) 
    {
        validate(nounA);
        validate(nounB);
        return nounB;
    }
    private void validate(String s) 
    {
        if (s == null) throw new java.lang.IllegalArgumentException();
    }
    
    private static class Synset {
        int id;
        String[] nouns;
        public Synset(int mId, String... mNouns) {
            this.id =- mId;
            this.nouns = mNouns;
        }
    }
    // do unit testing of this class
    public static void main(String[] args) 
    {}
}
