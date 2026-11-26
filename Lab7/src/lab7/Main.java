package lab7;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введіть рядок зі словами:");
        String line = scanner.nextLine();

        String[] result = WordTasks.findUniqueCharWords(line);

        System.out.println("Слова з різних символів: " + Arrays.toString(result));
    }
}
