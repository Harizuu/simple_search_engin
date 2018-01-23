package simple_se.Dictionary.Posting;

import java.util.Comparator;

/**
 * Posting consists of the document id and the frequency of the term's occurrence in this document
 */
public class Posting implements Comparable<Posting> {

private final int documentId;
private int freq;

public Posting(int documentId, int freq) {
        this.documentId = documentId;
        this.freq = freq;
        }

public int getdocumentId() {
        return documentId;
        }

public int getfreq() {
        return freq;
        }

public void incrementfreq() {
        freq++;
        }

@Override
public int compareTo(Posting posting) {
        return Comparator.comparing(Posting::getfreq).reversed()
                .thenComparing(Posting::getdocumentId).reversed()
                .compare(this, posting);
        }

@Override
public String toString() {
        return "(documentId: " + getdocumentId() + ", freq: " + getfreq() + ")";
        }
}
