package lab5.task3;

import java.io.*;

public class CipherDemo {

    public static void main(String[] args) {
        String originalFile = "source.txt";
        String encryptedFile = "encrypted.txt";
        String decryptedFile = "decrypted.txt";
        int key = 5;

        createSourceFile(originalFile);

        System.out.println("Починаємо процес шифрування...");
        encryptFile(originalFile, encryptedFile, key);

        System.out.println("Починаємо процес дешифрування...");
        decryptFile(encryptedFile, decryptedFile, key);

        System.out.println("Готово! Перевірте файли в папці проєкту.");
    }

    public static void encryptFile(String inputFile, String outputFile, int key) {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             CipherFilterWriter writer = new CipherFilterWriter(new FileWriter(outputFile), key)) {

            int c;
            while ((c = reader.read()) != -1) {
                writer.write(c);
            }
            System.out.println("Файл успішно зашифровано: " + outputFile);
        } catch (IOException e) {
            System.out.println("Помилка при шифруванні: " + e.getMessage());
        }
    }

    public static void decryptFile(String inputFile, String outputFile, int key) {
        try (
             CipherFilterReader reader = new CipherFilterReader(new FileReader(inputFile), key);
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

            int c;
            while ((c = reader.read()) != -1) {
                writer.write(c);
            }
            System.out.println("Файл успішно дешифровано: " + outputFile);
        } catch (IOException e) {
            System.out.println("Помилка при дешифруванні: " + e.getMessage());
        }
    }

    private static void createSourceFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("Це таємне повідомлення!\n");
            writer.write("Воно буде зашифроване за допомогою FilterWriter.");
        } catch (IOException e) {
            System.out.println("Помилка створення файлу: " + e.getMessage());
        }
    }
}
