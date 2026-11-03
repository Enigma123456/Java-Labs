package lab5.task1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class MaxWordLineFinder {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть шлях до текстового файлу (наприклад, test.txt): ");
        String filePath = scanner.nextLine();

        findAndPrintMaxWordLine(filePath);
    }

    public static void findAndPrintMaxWordLine(String filePath) {
        String maxWordsLine = null;
        int maxWordsCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                String trimmedLine = currentLine.trim();

                if (trimmedLine.isEmpty()) {
                    continue; // Порожні рядки
                }

                String[] words = trimmedLine.split("\\s+");
                int currentWordsCount = words.length;

                if (currentWordsCount > maxWordsCount) {
                    maxWordsCount = currentWordsCount;
                    maxWordsLine = currentLine;
                }
            }

            if (maxWordsLine != null) {
                System.out.println("\n--- Результат ---");
                System.out.println("Рядок з максимальною кількістю слів (" + maxWordsCount + " слів):");
                System.out.println(maxWordsLine);
            } else {
                System.out.println("Файл порожній або не містить жодного слова.");
            }

        } catch (IOException e) {
            System.out.println("Помилка роботи з файлом: " + e.getMessage());
        }
    }
}
