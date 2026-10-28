package lab4;

import lab4.exception.PassengerNotFoundException;
import lab4.exception.VehicleFullException;
import lab4.model.*;

public class Main {
    public static void main(String[] args) {
        FireEngine fireEngine = new FireEngine(2);
        PoliceCar policeCar = new PoliceCar(1);
        Taxi taxi = new Taxi(3);

        Firefighter firefighter = new Firefighter("Пожежник Василь");
        Firefighter firefighter2 = new Firefighter("Пожежник Андрій");
        Policeman policeman1 = new Policeman("Офіцер Дмитро");
        Policeman policeman2 = new Policeman("Офіцер Олексій");
        Policeman policeman3 = new Policeman("Офіцер Сергій");
        OrdinaryPassenger passenger = new OrdinaryPassenger("Студент Іван");

        fireEngine.boardPassenger(firefighter);
        policeCar.boardPassenger(policeman1);
        taxi.boardPassenger(passenger);
        taxi.boardPassenger(policeman3);
        System.out.println("Посадка виконана.");

        // fireEngine.boardPassenger(passenger);  // НЕ СКОМПІЛЮЄТЬСЯ

        Road road = new Road();
        road.addCarToRoad(fireEngine);
        road.addCarToRoad(taxi);
        road.addCarToRoad(policeCar);
        System.out.println("Людей на дорозі: " + road.getCountOfHumans());

        try {
            policeCar.boardPassenger(policeman2);
        } catch (VehicleFullException e) {
            System.out.println("Спіймано: " + e.getMessage());
        }

        try {
            fireEngine.alightPassenger(firefighter2);
        } catch (PassengerNotFoundException e) {
            System.out.println("Спіймано: " + e.getMessage());
        }
    }
}