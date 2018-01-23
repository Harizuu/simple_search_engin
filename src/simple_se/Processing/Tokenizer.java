package simple_se.Processing;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Tokenizing a text file
 */

public class Tokenizer {

    private ArrayList<String> tokens = new ArrayList<String>();

    public Tokenizer(String path) throws IOException {
        String data = new Reader(path).readFile();
        this.tokens = tokenize(data);
    }

    public ArrayList<String> getTokens() {
        return this.tokens;
    }

    public ArrayList<String> tokenize(String data) {
        String[] words = data.split("\\W");
        ArrayList<String> tokens = new ArrayList<>();
        for (String word : words) {
            if (word.length() > 0) {
                String token = word.toLowerCase();
                tokens.add(token);
            }
        }
        return tokens;
    }

}