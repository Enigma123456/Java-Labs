package lab3.controller;

import lab3.model.Book;
import lab3.model.PublisherComparator;
import lab3.view.ConsoleView;
import java.util.Arrays;

public class BookController {
    private Book[] books;
    private ConsoleView view;

    public BookController(ConsoleView view) {
        this.view = view;
        initData();
    }

    private void initData() {
        books = new Book[10];
        books[0] = new Book("Кобзар", "Шевченко Т.", "А-БА-БА-ГА-ЛА-МА-ГА", 2020, 350, 450.50);
        books[1] = new Book("Тигролови", "Багряний І.", "Фоліо", 2018, 280, 250.00);
        books[2] = new Book("Місто", "Підмогильний В.", "Основи", 2021, 320, 300.00);
        books[3] = new Book("Захар Беркут", "Франко І.", "Фоліо", 2015, 200, 150.00);
        books[4] = new Book("Чорна рада", "Куліш П.", "А-БА-БА-ГА-ЛА-МА-ГА", 2019, 400, 380.00);
        books[5] = new Book("Кайдашева сім'я", "Нечуй-Левицький І.", "Клуб сімейного дозвілля", 2017, 250, 180.00);
        books[6] = new Book("Тіні забутих предків", "Коцюбинський М.", "Основи", 2022, 190, 220.00);
        books[7] = new Book("Лісова пісня", "Українка Л.", "Фоліо", 2020, 150, 140.00);
        books[8] = new Book("Маруся Чурай", "Костенко Л.", "А-БА-БА-ГА-ЛА-МА-ГА", 2023, 310, 400.00);
        books[9] = new Book("Хіба ревуть воли...", "Мирний П.", "Клуб сімейного дозвілля", 2016, 500, 410.00);
    }

    public void run() {
        // Вимога 4: вивести вихідний масив після створення
        view.printMessage("\n=== Початковий масив книг (перевірка створення) ===");
        view.printBooks(books, books.length);

        boolean running = true;
        while (running) {
            view.printMenu();
            int choice = view.getUserChoice();

            switch (choice) {
                case 1:
                    view.printBooks(books, books.length);
                    break;
                case 2:
                    searchByAuthor();
                    break;
                case 3:
                    searchByPublisher();
                    break;
                case 4:
                    searchByYear();
                    break;
                case 5:
                    sortByPublisher();
                    break;
                case 0:
                    running = false;
                    view.printMessage("Роботу програми завершено.");
                    break;
                default:
                    view.printMessage("Невірний вибір. Спробуйте ще раз.");
            }
        }
    }

    private void searchByAuthor() {
        String author = view.getInputString("Введіть автора для пошуку: ");
        Book[] result = new Book[books.length];
        int count = 0;
        for (Book book : books) {
            if (book.getAuthor().toLowerCase().contains(author.toLowerCase())) {
                result[count++] = book;
            }
        }
        view.printBooks(result, count); // Якщо count = 0, View виведе повідомлення
    }

    private void searchByPublisher() {
        String publisher = view.getInputString("Введіть видавництво для пошуку: ");
        Book[] result = new Book[books.length];
        int count = 0;
        for (Book book : books) {
            if (book.getPublisher().toLowerCase().contains(publisher.toLowerCase())) {
                result[count++] = book;
            }
        }
        view.printBooks(result, count);
    }

    private void searchByYear() {
        int year = view.getInputInt("Введіть рік: ");
        Book[] result = new Book[books.length];
        int count = 0;
        for (Book book : books) {
            if (book.getYear() > year) {
                result[count++] = book;
            }
        }
        view.printBooks(result, count);
    }

    private void sortByPublisher() {
        Arrays.sort(books, new PublisherComparator());
        view.printMessage("Масив успішно відсортовано за видавництвами.");
        view.printBooks(books, books.length);
    }
}
