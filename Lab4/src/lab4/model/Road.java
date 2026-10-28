package lab4.model;

import java.util.ArrayList;
import java.util.List;

public class Road {
    public List<Vehicle<? extends Human>> carsInRoad = new ArrayList<>();

    public void addCarToRoad(Vehicle<? extends Human> car) {
        if (car == null) {
            throw new IllegalArgumentException("Транспорт не може бути null");
        }
        carsInRoad.add(car);
    }

    public int getCountOfHumans() {
        int count = 0;
        for (Vehicle<? extends Human> vehicle : carsInRoad) {
            count += vehicle.getOccupiedSeats();
        }
        return count;
    }
}
