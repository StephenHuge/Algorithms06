import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Outcast {
    private WordNet wordnet;  
    // constructor takes a WordNet object  
    public Outcast(WordNet wordnet){  
        this.wordnet = wordnet;  
    }  
  
    // given an array of WordNet nouns, return an outcast  
    public String outcast(String[] nouns){  
        int[] distance = new int[nouns.length];  
        for (int i=0; i<nouns.length; i++){  
            for (int j=i; j<nouns.length; j++){  
                int dist = wordnet.distance(nouns[i], nouns[j]);  
                distance[i] += dist;  
                if (i != j){  
                    distance[j] += dist;  
                }  
            }  
        }  
        int maxDistance = 0;  
        int maxIndex = 0;  
        for (int i=0; i<distance.length; i++){  
            if (distance[i] > maxDistance){  
                maxDistance = distance[i];  
                maxIndex = i;  
            }  
        }  
        return nouns[maxIndex];  
    }  
   /* public Outcast(WordNet wordnet)         // constructor takes a WordNet object
    {}
    public String outcast(String[] nouns)   // given an array of WordNet nouns, return an outcast
    {
        return null;
    }*/
    public static void main(String[] args)  // see test client below
    {
        WordNet wordnet = new WordNet(args[0], args[1]);
        Outcast outcast = new Outcast(wordnet);
        for (int t = 2; t < args.length; t++) {
            In in = new In(args[t]);
            String[] nouns = in.readAllStrings();
            StdOut.println(args[t] + ": " + outcast.outcast(nouns));
        }
    }
}
