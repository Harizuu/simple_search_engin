package simple_se.Dictionary;


import simple_se.Dictionary.Posting.Posting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;

/**
 * Cross-file terms grouping
 */

public class Grouper {
    private TreeMap<String, ArrayList<Posting>> invertedIndex = new TreeMap<>();
    private Indexer[] indexers;

    public Grouper(Indexer[] indexers) {
        groupIndexers();
    }

    public TreeMap<String, ArrayList<Posting>> getInvertedIndex() {
        return invertedIndex;
    }

    private void groupIndexers() {
        for (Indexer indexer : indexers) {
            TreeMap<String, Posting> index = indexer.getIndex();
            index.forEach(this::addToGroup);
        }
    }

    private void addToGroup(String term, Posting posting) {
        if (invertedIndex.containsKey(term)) {
            invertedIndex.get(term).add(posting);
        } else {
            invertedIndex.put(term, new ArrayList<>(Collections.singletonList(posting)));
        }
    }
}