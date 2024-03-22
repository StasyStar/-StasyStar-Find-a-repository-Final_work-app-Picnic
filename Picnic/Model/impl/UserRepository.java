package Model.impl;

import java.util.HashMap;
import java.util.Map;

import Model.ListFromFile;
import Model.Repository;


public class UserRepository implements Repository{
    private ListFromFile listFromFile;

    public UserRepository(String fileName) {
        listFromFile = new ListFromFile(fileName);
    }

    @Override
    public int countTotal() {
        return listFromFile.getListSize();
    }

    @Override
    public String findLongestWord() {
        String maxWord = "";
        for (String fruit : listFromFile.getListOfWords()) {
            if (fruit.length() > maxWord.length()) {
                maxWord = fruit;
            }
        }
        return maxWord;
    }

    @Override
    public Map<String, Integer> countWords() {
        HashMap<String, Integer> fruitQuantity = new HashMap<>();
        for (String fruit : listFromFile.getListOfWords()) {
            Integer countFruits = 1;
            if (fruitQuantity.containsKey(fruit.toLowerCase())) {
                fruitQuantity.put(fruit.toLowerCase(), fruitQuantity.get(fruit.toLowerCase()) + countFruits);
            } else {
                fruitQuantity.put(fruit.toLowerCase(), countFruits);
            }
        }
        return fruitQuantity;
    }
    
}
