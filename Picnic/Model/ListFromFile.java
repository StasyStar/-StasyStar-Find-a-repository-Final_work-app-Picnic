package Model;

import java.util.List;

public class ListFromFile {
    private List<String> listOfWords;

    public ListFromFile(String fileName) {
        listOfWords = InputData.readWordsFromFile(fileName);
    }

    public List<String> getListOfWords() {
        return listOfWords;
    }

    public int getListSize() {
        return listOfWords.size();
    }
}
