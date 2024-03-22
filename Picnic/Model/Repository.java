package Model;

import java.util.Map;

public interface Repository {
    int countTotal();
    String findLongestWord();
    Map<String, Integer> countWords();
}
