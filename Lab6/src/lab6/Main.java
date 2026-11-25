package lab6;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Translator translator = new Translator();
        fillDefaultWords(translator);

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    addWordFromKeyboard(translator, scanner);
                    break;
                case "2":
                    translatePhraseFromKeyboard(translator, scanner);
                    break;
                case "0":
                    running = false;
                    System.out.println("Роботу завершено.");
                    break;
                default:
                    System.out.println("Невідомий пункт меню, спробуйте ще раз.");
            }
        }
        scanner.close();
    }

    // Початкове наповнення словника
    private static void fillDefaultWords(Translator translator) {
        translator.addWord("hello", "привіт");
        translator.addWord("world", "світ");
        translator.addWord("i", "я");
        translator.addWord("love", "люблю");
        translator.addWord("java", "джава");
        translator.addWord("good", "гарний");
        translator.addWord("morning", "ранок");
        translator.addWord("my", "мій");
        translator.addWord("name", "ім'я");
        translator.addWord("is", "є");
        translator.addWord("book", "книга");
        translator.addWord("cat", "кіт");
        translator.addWord("dog", "собака");
        translator.addWord("big", "великий");
        translator.addWord("house", "будинок");
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("===== Англо-український перекладач =====");
        System.out.println("1 - Додати слово до словника");
        System.out.println("2 - Перекласти фразу");
        System.out.println("0 - Вихід");
        System.out.print("Ваш вибір: ");
    }

    // Введення нової пари слів з клавіатури
    private static void addWordFromKeyboard(Translator translator, Scanner scanner) {
        System.out.print("Введіть слово англійською: ");
        String english = scanner.nextLine();

        System.out.print("Введіть переклад українською: ");
        String ukrainian = scanner.nextLine();

        if (translator.addWord(english, ukrainian)) {
            System.out.println("Слово додано. Слів у словнику: " + translator.size());
        } else {
            System.out.println("Помилка: слова не можуть бути порожніми.");
        }
    }

    // Введення фрази та виведення її перекладу
    private static void translatePhraseFromKeyboard(Translator translator, Scanner scanner) {
        System.out.print("Введіть фразу англійською: ");
        String phrase = scanner.nextLine();

        String result = translator.translate(phrase);
        if (result.isEmpty()) {
            System.out.println("Ви не ввели фразу.");
        } else {
            System.out.println("Переклад: " + result);
        }
    }
}
