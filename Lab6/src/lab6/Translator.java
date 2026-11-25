package lab6;

import java.util.HashMap;
import java.util.Map;

public class Translator {
    // Словник: ключ - англійське слово, значення - український переклад
    private final Map<String, String> dictionary = new HashMap<>();

    // Додає пару слів у словник
    public boolean addWord(String english, String ukrainian) {
        if (english == null || ukrainian == null) {
            return false;
        }
        String key = normalize(english);
        String value = ukrainian.trim();

        if (key.isEmpty() || value.isEmpty()) {
            return false;
        }
        dictionary.put(key, value);
        return true;
    }

    // Перекладає фразу слово за словом
    public String translate(String phrase) {
        if (phrase == null || phrase.trim().isEmpty()) {
            return "";
        }

        String[] words = phrase.trim().split("\\s+");
        StringBuilder result = new StringBuilder();

        for (String word : words) {
            String key = normalize(word);
            String translated = dictionary.get(key);

            if (translated != null) {
                result.append(translated);
            } else {
                result.append("[").append(word).append("]");
            }
            result.append(" ");
        }
        return result.toString().trim();
    }

    // Кількість слів у словнику
    public int size() {
        return dictionary.size();
    }

    // Приводить слово до єдиного вигляду: без пробілів, без розділових знаків, нижній регістр
    private String normalize(String word) {
        return word.trim().toLowerCase().replaceAll("[^a-z']", "");
    }
}
