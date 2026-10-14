package lab3.view;

import lab3.model.Book;
import java.util.Scanner;

public class ConsoleView {
    private Scanner scanner = new Scanner(System.in);

    public void printMenu() {
        System.out.println("\n=== МЕНЮ ===");
        System.out.println("1. Вивести весь список книг");
        System.out.println("2. Отримати список книг зазначеного автора");
        System.out.println("3. Отримати список книг зазначеного видавництва");
        System.out.println("4. Отримати список книг, виданих пізніше вказаного року");
        System.out.println("5. Відсортувати книги за видавництвом");
        System.out.println("0. Вихід");
        System.out.print("Оберіть дію: ");
    }

    public int getUserChoice() {
        while (!scanner.hasNextInt()) {
            System.out.println("Помилка: введіть число.");
            scanner.next();
        }
        return scanner.nextInt();
    }

    public String getInputString(String prompt) {
        System.out.print(prompt);
        Scanner strScanner = new Scanner(System.in);
        return strScanner.nextLine();
    }

    public int getInputInt(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("Помилка: введіть число.");
            scanner.next();
        }
        return scanner.nextInt();
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printBooks(Book[] books, int count) {
        if (count == 0) {
            System.out.println("Книг за вашим запитом не знайдено.");
            return;
        }
        for (int i = 0; i < count; i++) {
            System.out.println(books[i]);
        }
    }
}
