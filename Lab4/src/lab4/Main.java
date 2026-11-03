package lab4;

import lab4.exception.PassengerNotFoundException;
import lab4.exception.VehicleFullException;
import lab4.model.*;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Road road = new Road();

        Taxi taxi = new Taxi(3);
        FireEngine fireEngine = new FireEngine(2);
        PoliceCar policeCar = new PoliceCar(1);

        road.addCarToRoad(taxi);
        road.addCarToRoad(fireEngine);
        road.addCarToRoad(policeCar);

        // Список усіх доступних людей
        Human[] people = {
                new OrdinaryPassenger("Студент Назар"),
                new OrdinaryPassenger("Студент Іван"),
                new OrdinaryPassenger("Пенсіонерка Марія"),
                new OrdinaryPassenger("Вчитель Петро"),
                new Firefighter("Пожежник Василь"),
                new Firefighter("Пожежник Андрій"),
                new Firefighter("Пожежник Степан"),
                new Policeman("Офіцер Дмитро"),
                new Policeman("Офіцер Олексій"),
                new Policeman("Офіцер Сергій")
        };

        Set<Human> boardedPeople = new HashSet<>();

        while (true) {
            System.out.println("\n=== МЕНЮ ===");
            System.out.println("1. Показати заповненість транспорту та кількості людей на дорогі");
            System.out.println("2. Посадити пасажира");
            System.out.println("3. Висадити пасажира");
            System.out.println("0. Вихід");
            System.out.print("Обери дію: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("\n--- СТАН ---");
                    System.out.println("Таксі: " + taxi.getOccupiedSeats() + "/" + taxi.getMaxSeats() + " місць");
                    System.out.println("Пожежна машина: " + fireEngine.getOccupiedSeats() + "/" + fireEngine.getMaxSeats() + " місць");
                    System.out.println("Поліцейська машина: " + policeCar.getOccupiedSeats() + "/" + policeCar.getMaxSeats() + " місць");
                    System.out.println("Загалом людей на дорозі: " + road.getCountOfHumans());
                    break;

                case "2":
                case "3":
                    boolean isBoarding = choice.equals("2");
                    System.out.println(isBoarding ? "\n--- КОГО САДЖАЄМО? ---" : "\n--- КОГО ВИСАДЖУЄМО? ---");

                    for (int i = 0; i < people.length; i++) {
                        String status = boardedPeople.contains(people[i]) ? "[В дорозі]" : "[Чекає]";
                        System.out.println((i + 1) + ". " + people[i].getName() + " " + status);
                    }
                    System.out.print("Обери номер людини: ");

                    int personIndex;
                    try {
                        personIndex = Integer.parseInt(scanner.nextLine()) - 1;
                        if (personIndex < 0 || personIndex >= people.length) throw new Exception();
                    } catch (Exception e) {
                        System.out.println("Помилка: невірний номер людини.");
                        continue;
                    }

                    Human selectedPerson = people[personIndex];

                    // Перевірки логіки перед посадкою/висадкою
                    if (isBoarding && boardedPeople.contains(selectedPerson)) {
                        System.out.println(">>> ПОМИЛКА ЛОГІКИ: Ця людина вже знаходиться у транспорті! Спочатку висадіть її.");
                        continue;
                    }
                    if (!isBoarding && !boardedPeople.contains(selectedPerson)) {
                        System.out.println(">>> ПОМИЛКА ЛОГІКИ: Ця людина зараз не знаходиться в жодному транспорті!");
                        continue;
                    }

                    System.out.println(isBoarding ? "\n--- В ЯКИЙ ТРАНСПОРТ? ---" : "\n--- З ЯКОГО ТРАНСПОРТУ? ---");
                    System.out.println("1. Таксі");
                    System.out.println("2. Пожежна машина");
                    System.out.println("3. Поліцейська машина");
                    System.out.print("Обери транспорт: ");
                    String carChoice = scanner.nextLine();

                    try {
                        if (carChoice.equals("1")) {
                            if (isBoarding) taxi.boardPassenger(selectedPerson);
                            else taxi.alightPassenger(selectedPerson);

                        } else if (carChoice.equals("2")) {
                            if (!(selectedPerson instanceof Firefighter)) {
                                System.out.println("ПОМИЛКА ТИПУ: Пожежна машина працює ТІЛЬКИ з пожежниками!");
                                continue;
                            }
                            if (isBoarding) fireEngine.boardPassenger((Firefighter) selectedPerson);
                            else fireEngine.alightPassenger((Firefighter) selectedPerson);

                        } else if (carChoice.equals("3")) {
                            if (!(selectedPerson instanceof Policeman)) {
                                System.out.println("ПОМИЛКА ТИПУ: Поліцейська машина працює ТІЛЬКИ з поліцейськими!");
                                continue;
                            }
                            if (isBoarding) policeCar.boardPassenger((Policeman) selectedPerson);
                            else policeCar.alightPassenger((Policeman) selectedPerson);

                        } else {
                            System.out.println("Помилка: невідомий транспорт.");
                            continue;
                        }

                        if (isBoarding) {
                            boardedPeople.add(selectedPerson);
                        } else {
                            boardedPeople.remove(selectedPerson);
                        }
                        System.out.println(">>> Операція виконана успішно!");

                    } catch (VehicleFullException | PassengerNotFoundException ex) {
                        System.out.println(">>> ВИКЛЮЧНА СИТУАЦІЯ: " + ex.getMessage());
                    }
                    break;

                case "0":
                    System.out.println("Завершення роботи.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Невірний ввід.");
            }
        }
    }
}