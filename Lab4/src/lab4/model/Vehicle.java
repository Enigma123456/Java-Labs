package lab4.model;

import lab4.exception.PassengerNotFoundException;
import lab4.exception.VehicleFullException;

import java.util.ArrayList;
import java.util.List;

public abstract class Vehicle<T extends Human> {
    private final int maxSeats;
    private final List<T> passengers = new ArrayList<>();

    public Vehicle(int maxSeats) {
        if (maxSeats <= 0) {
            throw new IllegalArgumentException("Кількість місць має бути більшою за 0");
        }
        this.maxSeats = maxSeats;
    }

    public int getMaxSeats() {
        return maxSeats;
    }

    public int getOccupiedSeats() {
        return passengers.size();
    }

    public void boardPassenger(T passenger) {
        if (passenger == null) {
            throw new IllegalArgumentException("Пасажир не може бути null");
        }
        if (passengers.size() >= maxSeats) {
            throw new VehicleFullException("Усі " + maxSeats + " місць зайнято");
        }
        passengers.add(passenger);
    }

    public void alightPassenger(T passenger) {
        if (!passengers.remove(passenger)) {
            throw new PassengerNotFoundException("Цього пасажира немає в транспорті");
        }
    }
}