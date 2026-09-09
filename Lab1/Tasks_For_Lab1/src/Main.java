import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть фразу: ");
        String text = scanner.nextLine();

        String[] words = text.split("[\\s\\d\\p{Punct}&&[^'’`]]+");

        String[] result = Arrays.stream(words)
                .filter(word -> !word.isEmpty())
                .filter(word -> {
                    String lower = word.toLowerCase();
                    return lower.length() == lower.chars().distinct().count();
                })
                .toArray(String[]::new);

        if (result.length == 0) {
            System.out.println("Немає слів які складаються тільки з унікальних символів");
        } else {
            System.out.println("Слова з унікальними символами: " + Arrays.toString(result));
        }

        scanner.close();
    }
}