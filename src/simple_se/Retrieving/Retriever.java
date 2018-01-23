package simple_se.Retrieving;

import simple_se.Dictionary.Posting.Posting;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Retriever {

    ArrayList<Posting> postings = new ArrayList<>();

    public Retriever(ArrayList<Posting> postings){
        this.postings = postings;
        sort();
    }

    public List<Integer> getLocations (){
        List<Integer> locations = new ArrayList<>();
        this.postings.forEach(posting -> locations.add(posting.getdocumentId()));
        return locations;
    }

    private void sort() {
         this.postings.sort(Comparator.<Posting>reverseOrder());
    }
}
