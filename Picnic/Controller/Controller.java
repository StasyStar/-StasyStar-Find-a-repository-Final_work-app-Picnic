package Controller;

import java.util.Map;

import Model.Repository;

public class Controller {
    private final Repository repository;

    public Controller(Repository repository) {
        this.repository = repository;
    }

    public int countTotal() {
        return repository.countTotal();
    }

    public String findLongestWord() {
        return repository.findLongestWord();
    }

    public Map<String, Integer> countWords() {
        return repository.countWords();
    }
}


