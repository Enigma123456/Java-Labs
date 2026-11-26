package lab7;

import java.util.Arrays;
import java.util.function.Predicate;

public class WordTasks {
    private static final Predicate<String> HAS_UNIQUE_CHARS =
            word -> word.codePoints().distinct().count()
                    == word.codePointCount(0, word.length());

    public static String[] findUniqueCharWords(String line) {
        if (line == null || line.isBlank()) {
            return new String[0];
        }
        return Arrays.stream(line.trim().split("\\s+"))
                .filter(HAS_UNIQUE_CHARS)
                .toArray(String[]::new);
    }
}
