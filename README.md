# Задание 1. Приложение для подсчета слов из текстового файла (JAVA)
## Информация о проекте
Входные данные: txt-файл с большим количеством слов, разделенных пробелами

Исходные данные: 
1. подсчет количества слов в файле
2. вывод самого длинного слова
3. вывод слова и количества его повторений в файле

Данная программа реализована с использованием паттерна MVC (Model, View, Controller) и основных принципов ООП.

## Создание сущности **ListFromFile и геттеров**
```
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
```

## Создание модуля Model
Данный модуль отвечает за логику приложения. В нем имеется интерфейс **Repository** и класс **UserRepositoty** реализующий методы данного интерфейса (countTotal - для вывода количества слов в файле, findLongestWord - для вывода самого длинного слова, countWords - для вывода слова и количества его повторений в файле). И здесь же находится класс **InputData** для считывания текстового файла и преобразования его в List<String> words.

**Класс UserRepositoty с основной логикой программы**
```
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
            
```
**Класс InputData для считывания txt-файла**
```
public class InputData {
    public static List<String> readWordsFromFile(String fileName) {
        List<String> words = new ArrayList<>();
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String word = scanner.next().toLowerCase();
                words.add(word);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File is not found: " + e.getMessage());
        }
        return words;
    }
}
```
## Создание модуля Controller
Данный модуль управляет всеми операциями между Model и View.
```
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
```
## Создание модуля View
Данный модуль создан для взаимодействия с пользователем. В нем имеется интерфейс **Viewable** и класс **UserView** реализующий методы данного интерфейса(run, prompt). run метод предлагает меню с командами для вывода количества слов в файле, вывода самого длинного слова и вывода слова и частоты его повторения, prompt - метод для считывания данных из консоли.
```
public class UserView implements Viewable {
    private final Controller controller;

    public UserView(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void run() {
        int command = -1;
        while (command != 0) {
            System.out.println("\nWelcome to Stasy Program! We have the following commad:\n"
            + "1. Сount the total number of words in the file.\n"
            + "2. Find the longest word in the file.\n"
            + "3. Сount how many times a word appears in the file.\n"
            + "0. Exit program.\n\n");
            command = Integer.parseInt(prompt("Enter a number of the command: "));
            switch (command) {
                case 1:
                    int wordCount = controller.countTotal();
                    System.out.println("The quantity of words in file is: " + wordCount + "\n");
                    break;
                case 2:
                    String longestWord = controller.findLongestWord();
                    System.out.println("The longest word in file is: " + longestWord + "\n");
                    break;
                case 3:
                    Map<String, Integer> wordMap = controller.countWords();
                    for (Map.Entry<String, Integer> entry : wordMap.entrySet()) {
                        System.out.println(entry.getKey() + " - " + entry.getValue());
                    }
                    System.out.println("\n");
                    break;
                case 0:
                    System.out.println("Program is closing.");
                    break;
                default:
                    System.out.println("Incorrect value. Try again.");
            }
        }
    }

    @Override
    public String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine().trim();
    }
}
```
## Класс AppRunner создает все сущности для реализации работы программы 
``` 
public class AppRunner {
    public static void run() {
        String fileName = "/Users/stasy/Documents/Education/GB/Java/finalWork/Picnic/input.txt";
        Repository repository = new UserRepository(fileName);
        Controller controller = new Controller(repository);
        UserView userView = new UserView(controller);
        userView.run();
    }
}
```
## Модуль Main для инициации запуска приложения 
```
public class Main {
    public static void main(String[] args) {
        AppRunner.run();
    }
}
```