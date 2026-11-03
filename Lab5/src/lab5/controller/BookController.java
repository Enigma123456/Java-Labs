package lab5.controller;

import lab5.model.Book;
import lab5.model.PublisherComparator;
import lab5.service.FileDataManager;
import lab5.view.ConsoleView;

import java.util.Arrays;

public class BookController {
    private Book[] books;
    private ConsoleView view;
    private FileDataManager fileManager;

    public BookController(ConsoleView view) {
        this.view = view;
        this.fileManager = new FileDataManager();
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
                case 6:
                    saveDataToFile();
                    break;
                case 7:
                    loadDataFromFile();
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
            if (book != null && book.getAuthor().toLowerCase().contains(author.toLowerCase())) {
                result[count++] = book;
            }
        }
        view.printBooks(result, count);
    }

    private void searchByPublisher() {
        String publisher = view.getInputString("Введіть видавництво для пошуку: ");
        Book[] result = new Book[books.length];
        int count = 0;
        for (Book book : books) {
            if (book != null && book.getPublisher().toLowerCase().contains(publisher.toLowerCase())) {
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
            if (book != null && book.getYear() > year) {
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

    private void saveDataToFile() {
        String path = view.getInputString("Введіть розташування та ім'я файлу для збереження (наприклад, books.txt): ");
        try {
            fileManager.saveBooksToFile(books, path);
            view.printMessage("Дані успішно збережено у файл: " + path);
        } catch (Exception e) {
            view.printMessage("Помилка під час збереження файлу: " + e.getMessage());
        }
    }

    private void loadDataFromFile() {
        String path = view.getInputString("Введіть розташування та ім'я файлу для завантаження (наприклад, books.txt): ");
        try {
            Book[] loadedBooks = fileManager.loadBooksFromFile(path);
            if (loadedBooks != null) {
                this.books = loadedBooks;
                view.printMessage("Дані успішно завантажено з файлу: " + path);
            }
        } catch (Exception e) {
            view.printMessage("Помилка під час завантаження файлу: " + e.getMessage());
        }
    }
}
