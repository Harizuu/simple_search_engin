package simple_se.Dictionary;


import simple_se.Dictionary.Posting.Posting;
import simple_se.Processing.Tokenizer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeMap;

/**
 * Mapping terms with their corresponding posting
 */

public class Indexer  implements Comparable<Indexer> {
    private static int id = 0;
    private int docId;
    private TreeMap<String, Posting> index = new TreeMap<>();
    private Tokenizer tokenizer;

    public Indexer(String file_path) throws IOException {
        this.docId = id++;
        this.tokenizer = new Tokenizer(file_path);
        tokensMapping();
    }

    public TreeMap<String, Posting> getIndex() {
        return index;
    }

    public int getDocId() {
        return docId;
    }

    private void tokensMapping() {
        ArrayList<String> tokens = tokenizer.getTokens();
        for (String token : tokens) {
            if (index.containsKey(token)) {
                index.get(token).incrementfreq();
            } else {
                index.put(token, new Posting(docId, 1));
            }
        }
    }

    @Override
    public int compareTo(Indexer indexer) {
        return Comparator.comparing(Indexer::getDocId).compare(this,indexer);
    }
}