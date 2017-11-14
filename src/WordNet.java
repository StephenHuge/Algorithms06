import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;

public class WordNet {

    private static final String COMMA = ",";

    private static final String SPACE = " ";

    private ST<String, Integer> syns;   // noun --> index

    private String[] keys;

    private Graph nouns;

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms)
    {
        validate(synsets);
        validate(hypernyms);
        syns = new ST<>();

        In in = new In(synsets);
        while(in.hasNextLine()) {
            String[] s = in.readLine().split(COMMA);

            int id = Integer.parseInt(s[0]);
            String[] nouns = s[1].split(SPACE);
            for (int i = 0; i < nouns.length; i++) {
                syns.put(nouns[i], id);     // add nouns with id to ST
            }
        }

    }

    // returns all WordNet nouns
    public Iterable<String> nouns() 
    {
        return syns.keys();
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) 
    {
        validate(word);
        return syns.contains(word);
    }

    // distance between nounA and nounB (defined below)
    // this is a problem of degree of separation, just check the book
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
    // do unit testing of this class
    public static void main(String[] args) 
    {
        //        String synset = "synsets15.txt";
        String synset = "synsets50000-subgraph.txt";
        String hyper = "hypernyms15Path.txt";
        synset = "src/" + synset;
        hyper = "src/" + hyper;

        WordNet wn = new WordNet(synset, hyper);

        System.out.println("------------------------");
        for (String s : wn.nouns())
            System.out.println(s + " " + wn.syns.get(s));
    }
}
