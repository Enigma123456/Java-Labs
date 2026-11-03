package lab5;

import lab5.controller.BookController;
import lab5.view.ConsoleView;

public class Main {
    public static void main(String[] args) {
        ConsoleView view = new ConsoleView();
        BookController controller = new BookController(view);

        controller.run();
    }
}
