package View.impl;

import java.util.Map;
import java.util.Scanner;

import Controller.Controller;
import View.Viewable;

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