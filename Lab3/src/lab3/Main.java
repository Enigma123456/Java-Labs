package lab3;

import lab3.controller.BookController;
import lab3.view.ConsoleView;

public class Main {
    public static void main(String[] args) {
        ConsoleView view = new ConsoleView();
        BookController controller = new BookController(view);

        controller.run();
    }
}
