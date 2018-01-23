package simple_se;

import simple_se.Dictionary.Indexer;
import simple_se.Dictionary.Grouper;
import simple_se.Retrieving.Retriever;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * The class is a menu-system.
 *
 */
class Menu {
    private final Scanner keyboard = new Scanner(System.in);
    HashMap<Integer,String> fileNameMap = new HashMap<>();

    public void run(){
        int command;

        Grouper dictionary = processFiles();
        if(dictionary!=null){
            do {
                command = chooseCommand();
                switch(command){
                    case 10:
                        System.out.println("Bye!");
                        break;
                    case 1:
                        System.out.println("Type your search term:");
                        String term = keyboard.next();
                        if(dictionary.getInvertedIndex().containsKey(term)){
                            System.out.println("No match found for the term: " + term);
                        }
                        else {
                            System.out.println(term + " appeared in these places:");
                            Retriever retriever = new Retriever(dictionary.getInvertedIndex().get(term));
                            retriever.getLocations().forEach(docId -> System.out.println(fileNameMap.get(docId)));
                        }
                        break;
                    default:
                        System.out.println("The command number " + command + " is unknown.");
                }

            } while (command != 10);
        }
        else{
            System.out.println("An Error has occurred! Please try later :)");
        }
    }

    private int chooseCommand() {
        System.out.println("");
        System.out.println("1. Search for a word");
        System.out.println("10. Quit the program.");

        System.out.print("Your choice: ");
        return keyboard.nextInt();
    }

    private Grouper processFiles(){
        try {
            List<File> files = Files.walk(Paths.get("./Documents"))
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .collect(Collectors.toList());
            Indexer[] indexers = new Indexer[files.size()];
            for(int i=0; i<files.size(); i++){
                indexers[i] = new Indexer(files.get(i).getPath());
                fileNameMap.put(indexers[i].getDocId(),files.get(i).getName());
            }
            return new Grouper(indexers);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
