package lab5.task4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlTagCounter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть URL для аналізу (наприклад, https://example.com): ");
        String urlString = scanner.nextLine();

        Map<String, Integer> tagFrequencies = countTags(urlString);

        if (tagFrequencies != null && !tagFrequencies.isEmpty()) {
            printLexicographicalOrder(tagFrequencies);
            printByFrequencyOrder(tagFrequencies);
        } else {
            System.out.println("Тегів не знайдено або не вдалося завантажити сторінку.");
        }
    }

    public static Map<String, Integer> countTags(String urlString) {
        Map<String, Integer> tagMap = new TreeMap<>();
        String regex = "</?([a-zA-Z][a-zA-Z0-9]*)\\b[^>]*>";
        Pattern pattern = Pattern.compile(regex);

        try {
            URL url = new URL(urlString);
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    Matcher matcher = pattern.matcher(line);
                    while (matcher.find()) {
                        String tagName = matcher.group(1).toLowerCase();
                        tagMap.put(tagName, tagMap.getOrDefault(tagName, 0) + 1);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Помилка підключення або зчитування URL: " + e.getMessage());
            return null;
        }

        return tagMap;
    }

    private static void printLexicographicalOrder(Map<String, Integer> map) {
        System.out.println("\n=== a. Теги в лексикографічному порядку ===");
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println("<" + entry.getKey() + "> : " + entry.getValue());
        }
    }

    private static void printByFrequencyOrder(Map<String, Integer> map) {
        System.out.println("\n=== b. Теги в порядку зростання частоти появи ===");

        List<Map.Entry<String, Integer> > list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue());

        for (Map.Entry<String, Integer> entry : list) {
            System.out.println("<" + entry.getKey() + "> : " + entry.getValue());
        }
    }
}
