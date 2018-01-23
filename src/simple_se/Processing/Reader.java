package simple_se.Processing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Reader {
    private String path;

    public Reader(String path) {
        this.path = path;
    }

    public String readFile() throws IOException {
        FileReader fileReader = new FileReader(path);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        int numOfLines = getFileLineCount();
        String[] data = new String[numOfLines];

        for (int i = 0; i < numOfLines; i++) {
            data[i] = bufferedReader.readLine();
        }

        bufferedReader.close();
        return convertStringArrayToString(data);
    }

    private int getFileLineCount() throws IOException {
        FileReader fileReader = new FileReader(path);
        BufferedReader bf = new BufferedReader(fileReader);

        String line;
        int numberOfLines = 0;

        while ((line = bf.readLine()) != null) {
            numberOfLines++;
        }

        bf.close();
        return numberOfLines;
    }

    private String convertStringArrayToString(String[] array) {
        return String.join(" ", array);
    }
}
