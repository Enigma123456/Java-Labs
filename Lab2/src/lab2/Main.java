package lab2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Список для зберігання всіх записів журналу
        List<JournalRecord> journal = new ArrayList<>();

        System.out.println("=== Журнал куратора ===");

        while (true) {
            System.out.println("\nОберіть дію:");
            System.out.println("1 - Додати новий запис");
            System.out.println("2 - Показати всі записи");
            System.out.println("0 - Вийти");
            System.out.print("Ваш вибір: ");

            String choice = scanner.nextLine().trim();

            if (choice.equals("0")) {
                System.out.println("Програму завершено.");
                break;
            } else if (choice.equals("1")) {
                addRecord(scanner, journal);
            } else if (choice.equals("2")) {
                showRecords(journal);
            } else {
                System.out.println("Невірний вибір. Спробуйте ще раз.");
            }
        }
        scanner.close();
    }

    private static void addRecord(Scanner scanner, List<JournalRecord> journal) {
        System.out.println("\n--- Введення даних студента ---");

        String lastName = getValidInput(scanner, "Прізвище: ", "^[А-ЯІЇЄҐа-яіїєґA-Za-z'\\-]+$",
                "Допускаються лише літери, апостроф та дефіс.");

        String firstName = getValidInput(scanner, "Ім'я: ", "^[А-ЯІЇЄҐа-яіїєґA-Za-z'\\-]+$",
                "Допускаються лише літери, апостроф та дефіс.");

        String birthDate = getValidInput(scanner, "Дата народження (ДД.ММ.РРРР): ", "^\\d{2}\\.\\d{2}\\.\\d{4}$",
                "Формат має бути строго ДД.ММ.РРРР (наприклад, 15.05.2005).");

        String phoneNumber = getValidInput(scanner, "Телефон (10 цифр, наприклад 0991234567): ", "^\\d{10}$",
                "Телефон має містити рівно 10 цифр без пробілів.");

        String street = getValidInput(scanner, "Вулиця: ", "^.+$",
                "Це поле не може бути порожнім.");

        String building = getValidInput(scanner, "Будинок: ", "^[0-9А-ЯІЇЄҐа-яіїєґA-Za-z\\-\\/]+$",
                "Некоректний формат номеру будинку.");

        String apartment = getValidInput(scanner, "Квартира: ", "^\\d+$",
                "Номер квартири має містити лише цифри.");

        JournalRecord record = new JournalRecord(lastName, firstName, birthDate, phoneNumber, street, building, apartment);
        journal.add(record);
        System.out.println("Запис успішно додано!");
    }

    private static void showRecords(List<JournalRecord> journal) {
        System.out.println("\n=== Всі записи журналу ===");
        if (journal.isEmpty()) {
            System.out.println("Журнал наразі порожній.");
        } else {
            for (int i = 0; i < journal.size(); i++) {
                System.out.println((i + 1) + ". " + journal.get(i).toString());
            }
        }
    }

    private static String getValidInput(Scanner scanner, String prompt, String regex, String errorMessage) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (input.matches(regex)) {
                return input;
            } else {
                System.out.println("Помилка: " + errorMessage);
            }
        }
    }
}